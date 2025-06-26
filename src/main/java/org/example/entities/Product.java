package org.example.entities;

import java.util.Random;

public class Product {
    private String category;
    private double price;
    private long id;
    private String name;

    public Product(String name, String category, double price) {
        Random random = new Random();
        this.id = random.nextLong();
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
