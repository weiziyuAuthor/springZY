package cn.analysis.beandefinition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/9/19 11:29
 */
@Configuration
public class PersonConfiguration {

    @Bean
    public Person person123() {
        return new Person();
    }
}
