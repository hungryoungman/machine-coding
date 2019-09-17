package com.ricky;

enum EmployeeType {
  CEO(0),
  MANAGER(1),
  EMPLOYEE(2);

  int value;

  private EmployeeType(int value) {
    this.value = value;
  }

  int getValue(EmployeeType employeeType) {
    return employeeType.value;
  }
}
