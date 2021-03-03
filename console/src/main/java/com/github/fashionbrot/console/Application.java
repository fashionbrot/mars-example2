package com.github.fashionbrot.console;

import com.github.fashionbrot.tool.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Date;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.github.fashionbrot"})
@MapperScan("com.github.fashionbrot.core.mapper")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        log.info("Start to finish:{}", DateUtil.formatDate(new Date()));
    }

}
