package com.ricky;

public enum Priority {
  P2(1),
  P1(2),
  P0(3);

  private int priority; // give this other name like code etc. its my fuckup for you.

  Priority(int priority) {
    this.priority = priority;
  }

  public int getPriority() {
    return priority;
  }
}
