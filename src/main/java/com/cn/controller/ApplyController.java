package com.cn.controller;

import com.cn.Util.ResultUtil;
import com.cn.dataobject.ApplyTbl;
import com.cn.enums.ApplyAppointmentEnum;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.form.ApplyAppointmentForm;
import com.cn.service.ApplyAppointmentService;
import com.cn.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyAppointmentService applyAppointmentService;

    /**
     * 需求预约信息保存
     * @param apply
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    public ResultVo<Object> save(@Valid ApplyAppointmentForm apply,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.GenerateErrorResult(ResultStatusCodeEnum.PARAM_ERROR.getCode(),
                    ResultStatusCodeEnum.PARAM_ERROR.getMessage());
        }else{
            Optional<ApplyTbl> optionalApplyTbl = Optional.of(new ApplyTbl());
            BeanUtils.copyProperties(apply,optionalApplyTbl.get());
            ApplyAppointmentEnum applyAppointmentEnum = applyAppointmentService.save(optionalApplyTbl.get());
            return ResultUtil.GenerateSuccessResult(applyAppointmentEnum.getCode(),
                                                    applyAppointmentEnum.getMessage(),null);
        }

    }

}
