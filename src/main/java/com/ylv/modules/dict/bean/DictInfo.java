package com.ylv.modules.dict.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 字典明细对象
 * @author youzhian
 */
@Data
@Table(name="DICT_DETAIL")
public class DictInfo {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String dictType;

    private String dictCode;

    private String dictValue;

    private Integer orderNum;

    private String dictRemark;

    private Date createTime;
}
