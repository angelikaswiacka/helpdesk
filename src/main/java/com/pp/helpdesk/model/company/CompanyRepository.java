package com.pp.helpdesk.model.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Long>{
    Company findByCompanyId(Long id);
}
