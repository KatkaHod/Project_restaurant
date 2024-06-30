package Order;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Orders {
    private List<Order> orders = new java.util.ArrayList<>();

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void addOrder(Order newOrder) {
        orders.add(newOrder);
    }

    public Order getOrderViaIndex(int index) {
        if (index >= 0 && index < orders.size()) {
            return orders.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index. Provided index: " + index);
        }
    }

    public void printOrders() {
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }
}
