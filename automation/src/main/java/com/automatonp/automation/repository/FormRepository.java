package com.automatonp.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatonp.automation.entity.FormEntity;

@Repository
public interface FormRepository extends JpaRepository < FormEntity, Long > {
//	FormEntity findByEmail(String email);
}
