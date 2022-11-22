package com.responsecode.change.controller;

import com.responsecode.change.controller.service.EmployeeService;
import com.responsecode.change.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * This is a Rest controller for Employee
 */
@RestController
public class EmployeeController {

    /**
     * EmployeeService instance
     */
    @Autowired
    private EmployeeService service;

    /**
     *
     * @param id employee id
     * @return Mono of Employee
     */
    @GetMapping("/employee/{id}")
    public Mono<Employee> getEmpById(@PathVariable("id") int id) {
        return service.getEmpById(id);
    }

    /**
     *
     * @param employee Employee object
     * @param response ServerHttpResponse -> this is used to set the status code conditionally
     * @return true or false
     */
    @PostMapping("/employee/save")
    public String saveEmployee(@RequestBody Employee employee, ServerHttpResponse response) {
        return service.saveEmployee(employee, response);
    }

}
