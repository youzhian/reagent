package com.ylv.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylv.modules.BaseController;
import com.ylv.modules.user.bean.UserInfo;
import com.ylv.modules.user.mapper.UserInfoMapper;
import com.ylv.modules.user.service.UserInfoService;
import com.ylv.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserInfoController extends BaseController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/list")
    public ModelAndView toList(){
        return new ModelAndView("user/list");
    }

    @PostMapping("/query")
    public Object findAll(@RequestBody UserInfo userInfo){
        QueryWrapper<UserInfo> query = new QueryWrapper<>();
        if(StringUtils.isNotBlank(userInfo.getName())) {
            query.lambda().like(UserInfo::getName, userInfo.getName());
        }
        if(StringUtils.isNotBlank(userInfo.getDelFlg())){
            query.lambda().eq(UserInfo::getDelFlg,userInfo.getDelFlg());
        }
        query.lambda().orderByDesc(UserInfo::getDelFlg).orderByAsc(UserInfo::getCreateTime);
        List<UserInfo> list = userInfoService.list(query);
        return success("查询成功",list);

    }

    @PostMapping("save")
    public Object save(@RequestBody UserInfo userInfo){
        if(StringUtils.isBlank(userInfo.getDelFlg())) {
            userInfo.setDelFlg(Constants.DEL_FLG_NORMAL);
        }
        if(userInfo.getId() == null){
            userInfo.setCreateTime(new Date());
        }
        userInfoService.saveOrUpdate(userInfo);
        return success("保存成功");
    }
}
