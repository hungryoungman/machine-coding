package com.ricky.service;

import com.ricky.Ride;
import com.ricky.SearchType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class RideService {
  private static RideService rideService;
  List<Ride> rides;
  Map<Pair<Integer, Integer>, PriorityQueue<Ride>> odToFastestRides;
  Map<Pair<Integer, Integer>, PriorityQueue<Ride>> odToEarliestRides;

  private RideService() {
    rides = new ArrayList<>();
    odToFastestRides = new HashMap<>();
    odToEarliestRides = new HashMap<>();
  }

  public static RideService getInstance() {
    if (rideService == null) {
      rideService = new RideService();
    }
    return rideService;
  }

  public void offerRide(
      int driverId, int vehicleNumber, int origin, int dest, Long startTime, Long duration) {
    Ride ride = new Ride(driverId, vehicleNumber, origin, dest, startTime, duration);
    rides.add(ride);

    Pair<Integer, Integer> odPair = new Pair<>(origin, dest);
    odToFastestRides.computeIfAbsent(
        odPair,
        k -> new PriorityQueue<>(11, ComparatorService.getComparatorFor(SearchType.FASTEST)));
    odToFastestRides.get(odPair).add(ride);

    odToEarliestRides.computeIfAbsent(
        odPair,
        k -> new PriorityQueue<>(11, ComparatorService.getComparatorFor(SearchType.EARLIEST)));
    odToEarliestRides.get(odPair).add(ride);
  }

  public Ride selectRide(int riderId, int origin, int dest, SearchType searchType) {
    Ride ride = null;
    Long timeOfRequest = System.currentTimeMillis();

    if (SearchType.EARLIEST.equals(searchType)) {
      ride = getEarliestRide(origin, dest, timeOfRequest);
    } else if (SearchType.FASTEST.equals(searchType)) {
      ride = getFastestRide(origin, dest, timeOfRequest);
    }
    return ride;
  }

  private Ride getFastestRide(int origin, int dest, Long timeOfRequest) {
    PriorityQueue priorityQueue = odToFastestRides.get(new Pair<>(origin, dest));
    if (priorityQueue == null) {
      return null;
    }

    while (!priorityQueue.isEmpty()) {
      Ride ride = (Ride) priorityQueue.peek();
      if (ride.getCreatedAt() > timeOfRequest) {
        priorityQueue.poll();
      }
      return ride;
    }
    return null;
  }

  private Ride getEarliestRide(int origin, int dest, Long timeOfRequest) {
    PriorityQueue priorityQueue = odToEarliestRides.get(new Pair<>(origin, dest));

    if (priorityQueue == null) {
      return null;
    }

    while (!priorityQueue.isEmpty()) {
      Ride ride = (Ride) priorityQueue.peek();
      if (ride.getCreatedAt() > timeOfRequest) {
        priorityQueue.poll();
      }
      return ride;
    }
    return null;
  }
}
