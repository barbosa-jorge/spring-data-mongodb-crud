package com.crud.mongodb.appmongodb.request;

import com.crud.mongodb.appmongodb.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {

    private String id;
    private String name;
    private String description;
    private List<Employee> employees;

}
