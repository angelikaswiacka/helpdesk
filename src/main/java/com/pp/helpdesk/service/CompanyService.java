package com.pp.helpdesk.service;

import com.pp.helpdesk.model.company.Company;
import com.pp.helpdesk.model.company.CompanyRepository;
import org.springframework.stereotype.Service;

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

    public Company getCompanyById(Long id){
        return companyRepository.findByCompanyId(id);
    }

    public String deleteCompany(Long id){
        Company companyToDelete = companyRepository.findByCompanyId(id);
        companyRepository.delete(companyToDelete);
        return "Company with id: " + id + " has been deleted.";
    }
}
