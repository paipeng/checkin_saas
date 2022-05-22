package com.paipeng.saas.checkin.controller;

import com.paipeng.saas.checkin.tenant.entity.Task;
import com.paipeng.saas.checkin.tenant.service.TaskService;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController extends BaseController {
    @Autowired
    private TaskService TaskService;

    @GetMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Task query(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("query: " + id);
        Task Task = TaskService.query(id);
        if (Task == null) {
            throw new SC_NOT_FOUND();
        }
        return Task;
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public List<Task> query() throws Exception {
        logger.trace("query all");
        return TaskService.query();
    }

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public Task save(@NotNull @RequestBody Task Task) throws Exception {
        logger.trace("save: " + Task);
        //response.setStatus(SC_CREATED);
        return TaskService.save(Task);
    }

    @PutMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Task update(@PathVariable("id") Long id, @NotNull @RequestBody Task task) throws Exception {
        logger.trace("update: " + task);
        //response.setStatus(SC_OK);
        return TaskService.update(id, task);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public void delete(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("delete" + id);
        //response.setStatus(SC_NO_CONTENT);
        TaskService.delete(id);
    }
}
