package com.gedc.entity;

public class Department {
    // @JsonIgnore
    private int id;
    // @JsonProperty("myName")
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

}
