package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.tenant.entity.Device;
import com.paipeng.saas.checkin.tenant.entity.Task;
import com.paipeng.saas.checkin.tenant.entity.User;
import com.paipeng.saas.checkin.tenant.repository.TaskRepository;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends BaseService{
    @Autowired
    private TaskRepository taskRepository;

    public Task get(long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public Task save(Task task) {
        if (task.getCompany() == null) {
            User user = getUserFromSecurity();
            task.setCompany(user.getCompany());
        }
        return taskRepository.saveAndFlush(task);
    }

    public Task update(Task task) {
        Task foundTask = get(task.getId());
        if (foundTask == null) {
            throw new SC_NOT_FOUND();
        }
        return taskRepository.saveAndFlush(task);
    }

    public void delete(long taskId) {
        Task foundTask = get(taskId);
        if (foundTask == null) {
            throw new SC_NOT_FOUND();
        }
        taskRepository.delete(foundTask);
    }
}
