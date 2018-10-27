package com.zqs.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Hello world!
 */

@EnableEurekaClient//通过注解@EnableEurekaClient 表明自己是一个eurekaclient.
@SpringBootApplication
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableWebMvc
@MapperScan(basePackages = {"com.zqs.api.dao"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }
}
