package com.cn.controller;

import com.cn.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apply")
public class ApplyController {
    @PostMapping("/save")
    public ResultVo<Object> save(){
        return null;
    }

}
