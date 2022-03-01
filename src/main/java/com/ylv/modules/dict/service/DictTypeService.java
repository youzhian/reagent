package com.ylv.modules.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylv.modules.dict.bean.DictType;

/**
 * 字典大类service
 */
public interface DictTypeService extends IService<DictType> {

    public boolean hasType(String type);
}
