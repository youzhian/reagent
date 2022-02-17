package com.ylv.modules.reagent.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "REAGENT_INFO")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ReagentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 试剂名称
     */
    private String reagentName;
    /**
     * 删除标识，0为已删除，1为未删除
     */
    private String delFlg;
    /**
     * 描述
     */
    private String remark;
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
