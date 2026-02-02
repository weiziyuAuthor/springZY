package cn.analysis.beandefinition;

import org.springframework.stereotype.Component;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/9/19 11:25
 */
@Component
public class Person {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
