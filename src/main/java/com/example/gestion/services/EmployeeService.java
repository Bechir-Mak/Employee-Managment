package com.example.gestion.services;

import com.example.gestion.exception.UserNotFoundException;
import com.example.gestion.model.Employee;
import com.example.gestion.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepo employeeRepo ;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List <Employee> findAllEmployee(){
        return  employeeRepo.findAll() ;

    }

    public Employee updateEmployee(Employee employee){
        Employee existedEmployee = employeeRepo.findEmployeeById(employee.getId()).orElse(null);
        existedEmployee.setName(employee.getName());
        existedEmployee.setEmail(employee.getEmail());
        existedEmployee.setPhone(employee.getPhone());
        existedEmployee.setImageUrl(employee.getImageUrl());
        existedEmployee.setJobTitle(employee.getJobTitle());
        return employeeRepo.save(existedEmployee);

    }

   public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id){

        employeeRepo.deleteEmployeeById(id);
    }
}
