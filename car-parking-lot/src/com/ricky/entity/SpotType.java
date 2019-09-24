package com.ricky.entity;

public enum SpotType {
  MOTORCYCLE(0),
  COMPACT(1),
  LARGE(2);

  private int id;

  SpotType(int id) {
    this.id = id;
  }

  public SpotType getByValue(int id) {
    for (SpotType spotType : SpotType.values()) {
      if (spotType.id == id) {
        return spotType;
      }
    }
    return null;
  }
}
