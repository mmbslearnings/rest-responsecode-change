package com.responsecode.change.controller.service;

import com.responsecode.change.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
    public Mono<Employee> getEmpById(int id) {
        Employee employee = new Employee(10, "Baba", 72);
        return Mono.just(employee);
    }

    public String saveEmployee(Employee employee, ServerHttpResponse response) {
        if (employee!=null && employee.getId()!=0) {
            System.out.println(employee);
            return "true";
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return "false";
        }
    }
}
