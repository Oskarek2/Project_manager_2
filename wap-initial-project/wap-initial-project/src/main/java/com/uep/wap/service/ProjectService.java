package com.uep.wap.service;

import com.uep.wap.model.Project;
import com.uep.wap.model.User;
import com.uep.wap.dto.UserDTO;
import com.uep.wap.repository.ProjectRepository;
import com.uep.wap.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Project addUserToProject(Long projectId, UserDTO userDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id = " + projectId));

        User user = new User();
        user.setName(userDTO.getName());

        userRepository.save(user);

        project.getUsers().add(user);
        return projectRepository.save(project);
    }
}
