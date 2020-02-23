package com.crud.mongodb.appmongodb;

import com.crud.mongodb.appmongodb.requests.DepartmentRequest;
import com.crud.mongodb.appmongodb.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping
    public ResponseEntity<Department> save(@RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(service.save(departmentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@RequestBody DepartmentRequest departmentRequest,
                                             @PathVariable("id") String departmentId) {
        return new ResponseEntity<Department>(
                service.update(departmentRequest, departmentId), HttpStatus.OK);
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
