package com.realtimeticketing.realtimetickeing.repository;

import com.realtimeticketing.realtimetickeing.model.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemConfigRepository extends JpaRepository<SystemConfig, Long> {
}