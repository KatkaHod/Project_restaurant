package com.restaurantManager;

import com.restaurant.Tracking.Order;

import java.time.Duration;
import java.util.List;

public class RestaurantManager {

    //1. Count of orders that are currently in progress
    public int countPendingOrders(List<Order> orders) {
        return (int) orders.stream()
                .filter(order -> order.getFulfilmentTime() == null && order.getOrderedTime() != null)
                .count();
    }

    //2. Count of orders that are not finished - the order is not paid yet
    public int countUnfinishedOrders(List<Order> orders) {
        return (int) orders.stream()
                .filter(order -> order.getFulfilmentTime() != null && !order.isPaid())
                .count();
    }

    //3. Orders sorted by time
    public void sortOrdersByOrderTime(List<Order> orders) {
        orders.sort((order1, order2) -> order1.getOrderedTime().compareTo(order2.getOrderedTime()));
    }

    //4. average fulfilment time in minutes
    public double calculateAverageFulfilmentTime(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getFulfilmentTime() != null && !order.getOrderedTime().isAfter(order.getFulfilmentTime()))
                .mapToLong(order -> Duration.between(order.getOrderedTime(), order.getFulfilmentTime()).toMinutes())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("No completed orders to calculate average fulfilment time."));
    }


}
