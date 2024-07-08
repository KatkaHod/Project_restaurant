package restaurantManager;

import Tracking.Order;
import restaurantExceptions.OrderException;

import java.time.Duration;
import java.time.LocalDateTime;
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
    public void todayOrderedDishes(List<Order> ordersList) {
        System.out.println("List of dishes ordered from today's date: ");

        for (Order order : ordersList) {
            if (order.getOrderedTime().isEqual(LocalDateTime.now())) {
                System.out.println(order.getDish().getTitle());
            }
        }
    }

    //6. Export order list for one table
    public void listOfOrdersForTable(List<Order> ordersList, int tableNumber) throws OrderException {
        if (tableNumber <= 0) {
            throw new OrderException("Table number must be at least >= 1. Provided table number: " + tableNumber);
        }
        System.out.println("Orders for table number: " + tableNumber);

        List<Order> tableOrders = ordersList.stream()
                .filter(order -> order.getTableNumber() = tableNumber)
                .toList();

        if (tableOrders.isEmpty()) {
            System.out.println("No orders found for table number: " + tableNumber);
        } else {
            tableOrders.forEach(order -> System.out.println(order.getDish().getTitle()));
        }
    }







}
