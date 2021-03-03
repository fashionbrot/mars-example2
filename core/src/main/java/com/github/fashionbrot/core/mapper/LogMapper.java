
package com.github.fashionbrot.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fashionbrot.common.req.LogReq;
import com.github.fashionbrot.core.dto.LogDTO;
import com.github.fashionbrot.core.entity.LogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2020-09-14
 */
@Repository
public interface LogMapper extends BaseMapper<LogEntity> {

    List<LogDTO> selectListByReq(LogReq req);
}