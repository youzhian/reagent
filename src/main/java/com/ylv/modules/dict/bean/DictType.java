package com.ylv.modules.dict.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 字典大类bean
 */
@Data
@Table(name="DICT_TYPE")
public class DictType {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String dictType;

    private String label;

    private Integer orderNum;

    private String dictDesc;

    private Date createTime;

    private Date updateTime;
}
