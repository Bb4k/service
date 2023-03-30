package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.ApplicationRepository;
import ro.unibuc.hello.dto.ApplicationDto;
import ro.unibuc.hello.entity.ApplicationEntity;

import java.util.List;

@SpringBootTest
@Tag("IT")
class ApplicationServiceTestIT {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationService applicationService;

    @Test
    void test_buildApplication_returnsApplicationsWithSameProjectId() {

        String projectId = "641cb9f23392f417094a78b5";
        String userId = "641cb9f23392f417094a78b6";
        Integer status = 0;

        // Arrange
        ApplicationDto appDto = new ApplicationDto(projectId, userId, status);
        applicationService.apply(appDto);

        // Act
        List<ApplicationEntity> apps = applicationService.getAppsByProjectId(projectId);

        // Assert
        Assertions.assertEquals(projectId, apps.get(0).getProjectId());
        Assertions.assertEquals(userId, apps.get(0).getUserId());
        Assertions.assertEquals(status, apps.get(0).getStatus());
    }
}