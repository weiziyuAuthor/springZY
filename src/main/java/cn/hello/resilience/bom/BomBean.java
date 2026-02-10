package cn.hello.resilience.bom;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ziyu.wei
 * <p>
 * 2026/2/10 10:15
 */
@Data
@ToString
public class BomBean {
    private String modelId;

    private Integer systemLevel;
    private String productCode;

    private String supProductCode;

    private Integer num;

    private List<BomBean> children = new ArrayList<>();

    public BomBean(String modelId, Integer systemLevel, String productCode, String supProductCode, Integer num) {
        this.modelId = modelId;
        this.systemLevel = systemLevel;
        this.productCode = productCode;
        this.supProductCode = supProductCode;
        this.num = num;
    }

    public BomBean(String productCode, String supProductCode) {
        this.productCode = productCode;
        this.supProductCode = supProductCode;
    }
}
