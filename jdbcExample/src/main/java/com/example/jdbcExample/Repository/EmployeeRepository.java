package com.example.jdbcExample.Repository;

import com.example.jdbcExample.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EmployeeRepository  {
    Employee findById(long id);
    Employee save(Employee employee);
    String update(Employee employee);
    List<Employee> findAll();
    List<Employee>findByDepartmentOrAddress(String department,String Address);
    List<Employee>getPage(int pageNo,int pageSize);
    List<Employee>BulkSave(List<Employee>employees);
    Employee BulSave(List<Employee> emp);
}
