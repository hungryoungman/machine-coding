package com.ricky.service;

import com.ricky.Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {
  private static EmployeeService employeeService;
  private Map<Integer, Employee> idToEmployee;
  private Map<Integer, List<Integer>> managerToSubordinates;
  private Map<Integer, Integer> employeeToManager;

  private EmployeeService() {
    idToEmployee = new HashMap<>();
    managerToSubordinates = new HashMap<>();
    employeeToManager = new HashMap<>();
  }

  public static EmployeeService getInstance() {
    if (employeeService == null) {
      employeeService = new EmployeeService();
    }
    return employeeService;
  }

  public void set(Employee employee) {
    idToEmployee.put(employee.getId(), employee);
  }

  public Employee getById(Integer id) {
    return idToEmployee.get(id);
  }

  public void setManager(Integer employeeId, Integer managerId) {
    managerToSubordinates.computeIfAbsent(managerId, k -> new ArrayList<>());
    managerToSubordinates.get(managerId).add(employeeId);

    employeeToManager.put(employeeId, managerId);
  }

  public List<Employee> getDirectSubordinates(Integer managerId) {
    List<Employee> employees = new ArrayList<>();
    List<Integer> subordinatesIds = managerToSubordinates.get(managerId);
    subordinatesIds.forEach(id -> employees.add(idToEmployee.get(id)));
    return employees;
  }

  public Boolean isCEO(Integer employeeId) {
    return !employeeToManager.containsKey(employeeId);
  }

  public Integer getCEO() {
    return idToEmployee.keySet().stream()
        .filter(id -> !employeeToManager.containsKey(id))
        .collect(Collectors.toList())
        .get(0);
  }

  public List<Employee> searchByName(String name) {
    return idToEmployee.values().stream()
        .filter(employee -> employee.getName().startsWith(name))
        .collect(Collectors.toList());
  }

  public List<Employee> getAllSubordinates(Integer managerId) {
    List<Integer> employeeIds = new ArrayList<>();

    dfsSubordinates(managerId, employeeIds);
    return employeeIds.stream().map(id -> idToEmployee.get(id)).collect(Collectors.toList());
  }

  private void dfsSubordinates(Integer managerId, List<Integer> employeeIds) {
    if (managerToSubordinates.get(managerId) == null) {
      return;
    }
    employeeIds.addAll(managerToSubordinates.get(managerId));
    managerToSubordinates.get(managerId).forEach(id -> dfsSubordinates(id, employeeIds));
  }
}
