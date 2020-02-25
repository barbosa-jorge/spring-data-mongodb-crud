package com.crud.mongodb.appmongodb.service.impl;

import com.crud.mongodb.appmongodb.dao.MongoTemplateDao;
import com.crud.mongodb.appmongodb.model.Department;
import com.crud.mongodb.appmongodb.request.DepartmentRequest;
import com.crud.mongodb.appmongodb.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("mongoTemplateService")
public class DepartmentServiceUsingMongoTemplateImpl implements DepartmentService {

    @Autowired
    private MongoTemplateDao mongoTemplateDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Department save(DepartmentRequest departmentRequest) {
        validateMandatoryFields(departmentRequest);
        return mongoTemplateDao.save(mapToDepartmentDocument(departmentRequest));
    }

    @Override
    public Department update(DepartmentRequest departmentRequest) {

        // Check department exists
        validateDepartment(departmentRequest.getId());

        validateMandatoryFields(departmentRequest);

        return mongoTemplateDao.save(mapToDepartmentDocument(departmentRequest));
    }

    @Override
    public void delete(String id) {
       Department department = mongoTemplateDao.findById(id);
       mongoTemplateDao.delete(department);
    }

    @Override
    public Department findById(String id) {
        return mongoTemplateDao.findById(id);
    }

    @Override
    public Department findByEmployeeName(String employeeName) {
        return mongoTemplateDao.findByEmployeeName(employeeName);
    }

    public Department findByEmployeeNameAndAgeBetween(String employeeName, int minAge, int maxAge) {
        return mongoTemplateDao.findByEmployeeNameAndAgeBetween(employeeName, minAge, maxAge);
    }

    @Override
    public List<Department> findAllPageable(Pageable pageable) {
        return mongoTemplateDao.findAllPageable(pageable);
    }

    private Department mapToDepartmentDocument(DepartmentRequest departmentRequest) {
       return modelMapper.map(departmentRequest, Department.class);
    }

    private void validateDepartment(String departmentId) {
        boolean exists = mongoTemplateDao.exists(new Query(Criteria.where("id").is(departmentId)));
        if (!exists) {
            throw new RuntimeException("Department not found!");
        }
    }

    private void validateMandatoryFields(DepartmentRequest departmentRequest) {
        if (StringUtils.isEmpty(departmentRequest.getName())) {
            throw new RuntimeException("Name must not be empty!");
        }
    }
}
