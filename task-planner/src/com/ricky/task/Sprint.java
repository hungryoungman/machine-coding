package com.ricky.task;

import java.util.ArrayList;
import java.util.List;

public class Sprint {
  private Integer id;
  private List<Task> taskList;

  public Sprint(Integer id) {
    this.id = id;
    taskList = new ArrayList<>();
  }

  public void addTask(Task task) {
    taskList.add(task);
  }

  public List<Task> getTaskList() {
    return taskList;
  }

  @Override
  public String toString() {
    return "Sprint{" + "id=" + id + ", taskList=" + taskList + '}' + "\n";
  }
}
