package cg.codegym.minitest.model.dto;

import java.util.Set;

public class ComputerDTO {
    private Long id;
    private String name;
    private String code;
    private Set<String> types;

    public ComputerDTO(Long id, String code, String name, Set<String> types) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.types = types;
    }
    public ComputerDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<String> getTypes() {
        return types;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }
}
