package com.ylv.modules.reagent.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "REAGENT_INFO")
public class ReagentInfo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 试剂名称
     */
    private String reagentName;
    /**
     * 删除标识，0为已删除，1为未删除
     */
    private String delFlg;

    /**
     * 排序字段
     */
    private Integer orderNum;

    /**
     * 描述
     */
    private String remark;
    /**
     * 试剂类型
     */
    private String reagentType;
    /**
     * 数据创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createOn;
    /**
     * 数据创建人
     */
    private String createBy;
    /**
     * 数据修改时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifyOn;
    /**
     * 数据修改人
     */
    private String modifyBy;
}
