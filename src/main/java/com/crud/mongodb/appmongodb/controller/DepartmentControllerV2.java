package com.crud.mongodb.appmongodb.controller;

import com.crud.mongodb.appmongodb.model.Department;
import com.crud.mongodb.appmongodb.request.DepartmentRequest;
import com.crud.mongodb.appmongodb.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departmentsV2")
public class DepartmentControllerV2 {

    @Autowired
    @Qualifier("mongoTemplateService")
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

    @GetMapping("/sorted-by")
    public ResponseEntity<List<Department>> getAllPageable(Pageable pageable) {
        return new ResponseEntity<>(service.findAllPageable(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") String departmentId) {
        return new ResponseEntity<>(service.findById(departmentId), HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<Department> getByEmployeeName(@RequestParam String name) {
        return new ResponseEntity<>(service.findByEmployeeName(name), HttpStatus.OK);
    }

    @GetMapping("/employee-by-name-and-age")
    public ResponseEntity<Department> getByEmployeeNameAndAge(@RequestParam String name,
                                                              @RequestParam Integer minAge,
                                                              @RequestParam Integer maxAge) {
        return new ResponseEntity<>(service.
                findByEmployeeNameAndAgeBetween(name, minAge,maxAge), HttpStatus.OK);
    }
}
