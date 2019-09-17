package com.ricky;

import com.ricky.service.EmployeeService;

/**
 * Create an employee database structure in which Employee has id, name, manager. Three
 * functionalities required were a) Given any id return all the employee details b) Given any
 * name(or id) list all the subordinates of the given employee. c) Given a name search with prefix
 * search property. 45 minutes of time was given and running code was asked. The interviewer first
 * asked the approach and then after coding, he reviewed the code thoroughly.
 */
public class Main {
  public static void main(String[] args) {
    EmployeeService employeeService = EmployeeService.getInstance();
    employeeService.set(new Employee(1, "america"));
    employeeService.set(new Employee(2, "b"));
    employeeService.set(new Employee(3, "ameresia"));
    employeeService.set(new Employee(4, "d"));
    employeeService.set(new Employee(5, "e"));

    /** Hierarchy a -> b; b -> c d; c -> e */
    employeeService.setManager(2, 1);
    employeeService.setManager(3, 2);
    employeeService.setManager(4, 2);
    employeeService.setManager(5, 3);

    System.out.println(employeeService.getCEO());
    System.out.println(employeeService.isCEO(3));
    System.out.println(employeeService.isCEO(1));
    System.out.println(employeeService.getDirectSubordinates(1));
    System.out.println(employeeService.getById(2));
    System.out.println(employeeService.searchByName("ame"));
    System.out.println(employeeService.getAllSubordinates(2));
    System.out.println(employeeService.getAllSubordinates(1));
  }
}
