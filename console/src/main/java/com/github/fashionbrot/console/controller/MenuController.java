package com.github.fashionbrot.console.controller;


import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.annotation.PersistentLog;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.Menu;
import com.github.fashionbrot.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */

@Controller
@RequestMapping("/system/menu")
public class MenuController {


    @Autowired
    private MenuService envInfoFacade;

    @MarsPermission("system:menu:index")
    @GetMapping("/index")
    public String index() {
        return "system/menu/menu" ;
    }

    @GetMapping("/index/add")
    public String indexAdd() {
        return "system/menu/add" ;
    }

    @GetMapping("/index/edit")
    public String indexEdit() {
        return "system/menu/edit" ;
    }


    @PersistentLog
    @RequestMapping(value = "add")
    @ResponseBody
    @MarsPermission("system:menu:add")
    public RespVo add(@RequestBody Menu envInfo) {
        envInfoFacade.add(envInfo);
        return RespVo.success();
    }

    @PersistentLog
    @RequestMapping(value = "update")
    @ResponseBody
    @MarsPermission("system:menu:update")
    public RespVo update(@RequestBody Menu envInfo) {
        envInfoFacade.update(envInfo);
        return RespVo.success();
    }

    @PersistentLog
    @RequestMapping(value = "deleteById")
    @ResponseBody
    @MarsPermission("system:menu:del")
    public RespVo deleteById(Long id) {
        envInfoFacade.deleteById(id);
        return RespVo.success();
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    @MarsPermission("system:menu:info")
    public RespVo queryById(Long id) {
        return RespVo.success(envInfoFacade.queryById(id));
    }

    @RequestMapping(value = "queryAllList")
    @ResponseBody
    @MarsPermission("system:menu:page")
    public RespVo queryAllList(Integer page, Integer pageSize) {
        return RespVo.success(envInfoFacade.queryAll(page, pageSize));
    }

    @RequestMapping(value = "queryAll")
    @ResponseBody
    public List<Menu> queryAll() {
        return envInfoFacade.queryAll();
    }

    @RequestMapping(value = "queryMenuLevel")
    @ResponseBody
    public List<Menu> queryMenuLevel(Menu menuBar) {
        return envInfoFacade.queryMenuLevel(menuBar);
    }


    @RequestMapping("loadCheckedMenu")
    @ResponseBody
    public List<Menu> loadCheckedMenu(@RequestParam("roleId") Long roleId) {
        return envInfoFacade.loadCheckedMenu(roleId);
    }

    @RequestMapping("loadAllMenu")
    @ResponseBody
    public List<Menu> loadAllMenu(Long roleId, Integer isShowCode) {
        return envInfoFacade.loadAllMenu(roleId, isShowCode);
    }

    @RequestMapping("updateRoleMenu")
    @ResponseBody
    @MarsPermission("role:list:update:menu")
    public RespVo updateRoleMenu(Long roleId, String menuIds) {
        return envInfoFacade.updateRoleMenu(roleId, menuIds);
    }


}
