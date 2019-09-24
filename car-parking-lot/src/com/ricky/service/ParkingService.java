package com.ricky.service;

import com.ricky.entity.ParkingLevel;
import com.ricky.entity.ParkingSpot;
import com.ricky.entity.Vehicle;
import java.util.List;
import java.util.Map;

public class ParkingService {
  private static ParkingService parkingService;
  private Map<Integer, ParkingLevel> levelMap;
  private int levels;

  private ParkingService(int levels) {
    this.levels = levels;
    for (int i = 0; i < levels; i++) {
      levelMap.put(i, new ParkingLevel(i));
    }
  }

  public List<ParkingSpot> parkVehicle(Vehicle vehicle) {
    return null;
  }

  public List<ParkingSpot> getVehicle(int vehicleId) {
    return null;
  }

  public int totalVehiclesParkedInLevel(int level) {
    return 0;
  }

  public void printParkingViewOfLevel(int level) {
    return;
  }
}
