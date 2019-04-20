package com.leone.seckill.service;

import com.leone.seckill.common.RedisPrefix;
import com.leone.seckill.common.Result;
import com.leone.seckill.domain.User;
import com.leone.seckill.exception.ExceptionMessage;
import com.leone.seckill.mapper.UserMapper;
import com.leone.seckill.utils.ImgCodeUtil;
import com.leone.seckill.vo.LoginVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderService orderService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录
     *
     * @param loginVO
     * @return
     */
    public Result login(LoginVO loginVO) {
        if (!check(loginVO.getAccount(), loginVO.getValidateCode())) {
            return Result.error(ExceptionMessage.USER_NOT_EXIST);
        }
        return Result.success(userMapper.findUserByAccountAndPassword(loginVO.getAccount(), loginVO.getPassword()));
    }

    /**
     * 校验验证码
     *
     * @param account
     * @param validateCode
     * @return
     */
    private boolean check(String account, String validateCode) {
        String catchCode = (String) redisTemplate.opsForValue().get(RedisPrefix.VALIDATE_CODE_PREFIX + account);
        if (ObjectUtils.isEmpty(catchCode)) {
            return false;
        }
        return validateCode.equals(catchCode);
    }


    /**
     * @param userId
     * @return
     */
    public User findOne(Long userId) {
        User user = userMapper.findOne(userId);
        Assert.notNull(user, "用户不存在");
        return user;
    }


    /**
     * 生成二维码并存入缓存
     *
     * @param code
     * @param response
     */
    public void verifyCode(String code, HttpServletResponse response) {
        String signal = String.valueOf(code.hashCode());
        String validateCode = ImgCodeUtil.createValidateCode(response, 80, 20);
        redisTemplate.opsForValue().set(RedisPrefix.VALIDATE_CODE_PREFIX + signal, validateCode);
    }


}
