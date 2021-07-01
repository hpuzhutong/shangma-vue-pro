package com.zhu.sm.common.util;

import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/30 22:00
 * @className: TreeUtil
 * @description:
 */
public class TreeUtil {

    /**
     * 从所有的数据中  查询出最顶级的孩子  递归查询
     *
     * @param root    最顶级的集合
     * @param allList 所有数据
     * @param <T>
     */
    public static <T> void buildTreeData(List<T> root, List<T> allList) {
        List<T> sortList = root.stream().sorted((t1, t2) -> {
            Integer t1Sort = (Integer) ReflectionUtils.getFieldValue(t1, "sort");
            Integer t2Sort = (Integer) ReflectionUtils.getFieldValue(t2, "sort");
            return t1Sort - t2Sort;
        }).collect(Collectors.toList());
        root.clear();
        root.addAll(sortList);
        root.forEach(t -> getChildren(t, allList));
    }


    /**
     * 找孩子方法
     *
     * @param t
     * @param allList
     * @param <T>
     */
    private static <T> void getChildren(T t, List<T> allList) {
//        Long id = (Long) ReflectionUtils.getFieldValue(t, "id");
//        List<T> children  = allList.stream().filter(t1 -> {
//            Long parentId = (Long) ReflectionUtils.getFieldValue(t, "parentId");
//            return parentId.equals(id);
//        }).collect(Collectors.toList());

        //两中代码效果一致
        List<T> children = allList.stream().filter(t1 -> ((Long) ReflectionUtils.getFieldValue(t, "id")).equals((Long) ReflectionUtils.getFieldValue(t1, "parentId"))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(children)) {
            List<T> sortChildren = children.stream().sorted((t1, t2) -> {
                Integer t1Sort = (Integer) ReflectionUtils.getFieldValue(t1, "sort");
                Integer t2Sort = (Integer) ReflectionUtils.getFieldValue(t2, "sort");
                return t1Sort - t2Sort;
            }).collect(Collectors.toList());
            ReflectionUtils.setFieldValue(t, "children", sortChildren);
            children.forEach(t1 -> getChildren(t1, allList));
        }

    }
}
