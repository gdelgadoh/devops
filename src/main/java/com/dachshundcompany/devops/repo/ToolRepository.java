package com.dachshundcompany.devops.repo;

import com.dachshundcompany.devops.domain.Tool;

import org.springframework.data.repository.CrudRepository;

public interface ToolRepository extends CrudRepository<Tool, Integer> {
    
}
