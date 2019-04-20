package com.leone.seckill.access;

import com.leone.seckill.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * @author leone
 **/
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        if (handler instanceof HandlerMethod) {
//            MiaoshaUser user = getUser(request, response);
//            UserContext.setUser(user);
//            HandlerMethod hm = (HandlerMethod) handler;
//            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
//            if (accessLimit == null) {
//                return true;
//            }
//            int seconds = accessLimit.seconds();
//            int maxCount = accessLimit.maxCount();
//            boolean needLogin = accessLimit.needLogin();
//            String key = request.getRequestURI();
//            if (needLogin) {
//                if (user == null) {
//                    render(response, CodeMsg.SESSION_ERROR);
//                    return false;
//                }
//                key += "_" + user.getId();
//            } else {
//                //do nothing
//            }
//            AccessKey ak = AccessKey.withExpire(seconds);
//            Integer count = redisService.get(ak, key, Integer.class);
//            if (count == null) {
//                redisService.set(ak, key, 1);
//            } else if (count < maxCount) {
//                redisService.incr(ak, key);
//            } else {
//                render(response, CodeMsg.ACCESS_LIMIT_REACHED);
//                return false;
//            }
//        }
        return true;
    }

//    private void render(HttpServletResponse response, CodeMsg cm) throws Exception {
//        response.setContentType("application/json;charset=UTF-8");
//        OutputStream out = response.getOutputStream();
//        String str = JSON.toJSONString(Result.error(cm));
//        out.write(str.getBytes("UTF-8"));
//        out.flush();
//        out.close();
//    }

//    private MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response) {
//        String paramToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
//        String cookieToken = getCookieValue(request, MiaoshaUserService.COOKI_NAME_TOKEN);
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//            return null;
//        }
//        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
//        return userService.getByToken(response, token);
//    }


//    private String getCookieValue(HttpServletRequest request, String cookiName) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null || cookies.length <= 0) {
//            return null;
//        }
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals(cookiName)) {
//                return cookie.getValue();
//            }
//        }
//        return null;
//    }

}
