package com.xl.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xl
 */


@Slf4j
@SpringBootApplication
//@EnableAspectJAutoProxy
public class RickApplication {

    public static void main(String[] args) {
        SpringApplication.run(RickApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  sa-token测试项目启动成功   ლ(´ڡ`ლ)ﾞ");
        log.info("==================当前项目目录 {} ================", System.getProperty("user.dir"));
    }

}
