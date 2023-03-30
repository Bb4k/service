package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.unibuc.hello.data.ProjectRepository;
import ro.unibuc.hello.dto.ProjectDto;
import ro.unibuc.hello.entity.ProjectEntity;
import ro.unibuc.hello.exception.ProjectNotFoundException;

import java.util.Optional;

@SpringBootTest
@Tag("IT")
class ProjectServiceTestIT {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @Test
    void test_saveProject_returnsProject() {
        // Arrange
        String userId = "1";
        String name = "Project Name Test Save";
        String description = "ProjectDescription Test Save";

        ProjectDto projectDto = new ProjectDto(userId, name, description);
        // Act
        ProjectEntity project = projectService.saveProject(projectDto);

        // Assert
        Assertions.assertEquals(userId, project.getUserId());
        Assertions.assertEquals(name, project.getName());
        Assertions.assertEquals(description, project.getDescription());
    }
    @Test
    void test_deleteProjectById_deletesProject() {
        String userId = "2";
        String name = "Project Name Test Delete";
        String description = "ProjectDescription Test Delete";

        ProjectDto projectDto = new ProjectDto(userId, name, description);
        ProjectEntity project = projectService.saveProject(projectDto);
        String projectId = project.getId();
        projectService.deleteProjectById(projectId);

        try {
            projectService.getProjectById(projectId);
            Assertions.fail("The project should be deleted.");
        } catch (ProjectNotFoundException e) {
            // The project was deleted successfully.
        }
    }
}
