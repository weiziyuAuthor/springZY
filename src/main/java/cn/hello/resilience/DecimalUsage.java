package cn.hello.resilience;

import java.math.BigDecimal;

/**
 * @Author ziyu.wei
 * <p>
 * 2026/2/2 14:37
 */
public class DecimalUsage {
    public static void main(String[] args) {
        compare();
    }

    public static void compare() {
        BigDecimal one = BigDecimal.ZERO;

//        compareTo equal reduce
        System.out.println(one.compareTo(new BigDecimal("10")));
        System.out.println(one.compareTo(new BigDecimal("0")));
        System.out.println(one.compareTo(new BigDecimal("-10")));
    }

}
