package com.crud.mongodb.appmongodb.service;

import com.crud.mongodb.appmongodb.model.Department;
import com.crud.mongodb.appmongodb.request.DepartmentRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    Department findById(String id);
    Department save(DepartmentRequest departmentRequest);
    Department update(DepartmentRequest departmentRequest);
    void delete(String id);
    Department findByEmployeeName(String employeeName);
    Department findByEmployeeNameAndAgeBetween(String employeeName, int minAge, int maxAge);
    List<Department> findAllPageable(Pageable pageable);
}
