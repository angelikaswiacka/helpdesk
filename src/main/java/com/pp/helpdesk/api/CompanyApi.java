package com.pp.helpdesk.api;

import com.pp.helpdesk.model.company.Company;
import com.pp.helpdesk.service.CompanyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public Company getCompanyById(@RequestParam(name = "id") Long id) {
        return companyService.getCompanyById(id);
    }

    @DeleteMapping("/delete")
    public String deleteCompany(@RequestParam(name = "id") Long id) {
        return companyService.deleteCompany(id);
    }

    @GetMapping("/exportJson")
    public ResponseEntity<byte[]> exportJsonFile() throws IOException {
        String companyJsonString = companyService.exportJson();

        byte[] companyJsonBytes = companyJsonString.getBytes();

        File outputFile = new File("exported.json");
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(companyJsonBytes);
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=customers.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(companyJsonBytes.length)
                .body(companyJsonBytes);
    }

    @PostMapping("/importJson")
    public List<Company> importJsonFile() throws FileNotFoundException {
        File exported = new File("exported.json");

        return companyService.importJson(exported);
    }
}
