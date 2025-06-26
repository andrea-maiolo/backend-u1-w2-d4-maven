package org.example;

import com.github.javafaker.Faker;
import org.example.entities.Customer;
import org.example.entities.Order;
import org.example.entities.Product;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        Faker faker = new Faker(Locale.ITALY);
        Random random = new Random();

        Supplier<Product> bookSupp = () -> {
            String prodName = faker.book().title();
            String prodCat = "book";
            double prodPrice = Math.round(random.nextDouble(10.01, 100.99) * 100.0) / 100.0;
            return new Product(prodName, prodCat, prodPrice);
        };

        Supplier<Product> airSupp = () -> {
            String prodName = faker.aviation().aircraft();
            String prodCat = "aircraft";
            double prodPrice = Math.round(random.nextDouble(100.01, 1000.99) * 100.0) / 100.0;
            return new Product(prodName, prodCat, prodPrice);
        };

        Supplier<Product> beerSupp = () -> {
            String prodName = faker.beer().name();
            String prodCat = "beer";
            double prodPrice = Math.round(random.nextDouble(2.01, 30.99) * 100.0) / 100.0;
            return new Product(prodName, prodCat, prodPrice);
        };


        Supplier<Customer> customerSupp = () -> {
            int customerTier = random.nextInt(1, 4);
            String customerName = faker.leagueOfLegends().champion();
            return new Customer(customerName, customerTier);
        };

        List<Product> allProducts = new ArrayList<>();
        List<Product> allBookProducts = new ArrayList<>();
        List<Product> allAircraftProducts = new ArrayList<>();
        List<Product> allBeerProducts = new ArrayList<>();
        List<Customer> allCustomers = new ArrayList<>();
        List<Order> allOrders = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            allBookProducts.add(bookSupp.get());
            allAircraftProducts.add(airSupp.get());
            allBeerProducts.add(beerSupp.get());
        }

        for (int i = 0; i < 20; i++) {
            allCustomers.add(customerSupp.get());
        }

        allProducts.addAll(allAircraftProducts);
        allProducts.addAll(allBeerProducts);
        allProducts.addAll(allBookProducts);


        Supplier<Order> orderSupp = () -> {
            int indexRandom = random.nextInt(1, 20);
            Customer currentCustomerSelected = allCustomers.get(indexRandom);
            return new Order(currentCustomerSelected);
        };

        for (int i = 0; i < 50; i++) {
            allOrders.add(orderSupp.get());
        }

        for (int i = 0; i < allOrders.size(); i++) {
            int randomIndexProduct = random.nextInt(1, 60);
            int randomIndexOrder = random.nextInt(1, 50);
            Product randomProduct = allProducts.get(randomIndexProduct);
            allOrders.get(randomIndexOrder).addToCart(randomProduct);
        }

//esercizio 1

//        Map<String, List<User>> usersByCity = users.stream().filter(user -> user.getAge() > 17).collect(Collectors.groupingBy(user -> user.getCity()));
//        usersByCity.forEach((city, usersList) -> System.out.println("Città: " + city + ", " + usersList));
        Map<Customer, List<Order>> ordersByCustomer = allOrders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer()));

        ordersByCustomer.forEach((custmer, order) -> System.out.println("cust: " + custmer + "order list: " + order));


        //esercizio 2
// 8. Raggruppiamo gli user per città e calcoliamo varie statistiche come media età, somma età, età minima, età massima...
//        Map<String, IntSummaryStatistics> statsPerCity = users.stream().collect(Collectors.groupingBy(user -> user.getCity(), Collectors.summarizingInt(user -> user.getAge())));
//        statsPerCity.forEach((city, stats) -> System.out.println("Città: " + city + ", stats: " + stats));

        Map<Customer, DoubleSummaryStatistics> totalPerCustomer = allOrders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer(),
                        Collectors.summarizingDouble(order -> order.getProductList().stream()
                                .map(product -> product.getPrice())
                                .reduce(0.0, (a, b) -> a + b))));


        totalPerCustomer.forEach((customer, stats) ->
                System.out.println("Customer: " + customer + " total: " + stats.getSum()));


        //esercizio 3
        // 1. Otteniamo i 5 user più vecchi, tramite il sorted li ordino per età decrescente, poi tramite il limit tengo solo i primi 5
//        List<User> fiveOldUsers = users.stream().sorted(Comparator.comparing(User::getAge).reversed()).skip(0).limit(10).toList();
//        fiveOldUsers.forEach(user -> System.out.println(user));

//        List<Product> mostExpensive = allProducts.stream()
//                .sorted(Comparator.comparing(product -> product.getPrice()
//                                .reversed())
//                        .limit(10)
//                        .toList()
//                );


        //mostExpensive.forEach(prod -> System.out.println(prod));


        //esercizio4
//        Map<Order, DoubleSummaryStatistics> averagePerOrder = allOrders.stream()
//                .collect(Collectors.groupingBy((order -> order.getProductList().stream()
//                        .map(product -> product.getPrice())
//                        .reduce(0.0, (product, nexProduct) -> product + nexProduct))));
//
//
//        averagePerOrder.forEach((order, stats) ->
//                System.out.println("Order: " + order + " average order total: " + stats.getAverage()));
//
//

        //esercizio5
        Map<String, Double> totalPerCategory = allProducts.stream()
                .collect(Collectors.groupingBy(
                        product -> product.getCategory(),
                        Collectors.summingDouble(product -> product.getPrice())
                ));

        totalPerCategory.forEach((category, total) ->
                System.out.println("Category: " + category + " total: " + total));

    }
}
