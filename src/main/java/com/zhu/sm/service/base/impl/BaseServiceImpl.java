package com.zhu.sm.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.sm.mapper.base.MyMapper;
import com.zhu.sm.service.base.BaseService;
import com.zhu.sm.common.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    @Override
    public void getCascadeChildrenIds(Long id, List<Long> ids) {
        List<T> ts = myMapper.selectList(new QueryWrapper<T>().eq("parent_id", id));
        if (!CollectionUtils.isEmpty(ts)) {
            ts.forEach(t -> {
                ids.add((Long) ReflectionUtils.getFieldValue(t, "id"));
                getCascadeChildrenIds((Long) ReflectionUtils.getFieldValue(t, "id"), ids);
            });
        }
    }


}
