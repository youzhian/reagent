package com.ylv.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylv.modules.user.bean.UserInfo;
import com.ylv.modules.user.mapper.UserInfoMapper;
import com.ylv.modules.user.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
}
