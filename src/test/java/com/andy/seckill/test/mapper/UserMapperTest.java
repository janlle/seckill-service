package com.andy.seckill.test.mapper;

import com.andy.seckill.SecKillApplication;
import com.andy.seckill.domain.User;
import com.andy.seckill.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecKillApplication.class})
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private Random random;

    @Test
    public void saveUserTest() {
        Date date = new Date();
        for (int i = 0; i < 10000; i++) {
            userMapper.save(new User(String.valueOf(10016212 + i), "james-" + i, UUID.randomUUID().toString().replace("-", "").substring(16), "广东省 广州市 天河区", random.nextInt(30) + 18, "1596236" + random.nextInt(900) + 100, date, false));
        }
    }

}
