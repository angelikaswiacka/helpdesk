package com.pp.helpdesk.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.pp.helpdesk.model.company.Company;
import com.pp.helpdesk.model.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(Company company){
     return companyRepository.save(company);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findByCompanyId(id);
    }

    public String deleteCompany(Long id) {
        Company companyToDelete = companyRepository.findByCompanyId(id);
        companyRepository.delete(companyToDelete);
        return "Company with id: " + id + " has been deleted.";
    }

    public String exportJson() {
        List<Company> companies = companyRepository.findAll();
        Gson gson = new Gson();
        String CompanyInJson = gson.toJson(companies);
        return CompanyInJson;
    }

    public List<Company> importJson(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Company>>() {
        }.getType();
        JsonReader reader = new JsonReader(new FileReader(file));
        List<Company> companies = gson.fromJson(reader, type);

        companies.forEach(company -> {
            if (company.getCompanyId() != null)
                company.setCompanyId(null);
        });

        return companyRepository.saveAll(companies);
    }
}
