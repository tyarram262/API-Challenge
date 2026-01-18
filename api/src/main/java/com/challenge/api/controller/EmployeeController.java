package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee models as necessary.
     * @return One or more Employees.
     */

    //Creating mock Employee models for test reference
    private final List<Employee> employees = new ArrayList<>();


    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        for(Employee employee : employees){
            if(employee.getUuid().equals(uuid)){
                return employee;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with this uuid does not exist");
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param requestBody hint!
     * @return Newly created Employee
     */
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        try{
            employee.setUuid(UUID.randomUUID()); //included in the imported UUID class
            employees.add(employee);
            return employee;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong while creating your employee. Double check all the appropriate information is included and try again.");
        }
    }
}


/*
    Include in the documentation a quick guide of each request:

    All requests should be routed to the endpoint /api/v1/employee!!

    Post request should include employee data in the request in the following form:
    {
  "firstName": "Tan",
  "lastName": "Yar",
  "fullName": "Tan Yar",
  "salary": 90000,
  "age": 20,
  "jobTitle": "Software Engineer",
  "email": "tan.yar@company.com",
  "contractHireDate": "01-28-2026"
}

Get Request (with uuid) will return an employee if found and throw an error if none exist with that uuid.

*/