package com.ricky;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Scheduler scheduler = Scheduler.getInstance();
    List<Job> jobs = new ArrayList<>();
    jobs.add(new Job("J1", 10, Priority.P0, 10, UserType.ROOT));
    jobs.add(new Job("J2", 20, Priority.P0, 40, UserType.ADMIN));
    jobs.add(new Job("J3", 15, Priority.P2, 40, UserType.ROOT));
    jobs.add(new Job("J4", 30, Priority.P1, 40, UserType.USER));
    jobs.add(new Job("J5", 10, Priority.P2, 30, UserType.USER));

    System.out.println(jobs);

    int nThreads = 2;
    scheduler.sjf(jobs, nThreads);
    scheduler.fcfs(jobs, nThreads);
    scheduler.fps(jobs, nThreads);
    scheduler.edf(jobs, nThreads);
  }
}
