package ro.unibuc.hello.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.unibuc.hello.dto.ApplicationDto;
import ro.unibuc.hello.entity.ApplicationEntity;
import ro.unibuc.hello.data.ApplicationRepository;
import java.util.List;

@Component
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<ApplicationEntity> getApplications() {
        return applicationRepository.findAll();
    }

    public ApplicationEntity saveApplication(ApplicationDto applicationDto) {
        ApplicationEntity applicationEntity = new ApplicationEntity();

        applicationEntity.setProject_id(applicationDto.project_id);
        applicationEntity.setUser_id(applicationDto.user_id);

        return applicationRepository.save(applicationEntity);
    }
}
