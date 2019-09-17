package com.ricky.service;

import com.ricky.TaskStatus;
import com.ricky.task.Sprint;
import com.ricky.task.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskManager {
  private static TaskManager taskManager;
  Map<Integer, List<Integer>> userIdToSprintIds;
  Map<Integer, Sprint> sprintIdToSprint;
  Map<Integer, Task> taskIdToTask;
  Map<Integer, Integer> taskIdToAssigneeId;

  private TaskManager() {
    userIdToSprintIds = new HashMap<>();
    sprintIdToSprint = new HashMap<>();
    taskIdToTask = new HashMap<>();
    taskIdToAssigneeId = new HashMap<>();
  }

  public static TaskManager getInstance() {
    if (taskManager == null) {
      taskManager = new TaskManager();
    }
    return taskManager;
  }

  public Integer createSprint(Integer userId) {
    if (userIdToSprintIds.get(userId) == null) {
      userIdToSprintIds.put(userId, new ArrayList<>());
    }
    Integer sprintId = UUID.randomUUID().hashCode(); // will fail on collision
    Sprint sprint = new Sprint(sprintId);
    userIdToSprintIds.get(userId).add(sprintId);
    sprintIdToSprint.put(sprintId, sprint);
    return sprintId;
  }

  public void addTaskToSprint(Integer sprintId, Task task) {
    if (sprintIdToSprint.get(sprintId) == null) {
      System.out.println("No sprint for " + sprintId);
      return;
    }
    taskIdToTask.put(task.getId(), task);
    sprintIdToSprint.get(sprintId).addTask(task);
  }

  public void setAssigneeForTask(Integer taskId, Integer assigneeId) {
    taskIdToAssigneeId.put(taskId, assigneeId);
  }

  public void changeStatusForTask(Integer taskId, TaskStatus status) {
    taskIdToTask.get(taskId).setStatus(status);
  }

  public List<Task> showSprintTasks(Integer sprintId) {
    return sprintIdToSprint.get(sprintId).getTaskList();
  }

  public List<Task> getAllTasksAssignedTo(Integer assigneeId) {
    return taskIdToAssigneeId.keySet().stream()
        .filter(id -> Objects.equals(taskIdToAssigneeId.get(id), assigneeId))
        .map(id -> taskIdToTask.get(id))
        .collect(Collectors.toList());
  }

  public List<Sprint> getAllSprintsAssignedBy(Integer assignorId) {
    return userIdToSprintIds.get(assignorId).stream()
        .map(id -> sprintIdToSprint.get(id))
        .collect(Collectors.toList());
  }
}
