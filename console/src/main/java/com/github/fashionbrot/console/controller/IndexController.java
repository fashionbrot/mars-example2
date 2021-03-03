package com.github.fashionbrot.console.controller;

import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.util.CookieUtil;
import com.github.fashionbrot.core.entity.Menu;
import com.github.fashionbrot.core.service.MenuService;
import com.github.fashionbrot.core.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */

@Controller
public class IndexController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private Environment environment;

    @GetMapping("/index")
    public String index(ModelMap mmap,String view){
        LoginModel loginModel = userLoginService.getLogin();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.loadAllMenu2(loginModel.getRoleId(),loginModel.isSuperAdmin());
        mmap.put("menus", menus);
        mmap.put("user", loginModel);
        if ("top".equals(view) || "true".equals(environment.getProperty("menu.top"))){
            return "index-topnav";
        }
        return "index";
    }


    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin() {
        return "skin";
    }

    // 切换菜单
    @GetMapping("/system/menuStyle/{style}")
    public void menuStyle(@PathVariable String style, HttpServletResponse response) {
        CookieUtil.setCookie(response, "nav-style", style);
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        return "main";
    }


    @GetMapping("/control")
    public String control(){
        return "/control";
    }


}
