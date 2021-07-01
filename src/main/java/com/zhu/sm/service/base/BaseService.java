package com.zhu.sm.service.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:46
 * @className: BaseService
 * @description:
 */
public interface BaseService<T> {
    /**
     * 查询所有
     */
    List<T> findAll();

    /**
     * 通过id查询
     */
    T findById(long id);

    /**
     * 增加
     */
    int add(T t);

    /**
     * 修改
     */
    int update(T t);

    /**
     * 按id删除
     */
    int deleteById(Long id);

    /**
     * 批量删除
     */
    int batchDelByIds(List<Long> ids);




}
