package com.zhu.sm.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhu.sm.entity.base.BaseEntity;
import com.zhu.sm.mapper.base.MyMapper;
import com.zhu.sm.service.base.BaseService;
import com.zhu.sm.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:47
 * @className: BaseServiceImpl
 * @description:
 */

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private MyMapper<T> myMapper;   //泛型T  tmd

    @Override
    public List<T> findAll() {
        return myMapper.selectList(null);
    }

    @Override
    public T findById(long id) {
        return (T) myMapper.selectById(id);
    }

    @Override
    public int add(T t) {
        //使用反射
        ReflectionUtils.invokeMethod(t,"setData",null,null);
        return myMapper.insert(t);
    }

    @Override
    public int update(T t) {
        ReflectionUtils.invokeMethod(t,"setData",null,null);
        return myMapper.updateById(t);
    }

    @Override
    public int deleteById(Long id) {
        return myMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int batchDelByIds(List<Long> ids) {
        return myMapper.deleteBatchIds(ids);
    }
}
