package com.pp.helpdesk.api;

import com.pp.helpdesk.model.application.Application;
import com.pp.helpdesk.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationApi {
    private ApplicationService applicationService;

    public ApplicationApi(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/save")
    public Application saveApplication(@RequestBody Application application){
        return applicationService.saveApplication(application);
    }

    @GetMapping("getAll")
    public List<Application> getAllApplications(){
        return applicationService.getAllCompanies();
    }

    @GetMapping("/getId")
    public Application getApplicationById(@RequestParam(name = "id") Long id){
        return applicationService.getApplicationById(id);
    }

    @DeleteMapping("delete")
    public String deleteApplication(@RequestParam(name="id") Long id){
        return applicationService.deleteApplication(id);
    }
}
