package com.aizatgaz.service;

import com.aizatgaz.dao.TaskDAO;
import com.aizatgaz.domain.entity.Task;
import com.aizatgaz.domain.entity.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private TaskDAO<Task, Integer> taskDAO;

    @Autowired
    public TaskService(TaskDAO<Task, Integer> taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getAllTasks(int offset, int count) {
        return taskDAO.getPage(offset, count);
    }

    public int count() {
        return taskDAO.count();
    }

    public Task edit(Task task) {
        taskDAO.saveOrUpdate(task);
        return task;
    }

    public Task create(String description, Status status) {
        Task task = new Task();
        task.setStatus(status);
        task.setDescription(description);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    public void delete(int id) {
        if (taskDAO.existsById(id)) {
            taskDAO.deleteById(id);
            return;
        }
        throw new RuntimeException("Task not found");
    }

    public Task getById(int id) {
        return taskDAO.findById(id);
    }
}
