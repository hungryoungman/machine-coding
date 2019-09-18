package com.ricky;

import com.ricky.entity.Item;
import com.ricky.entity.Order;
import com.ricky.entity.Resto;
import com.ricky.service.FoodOrderService;

/**
 * Design a food order system. The code should be modular and extensible. Some conditions are there
 *
 * <p>1) Each restaurant has a processing power(say P). It can process the number of items(N<=P) at
 * a single point of time. Once it takes the order, processing power will be reduced by N.
 *
 * <p>2) An order can be placed at one restaurant. (All items should be ordered from a restaurant
 * which has the sufficient processing power and all items should available in restaurant)
 *
 * <p>3) Place the order in a restaurant that has the lowest price of the order.
 *
 * <p>4) Processing power will be restored on the completion of an order.
 */
public class Main {
  public static void main(String[] args) {
    FoodOrderService foodOrderService = FoodOrderService.getInstance();
    Order order1 = new Order(1);
    Order order2 = new Order(2);

    Item item1 = new Item(1, "a");
    Item item2 = new Item(2, "b");
    Item item3 = new Item(3, "c");
    Item item4 = new Item(4, "d");

    order1.addItem(item1); // should have taken multiplicity of items into consideration.
    order1.addItem(item2);

    order2.addItem(item2);
    order2.addItem(item4);

    Resto resto1 = new Resto(1, 3);
    resto1.addItem(item1, 1);
    resto1.addItem(item2, 1);
    Resto resto2 = new Resto(2, 4);
    resto2.addItem(item1, 1);
    resto2.addItem(item2, 1);
    resto2.addItem(item3, 1);
    resto2.addItem(item4, 1);

    foodOrderService.addResto(resto1);
    foodOrderService.addResto(resto2);

    System.out.println(foodOrderService.assignAndGetRestoForOrder(order1));
    System.out.println(foodOrderService.assignAndGetRestoForOrder(order2));
    System.out.println(foodOrderService.assignAndGetRestoForOrder(order1));
  }
}
