package ro.unibuc.hello.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dto.ApplicationDto;
import ro.unibuc.hello.dto.ProjectDto;
import ro.unibuc.hello.entity.ApplicationEntity;
import ro.unibuc.hello.entity.ProjectEntity;
import ro.unibuc.hello.entity.UserEntity;
import ro.unibuc.hello.service.ProjectService;
import ro.unibuc.hello.service.UserService;
import ro.unibuc.hello.service.ApplicationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ApplicationService applicationService;
    private ProjectService projectService;
    private UserService userService;


    @GetMapping("/get")
    public List<ApplicationEntity> getAllApplications() {
        return applicationService.getApplications();
    }

    @PostMapping(path = "/send")
    public ResponseEntity<?> sendApplication(@RequestBody ApplicationDto applicationDto) {
        ProjectEntity foundProject = projectService.getProjectById(applicationDto.user_id);
        UserEntity foundUser = userService.getUser(applicationDto.project_id);

        if (foundUser != null && foundProject != null) {
            LOGGER.info("ApplicationController: " + applicationDto);
            ApplicationEntity application = applicationService.saveApplication(applicationDto);

            return ResponseEntity.ok(application);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user ID or project ID");
        }
    }
}