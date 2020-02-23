package com.crud.mongodb.appmongodb.service.impl;

import com.crud.mongodb.appmongodb.model.Department;
import com.crud.mongodb.appmongodb.repository.DepartmentRepository;
import com.crud.mongodb.appmongodb.request.DepartmentRequest;
import com.crud.mongodb.appmongodb.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Department save(DepartmentRequest departmentRequest) {
        validateMandatoryFields(departmentRequest);
        return repository.save(mapToDepartmentDocument(departmentRequest));
    }

    @Override
    public Department update(DepartmentRequest departmentRequest) {

        // Check department exists
        validateDepartment(departmentRequest.getId());

        validateMandatoryFields(departmentRequest);

        return repository.save(mapToDepartmentDocument(departmentRequest));
    }

    @Override
    public void delete(String id) {

        // Check department exists
        validateDepartment(id);

        repository.deleteById(id);
    }

    @Override
    public Department findByEmployeeName(String employeeName) {
        return null;
    }

    @Override
    public Department findByEmployeeNameAndAgeBetween(String employeeName, int minAge, int maxAge) {
        return null;
    }

    @Override
    public Department findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found!"));
    }

    public Department findByName(String name) {
        return null;
    }

    private Department mapToDepartmentDocument(DepartmentRequest departmentRequest) {
       return modelMapper.map(departmentRequest, Department.class);
    }

    private void validateDepartment(String departmentId) {
        repository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found!"));
    }

    private void validateMandatoryFields(DepartmentRequest departmentRequest) {
        if (StringUtils.isEmpty(departmentRequest.getName())) {
            throw new RuntimeException("Name must not be empty!");
        }
    }
}
