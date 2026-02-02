package cn.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.config.annotation.EnableWs;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/4/18 16:09
 * analysis springboot start
 *
 */
@SpringBootApplication
@ComponentScan(basePackages= "cn.hello")
@EnableAutoConfiguration
//@EnableWs
//@EnableAutoConfiguration(exclude = {cn.hello.ctr.TestController.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
