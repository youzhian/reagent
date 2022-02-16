package com.ylv.modules;

import com.ylv.util.ResponseUtil;

public class BaseController {

    protected ResponseUtil success(){
        return success(null);
    }

    protected ResponseUtil success(String msg){
        return success(msg,null);
    }

    protected ResponseUtil success(String msg,Object data){
        return ResponseUtil.success(msg,data);
    }

    protected ResponseUtil error(String msg){
        return error(null,msg);
    }

    protected ResponseUtil error(String msg,Object data){
        return error(null,msg,data);
    }

    protected ResponseUtil error(String code,String msg){
        return error(code,msg,null);
    }

    protected ResponseUtil error(String code,String msg,Object data){
        return ResponseUtil.error(code,msg,data);
    }
}
