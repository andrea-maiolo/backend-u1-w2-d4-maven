package org.example.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {
    public Customer customer;
    private List<Product> productList;
    private LocalDate orderDate;
    private long id;
    private String status;
    private LocalDate deliveryDate;

    public Order(Customer c) {
        this.customer = c;
        Random random = new Random();
        this.id = random.nextLong();
        this.status = "pending";
        this.orderDate = LocalDate.now();
        this.deliveryDate = LocalDate.now().plusDays(3);
        this.productList = new ArrayList<>();
    }

    public void addToCart(Product p) {
        this.productList.add(p);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
    

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", productList=" + productList +
                ", customer=" + customer +
                '}';
    }
}
