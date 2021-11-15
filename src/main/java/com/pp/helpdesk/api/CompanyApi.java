package com.pp.helpdesk.api;

import com.pp.helpdesk.model.company.Company;
import com.pp.helpdesk.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")

public class CompanyApi {
    private CompanyService companyService;

    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/saveTest")
    public Company saveCompanyTest() {

        Company companyTestName = new Company("Margarteka", "Zielna 7", "66-606", "Polska", "6653043087", "933046008");
        return companyService.saveCompany(companyTestName);
    }

    @PostMapping("/save")
    public Company saveCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @GetMapping("/getAll")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/getId")
    public Company getCompanyById(@RequestParam(name = "id") Long id){
        return companyService.getCompanyById(id);
    }

    @DeleteMapping("/delete")
    public String deleteCompany(@RequestParam(name = "id") Long id){
        return companyService.deleteCompany(id);
    }
}
