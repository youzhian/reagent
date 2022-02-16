package com.ylv.modules;

import com.alibaba.fastjson.JSONObject;
import com.ylv.modules.menu.service.MenuService;
import com.ylv.modules.menu.vo.TreeMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController extends BaseController{

    @Autowired
    private MenuService menuService;

    @GetMapping(value = {"/","/index"})
    public ModelAndView index(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");

        List<TreeMenu> menus = menuService.getUserMenus(1L);
        mv.addObject("menus", JSONObject.toJSON(menus));

        return mv;
    }

    @GetMapping("testElement")
    public String testElement(){
        return "testElementUI";
    }
}
