package com.cn.controller;

import com.cn.Util.ResultUtil;
import com.cn.enums.ResultStatusCodeEnum;
import com.cn.enums.UserLoginEnum;
import com.cn.service.MemberService;
import com.cn.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 保存会员信息
     * @param telephone
     * @return
     */
    @PostMapping("/save")
    public ResultVo<Object> save(@RequestParam("telephone") String telephone){
        UserLoginEnum userLoginEnum = memberService.updateMember(telephone);
        return ResultUtil.GenerateSuccessResult(userLoginEnum.getCode(),userLoginEnum.getMessage(),null);
    }

}
