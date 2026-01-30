package com.challenge.api;

import org.springframework.stereotype.Service;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;


@Service
public class EmployeeService{
    private List<Employee> employees = new ArrayList<>();

    public Employee createEmployee(EmployeeImpl employee){
        employee.setUuid(UUID.randomUUID());
        employees.add(employee);
        return employee;
    }

    public List<Employee> getAllEmployees(){
        return employees;
    }

    public Employee getEmployeeByUUID(UUID uuid){
        for(Employee employee : employees){
            if(employee.getUuid().equals(uuid)){
                return employee;
            }
        }
        return null;
    }
}