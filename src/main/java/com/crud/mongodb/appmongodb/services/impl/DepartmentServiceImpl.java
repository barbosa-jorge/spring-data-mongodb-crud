package com.crud.mongodb.appmongodb.services.impl;

import com.crud.mongodb.appmongodb.model.Department;
import com.crud.mongodb.appmongodb.repository.DepartmentRepository;
import com.crud.mongodb.appmongodb.requests.DepartmentRequest;
import com.crud.mongodb.appmongodb.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department save(DepartmentRequest departmentRequest) {

        Department department = new Department();
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());
        department.setEmployees(departmentRequest.getEmployees());

        return repository.save(department);
    }

    @Override
    public Department update(DepartmentRequest departmentRequest, String id) {

        Department department = new Department();
        department.setId(id);
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());
        department.setEmployees(departmentRequest.getEmployees());

        return repository.save(department);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Department findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found!"));
    }
}
