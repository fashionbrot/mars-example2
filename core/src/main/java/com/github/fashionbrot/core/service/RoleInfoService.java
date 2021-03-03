package com.github.fashionbrot.core.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.fashionbrot.core.dao.MenuRoleRelationDao;
import com.github.fashionbrot.core.dao.RoleInfoDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.exception.MarsException;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.core.entity.MenuRoleRelation;
import com.github.fashionbrot.core.entity.RoleInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@Service
@Slf4j
public class RoleInfoService  {

    @Autowired
    protected RoleInfoDao roleInfoDao;

    @Autowired
    private MenuRoleRelationDao menuRoleRelationDao;

    @Autowired
    private MenuService menuService;

    @Transactional(rollbackFor = Exception.class)
    public void add(RoleInfo roleInfo) {
        //roleInfo.setRoleCode(ChineseToSpellUtil.getPingYin(roleInfo.getRoleName()));
        if (roleInfoDao.add(roleInfo)!=1){
            throw new MarsException(RespCode.SAVE_ERROR);
        }
        menuService.updateRoleMenu(roleInfo.getId(),roleInfo.getMenuIds());
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(RoleInfo roleInfo) {
        //roleInfo.setRoleCode(ChineseToSpellUtil.getPingYin(roleInfo.getRoleName()));
        if (roleInfoDao.update(roleInfo)!=1){
            throw new MarsException(RespCode.UPDATE_ERROR);
        }
        menuService.updateRoleMenu(roleInfo.getId(),roleInfo.getMenuIds());
    }


    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        if(roleInfoDao.deleteById(id)!=1){
            throw new MarsException(RespCode.DELETE_ERROR);
        }
        menuRoleRelationDao.delete(new QueryWrapper<MenuRoleRelation>().eq("role_id",id));
    }


    public RoleInfo queryById(Long id) {
        return roleInfoDao.queryById(id);
    }


    public List<RoleInfo> queryAll() {
        return roleInfoDao.queryAll(null);
    }


    public PageVo page(Integer pageNum,Integer pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<RoleInfo> roleInfos = roleInfoDao.queryAll(null);
        return PageVo.builder()
                .rows(roleInfos)
                .total(page.getTotal())
                .build();
    }



}
