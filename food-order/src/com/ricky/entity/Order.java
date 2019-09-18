package com.ricky.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
  private int id;
  private List<Item> items;

  public Order(int id) {
    this.id = id;
    this.items = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public int getProcessingPower() {
    return items.size();
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
}
