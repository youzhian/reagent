package com.ylv.modules.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylv.modules.reagent.bean.ReagentInfo;
import com.ylv.modules.reagent.service.ReagentInfoService;
import com.ylv.modules.stock.bean.StockDetail;
import com.ylv.modules.stock.mapper.StockDetailMapper;
import com.ylv.modules.stock.service.StockDetailService;
import com.ylv.modules.stock.vo.ReagentStock;
import com.ylv.util.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockDetailServiceImpl extends ServiceImpl<StockDetailMapper, StockDetail> implements StockDetailService {

    @Resource
    private StockDetailMapper stockDetailMapper;

    @Resource
    private ReagentInfoService reagentInfoService;

    @Override
    public List<ReagentStock> queryReagentStock() {

        List<ReagentInfo> reagentInfos = reagentInfoService.list(new QueryWrapper<ReagentInfo>().lambda()
                .eq(ReagentInfo::getDelFlg, Constants.DEL_FLG_NORMAL)
                .orderByAsc(ReagentInfo::getOrderNum).orderByDesc(ReagentInfo::getModifyOn));

        List<Integer> reagentIds = null;

        List<ReagentStock> result = new ArrayList<>();

        if(reagentInfos != null && !reagentInfos.isEmpty()){
            reagentIds = reagentInfos.stream().map(ReagentInfo::getId).collect(Collectors.toList());
            /**
             * 查询库存
             */
            List<StockDetail> details = list(new QueryWrapper<StockDetail>().lambda().in(StockDetail::getReagentId,reagentIds));

            Map<String,List<StockDetail>> detailMap = new HashMap<>();
            //出入库信息根据试剂进行分组
            if(details != null && !details.isEmpty()){
                detailMap = details.stream().collect(Collectors.groupingBy(s->String.valueOf(s.getReagentId())));
            }

            Map<String, List<StockDetail>> finalDetailMap = detailMap;

            reagentInfos.stream().forEach(s->{
                String key = String.valueOf(s.getId());
                ReagentStock rs = new ReagentStock();
                rs.setReagentId(s.getId());
                rs.setReagentName(s.getReagentName());
                rs.setStock(0);

                if(finalDetailMap.containsKey(key)){
                    List<StockDetail> list = finalDetailMap.get(key);
                    if(list != null && !list.isEmpty()){
                        //算出剩余库存
                        int sum = list.stream().mapToInt(d->{
                            //若是出库
                            if(Constants.STOCK_TYPE_POP.equals(d.getStockType())){
                                return -d.getNum();
                            }else {
                                return d.getNum();
                            }
                        }).sum();
                        rs.setStock(sum);
                    }
                }

                result.add(rs);
            });
        }

        return result;
    }

    @Override
    public Long getReagentStockById(Integer reagentId) {
        if(reagentId != null) {
            QueryWrapper<StockDetail> query = new QueryWrapper<>();
            query.lambda().eq(StockDetail::getReagentId,reagentId);
            List<StockDetail> list = list(query);
            if(list != null && !list.isEmpty()){
                //算出剩余库存
                long sum = list.stream().mapToInt(d->{
                    //若是出库
                    if(Constants.STOCK_TYPE_POP.equals(d.getStockType())){
                        return -d.getNum();
                    }else {
                        return d.getNum();
                    }
                }).sum();
                return sum;
            }
        }
        return null;
    }
}
