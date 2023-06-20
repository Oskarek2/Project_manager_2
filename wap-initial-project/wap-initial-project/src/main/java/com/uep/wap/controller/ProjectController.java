package com.uep.wap.controller;

import com.uep.wap.model.Project;
import com.uep.wap.dto.UserDTO;
import com.uep.wap.service.ProjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @PostMapping("/{projectId}/addUser")
    public Project addUserToProject(@PathVariable Long projectId, @RequestBody UserDTO userDTO) {
        return projectService.addUserToProject(projectId, userDTO);
    }
}
