package com.cn.Util;

import com.cn.enums.ResultStatusCodeEnum;
import com.cn.vo.ResultVo;

/**
 * 返给前端数据生成数据工具类
 */
public class ResultUtil {
    /**
     *生成结果成功
     * @param data
     * @return
     */
    public static ResultVo GenerateSuccessResult(Integer code ,String message,Object data){
        ResultVo resultVo=new ResultVo();
        resultVo.setStatus(code);
        resultVo.setMsg(message);
        resultVo.setData(data);
        return resultVo;
    }

    /**
     * 生成结果失败
     * @param code
     * @param message
     * @return
     */
    public static ResultVo GenerateErrorResult(Integer code,String message){
        ResultVo resultVo=new ResultVo();
        resultVo.setStatus(code);
        resultVo.setMsg(message);
        return resultVo;
    }

}
