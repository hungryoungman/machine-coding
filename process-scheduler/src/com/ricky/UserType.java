package com.ricky;

public enum UserType {
  ROOT(0, 3),
  ADMIN(1, 2),
  USER(2, 1);
  private int code, priority;

  UserType(int id, int priority) {
    this.code = id;
    this.priority = priority;
  }

  public int getPriority() {
    return priority;
  }
}
