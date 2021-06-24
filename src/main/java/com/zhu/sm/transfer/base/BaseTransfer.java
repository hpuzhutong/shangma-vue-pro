package com.zhu.sm.transfer.base;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/24 13:19
 * @className: BaseTrandsfer
 * @description:
 */
public class BaseTransfer<DTO, Entity> {

    /**
     * entity转DTO
     */
    public DTO toDTO(Entity entity) {
        try {
            Class<DTO> entityClass = (Class<DTO>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            DTO o = entityClass.newInstance();
            BeanUtils.copyProperties(entity, o);
            return o;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * entity转DTO
     */
    public List<DTO> toDTO(List<Entity> list) {
        List<DTO> dtos = new ArrayList<>();
        list.forEach(entity -> dtos.add(toDTO(entity)));
        return dtos;
    }

    /**
     * DTO转Entity
     *
     * @param dto
     * @return
     */
    public Entity toEntity(DTO dto) {
        try {
            Class<Entity> entityClass = (Class<Entity>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Entity entity = entityClass.newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * DTO转Entity
     * @param dto
     * @return
     */
    public List<Entity> toEntity(List<DTO> list) {
        List<Entity> entities = new ArrayList<>();
        list.forEach(dto -> entities.add(toEntity(dto)));
        return  entities;
    }



}
