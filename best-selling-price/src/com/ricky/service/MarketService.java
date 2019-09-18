package com.ricky.service;

import com.ricky.entity.Product;
import com.ricky.entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketService {
  private static MarketService marketService;

  private Map<String, Product> productIdToProduct;
  private Map<String, User> userIdToUser;

  private Map<String, List<String>> userIdToPurchasedProductIds;
  private Map<String, Boolean> blackListedUsers;
  private Map<String, Integer> productIdToSellCount;

  private MarketService() {
    productIdToProduct = new HashMap<>();
    userIdToUser = new HashMap<>();
    userIdToPurchasedProductIds = new HashMap<>();
    blackListedUsers = new HashMap<>();
    productIdToSellCount = new HashMap<>();
  }

  public static MarketService getInstance() {
    if (marketService == null) {
      marketService = new MarketService();
    }
    return marketService;
  }

  public void addProduct(Product product) {
    productIdToProduct.put(product.getId(), product);
  }

  public void purchaseProduct(User user, Product product) {
    userIdToUser.putIfAbsent(user.getId(), user);
    if (productIdToProduct.get(product.getId()) == null) {
      System.out.println("No product found for productId " + product.getId());
      return;
    }

    userIdToPurchasedProductIds.computeIfAbsent(user.getId(), k -> new ArrayList<>());
    userIdToPurchasedProductIds.get(user.getId()).add(product.getId());

    increProductSellCount(product.getId());
  }

  public void returnProduct(User user, Product product) {
    if (productIdToProduct.get(product.getId()) == null) {
      System.out.println("No product found for productId " + product.getId());
      return;
    }

    if (userIdToUser.get(user.getId()) == null) {
      System.out.println("No user for userId " + user.getId());
    }

    userIdToPurchasedProductIds.computeIfAbsent(user.getId(), k -> new ArrayList<>());
    Boolean isRemoved = userIdToPurchasedProductIds.get(user.getId()).remove(product.getId());

    if (isRemoved) {
      decreProductSellCount(product.getId());
    }
  }

  public void blackListUser(User user) {
    blackListedUsers.put(user.getId(), true);
    userIdToPurchasedProductIds.get(user.getId()).forEach(this::decreProductSellCount);
  }

  // if there is a tie, you should show list<> but i am not.
  public Product bestSellingProduct() {
    String productId =
        productIdToSellCount.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    return productIdToProduct.get(productId);
  }

  private void increProductSellCount(String productId) {
    Integer sellCount = productIdToSellCount.get(productId);
    sellCount = sellCount == null ? 0 : sellCount;
    productIdToSellCount.put(productId, sellCount + 1);
  }

  private void decreProductSellCount(String productId) {
    Integer sellCount = productIdToSellCount.get(productId);
    sellCount = sellCount == null ? 0 : sellCount;
    productIdToSellCount.put(productId, sellCount - 1);
  }

  public void printProductWithSellCount() {
    productIdToSellCount
        .entrySet()
        .forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
  }
}
