package com.aizatgaz.dao;

import com.aizatgaz.domain.entity.Task;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TaskDAOImpl implements TaskDAO<Task, Integer> {

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Task entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public Task findById(Integer id) {
        return sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id) != null;
    }

    @Override
    public List<Task> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Task", Task.class).getResultList();
    }

    @Override
    public int count() {
        return Math.toIntExact(sessionFactory.getCurrentSession().createQuery("select count(*) from Task", Long.class).getSingleResult());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        delete(findById(id));
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Task entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }

    @Override
    public List<Task> getPage(int offset, int count) {
        return sessionFactory.getCurrentSession().createQuery("from Task", Task.class)
                .setFirstResult(offset)
                .setMaxResults(count)
                .getResultList();
    }
}
