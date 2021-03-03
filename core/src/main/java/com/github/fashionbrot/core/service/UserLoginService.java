package com.github.fashionbrot.core.service;


import com.github.fashionbrot.common.constant.GlobalConst;
import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.exception.MarsException;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.util.CookieUtil;
import com.github.fashionbrot.common.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserLoginService {

    @Autowired
    private HttpServletRequest request;

    public LoginModel getLogin() {
        String token = CookieUtil.getCookieValue(request, GlobalConst.AUTH_KEY, false);
        if (StringUtils.isEmpty(token)) {
            throw new MarsException(RespCode.SIGNATURE_MISMATCH);
        }
        return JwtTokenUtil.getLogin(token);
    }

    public LoginModel getSafeLogin() {
        String token = CookieUtil.getCookieValue(request, GlobalConst.AUTH_KEY, false);
        if (StringUtils.isNotEmpty(token)) {
            return JwtTokenUtil.getLogin(token);
        }
        return null;
    }


}
