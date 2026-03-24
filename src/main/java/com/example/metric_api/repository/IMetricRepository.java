package com.example.metric_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.metric_api.model.SystemLog;

public interface IMetricRepository extends JpaRepository<SystemLog, Long>{

}
