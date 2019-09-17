package com.ricky;

import com.ricky.service.TaskManager;
import com.ricky.task.Bug;
import com.ricky.task.Feature;
import com.ricky.task.Story;

/**
 * Design a task planner. Different types of tasks are present – bug, feature and story. And their
 * attributes are given. Also a Sprint which is a collection of tasks. A person should be able to
 * create a Sprint, add a task to Sprint, change assignee or status of task, show all tasks of a
 * particular Sprint and show all tasks assigned to a user, etc.
 */
public class Main {
  public static void main(String[] args) {
    TaskManager taskManager = TaskManager.getInstance();
    Integer sprintId = taskManager.createSprint(1);
    taskManager.addTaskToSprint(sprintId, new Bug(11, "bt", "bd"));
    taskManager.addTaskToSprint(sprintId, new Feature(12, "ft", "fd"));

    Integer sprintId2 = taskManager.createSprint(1);
    taskManager.addTaskToSprint(sprintId2, new Bug(21, "bt2", "bd2"));
    taskManager.addTaskToSprint(sprintId2, new Feature(22, "ft2", "fd2"));
    taskManager.addTaskToSprint(sprintId2, new Story(23, "st2", "sd2"));

    taskManager.changeStatusForTask(11, TaskStatus.CLOSED);
    taskManager.changeStatusForTask(23, TaskStatus.CLOSED);

    taskManager.setAssigneeForTask(11, 3);
    taskManager.setAssigneeForTask(22, 3);

    System.out.println(taskManager.showSprintTasks(sprintId));
    System.out.println(taskManager.showSprintTasks(sprintId2));

    System.out.println(taskManager.getAllTasksAssignedTo(3));

    System.out.println(taskManager.getAllSprintsAssignedBy(1));
  }
}
