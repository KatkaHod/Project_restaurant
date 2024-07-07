package restaurantManager;

import Tracking.Order;

import java.time.Duration;
import java.util.List;

public class RestaurantManager {

    //1. Count of orders that are currently in progress
    public int countPendingOrders(List<Order> orders) {
        int pendingOrdersCount = (int) orders.stream()
                .filter(order -> order.getFulfilmentTime() == null && order.getOrderedTime() != null)
                .count();
        System.out.println("Count of orders in progress: " + pendingOrdersCount);
        return pendingOrdersCount;
    }

    //2. Count of orders that are not finished - the order is not paid yet
    public int countUnfinishedOrders(List<Order> orders) {
        int unfinishedOrders = (int) orders.stream()
                .filter(order -> order.getFulfilmentTime() != null && !order.isPaid())
                .count();
        System.out.println("Count of unfinished orders: " + unfinishedOrders);
        return unfinishedOrders;
    }

    //3. Orders sorted by time
    public void sortOrdersByOrderTime(List<Order> orders) {
        orders.sort((order1, order2) -> order1.getOrderedTime().compareTo(order2.getOrderedTime()));
    }

    //4. average fulfilment time in minutes
    public double calculateAverageFulfilmentTime(List<Order> orders) {
        double averageMinutes = orders.stream()
                .filter(order -> order.getFulfilmentTime() != null && !order.getOrderedTime().isAfter(order.getFulfilmentTime()))
                .mapToLong(order -> Duration.between(order.getOrderedTime(), order.getFulfilmentTime()).toMinutes())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("No completed orders to calculate average fulfilment time."));
        System.out.printf("Average fulfilment time: %.2f minutes%n", averageMinutes);

        return averageMinutes;
    }

    //5.



}
