package com.ylv.modules.stock.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylv.modules.BaseController;
import com.ylv.modules.stock.bean.StockDetail;
import com.ylv.modules.stock.service.StockDetailService;
import com.ylv.modules.stock.vo.ReagentStock;
import com.ylv.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        QueryWrapper<StockDetail> query = new QueryWrapper<>();

        if(reagentId != null) {
            query.lambda().eq(StockDetail::getReagentId, reagentId);
        }

        List<StockDetail> list = stockDetailService.list(query);

        return  success("查询成功",list);
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

    /**
     * 保存信息
     * @param stockDetail
     * @return
     */
    @PostMapping("save")
    public Object save(@RequestBody StockDetail stockDetail){
        //若是出库，需判断剩余库存
        if(Constants.STOCK_TYPE_POP.equals(stockDetail.getStockType())) {
            Long stock = stockDetailService.getReagentStockById(stockDetail.getReagentId());
            if (stock != null && stock < stockDetail.getNum()) {
                return error("出库失败！本试剂剩余库存为："+stockDetail.getNum()+"，小于出库数量："+stock);
            }
        }
        stockDetailService.saveOrUpdate(stockDetail);

        return success("保存成功");
    }
}
