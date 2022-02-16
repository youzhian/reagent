package com.ylv.modules.reagent.controller;

import com.ylv.modules.BaseController;
import com.ylv.modules.reagent.bean.ReagentInfo;
import com.ylv.modules.reagent.service.ReagentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reagent")
public class ReagentController extends BaseController {

    @Autowired
    private ReagentInfoService reagentInfoService;

    @GetMapping("/list")
    public ModelAndView toList(){
        ModelAndView mv = new ModelAndView("reagent/list");
        return mv;
    }

    @PostMapping("query")
    public Object query(){
        return null;
    }

    @PostMapping("save")
    public Object save(@RequestBody ReagentInfo reagentInfo, HttpServletRequest request){

        return success("保存成功");
    }
}
