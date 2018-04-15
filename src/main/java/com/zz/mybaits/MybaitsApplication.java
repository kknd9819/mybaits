package com.zz.mybaits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.zz.mybaits.mapper")
@EnableSwagger2
public class MybaitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybaitsApplication.class, args);
    }
}
