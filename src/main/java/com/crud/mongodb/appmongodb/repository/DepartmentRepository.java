package com.crud.mongodb.appmongodb.repository;

import com.crud.mongodb.appmongodb.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    Optional<Department> findById(String id);
}
