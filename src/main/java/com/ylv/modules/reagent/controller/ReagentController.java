package com.ylv.modules.reagent.controller;

import com.ylv.modules.BaseController;
import com.ylv.modules.reagent.bean.ReagentInfo;
import com.ylv.modules.reagent.service.ReagentInfoService;
import com.ylv.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/reagent")
public class ReagentController extends BaseController {

    @Autowired
    private ReagentInfoService reagentInfoService;

    @GetMapping("/list")
    public ModelAndView toList(){
        ModelAndView mv = new ModelAndView("reagent/list");
        return mv;
    }

    @PostMapping("query")
    public Object query(@RequestBody ReagentInfo reagentInfo){
        List<ReagentInfo> list = reagentInfoService.list(reagentInfo);
        return success("查询成功",list);
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

}
