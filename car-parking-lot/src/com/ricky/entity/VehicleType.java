package com.ricky.entity;

public enum VehicleType {
  MOTORCYCLE(0),
  CAR(1),
  BUS(2);

  private int id;

  VehicleType(int id) {
    this.id = id;
  }

  public VehicleType getByValue(int id) {
    for (VehicleType vehicleType : VehicleType.values()) {
      if (vehicleType.id == id) {
        return vehicleType;
      }
    }
    return null;
  }
}
