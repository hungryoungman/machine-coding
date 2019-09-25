package com.ricky.entity;

public class ParkingSpot {
  private SpotType spotType;
  private boolean isFree;
  private int level;
  private int vehicleId;

  public ParkingSpot(SpotType spotType) {
    this.spotType = spotType;
    this.vehicleId = -1;
    isFree = true;
  }

  public SpotType getSpotType() {
    return spotType;
  }

  public void setSpotType(SpotType spotType) {
    this.spotType = spotType;
  }

  public boolean isFree() {
    return isFree;
  }

  public void setFree(boolean free) {
    isFree = free;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(int vehicleId) {
    this.vehicleId = vehicleId;
  }
}
