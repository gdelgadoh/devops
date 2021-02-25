package com.dachshundcompany.devops.web.dto;

public class ToolDto {

    private String name;
    private String provider;

    public String getName() {
        return this.name;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setName(String name) {
        this.name = name;        
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

}
