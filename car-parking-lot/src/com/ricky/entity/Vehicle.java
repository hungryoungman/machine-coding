package com.ricky.entity;

public class Vehicle {
  private VehicleType vehicleType;
  private int id;

  public Vehicle(VehicleType vehicleType, int id) {
    this.vehicleType = vehicleType;
    this.id = id;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
