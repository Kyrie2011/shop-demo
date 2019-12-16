package com.hps.shop;

import com.hps.utils.IDWorker;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author heps
 * @date 2019/12/15 23:30
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.hps.shop", "com.hps.utils"})
@MapperScan("com.hps.shop.mapper")
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public IDWorker idWorker() {
        return new IDWorker(1, 2);
    }
}
