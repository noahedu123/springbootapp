package com.cn.controller;


import com.cn.Exception.appException;
import com.cn.Util.ResultUtil;
import com.cn.dataobject.CreditcardApplicationInformationTbl;
import com.cn.enums.CreditcardEnum;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.CreditcardApplicationInformationService;
import com.cn.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/creditcard")
@Slf4j
public class CreditcardController {
    @Autowired
    private CreditcardApplicationInformationService informationService;

    /**
     * 信用卡申请信息保存
     * @param from
     * @return
     */
    @PostMapping("/save")
    public ResultVo<Object> save(@Valid CreditcardApplicationInformationTbl from,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.GenerateErrorResult(ResultStatusCodeEnum.PARAM_ERROR.getCode(),
                    ResultStatusCodeEnum.PARAM_ERROR.getMessage());
        }else {
            CreditcardEnum result = informationService.save(from);
            return ResultUtil.GenerateSuccessResult(result.getCode(),result.getMessage(),null);

        }
    }

}
