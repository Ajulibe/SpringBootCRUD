package com.ajulibe.java.SpringBootApi.service;

import com.ajulibe.java.SpringBootApi.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    public EmployeeEntity createEmployee(String empId, String fname, String sname) {
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmpId(empId);
        emp.setFirstName(fname);
        emp.setSecondName(sname);
        return emp;
    }

    public void deleteEmployee(String empId) {
    }
}