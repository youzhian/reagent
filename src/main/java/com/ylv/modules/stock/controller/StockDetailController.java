package com.ylv.modules.stock.controller;

import com.ylv.modules.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("stock")
public class StockDetailController extends BaseController {

    @RequestMapping("list")
    public ModelAndView query(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("stock/list");
        return mv;
    }
}
