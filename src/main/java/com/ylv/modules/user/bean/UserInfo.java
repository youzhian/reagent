package com.ylv.modules.user.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String password;
}
