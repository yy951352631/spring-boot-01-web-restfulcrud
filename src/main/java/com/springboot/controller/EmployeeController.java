package com.springboot.controller;

import com.springboot.dao.DepartmentDao;
import com.springboot.dao.EmployeeDao;
import com.springboot.entities.Department;
import com.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Wtq
 * @date 2019/8/9 - 9:44
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @GetMapping(value = "/emps")
    public String list(Model model) {
        //查询所有员工，返回列表页面
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //来到添加员工页面
    @GetMapping(value = "/emp")
    public String toAddPage(Model model) {
        //查出所有的部门，在页面下拉框中显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    @PostMapping(value = "/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);
        //  '/'代表当前项目的路径
        return "redirect:/emps";
    }

    //来到我们的修改员工页面
    @GetMapping(value = "/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        //显示部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        return "emp/edit";
    }

    //这里用于受理我们的修改员工请求  ;需要提交员工id
    @PutMapping(value = "/emp")
    public String updateEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //这里来处理我们的删除请求
    @DeleteMapping(value = "/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
