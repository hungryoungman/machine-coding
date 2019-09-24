package com.ricky.entity;

public class ParkingSpot {
  private SpotType spotType;
  private boolean isFree;
  private Coordinate coordinate;
  private int level;

  public ParkingSpot(SpotType spotType, Coordinate coordinate, int level) {
    this.spotType = spotType;
    this.coordinate = coordinate;
    this.level = level;
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

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
