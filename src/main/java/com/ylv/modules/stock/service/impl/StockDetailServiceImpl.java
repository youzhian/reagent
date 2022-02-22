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

        List<ReagentInfo> reagentInfos = reagentInfoService.list(new QueryWrapper<ReagentInfo>().lambda().eq(ReagentInfo::getDelFlg, Constants.DEL_FLG_NORMAL));

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
                details.stream().collect(Collectors.groupingBy(StockDetail::getReagentId));
            }

            reagentInfos.stream().forEach(s->{
                String key = String.valueOf(s.getId());
                ReagentStock rs = new ReagentStock();
                rs.setReagentId(s.getId());
                rs.setReagentName(s.getReagentName());
                rs.setStock(0);

                if(detailMap.containsKey(key)){

                }

                result.add(rs);
            });
        }

        return result;
    }
}
