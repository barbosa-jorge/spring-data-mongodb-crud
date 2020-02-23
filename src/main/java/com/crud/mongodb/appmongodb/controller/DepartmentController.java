package com.crud.mongodb.appmongodb.controller;

import com.crud.mongodb.appmongodb.model.Department;
import com.crud.mongodb.appmongodb.request.DepartmentRequest;
import com.crud.mongodb.appmongodb.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService service;

    @PostMapping
    public ResponseEntity<Department> save(@RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(service.save(departmentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@RequestBody DepartmentRequest departmentRequest,
                                             @PathVariable("id") String departmentId) {

        departmentRequest.setId(departmentId);
        return new ResponseEntity(service.save(departmentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String departmentId) {
        service.delete(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") String departmentId) {
        return new ResponseEntity<>(service.findById(departmentId), HttpStatus.OK);
    }
}
