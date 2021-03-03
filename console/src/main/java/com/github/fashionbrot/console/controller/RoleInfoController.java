package com.github.fashionbrot.console.controller;


import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.annotation.PersistentLog;
import com.github.fashionbrot.common.req.PageReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.RoleInfo;
import com.github.fashionbrot.core.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@Controller
@RequestMapping("/system/role")
public class RoleInfoController {

    @Autowired
    private RoleInfoService envInfoFacade;

    @MarsPermission("system:role:index")
    @GetMapping("/index")
    public String index(){
        return "system/role/role";
    }

    @GetMapping("/index/add")
    public String indexAdd(){
        return "system/role/add";
    }

    @GetMapping("/index/edit")
    public String indexEdit(){
        return "system/role/edit";
    }




    @PersistentLog
    @RequestMapping(value = "add")
    @ResponseBody
    @MarsPermission("system:role:add")
    public RespVo add(@RequestBody RoleInfo roleInfo) {
        envInfoFacade.add(roleInfo);
        return RespVo.success();
    }

    @PersistentLog
    @RequestMapping(value = "update")
    @ResponseBody
    @MarsPermission("system:role:update")
    public RespVo update(@RequestBody RoleInfo roleInfo) {
        envInfoFacade.update(roleInfo);
        return RespVo.success();
    }

    @PersistentLog
    @RequestMapping(value = "deleteById")
    @ResponseBody
    @MarsPermission("system:role:del")
    public RespVo deleteById(Long id) {
        envInfoFacade.deleteById(id);
        return RespVo.success();
    }

    @RequestMapping(value = "queryById")
    @ResponseBody
    @MarsPermission("system:role:info")
    public RespVo queryById(Long id) {
        return RespVo.success(envInfoFacade.queryById(id));
    }


    @RequestMapping(value = "page")
    @ResponseBody
    @MarsPermission("system:role:page")
    public RespVo page(PageReq pageReq) {
        return RespVo.success(envInfoFacade.page(pageReq.getPageNum(),pageReq.getPageSize()));
    }


    @RequestMapping(value = "queryAll")
    @ResponseBody
    public List<RoleInfo> queryAll() {
        return envInfoFacade.queryAll();
    }
}
