package com.crud.mongodb.appmongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("department")
@Data
public class Department {

    @Id
    private String id;
    @Indexed(name = "departmentName")
    private String name;
    private String description;
    private List<Employee> employees;

}
