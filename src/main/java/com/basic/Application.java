package com.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.MultipartConfigElement;

/**
 * Created by dell-pc on 2016/4/19.
 * nohup java -jar popularity_awaredifferentiatedschedulingWeb-1.0-SNAPSHOT.jar > /root/TJ/logs/popularity_awaredifferentiatedschedulingWeb.log &
 */

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("applicationContext.xml")
public class Application {

//   设置文件传输的最大文件大小
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("5280KB");
        factory.setMaxRequestSize("5280KB");
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
