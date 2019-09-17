package com.ricky.task;

public class Story extends Task {
  private String storyField;

  public Story(Integer id, String title, String description) {
    super(id, title, description);
    storyField = "This is a story!!";
  }

  public String getStoryField() {
    return storyField;
  }

  public void setStoryField(String storyField) {
    this.storyField = storyField;
  }

  @Override
  public String toString() {
    String s = super.toString();
    return s + " Story{" + "storyField='" + storyField + '\'' + '}' + "\n";
  }
}
