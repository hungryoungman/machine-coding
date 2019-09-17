package com.ricky;

public class Ride {
  private int driverId;
  private int vehicleNumber;
  private int origin;
  private int dest;
  private Long startTime;
  private Long duration;
  private Long createdAt;

  public Ride(
      int driverId, int vehicleNumber, int origin, int dest, Long startTime, Long duration) {
    this.driverId = driverId;
    this.vehicleNumber = vehicleNumber;
    this.origin = origin;
    this.dest = dest;
    this.startTime = startTime;
    this.duration = duration;
    this.createdAt = System.currentTimeMillis();
  }

  public int getDriverId() {
    return driverId;
  }

  public void setDriverId(int driverId) {
    this.driverId = driverId;
  }

  public int getVehicleNumber() {
    return vehicleNumber;
  }

  public void setVehicleNumber(int vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
  }

  public int getOrigin() {
    return origin;
  }

  public void setOrigin(int origin) {
    this.origin = origin;
  }

  public int getDest() {
    return dest;
  }

  public void setDest(int dest) {
    this.dest = dest;
  }

  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }

  public Long getDuration() {
    return duration;
  }

  public void setDuration(Long duration) {
    this.duration = duration;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "Ride{"
        + "driverId="
        + driverId
        + ", vehicleNumber="
        + vehicleNumber
        + ", origin="
        + origin
        + ", dest="
        + dest
        + ", startTime="
        + startTime
        + ", duration="
        + duration
        + ", createdAt="
        + createdAt
        + '}';
  }
}
