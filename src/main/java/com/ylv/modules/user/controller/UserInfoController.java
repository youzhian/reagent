package com.ylv.modules.user.controller;

import com.ylv.modules.user.bean.UserInfo;
import com.ylv.modules.user.mapper.UserInfoMapper;
import com.ylv.modules.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/list")
    public List<UserInfo> findAll(){
        return userInfoService.list();
    }

    @PostMapping("save")
    public Boolean save(@RequestBody UserInfo userInfo){
        userInfoService.saveOrUpdate(userInfo);
        return Boolean.TRUE;
    }

    public static void main(String []args){

        String url = "http://api.otgov.com/uaa/oauth/token";
        //url = "http://www.baidu.com";
        String code = "";

        RestTemplate template = new RestTemplate();

        HashMap<String,String> param = new HashMap<>();
        param.put("client_id","qdcsdn_index");
        param.put("client_secret","secuirty");
        param.put("grant_type","authorization_code");
        param.put("code",code);
        param.put("scope","admin user third");

        ResponseEntity<String> result = template.postForEntity(url,param,String.class);

        System.out.println(result);
    }
}
