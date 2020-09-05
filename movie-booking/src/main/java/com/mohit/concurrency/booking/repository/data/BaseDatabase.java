package com.mohit.concurrency.booking.repository.data;

import com.mohit.concurrency.booking.model.entity.BaseEntity;

public interface BaseDatabase<T extends BaseEntity> {
    boolean save(T entity);
    T findById(Long id);
}
