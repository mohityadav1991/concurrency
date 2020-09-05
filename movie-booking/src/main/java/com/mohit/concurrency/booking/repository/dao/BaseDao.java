package com.mohit.concurrency.booking.repository.dao;

import com.mohit.concurrency.booking.model.entity.BaseEntity;
import com.mohit.concurrency.booking.repository.data.BaseDatabase;

import java.util.List;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
public interface BaseDao<T extends BaseEntity, DB extends BaseDatabase> {

    default T saveOrUpdate(T entity) {
        getDatabase().save(entity);
        return entity;
    }
    default List<T> saveOrUpdateAll(List<T> entities) {
        entities.forEach(entity -> saveOrUpdate(entity));
        return entities;
    }
    default T findOne(Long entityId) {
        return (T) getDatabase().findById(entityId);
    }

    DB getDatabase();
}
