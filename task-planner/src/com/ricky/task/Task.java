package com.ricky.task;

import com.ricky.TaskStatus;

public abstract class Task {
  private Integer id;
  private String title;
  private String description;
  private TaskStatus status;

  public Task(Integer id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = TaskStatus.OPEN;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Task{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", status="
        + status
        + '}';
  }
}
