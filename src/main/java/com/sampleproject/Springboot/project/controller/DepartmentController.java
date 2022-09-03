package com.sampleproject.Springboot.project.controller;

import com.sampleproject.Springboot.project.entity.Department;
import com.sampleproject.Springboot.project.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("inside the save department in DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList() {
        LOGGER.info("inside the fetchDepartmentList in DepartmentController");
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) {
        LOGGER.info("inside the fetchDepartmentById in DepartmentController");
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        LOGGER.info("inside the deleteDepartmentById in DepartmentController");
        departmentService.deleteDepartmentById(departmentId);
        return "department deleted successfully!!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody String departmentName) {
        Department department = departmentService.fetchDepartmentById(departmentId);
        department.setDepartmentName(departmentName);
        return departmentService.saveDepartment(department);
    }

    @PostMapping("/departments/saveAll/{size}")
    public void createBulkDepartments(@PathVariable("size") Long size) {
        LOGGER.info("inside the createBulkDepartments in DepartmentController");
        for (int i = 0; i < size; i++) {
            Department department = new Department();
            department.setDepartmentName("cse "+i);
            department.setDepartmentCode(""+(1000+i));
            department.setDepartmentAddress("Delhi-hq"+i);
            departmentService.saveDepartment(department);
        }
    }

    @PostMapping("/departments/saveAll")
    public void createBulkDepartments(@RequestBody List<Department> departments) {
        LOGGER.info("inside the createBulkDepartments in DepartmentController");
        for (Department department : departments) {
            departmentService.saveDepartment(department);
        }
    }

}
