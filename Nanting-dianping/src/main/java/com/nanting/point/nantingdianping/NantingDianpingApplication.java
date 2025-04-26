package com.nanting.point.nantingdianping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.nanting.point.nantingdianping.mapper")
@SpringBootApplication
public class NantingDianpingApplication {

    public static void main(String[] args) {
        SpringApplication.run(NantingDianpingApplication.class, args);
    }

}
