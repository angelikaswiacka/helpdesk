package com.pp.helpdesk.service;

import com.pp.helpdesk.model.application.Application;
import com.pp.helpdesk.model.application.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }
    public Application saveApplication(Application application){
        return applicationRepository.save(application);
    }

    public List<Application> getAllCompanies(){
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id){
        return applicationRepository.findByApplicationId(id);
    }

    public String deleteApplication(Long id){
        Application applicationToDelete = applicationRepository.findByApplicationId(id);
        applicationRepository.delete(applicationToDelete);
        return "Application with id: " + id + " has been deleted.";

    }
}
