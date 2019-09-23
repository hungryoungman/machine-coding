package com.ricky;

public class Job {
  private String name;
  private int duration;
  private Priority priority;
  private int deadline;
  private UserType userType;

  public Job(String name, int duration, Priority priority, int deadline, UserType userType) {
    this.name = name;
    this.duration = duration;
    this.priority = priority;
    this.deadline = deadline;
    this.userType = userType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public int getDeadline() {
    return deadline;
  }

  public void setDeadline(int deadline) {
    this.deadline = deadline;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  @Override
  public String toString() {
    return "Job{" + "name='" + name + '\'' + '}';
  }
}
