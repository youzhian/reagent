package com.ylv.modules.stock.controller;

import com.ylv.modules.BaseController;
import com.ylv.modules.stock.service.StockDetailService;
import com.ylv.modules.stock.vo.ReagentStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockDetailController extends BaseController {

    @Autowired
    private StockDetailService stockDetailService;

    @RequestMapping("list")
    public ModelAndView toList(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("stock/list");
        return mv;
    }

    @GetMapping("query/{reagentId}")
    public Object query(@PathVariable Integer reagentId){
        return  success();
    }

    /**
     * 查询每个试剂的剩余库存
     * @return
     */
    @GetMapping("getStock")
    public Object getReagentAndStock(){
        List<ReagentStock> stocks = stockDetailService.queryReagentStock();
        return success("查询成功",stocks);
    }
}
