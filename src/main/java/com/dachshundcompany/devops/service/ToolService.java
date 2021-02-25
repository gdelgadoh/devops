package com.dachshundcompany.devops.service;

import java.util.Optional;

import com.dachshundcompany.devops.domain.Tool;
import com.dachshundcompany.devops.repo.ToolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolService {

    private ToolRepository toolRepository;

    @Autowired
    public ToolService (ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    /**
     * 
     * @param name
     * @param provider
     * @return
     */
    public Tool createTool(String name, String provider) {
        return toolRepository.save(new Tool(name, provider));
    }

    /**
     * 
     * @return
     */
    public Iterable<Tool> findAll() {
        return toolRepository.findAll();
    }

    /**
     * 
     * @param id
     * @return
     */
    public Tool findById(Integer id) {

        Tool tool = null;
        Optional <Tool>  response = toolRepository.findById(id);
        
        if  (response.isPresent()) {
            tool = response.get();
        }
        return tool;
    }

    public void deleteToolById(Integer id) {
        toolRepository.deleteById(id);
    }
}
