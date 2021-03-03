package com.github.fashionbrot.common.constant;


import com.github.fashionbrot.common.vo.RespVo;

import java.util.regex.Pattern;

public class GlobalConst {

    public final static String AUTH_KEY = "Authorization";


    public final static String ROLE_NAME = "qazxsw";

    public final static String REAL_NAME = "edcvfr";

    public final static RespVo RESP_VO = RespVo.success(null);

    public final static String PHONE_PATTERN="^1(3([0-35-9]\\d|4[1-8])|4[14-9]\\d|5([0125689]\\d|7[1-79])|66\\d|7[2-35-8]\\d|7[0]\\d|8\\d{2}|9[89]\\d)\\d{7}$";


    public static void main(String[] args) {
        String phone="17796002410";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        boolean isMatcher = pattern.matcher(phone).matches();
        if (!isMatcher) {
            System.out.println("不合法");
        }
    }
}
