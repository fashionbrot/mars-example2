package com.github.fashionbrot.core.service;

import com.github.fashionbrot.tool.UuidUtil;
import com.github.fashionbrot.common.util.CaptchaUtil;
import com.github.fashionbrot.common.vo.CaptchaVo;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class CaptchaService {

    @Autowired
    private HttpServletResponse response;

    public void getCaptcha() {
        ArithmeticCaptcha captcha = CaptchaUtil.getCaptcha();
        try {
            CaptchaUtil.out(captcha,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CaptchaVo getCaptcha(String source) {
        ArithmeticCaptcha captcha = CaptchaUtil.getCaptcha();
        String value = captcha.text();
        String captchaId = UuidUtil.getUuid();
        String base64 = captcha.toBase64();
        /*redisService.set(source + captchaId, value, 120);
        if (log.isDebugEnabled()){
            log.debug("getCaptcha captchaId:{} captcha:{} value:{}",captchaId,captcha.getArithmeticString(),value);
        }*/
        return CaptchaVo.builder()
                .captchaId(captchaId)
                .captcha(base64)
                .build();
    }

}
