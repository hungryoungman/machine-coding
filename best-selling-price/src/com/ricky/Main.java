package com.ricky;

import com.ricky.entity.Product;
import com.ricky.entity.User;
import com.ricky.service.MarketService;

/**
 * You need to make a program in which
 *
 * <p>1.) You can add a product -> addProduct(“p1”)
 *
 * <p>2.) A user can purchase a product -> purchase(“u1″,”p1”)
 *
 * <p>3.) A user can return a product -> returnProduct(“u1″,”p1”)
 *
 * <p>4.) A user can be blacklisted and all of his purchases will be marked null ->
 * blackListUser(“u1”)
 *
 * <p>5.) Display the best selling product -> bestSelling() Best selling product will be the one
 * which have been bought by most number of unique users.
 *
 * <p>Bonus: Display the best selling products for each category.
 */
public class Main {
  public static void main(String[] args) {
    MarketService marketService = MarketService.getInstance();

    Product p1 = new Product("p1", "c1");
    Product p2 = new Product("p2", "c2");
    Product p3 = new Product("p3", "c3");

    User u1 = new User("u1");
    User u2 = new User("u2");

    marketService.addProduct(p1);
    marketService.addProduct(p2);
    marketService.addProduct(p3);

    marketService.purchaseProduct(u1, p1);
    marketService.purchaseProduct(u1, p2);
    marketService.purchaseProduct(u2, p1);
    marketService.purchaseProduct(u2, p1);

    marketService.printProductWithSellCount();
    System.out.println(marketService.bestSellingProduct());

    marketService.purchaseProduct(u2, p2);
    marketService.returnProduct(u2, p1);
    marketService.returnProduct(u2, p1);
    marketService.purchaseProduct(u1, p1);
    marketService.returnProduct(u1, p2);

    marketService.printProductWithSellCount();
    System.out.println(marketService.bestSellingProduct());

    marketService.blackListUser(u1);

    marketService.printProductWithSellCount();
    System.out.println(marketService.bestSellingProduct());
  }
}
