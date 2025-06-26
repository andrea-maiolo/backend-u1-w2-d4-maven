package org.example.entities;

import java.util.Random;

public class Customer {
    private int tier;
    private String name;
    private long id;

    public Customer(String name, int tier) {
        this.name = name;
        Random random = new Random();
        this.id = random.nextLong();
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "tier=" + tier +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
