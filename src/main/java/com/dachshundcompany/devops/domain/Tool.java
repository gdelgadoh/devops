package com.dachshundcompany.devops.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Tool {

    @Id
    @GeneratedValue
    private Integer code;

    @Column
    private String name;

    @Column
    private String provider;

    protected Tool() {
    }

    public Tool (String name, String provider) {
        this.name = name;
        this.provider = provider;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getProvider() {
        return provider;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Tool{" + "code=" + code + ", name='" + name + '\'' + ", provider='" + provider + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tool tool = (Tool) o;
        return Objects.equals(code, tool.code) && Objects.equals(name, tool.name) && Objects.equals(provider, tool.provider);
    }


    @Override
    public int hashCode() {
        return Objects.hash(code, name, provider);
    }
}