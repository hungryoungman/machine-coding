package com.ricky.service;

import com.ricky.entity.ParkingLevel;
import com.ricky.entity.ParkingSpot;
import com.ricky.entity.SpotType;
import com.ricky.entity.Vehicle;
import com.ricky.entity.VehicleType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParkingService {
  private static ParkingService parkingService;
  private Map<Integer, ParkingLevel> levelMap;
  private int levels;
  private Map<Integer, List<ParkingSpot>> vehicleIdToParkingSpots;

  private ParkingService(int levels) {
    this.levels = levels;
    for (int i = 0; i < levels; i++) {
      levelMap.put(i, new ParkingLevel(i));
    }
    vehicleIdToParkingSpots = new HashMap<>();
  }

  public static ParkingService getInstance(int levels) {
    if (parkingService == null) {
      parkingService = new ParkingService(levels);
    }
    return parkingService;
  }

  // completed
  public void parkVehicle(Vehicle vehicle) {
    for (int i = 0; i < levels; i++) {
      ParkingLevel parkingLevel = levelMap.get(i);
      boolean vehicleParked = parkVehicleOnLevel(parkingLevel, vehicle);
      if (vehicleParked) {
        return;
      }
    }
    System.out.println("Vehicle Could Not Be Parked!!");
  }

  private boolean parkVehicleOnLevel(ParkingLevel parkingLevel, Vehicle vehicle) {
    if (VehicleType.MOTORCYCLE.equals(vehicle.getVehicleType())) {
      return park(parkingLevel, 1, SpotType.MOTORCYCLE, vehicle);
    } else if (VehicleType.CAR.equals(vehicle.getVehicleType())) {
      return park(parkingLevel, 1, SpotType.COMPACT, vehicle);
    } else if (VehicleType.BUS.equals(vehicle.getVehicleType())) {
      return park(parkingLevel, 5, SpotType.LARGE, vehicle);
    }
    return false;
  }

  // completed, park in a row.
  private boolean park(
      ParkingLevel parkingLevel,
      int spotsRequired,
      SpotType leastSpotTypeRequired,
      Vehicle vehicle) {
    List<List<ParkingSpot>> parkingSpotMatrix = parkingLevel.getParkingSpots();
    int n = parkingSpotMatrix.size();
    for (int i = 0; i < n; ++i) {
      List<ParkingSpot> parkingSpotList = parkingSpotMatrix.get(i);
      int m = parkingSpotList.size();
      if (m < spotsRequired) {
        continue;
      }

      int j = 0, cnt = 0, lastSpotId = 0;
      while (j < m && cnt < spotsRequired) {
        if (parkingSpotList.get(j).getSpotType().getId() >= leastSpotTypeRequired.getId()) {
          cnt++;
          lastSpotId = j;
        } else {
          cnt = 0;
        }
        j++;
      }
      if (cnt == spotsRequired) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        for (j = lastSpotId; j > lastSpotId - spotsRequired; j--) {
          parkingSpotList.get(j).setFree(false);
          parkingSpotList.get(j).setVehicleId(vehicle.getId());
          parkingSpots.add(parkingSpotList.get(j));
        }
        vehicleIdToParkingSpots.put(vehicle.getId(), parkingSpots);
        return true;
      }
    }
    return false;
  }

  // completed
  public void unParkVehicle(int vehicleId) {
    if (!vehicleIdToParkingSpots.containsKey(vehicleId)) {
      return;
    }
    List<ParkingSpot> parkingSpotList = vehicleIdToParkingSpots.get(vehicleId);
    parkingSpotList.forEach(
        spot -> {
          spot.setFree(true);
          spot.setVehicleId(-1);
        });
    vehicleIdToParkingSpots.remove(vehicleId);
  }

  // completed
  public List<ParkingSpot> getVehicleParkingSpot(int vehicleId) {
    if (!vehicleIdToParkingSpots.containsKey(vehicleId)) {
      System.out.println("No Such Vehicle");
      return new ArrayList<>();
    }
    return vehicleIdToParkingSpots.get(vehicleId);
  }

  // completed
  public int totalVehiclesParkedInLevel(int level) {
    ParkingLevel parkingLevel = levelMap.get(level);
    Set<Integer> vehicleIdSet = new HashSet<>();

    List<List<ParkingSpot>> parkingSpotMatrix = parkingLevel.getParkingSpots();
    int n = parkingSpotMatrix.size();
    for (int i = 0; i < n; ++i) {
      List<ParkingSpot> parkingSpotList = parkingSpotMatrix.get(i);
      int m = parkingSpotList.size();
      for (int j = 0; j < m; ++j) {
        if (parkingSpotList.get(j).getVehicleId() != -1) {
          vehicleIdSet.add(parkingSpotList.get(j).getVehicleId());
        }
      }
    }
    return vehicleIdSet.size();
  }

  // completed
  public void printParkingViewOfLevel(int level) {
    ParkingLevel parkingLevel = levelMap.get(level);
    List<List<ParkingSpot>> parkingSpotMatrix = parkingLevel.getParkingSpots();
    int n = parkingSpotMatrix.size();
    for (int i = 0; i < n; ++i) {
      List<ParkingSpot> parkingSpotList = parkingSpotMatrix.get(i);
      int m = parkingSpotList.size();
      for (int j = 0; j < m; ++j) {
        if (parkingSpotList.get(j).getVehicleId() == -1) {
          System.out.print("- ");
        } else {
          System.out.print(parkingSpotList.get(j).getVehicleId() + " ");
        }
      }
      System.out.println();
    }
  }

  // completed
  public void assignParkingStructureOnLevel(int level, List<List<ParkingSpot>> parkingSpotMatrix) {
    ParkingLevel parkingLevel = levelMap.get(level);
    if (parkingLevel == null) {
      parkingLevel = new ParkingLevel(level);
    }

    int n = parkingSpotMatrix.size();
    for (int i = 0; i < n; ++i) {
      List<ParkingSpot> parkingSpotList = parkingSpotMatrix.get(i);
      int m = parkingSpotList.size();
      for (int j = 0; j < m; ++j) {
        parkingSpotList.get(j).setLevel(level);
      }
    }

    parkingLevel.setParkingSpots(parkingSpotMatrix);
    levelMap.put(level, parkingLevel);
  }
}
