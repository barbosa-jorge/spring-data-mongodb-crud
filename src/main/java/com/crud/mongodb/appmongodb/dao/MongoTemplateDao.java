package com.crud.mongodb.appmongodb.dao;

import com.crud.mongodb.appmongodb.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class MongoTemplateDao {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DEPARTMENT = "department";

    @Autowired
    private MongoTemplate mongoTemplate;

    public Department save(Department department) {
        return mongoTemplate.save(department, DEPARTMENT);
    }

    public void delete(Department department) {
        mongoTemplate.remove(department, DEPARTMENT);
    }

    public boolean exists(Query query) {
        return mongoTemplate.exists(query, Department.class);
    }

    public Department findById(String departmentId) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where(ID).is(departmentId)), Department.class);
    }

    public Department findByEmployeeName(String employeeName) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("employees.name").regex("^"+employeeName)), Department.class);
    }

    public Department findByEmployeeNameAndAgeBetween(String employeeName, int minAge, int maxAge) {
        return mongoTemplate.findOne(
                Query.query(Criteria
                        .where("employees.name")
                        .regex("^"+employeeName)
                        .and("employees.age").gt(minAge).lt(maxAge)), Department.class);
    }

    public Department findByDepartmentName(String departmentName) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where(NAME).is(departmentName)), Department.class);
    }

    // Update first match only
    public void updateFirstFieldValue(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, Department.class);
    }

    // Update all matches
    public void updateMultiFieldValue(Query query, Update update) {
        mongoTemplate.updateMulti(query, update, Department.class);
    }

    // Update if found, otherwise insert.
    public void upsert(Query query, Update update) {
        mongoTemplate.upsert(query, update, Department.class);
    }

    // Modify if found, but returns the previous one, before updating
    public Department findAndModify(Query query, Update update) {
        return mongoTemplate.findAndModify(query, update, Department.class);
    }
}
