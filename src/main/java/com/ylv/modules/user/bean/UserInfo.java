package com.ylv.modules.user.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="USER_INFO")
public class UserInfo {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String name;

    private String password;
}
