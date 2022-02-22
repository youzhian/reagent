package com.ylv.modules.reagent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylv.modules.reagent.bean.ReagentInfo;

import java.util.List;

public interface ReagentInfoService extends IService<ReagentInfo> {
    /**
     * 检查试剂名称是否已重复，若重复返回true
     * @param id 更新时数据的ID
     * @param reagentName 试剂名称
     * @return 重复则返回true
     */
    public boolean checkExists(Integer id,String reagentName);

    /**
     * 新增或保存试剂信息
     * @param reagentInfo
     * @param userName 操作人的ID
     * @return
     */
    public Integer addOrUpdate(ReagentInfo reagentInfo,String userName);

    /**
     * 根据条件查询试剂信息
     * @param reagentInfo 查询条件，主要是名称和状态
     * @return
     */
    public List<ReagentInfo> list(ReagentInfo reagentInfo);

    /**
     * 对数据进行逻辑删除
     * @param id
     * @return
     */
    public boolean  logicalDeleteBy(Integer id);

}
