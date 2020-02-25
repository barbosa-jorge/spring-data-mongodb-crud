package com.crud.mongodb.appmongodb.services;

import com.crud.mongodb.appmongodb.model.Department;
import com.crud.mongodb.appmongodb.requests.DepartmentRequest;

public interface DepartmentService {
    Department findById(String id);
    Department save(DepartmentRequest departmentRequest);
    Department update(DepartmentRequest departmentRequest, String id);
    void delete(String id);
}
