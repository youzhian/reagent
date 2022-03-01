package com.ylv.modules.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylv.modules.dict.bean.DictInfo;
import com.ylv.modules.dict.mapper.DictDetailMapper;
import com.ylv.modules.dict.service.DictInfoService;
import org.springframework.stereotype.Service;

@Service
public class DictInfoServiceImpl extends ServiceImpl<DictDetailMapper, DictInfo> implements DictInfoService {
}
