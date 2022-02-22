package com.ylv.modules.stock.vo;

import lombok.Data;

@Data
public class ReagentStock {
    /**
     * 试剂ID
     */
    private Integer reagentId;
    /**
     * 试剂名称
     */
    private String reagentName;
    /**
     * 剩余库存
     */
    private Integer stock;

}
