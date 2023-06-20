package com.uep.wap.controller;

import com.uep.wap.dto.TaskDTO;
import com.uep.wap.model.Task;
import com.uep.wap.model.User;
import com.uep.wap.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
//
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/addTask")
    public User addTaskToUser(@PathVariable Long userId, @RequestBody TaskDTO task) {
        return userService.addTaskToUser(userId, task);
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public void deleteTask(@PathVariable Long userId, @PathVariable Long taskId) throws ChangeSetPersister.NotFoundException {
        userService.deleteTask(userId, taskId);
    }


}
