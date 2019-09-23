package com.ricky;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Scheduler {
  private static Scheduler scheduler;

  private Scheduler() {}

  public static Scheduler getInstance() {
    if (scheduler == null) {
      scheduler = new Scheduler();
    }
    return scheduler;
  }

  public void fcfs(List<Job> jobs, int nThreads) {
    ThreadManager threadManager = new ThreadManager(nThreads);
    jobs.forEach(
        job -> {
          try {
            threadManager.assignJobToFreeThread(job);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        });
    System.out.println("FCFS");
    printJobs(threadManager.getThreadIdToJobName());
    System.out.println();
  }

  public void sjf(List<Job> jobs, int threads) {
    PriorityQueue<Job> jobPriorityQueue = initSjfQ(jobs, threads);
    ThreadManager threadManager = new ThreadManager(threads);
    while (!jobPriorityQueue.isEmpty()) {
      try {
        threadManager.assignJobToFreeThread(jobPriorityQueue.poll());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println("SJF");
    printJobs(threadManager.getThreadIdToJobName());
    System.out.println();
  }

  private PriorityQueue<Job> initSjfQ(List<Job> jobs, int threads) {
    PriorityQueue<Job> jobPriorityQueue =
        new PriorityQueue<>(
            11,
            new Comparator<Job>() {
              @Override
              public int compare(Job j1, Job j2) {
                if (j1.getDuration() == j2.getDuration()) {
                  return j2.getPriority().getPriority() - j1.getPriority().getPriority();
                }
                return j1.getDuration() - j2.getDuration();
              }
            });
    jobs.forEach(job -> jobPriorityQueue.add(job));
    return jobPriorityQueue;
  }

  public void fps(List<Job> jobs, int threads) {
    PriorityQueue<Job> jobPriorityQueue = initFpsQ(jobs, threads);
    ThreadManager threadManager = new ThreadManager(threads);
    while (!jobPriorityQueue.isEmpty()) {
      try {
        threadManager.assignJobToFreeThread(jobPriorityQueue.poll());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println("FPS");
    printJobs(threadManager.getThreadIdToJobName());
    System.out.println();
  }

  private PriorityQueue<Job> initFpsQ(List<Job> jobs, int threads) {
    PriorityQueue<Job> jobPriorityQueue =
        new PriorityQueue<>(
            11,
            new Comparator<Job>() {
              @Override
              public int compare(Job j1, Job j2) {
                if (j1.getPriority() == j2.getPriority()) {
                  if (j1.getUserType() == j2.getUserType()) {
                    return j2.getDuration() - j1.getDuration();
                  }
                  return j2.getUserType().getPriority() - j1.getUserType().getPriority();
                }
                return j2.getPriority().getPriority() - j1.getPriority().getPriority();
              }
            });
    jobs.forEach(job -> jobPriorityQueue.add(job));
    return jobPriorityQueue;
  }

  public void edf(List<Job> jobs, int threads) {
    PriorityQueue<Job> jobPriorityQueue = initEdfQ(jobs, threads);
    ThreadManager threadManager = new ThreadManager(threads);
    while (!jobPriorityQueue.isEmpty()) {
      try {
        threadManager.assignJobToFreeThread(jobPriorityQueue.poll());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println("EDF");
    printJobs(threadManager.getThreadIdToJobName());
    System.out.println();
  }

  private PriorityQueue<Job> initEdfQ(List<Job> jobs, int threads) {
    PriorityQueue<Job> jobPriorityQueue =
        new PriorityQueue<>(
            11,
            new Comparator<Job>() {
              @Override
              public int compare(Job j1, Job j2) {
                int j1Closing = j1.getDeadline() - j1.getDuration();
                int j2Closing = j2.getDeadline() - j2.getDuration();

                if (j1Closing == j2Closing) {
                  if (j1.getPriority().getPriority() == j2.getPriority().getPriority()) {
                    return j1.getDuration() - j2.getDuration();
                  }
                  return j2.getPriority().getPriority() - j1.getPriority().getPriority();
                }
                return j1Closing - j2Closing;
              }
            });
    jobs.forEach(job -> jobPriorityQueue.add(job));
    return jobPriorityQueue;
  }

  private void printJobs(Map<Integer, List<String>> threadToJobs) {
    for (Integer i : threadToJobs.keySet()) {
      System.out.println("Thread " + i);
      threadToJobs.get(i).forEach(x -> System.out.print(x + " "));
      System.out.println();
    }
  }
}
