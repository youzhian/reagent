package com.ylv.modules.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylv.modules.dict.bean.DictType;
import com.ylv.modules.dict.mapper.DictTypeMapper;
import com.ylv.modules.dict.service.DictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {

    @Override
    public boolean hasType(String type) {
        if(StringUtils.isNotBlank(type)) {
            int count = count(new QueryWrapper<DictType>().lambda().eq(DictType::getDictType, type));
            return count>0;
        }
        return false;
    }
}
