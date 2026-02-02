package cn.analysis;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/9/18 17:48
 *
 * source test module, need analysis
 *
 * 重要接口的入口， 可参照优秀代码
 */
public class Enter {


    private BeanFactory beanFactory;

    private AbstractBeanFactory abstractBeanFactory;

    private ApplicationContext applicationContext;
    private AbstractApplicationContext abstractApplicationContext;


    private Environment environment;
    private EnvironmentAware environmentAware;
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;

    private BeanDefinition beanDefinition;
    private BeanDefinitionRegistry beanDefinitionRegistry;
    private DefaultListableBeanFactory defaultListableBeanFactory;


    private BeanPostProcessor beanPostProcessor;
    private BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor;
    private DestructionAwareBeanPostProcessor destructionAwareBeanPostProcessor;
    private BeanFactoryPostProcessor beanFactoryPostProcessor;


    private SpringApplication springApplication;

}
