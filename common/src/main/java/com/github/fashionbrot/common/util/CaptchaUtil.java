package com.github.fashionbrot.common.util;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;

import java.io.OutputStream;

public class CaptchaUtil {


    public static ArithmeticCaptcha getCaptcha(){
        ArithmeticCaptcha captcha =new ArithmeticCaptcha(150, 50);
        try {
            captcha.setFont(Captcha.FONT_5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return captcha;
    }

    public static void out(ArithmeticCaptcha captcha,OutputStream out){
        if (captcha!=null){
            captcha.out(out);
        }
    }

    public static String base64(ArithmeticCaptcha captcha){
        if (captcha!=null){
           return captcha.toBase64();
        }
        return null;
    }

}
