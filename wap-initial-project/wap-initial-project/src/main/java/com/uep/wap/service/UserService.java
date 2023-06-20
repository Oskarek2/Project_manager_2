package com.uep.wap.service;

import com.uep.wap.dto.TaskDTO;
import com.uep.wap.exceptions.BadRequestException;
import com.uep.wap.model.Task;
import com.uep.wap.model.User;
import com.uep.wap.repository.TaskRepository;
import com.uep.wap.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public User addTaskToUser(Long userId, TaskDTO task) {
        Optional<User> userOptional = userRepository.findById(userId);
        System.out.println("HEE1");
        if (userOptional.isPresent()) {
            System.out.println("HEE2");
            Task task_ = new Task();
            task_.setTitle(task.getTitle());
            task_.setDescription(task.getDescription());
            User user = userOptional.get();
            task_.setUser(user);
            taskRepository.save(task_);
            return user;
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteTask(Long userId, Long taskId) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(userId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (!user.getTasks().contains(task)) {
            throw new BadRequestException("Task is not associated with the user");
        }

        user.getTasks().remove(task);
        userRepository.save(user);
        taskRepository.delete(task);
    }

}
