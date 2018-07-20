package com.cn.handler;

import com.cn.Util.ResultUtil;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.cn.Exception.appException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class appExceptionHander {
    @ExceptionHandler(value = appException.class)
    public ResultVo handlerAppException(appException e){
        return ResultUtil.GenerateErrorResult(e.getCode(),e.getMessage());
    }
}
