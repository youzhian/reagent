package com.ylv.modules.stock.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class StockDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 试剂ID
     */
    private Long reagentId;
    /**
     * 试剂名称
     */
    private String reagentName;
    /**
     * 出入库类型，1为入库，0为出库，默认0
     */
    private String stockType;
    /**
     * 出入库数量
     */
    private int num;
    /**
     * 操作人名称
     */
    private String operator;
    /**
     * 操作人ID
     */
    private Long operatorId;
    /**
     * 出入库时间
     */
    private Date createTime;
}
