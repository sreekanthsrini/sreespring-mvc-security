package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }


    // add a maping for list of employee

    @GetMapping("/list")
    public String listEmployees(Model theModel){
        // get the employee from the db
        List<Employee> theEmployees=employeeService.findAll();




        theModel.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Employee theEmployee= new Employee();
        theModel.addAttribute("employee",theEmployee);
        return "employees/employee-form";
    }

    // add code for updfate page
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){
        // get the employee from service
        Employee thEmployee=employeeService.findById(theId);
        // set employye inn the model to populete the form
        theModel.addAttribute("employee", thEmployee);
        // send over to the form
        return "employees/employee-form";
    }
    
    // code for delete the emploeyee
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        // delete the employee
        employeeService.deleteById(theId);
        // redirect to the employee list
        return "redirect:/employees/list";


    }








    // this code is used to save the employee to the data base
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save the employee
        employeeService.save(theEmployee);

        // use redirect to prevent duplicate submition
        return "redirect:/employees/list";
    }
    
}
