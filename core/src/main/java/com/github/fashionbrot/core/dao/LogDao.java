package com.github.fashionbrot.core.dao;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.fashionbrot.common.req.LogReq;
import com.github.fashionbrot.core.dto.LogDTO;
import com.github.fashionbrot.core.entity.LogEntity;

import java.util.List;


/**
 * 日志表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2020-09-14
 */

public interface LogDao extends IService<LogEntity> {


    List<LogDTO> selectListByReq(LogReq req);
}