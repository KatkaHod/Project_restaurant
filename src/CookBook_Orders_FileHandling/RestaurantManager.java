package CookBook_Orders_FileHandling;

import java.util.List;

public class RestaurantManager {

    //1a. Count of orders that are currently in progress -
    public int countPendingOrders(List<Order> orders) {
        return (int) orders.stream()
                .filter(order -> order.getFulfilmentTime() == null && order.getOrderedTime() != null)
                .count();
    }

    //1b. Count of orders that are not finished - the order is not paid yet
    public int countUnfinishedOrders(List<Order> orders) {
        return (int) orders.stream()
                .filter(order -> order.getFulfilmentTime() != null && !order.isPaid())
                .count();
    }

    //2. Sort orders by order time
    public void sortOrdersByOrderTime(List<Order> orders) {
        orders.sort((order1, order2) -> order1.getOrderedTime().compareTo(order2.getOrderedTime()));
    }











}
