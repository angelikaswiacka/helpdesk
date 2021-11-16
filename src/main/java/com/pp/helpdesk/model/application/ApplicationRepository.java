package com.pp.helpdesk.model.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository <Application, Long> {
    Application findByApplicationId(Long id);
}
