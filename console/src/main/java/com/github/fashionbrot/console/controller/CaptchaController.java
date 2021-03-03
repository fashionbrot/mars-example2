package com.github.fashionbrot.console.controller;

import com.github.fashionbrot.core.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("captcha")
    public void getCaptcha(){
        captchaService.getCaptcha();
    }

}
