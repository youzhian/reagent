package com.ylv.modules.dict.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylv.modules.BaseController;
import com.ylv.modules.dict.bean.DictInfo;
import com.ylv.modules.dict.service.DictInfoService;
import com.ylv.modules.dict.service.DictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dictInfo")
public class DictInfoController extends BaseController {

    @Autowired
    private DictInfoService dictInfoService;

    @Autowired
    private DictTypeService dictTypeService;

    @GetMapping("dictByType")
    public Object dictInfoByType(String type){
        if(StringUtils.isNotBlank(type)){
            List<DictInfo> list = dictInfoService.list(new QueryWrapper<DictInfo>().lambda().eq(DictInfo::getDictType,type));
            return success("查询成功",list);
        }
        return error("字典类型不能为空");
    }

    @PostMapping("save")
    public Object save(@RequestBody DictInfo dictInfo){
        if(dictInfo != null){
            if(StringUtils.isBlank(dictInfo.getDictType())){
                return error("字典类型不能为空");
            }

            boolean has = dictTypeService.hasType(dictInfo.getDictType());

            QueryWrapper<DictInfo> query = new QueryWrapper<DictInfo>();

            query.lambda().eq(DictInfo::getDictType,dictInfo.getDictType())
                .eq(DictInfo::getDictCode,dictInfo.getDictCode());

            if(dictInfo.getId() != null){
                query.lambda().ne(DictInfo::getId,dictInfo.getId());
            }

            int count = dictInfoService.count(query);
            if(count>0){
                return error("当前字典细项已存在");
            }
            if(!has){
                return error("对应的字典类型不存在");
            }
            dictInfoService.save(dictInfo);
            return success("保存成功",dictInfo.getId());
        }
        return error("数据为空");
    }
}
