package com.github.fashionbrot.console.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.req.LogReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.LogEntity;
import com.github.fashionbrot.core.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;


/**
 * 日志表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2020-09-14
 */

@Controller
@RequestMapping("/system/log")
@Api(tags="日志表")
@ApiSort(20013942)
public class LogController {

    @Autowired
    private LogService logService;


    @MarsPermission("system:log:index")
    @GetMapping("/index")
    public String index(){
        return "system/log/log";
    }


    @MarsPermission("system:log:index:detail")
    @GetMapping("/index/detail")
    public String detail( Long id, ModelMap modelMap){
        LogEntity data = logService.selectById(id);
        modelMap.put("operLog",data);
        return "system/log/detail";
    }


    @ApiOperation("数据列表—分页")
    @MarsPermission("system:log:page")
    @GetMapping("/page")
    @ResponseBody
    public RespVo page(LogReq req){
        return  RespVo.success(logService.pageList(req));
    }


    @ApiOperation("数据列表")
    @GetMapping("/queryList")
    @ResponseBody
    public Collection<LogEntity> queryList(@RequestParam Map<String, Object> params){
        return  logService.queryList(params);
    }


    @ApiOperation("根据id查询")
    @PostMapping("/selectById")
    @ResponseBody
    public RespVo selectById(Long id){
        LogEntity data = logService.selectById(id);
        return RespVo.success(data);
    }


    @ApiOperation("新增")
    @PostMapping("/insert")
    @ResponseBody
    public RespVo add(@RequestBody LogEntity entity){
        logService.insert(entity);
        return RespVo.success();
    }


    @ApiOperation("修改")
    @PostMapping("/updateById")
    @ResponseBody
    public RespVo updateById(@RequestBody LogEntity entity){
        logService.updateById(entity);
        return RespVo.success();
    }


    @ApiOperation("根据id删除")
    @MarsPermission("system:log:del")
    @PostMapping("/deleteById")
    @ResponseBody
    public RespVo deleteById(Long id){
        logService.deleteById(id);
        return RespVo.success();
    }


    @ApiOperation("批量删除")
    @PostMapping("/deleteByIds")
    @ResponseBody
    public RespVo delete(@RequestBody Long[] ids){
        logService.deleteBatchIds(Arrays.asList(ids));
        return RespVo.success();
    }


}