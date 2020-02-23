package com.crud.mongodb.appmongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    private String id;
    @Indexed(name = "departmentName")
    private String name;
    private String description;
    private List<Employee> employees;

}
