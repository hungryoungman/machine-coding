package com.ricky;

public class Thread {
  private int id;
  private boolean isFree;
  private int clockTime;
  private Job assignedJob;

  public Thread(int id) {
    this.id = id;
    isFree = false;
    clockTime = 0;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isFree() {
    return isFree;
  }

  public void setFree(boolean free) {
    isFree = free;
  }

  public Job getAssignedJob() {
    return assignedJob;
  }

  public void setAssignedJob(Job assignedJob) {
    this.assignedJob = assignedJob;
  }

  public int getClockTime() {
    return clockTime;
  }

  public void setClockTime(int clockTime) {
    this.clockTime = clockTime;
  }
}
