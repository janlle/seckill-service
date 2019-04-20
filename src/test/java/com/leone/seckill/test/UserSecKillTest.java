package com.leone.seckill.test;

import com.leone.seckill.utils.HttpUtil;
import com.leone.seckill.vo.SecKillVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.util.Random;

/**
 * <p> 注意：Junit单元测试不支持多线程
 *
 * @author leone
 * @since 2019-04-18
 **/
@Slf4j
public class UserSecKillTest {

    private final static String path = "320d563c67fd46688182ce527fadccf7";

    private final static String url = "http://127.0.0.1:8080/api/" + path + "/kill";

    private static Random random = new Random();

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) throws Exception {
        log.info("url: {}", url);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            final String body = objectMapper.writeValueAsString(new SecKillVO((long) random.nextInt(7) + 1, (long) random.nextInt(9999) + 229));
            new Thread(() -> {
                for (int j = 0; j < random.nextInt(5) + 1; j++) {
                    String result = HttpUtil.sendPost(url, body, null, MediaType.APPLICATION_JSON_VALUE);
                    log.info("result: {}", result);
                }
            }).start();
        }
        long endTime = System.currentTimeMillis();
        log.info("time: {} millisecond", (endTime - startTime));
    }

}
