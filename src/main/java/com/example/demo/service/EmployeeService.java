package com.example.demo.service;



import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertToDTO).toList();
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(this::convertToDTO);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setEmployeeNumber(employeeDetails.getEmployeeNumber());
        employee.setInitials(employeeDetails.getInitials());
        employee.setLastName(employeeDetails.getLastName());
        employee.setDesignation(employeeDetails.getDesignation());
        employee.setActiveStatus(employeeDetails.isActiveStatus());
        return convertToDTO(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getEmployeeNumber(), employee.getInitials(), employee.getLastName(), employee.getDesignation(), employee.isActiveStatus());
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setEmployeeNumber(employeeDTO.getEmployeeNumber());
        employee.setInitials(employeeDTO.getInitials());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setActiveStatus(employeeDTO.isActiveStatus());
        return employee;
    }
}
