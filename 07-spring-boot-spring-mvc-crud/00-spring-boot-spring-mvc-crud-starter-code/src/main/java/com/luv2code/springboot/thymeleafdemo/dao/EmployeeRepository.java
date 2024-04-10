package com.luv2code.springboot.thymeleafdemo.dao;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!
    // ADD A METHOD TO SORT BY LAST NAME
    public List<Employee> findAllByOrderByLastNameAsc();


}
