package ro.unibuc.hello.controller;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.unibuc.hello.dto.ApplicationDto;
import ro.unibuc.hello.dto.ProjectDto;
import ro.unibuc.hello.dto.UpdateProjectDto;
import ro.unibuc.hello.entity.ApplicationEntity;
import ro.unibuc.hello.entity.ProjectEntity;
import ro.unibuc.hello.exception.ProjectNotFoundException;
import ro.unibuc.hello.service.ApplicationService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
@RestController
@RequestMapping("/applications")
public class ApplicationEntityController {
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    MeterRegistry metricsRegistry;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/get-all")
    @Timed(value = "hello.application.get.all.time", description = "Time taken to return all the applications")
    @Counted(value = "hello.application.get.all.counted", description = "Times all the applications were returned")
    public List<ApplicationEntity> getAllApplications() {
        metricsRegistry.counter("non_aop_metric", "application", "get-all-counted").increment(counter.incrementAndGet());
        return applicationService.getApplications();
    }

    @RequestMapping(method = {RequestMethod.GET}, value="/project/{id}")
    @ResponseBody
    @Timed(value = "hello.application.for.project.id.time", description = "Time taken to return all the applications for a project")
    @Counted(value = "hello.application.get.all.counted", description = "Times all the applications were returned for a project")
    public ResponseEntity<?> getApplicationForProject(@PathVariable("id") String id){
//        metricsRegistry.counter("non_aop_metric", "application", "get-app-for-project-counted").increment(counter.incrementAndGet());

        try {
            List<ApplicationEntity> apps = applicationService.getAppsByProjectId(id);

            return ResponseEntity.ok(apps);
        } catch (RuntimeException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found", exc);
        }
    }

    @PostMapping(path = "/apply")
    @Timed(value = "hello.application.apply.time", description = "Time taken to apply for a project")
    @Counted(value = "hello.application.apply.counted", description = "Times applied")
    public ResponseEntity<?> createApplication(@RequestBody ApplicationDto applicationDto) {
//        metricsRegistry.counter("non_aop_metric", "application", "apply-counted").increment(counter.incrementAndGet());

        ApplicationEntity app = applicationService.apply(applicationDto);
        return ResponseEntity.ok(app);
    }

    //add exception
    @PutMapping("/accept/{id}")
    @Timed(value = "hello.application.accept.time", description = "Time taken to accept an apply")
    @Counted(value = "hello.application.accept.counted", description = "Times accepted applies")
    public ResponseEntity<?> acceptApplication(@PathVariable("id") String id) {
//        metricsRegistry.counter("non_aop_metric", "application", "accept-counted").increment(counter.incrementAndGet());

        applicationService.acceptApplicationById(id);
        String response = "Accepted " + id;
        return ResponseEntity.ok(response);
    }

    //add exception
    @PutMapping("/reject/{id}")
    @Timed(value = "hello.application.reject.time", description = "Time taken to reject an apply")
    @Counted(value = "hello.application.reject.counted", description = "Times rejected applies")
    public ResponseEntity<?> rejectApplication(@PathVariable("id") String id) {
//        metricsRegistry.counter("non_aop_metric", "application", "reject-counted").increment(counter.incrementAndGet());

        applicationService.rejectApplicationById(id);
        String response = "Rejected " + id;
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Timed(value = "hello.application.delete.time", description = "Time taken to delete an apply")
    @Counted(value = "hello.application.delete.counted", description = "Times deleted applies")
    public ResponseEntity<?> deleteApplicationById(@PathVariable("id") String id) {
//        metricsRegistry.counter("non_aop_metric", "application", "reject-counted").increment(counter.incrementAndGet());

        applicationService.deleteApplicationById(id);
        String response = "Deleted " + id;
        return ResponseEntity.ok(response);
    }

}