package com.ylv.modules.reagent.vo;

import com.ylv.modules.reagent.bean.ReagentInfo;

public class ReagentInfoVo extends ReagentInfo {

    Integer pageSize;

    Integer pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
