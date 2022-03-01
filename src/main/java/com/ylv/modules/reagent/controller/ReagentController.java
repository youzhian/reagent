package com.ylv.modules.reagent.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylv.modules.BaseController;
import com.ylv.modules.dict.bean.DictInfo;
import com.ylv.modules.dict.bean.DictType;
import com.ylv.modules.dict.service.DictInfoService;
import com.ylv.modules.dict.service.DictTypeService;
import com.ylv.modules.reagent.bean.ReagentInfo;
import com.ylv.modules.reagent.service.ReagentInfoService;
import com.ylv.modules.reagent.vo.ReagentInfoVo;
import com.ylv.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reagent")
public class ReagentController extends BaseController {

    @Autowired
    private ReagentInfoService reagentInfoService;

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private DictInfoService dictInfoService;

    @GetMapping("/list")
    public ModelAndView toList(){
        ModelAndView mv = new ModelAndView("reagent/list");
        return mv;
    }

    @PostMapping("query")
    public Object query(@RequestBody ReagentInfoVo reagentInfo){
        Integer pageSize = reagentInfo.getPageSize();
        Integer pageNum = reagentInfo.getPageNum();
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        IPage<ReagentInfo> result = reagentInfoService.list(reagentInfo,pageSize,pageNum);
        return success("查询成功",result);
    }

    @PostMapping("save")
    public Object save(@RequestBody ReagentInfo reagentInfo, HttpServletRequest request){
        if(reagentInfo == null || StringUtils.isBlank(reagentInfo.getReagentName())){
            return error("试剂名称不能为空");
        }
        //检查结果
        boolean checkResult = reagentInfoService.checkExists(reagentInfo.getId(),reagentInfo.getReagentName());
        if(checkResult){
            return error("该试剂名称["+reagentInfo.getReagentName()+"]已存在，请勿重复维护");
        }
        Integer id = reagentInfoService.addOrUpdate(reagentInfo,getCurrentUserName());
        return success("保存成功",id);
    }

    /**
     * 删除
     * @param id 要删除的ID
     * @return
     */
    @DeleteMapping("delete/{id}")
    public Object deleteById(@PathVariable Integer id){
        reagentInfoService.logicalDeleteBy(id);
        return success();
    }

    /**
     * 恢复删除的数据
     * @param id
     * @return
     */
    @GetMapping("recovery/{id}")
    public Object recoveryById(@PathVariable Integer id){
        if(id != null) {
            ReagentInfo info = new ReagentInfo();
            info.setId(id);
            info.setDelFlg(Constants.DEL_FLG_NORMAL);
            reagentInfoService.updateById(info);
        }
        return success("恢复成功");
    }

    @GetMapping("queryReagentType")
    public Object queryReagentType(){
        List<DictInfo> list = dictInfoService.list(new QueryWrapper<DictInfo>().lambda().eq(DictInfo::getDictType,Constants.REAGENT_TYPE));
        return success("查询成功",list);
    }

    @PostMapping("addReagentTye")
    public Object addReagentType(@RequestBody DictInfo dictInfo){
        if(dictInfo == null){
            return error("试剂类型信息不能为空");
        }
        if(StringUtils.isBlank(dictInfo.getDictCode())){
            return error("试剂类型代码不能为空");
        }
        if(StringUtils.isBlank(dictInfo.getDictValue())){
            return error("试剂类型名称不能为空");
        }
        dictInfo.setDictType(Constants.REAGENT_TYPE);
        boolean has = dictTypeService.hasType(Constants.REAGENT_TYPE);
        //生成字典类型
        if(!has){
            DictType type = new DictType();
            type.setDictType(Constants.REAGENT_TYPE);
            type.setLabel("试剂类型");
            type.setOrderNum(1);
            type.setDictDesc("试剂类型");
            type.setCreateTime(new Date());
            type.setUpdateTime(new Date());
            dictTypeService.save(type);
        }

        QueryWrapper<DictInfo> query = new QueryWrapper<>();
        query.lambda().eq(DictInfo::getDictCode,dictInfo.getDictCode()).eq(DictInfo::getDictValue,dictInfo.getDictValue());
        int count = dictInfoService.count(query);
        if(count>0){
            return error("该试剂类型已存在，请勿重复添加");
        }
        dictInfo.setId(null);
        dictInfo.setCreateTime(new Date());
        dictInfo.setDictType(Constants.REAGENT_TYPE);
        dictInfoService.save(dictInfo);
        return success("新增试剂类型成功");
    }
}
