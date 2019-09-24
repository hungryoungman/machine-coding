package com.ricky.entity;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
  private int level;
  private List<List<ParkingSpot>> parkingSpots;

  public ParkingLevel(int level) {
    this.level = level;
    parkingSpots = new ArrayList<>();
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public List<List<ParkingSpot>> getParkingSpots() {
    return parkingSpots;
  }

  public void setParkingSpots(List<List<ParkingSpot>> parkingSpots) {
    this.parkingSpots = parkingSpots;
  }
}
