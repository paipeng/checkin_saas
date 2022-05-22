package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.tenant.entity.Task;
import com.paipeng.saas.checkin.tenant.entity.User;
import com.paipeng.saas.checkin.tenant.repository.TaskRepository;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends BaseService {
    @Autowired
    private TaskRepository taskRepository;

    public Task query(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> query() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        if (task.getCompany() == null) {
            User user = getUserFromSecurity();
            task.setCompany(user.getCompany());
        }
        return taskRepository.saveAndFlush(task);
    }

    public Task update(Long taskId, Task task) {
        Task foundTask = query(taskId);
        if (foundTask == null) {
            throw new SC_NOT_FOUND();
        }
        foundTask.setName(task.getName());
        foundTask.setDescription(task.getDescription());
        foundTask.setState(task.getState());
        foundTask.setStartTime(task.getStartTime());
        foundTask.setEndTime(task.getEndTime());
        logger.trace("foundTask: " + foundTask.getCompany().getName());
        return taskRepository.saveAndFlush(foundTask);
    }

    public void delete(Long taskId) {
        Task foundTask = query(taskId);
        if (foundTask == null) {
            throw new SC_NOT_FOUND();
        }
        taskRepository.delete(foundTask);
    }
}
