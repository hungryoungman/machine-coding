package com.ricky;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class ThreadManager {
  private int nThreads;
  private PriorityQueue<Thread> threadQueue;
  private HashMap<Integer, List<String>> threadIdToJobName;

  public ThreadManager(int nThreads) {
    this.nThreads = nThreads;
    threadIdToJobName = new HashMap<>();
    init();
  }

  private void init() {
    threadQueue = new PriorityQueue<>(nThreads, Comparator.comparing(Thread::getClockTime));
    for (int i = 1; i <= nThreads; i++) {
      threadQueue.add(new Thread(i));
      threadIdToJobName.put(i, new ArrayList<>());
    }
  }

  /*
  Work is to get free thread. Assign a job already selected by the scheduler.
  It manages only thread functions.
   */
  public void assignJobToFreeThread(Job job) throws Exception {
    Thread thread = threadQueue.poll();
    Job assignedJob = thread.getAssignedJob();
    if (assignedJob != null) {
      thread.setClockTime(thread.getClockTime() + assignedJob.getDuration());
    }

    if (thread.getClockTime() + job.getDuration() > job.getDeadline()) {
      throw new Exception(
          "No thread for Job deadline: "
              + job.getName()); // what is better to throw exception or to sout ?
    }

    thread.setAssignedJob(job);
    threadIdToJobName.get(thread.getId()).add(job.getName());
    threadQueue.add(thread);
  }

  public HashMap<Integer, List<String>> getThreadIdToJobName() {
    return threadIdToJobName;
  }
}
