package CookBook_Orders_FileHandling;

import java.util.List;

public class RestaurantManager {

    //Count of orders that are currently in progress -

    public int countPendingOrders(List<Order> orders) {
        return (int) orders.stream()
                .filter(order -> order.getFulfilmentTime() == null && order.getOrderedTime() != null)
                .count();
    }

    //Count of orders that are not finished - the order is not paid yet
    public int countUnfinishedOrders(List<Order> orders) {
        return (int) orders.stream()
                .filter(order -> order.getFulfilmentTime() != null && !order.isPaid())
                .count();
    }







}
