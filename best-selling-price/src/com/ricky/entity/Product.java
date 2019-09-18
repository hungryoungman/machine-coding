package com.ricky.entity;

public class Product {
  private String id;
  private String categoryId;

  public Product(String id, String categoryId) {
    this.id = id;
    this.categoryId = categoryId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  @Override
  public String toString() {
    return "Product{" + "id='" + id + '\'' + ", categoryId='" + categoryId + '\'' + '}';
  }
}
