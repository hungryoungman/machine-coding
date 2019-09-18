package com.ricky.service;

import com.ricky.entity.Order;
import com.ricky.entity.Resto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class FoodOrderService {
  private static FoodOrderService foodOrderService;
  private RestoService restoService;

  private Map<Integer, Resto> restoIdToResto;
  private Map<Integer, Order> orderIdToOrder;
  private Map<Integer, List<Integer>> restoIdToOrders;

  private FoodOrderService() {
    restoIdToResto = new HashMap<>();
    orderIdToOrder = new HashMap<>();
    restoIdToOrders = new HashMap<>();
    restoService = RestoService.getInstance();
  }

  public static FoodOrderService getInstance() {
    if (foodOrderService == null) {
      foodOrderService = new FoodOrderService();
    }
    return foodOrderService;
  }

  public void addResto(Resto resto) {
    restoIdToResto.put(resto.getId(), resto);
  }

  public Resto assignAndGetRestoForOrder(Order order) {
    if (orderIdToOrder.get(order.getId()) != null) {
      System.out.println("Order has been Served or Expired !!");
      return null;
    }

    PriorityQueue<Pair<Integer, Integer>> queue = getPriorityQueue();
    restoIdToResto
        .values()
        .forEach(
            resto -> {
              if (restoService.canServe(resto, order)) {
                queue.add(new Pair<>(resto.getId(), restoService.getPriceOfOrder(resto, order)));
              }
            });
    Pair<Integer, Integer> restoToPrice = queue.peek();

    if (restoToPrice != null) {
      return assignOrderToResto(restoToPrice.getKey(), order);
    }

    return null;
  }

  private Resto assignOrderToResto(Integer restoId, Order order) {
    Resto resto = restoIdToResto.get(restoId);
    resto.setAvailablePower(resto.getAvailablePower() - order.getProcessingPower());
    orderIdToOrder.put(order.getId(), order);
    restoIdToOrders.computeIfAbsent(resto.getId(), k -> new ArrayList<>());
    restoIdToOrders.get(resto.getId()).add(order.getId());
    return resto;
  }

  private PriorityQueue<Pair<Integer, Integer>> getPriorityQueue() {
    return new PriorityQueue<>(11, Comparator.comparingInt(Pair::getValue));
  }
}
