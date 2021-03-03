package com.github.fashionbrot.console.controller;

import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.annotation.NoReturnValue;
import com.github.fashionbrot.common.annotation.PersistentLog;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.util.CookieUtil;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.UserInfo;
import com.github.fashionbrot.core.service.UserInfoService;
import com.github.fashionbrot.core.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@Controller
@RequestMapping("/system/user")
@Slf4j
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserLoginService userLoginService;

    @MarsPermission("system:user:index")
    @GetMapping("/index")
    public String index(String a) {
        return "system/user/user" ;
    }

    @GetMapping("/index/add")
    public String indexAdd() {
        return "/system/user/add" ;
    }

    @RequestMapping("/index/edit/")
    public String edit(Long id, ModelMap modelMap) {
        modelMap.put("info", userInfoService.queryById(id));
        return "system/user/edit" ;
    }

    @GetMapping("/profile/resetPwd")
    public String test(ModelMap mmap) {
        LoginModel loginModel = userLoginService.getLogin();
        mmap.put("user", loginModel);
        return "system/user/resetPwd" ;
    }

    @PersistentLog
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespVo login(@RequestParam("username") String userName,
                        @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        return userInfoService.login(userName, password, request, response);
    }

    @RequestMapping("/logout")
    @NoReturnValue
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response);
        return "login" ;
    }


    @PersistentLog
    @RequestMapping("/resetPwd")
    @ResponseBody
    @MarsPermission("system:user:resetPwd")
    public RespVo resetPwd(String oldPassword, String newPassword) {
        return userInfoService.resetPwd(oldPassword, newPassword);
    }

    @PersistentLog
    @RequestMapping("/add")
    @ResponseBody
    @MarsPermission("system:user:add")
    public RespVo add(@RequestBody UserInfo userInfo) {
        userInfoService.add(userInfo);
        return RespVo.success();
    }

    @PersistentLog
    @RequestMapping("/update")
    @ResponseBody
    @MarsPermission("system:user:update")
    public RespVo update(@RequestBody UserInfo userInfo) {
        userInfoService.update(userInfo);
        return RespVo.success();
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    @MarsPermission("system:user:del")
    public RespVo deleteById(@RequestParam("id") Long id) {
        userInfoService.deleteById(id);
        return RespVo.success();
    }


    @RequestMapping("/queryById")
    @ResponseBody
    @MarsPermission("system:user:info")
    public RespVo queryById(@RequestParam("id") Long id) {
        return RespVo.success(userInfoService.queryById(id));
    }


    @RequestMapping("queryAll")
    @ResponseBody
    @MarsPermission("system:user:page")
    public RespVo queryAll(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "20") Integer pageSize) {
        return RespVo.success(userInfoService.queryAll(pageNum, pageSize));
    }

    @RequestMapping("queryRoleAll")
    @ResponseBody
    public RespVo queryRoleAll() {
        return RespVo.success(userInfoService.queryRoleAll());
    }


}
