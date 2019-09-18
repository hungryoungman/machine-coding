package com.ricky.entity;

import java.util.HashMap;
import java.util.Map;

public class Resto {
  private int id;
  private int totalPower;
  private int availablePower;
  private Map<Integer, Integer> availableItems;

  public Resto(int id, int totalPower) {
    this.id = id;
    this.totalPower = totalPower;
    this.availablePower = totalPower;
    this.availableItems = new HashMap<>();
  }

  public void addItem(Item item, Integer price) {
    this.availableItems.put(item.getId(), price);
  }

  public Boolean hasItem(Item item) {
    return this.availableItems.get(item.getId()) == null ? Boolean.FALSE : Boolean.TRUE;
  }

  public Integer getItemPrice(Item item) {
    Integer price = this.availableItems.get(item.getId());
    return null == price ? 0 : price;
  }

  public int getTotalPower() {
    return totalPower;
  }

  public void setTotalPower(int totalPower) {
    this.totalPower = totalPower;
  }

  public int getAvailablePower() {
    return availablePower;
  }

  public void setAvailablePower(int availablePower) {
    this.availablePower = availablePower;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Resto{"
        + "id="
        + id
        + ", totalPower="
        + totalPower
        + ", availablePower="
        + availablePower
        + "}";
  }
}
