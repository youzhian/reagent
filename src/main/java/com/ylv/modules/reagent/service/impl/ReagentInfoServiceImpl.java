package com.ylv.modules.reagent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylv.modules.reagent.bean.ReagentInfo;
import com.ylv.modules.reagent.mapper.ReagentInfoMapper;
import com.ylv.modules.reagent.service.ReagentInfoService;
import com.ylv.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ReagentInfoServiceImpl extends ServiceImpl<ReagentInfoMapper,ReagentInfo> implements ReagentInfoService {

    @Resource
    private ReagentInfoMapper reagentInfoMapper;

    @Override
    public boolean checkExists(Integer id, String reagentName) {
        int count = 0;
        QueryWrapper<ReagentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReagentInfo::getReagentName,reagentName);
        if(id != null){
            queryWrapper.lambda().ne(ReagentInfo::getId,id);
        }
        count = reagentInfoMapper.selectCount(queryWrapper);
        return count > 0;
    }

    @Override
    public Integer addOrUpdate(ReagentInfo reagentInfo,String userName) {
        if(reagentInfo != null) {
            boolean checkResult = checkExists(reagentInfo.getId(), reagentInfo.getReagentName());
            if (!checkResult) {
                Date curr = new Date();
                //若ID为空，则认为是新增
                if (reagentInfo.getId() == null) {
                    reagentInfo.setDelFlg(Constants.DEL_FLG_NORMAL);
                    reagentInfo.setCreateBy(userName);
                    reagentInfo.setCreateOn(curr);
                }

                reagentInfo.setModifyOn(curr);
                reagentInfo.setModifyBy(userName);
                saveOrUpdate(reagentInfo);

                return reagentInfo.getId();
            }
        }
        return null;
    }

    @Override
    public IPage<ReagentInfo> list(ReagentInfo reagentInfo,Integer pageSize,Integer pageNum) {

        QueryWrapper<ReagentInfo> query = new QueryWrapper();

        if(StringUtils.isNotBlank(reagentInfo.getReagentName())) {
            query.lambda().like(ReagentInfo::getReagentName,reagentInfo.getReagentName());
        }
        if(StringUtils.isNotBlank(reagentInfo.getDelFlg())) {
            query.lambda().eq(ReagentInfo::getDelFlg,reagentInfo.getDelFlg());
        }
        if(StringUtils.isNotBlank(reagentInfo.getReagentType())){
            query.lambda().eq(ReagentInfo::getReagentType,reagentInfo.getReagentType());
        }
        if(StringUtils.isNotBlank(reagentInfo.getCreateBy())) {
            query.lambda().eq(ReagentInfo::getCreateBy,reagentInfo.getCreateBy());
        }
        if(StringUtils.isNotBlank(reagentInfo.getModifyBy())) {
            query.lambda().eq(ReagentInfo::getModifyBy,reagentInfo.getModifyBy());
        }

        query.lambda().orderByDesc(ReagentInfo::getDelFlg).orderByAsc(ReagentInfo::getReagentType)
                .orderByAsc(ReagentInfo::getOrderNum).orderByDesc(ReagentInfo::getModifyOn);
        IPage<ReagentInfo> page = new Page<>(pageNum,pageSize);
        IPage<ReagentInfo> result = reagentInfoMapper.selectPage(page,query);
        return result;
    }

    @Override
    public boolean logicalDeleteBy(Integer id) {
        ReagentInfo s = new ReagentInfo();
        s.setId(id);
        s.setDelFlg(Constants.DEL_FLG_DELETE);
        int r = reagentInfoMapper.updateById(s);
        return r>0;
    }
}
