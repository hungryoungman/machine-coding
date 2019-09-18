package com.ricky.service;

import com.ricky.entity.Item;
import com.ricky.entity.Order;
import com.ricky.entity.Resto;

public class RestoService {
  private static RestoService restoService;

  private RestoService() {}

  static RestoService getInstance() {
    if (restoService == null) {
      restoService = new RestoService();
    }
    return restoService;
  }

  public Boolean canServe(Resto resto, Order order) {
    if (order.getProcessingPower() > resto.getAvailablePower()) {
      return false;
    }

    for (Item item : order.getItems()) {
      if (!resto.hasItem(item)) {
        return Boolean.FALSE;
      }
    }
    return Boolean.TRUE;
  }

  public int getPriceOfOrder(Resto resto, Order order) {
    return order.getItems().stream().mapToInt(resto::getItemPrice).sum();
  }
}
