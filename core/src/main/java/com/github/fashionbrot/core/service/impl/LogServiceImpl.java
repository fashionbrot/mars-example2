package com.github.fashionbrot.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.fashionbrot.core.dao.LogDao;
import com.github.fashionbrot.core.dto.LogDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.exception.CurdException;
import com.github.fashionbrot.common.req.LogReq;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.core.entity.LogEntity;
import com.github.fashionbrot.core.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 日志表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2020-09-14
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public Collection<LogEntity> queryList(Map<String, Object> params) {
        return logDao.listByMap(params);
    }

    /**
     * 查询数据列表
     * @param params 查询条件
     * @return List<LogEntity>
     */
    public Collection<LogEntity> logByMap(Map<String, Object> params){
        return logDao.listByMap(params);
    }

    /**
    * 分页查询
    * @param req
    * @return
    */
    public PageVo pageList(LogReq req){
        Page<?> page= PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<LogDTO> list = logDao.selectListByReq(req);

        return PageVo.builder()
                .rows(list)
                .total(page.getTotal())
                .build();
    }


    @Override
    public void insert(LogEntity entity) {
        if(!logDao.save(entity)){
            throw new CurdException(RespCode.SAVE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(Collection<LogEntity> entityList) {
        return logDao.saveBatch(entityList,30);
    }

    /**
     * 批量插入
     *
     * @param entityList
     * @param batchSize
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(Collection<LogEntity> entityList, int batchSize) {
        return logDao.saveBatch(entityList,batchSize);
    }


    @Override
    public void updateById(LogEntity entity) {
        if(!logDao.updateById(entity)){
            throw new CurdException(RespCode.UPDATE_ERROR);
        }
    }

    @Override
    public void update(LogEntity entity, Wrapper<LogEntity> updateWrapper) {
        if(!logDao.update(entity, updateWrapper)){
            throw new CurdException(RespCode.UPDATE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<LogEntity> entityList) {
        return logDao.updateBatchById(entityList,30);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<LogEntity> entityList, int batchSize) {
        return logDao.updateBatchById(entityList,batchSize);
    }

    @Override
    public LogEntity selectById(Serializable id) {
        return logDao.getById(id);
    }

    @Override
    public void deleteById(Serializable id) {
        if(!logDao.removeById(id)){
            throw new CurdException(RespCode.DELETE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchIds(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isNotEmpty(idList)){
            boolean result = logDao.removeByIds(idList);
            if (!result){
                throw new CurdException(RespCode.DELETE_ERROR);
            }
        }
    }

    @Override
    public boolean deleteByMap(Map<String, Object> columnMap) {
        return logDao.removeByMap(columnMap);
    }

    @Override
    public boolean delete(Wrapper<LogEntity> queryWrapper) {
        return logDao.remove(queryWrapper);
    }



}