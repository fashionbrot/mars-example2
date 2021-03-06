package com.github.fashionbrot.core.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.fashionbrot.core.entity.MenuRoleRelation;
import com.github.fashionbrot.core.mapper.MenuRoleRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MenuRoleRelationDao  {

    @Autowired
    private MenuRoleRelationMapper menuRoleRelationMapper;


    public int delete(QueryWrapper queryWrapper) {
        return menuRoleRelationMapper.delete(queryWrapper);
    }


    public int insert(MenuRoleRelation relation) {
        return menuRoleRelationMapper.insert(relation);
    }
}
