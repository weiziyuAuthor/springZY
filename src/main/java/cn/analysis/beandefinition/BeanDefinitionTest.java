package cn.analysis.beandefinition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/9/19 11:25
 */
public class BeanDefinitionTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.analysis.beandefinition");
        BeanDefinition beanDefinition = applicationContext.getBeanDefinition("person");
        System.out.println(beanDefinition);

        System.out.println("---------------------------");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersonConfiguration.class);
       BeanDefinition beanDefinition1 = context.getBeanDefinition("person123");
        System.out.println(beanDefinition1);
    }
}
