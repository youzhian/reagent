package com.ylv.util;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ResponseUtil {

    public final static String SUCCESS_CODE="200";

    public final static String SUCCESS_MSG = "成功";

    public final static String ERROR_CODE = "500";

    public final static String ERROR_MSG = "失败";

    private String code;

    private String msg;

    private Object data;


    public static ResponseUtil success(String msg,Object data){
        ResponseUtil ru = new ResponseUtil();
        ru.setCode(SUCCESS_CODE);
        ru.setMsg(msg);
        if(StringUtils.isBlank(msg)){
            ru.setMsg(SUCCESS_MSG);
        }
        if(data != null){
            ru.setData(data);
        }

        return ru;
    }

    public static ResponseUtil error(String code,String msg,Object data){
        ResponseUtil r = new ResponseUtil();
        if(StringUtils.isBlank(code)){
            r.setCode(ERROR_CODE);
        }else {
            r.setCode(code);
        }
        if(StringUtils.isBlank(msg)){
            r.setMsg(ERROR_MSG);
        }else {
            r.setMsg(msg);
        }
        if(data != null){
            r.setData(data);
        }
        return r;
    }

}
