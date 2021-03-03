package com.github.fashionbrot.core.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fashionbrot.common.req.LogReq;
import com.github.fashionbrot.core.dao.LogDao;
import com.github.fashionbrot.core.dto.LogDTO;
import com.github.fashionbrot.core.entity.LogEntity;
import com.github.fashionbrot.core.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogDaoImpl extends ServiceImpl<LogMapper, LogEntity> implements LogDao {

    @Autowired
    private LogMapper logMapper;


    @Override
    public List<LogDTO> selectListByReq(LogReq req) {
        return logMapper.selectListByReq(req);
    }
}
