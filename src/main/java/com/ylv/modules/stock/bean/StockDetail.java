package com.ylv.modules.stock.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "STOCK_DETAIL")
public class StockDetail {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 试剂ID
     */
    private Integer reagentId;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
