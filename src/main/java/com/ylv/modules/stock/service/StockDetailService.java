package com.ylv.modules.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylv.modules.stock.bean.StockDetail;
import com.ylv.modules.stock.vo.ReagentStock;

import java.util.List;

public interface StockDetailService extends IService<StockDetail> {
    /**
     * 按试剂ID进行库存分组统计，计算剩余库存
     * @return
     */
    public List<ReagentStock> queryReagentStock();
}
