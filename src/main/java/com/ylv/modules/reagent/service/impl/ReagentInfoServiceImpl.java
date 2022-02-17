package com.ylv.modules.reagent.service.impl;

import com.ylv.modules.reagent.bean.ReagentInfo;
import com.ylv.modules.reagent.repository.ReagentInfoRepository;
import com.ylv.modules.reagent.service.ReagentInfoService;
import com.ylv.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReagentInfoServiceImpl implements ReagentInfoService {

    @Autowired
    private ReagentInfoRepository reagentInfoRepository;

    @Override
    public boolean checkExists(Long id, String reagentName) {
        int count = 0;
        if(id != null){
            count = reagentInfoRepository.countByReagentNameEqualsAndIdIsNot(reagentName,id);
        }else{
            count = reagentInfoRepository.countByReagentName(reagentName);
        }
        return count > 0;
    }

    @Override
    public Long addOrUpdate(ReagentInfo reagentInfo,String userName) {
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

                ReagentInfo result  = reagentInfoRepository.saveAndFlush(reagentInfo);
                if(result != null){
                    return result.getId();
                }
            }
        }
        return null;
    }

    @Override
    public List<ReagentInfo> list(ReagentInfo reagentInfo) {

        ReagentInfo query = new ReagentInfo();

        if(StringUtils.isNotBlank(reagentInfo.getReagentName())) {
            query.setReagentName(reagentInfo.getReagentName());
        }
        if(StringUtils.isNotBlank(reagentInfo.getDelFlg())) {
            query.setDelFlg(reagentInfo.getDelFlg());
        }
        if(StringUtils.isNotBlank(reagentInfo.getCreateBy())) {
            query.setCreateBy(reagentInfo.getCreateBy());
        }
        if(StringUtils.isNotBlank(reagentInfo.getModifyBy())) {
            query.setModifyBy(reagentInfo.getModifyBy());
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("reagentName",ExampleMatcher.GenericPropertyMatchers.contains());

        Example<ReagentInfo> example = Example.of(query,matcher);
        List<ReagentInfo> list = reagentInfoRepository.findAll(example);
        return list;
    }

    @Override
    public boolean logicalDeleteBy(Long id) {
        ReagentInfo s = new ReagentInfo();
        s.setId(id);
        s.setDelFlg(Constants.DEL_FLG_DELETE);
        reagentInfoRepository.save(s);
        return false;
    }
}
