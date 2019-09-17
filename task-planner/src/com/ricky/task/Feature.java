package com.ricky.task;

public class Feature extends Task {
  private String featureField;

  public Feature(Integer id, String title, String description) {
    super(id, title, description);
    featureField = "This is a feature!!";
  }

  @Override
  public String toString() {
    String s = super.toString();
    return s + " Feature{" + "featureField='" + featureField + '\'' + '}' + "\n";
  }
}
