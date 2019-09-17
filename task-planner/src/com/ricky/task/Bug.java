package com.ricky.task;

public class Bug extends Task {
  private String bugField;

  public Bug(Integer id, String title, String description) {
    super(id, title, description);
    this.bugField = "This is a bug!!";
  }

  @Override
  public String toString() {
    String s = super.toString();
    return s + " Bug{" + "bugField='" + bugField + '\'' + '}' + "\n";
  }
}
