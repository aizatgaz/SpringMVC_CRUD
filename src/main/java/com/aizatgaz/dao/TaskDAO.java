package com.aizatgaz.dao;


import com.aizatgaz.domain.entity.Task;

import java.util.List;

public interface TaskDAO<T, ID> {

    void saveOrUpdate(T entity);

    T findById(ID id);

    boolean existsById(ID id);

    List<T> findAll();

    int count();

    void deleteById(ID id);

    void delete(T entity);

    List<Task> getPage(int offset, int count);
}
