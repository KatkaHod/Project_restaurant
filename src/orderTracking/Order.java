package orderTracking;

import recipeStack.Dish;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Dish dishName;
    private Table table;
    private int quantity;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private boolean paid;
    private List<Order> listOfOrders = new java.util.ArrayList<>();


    public Order(Dish dishName, int quantity, Table table) {
        this.dishName = dishName;
        this.quantity = quantity;
        this.table = table;
        this.orderedTime = LocalDateTime.now();
        this.fulfilmentTime = null; // The order has not yet been processed
        this.paid = false; // Order not yet paid
    }

    //mark when the order is done
    public void markAsFulfilled() {
        this.fulfilmentTime = LocalDateTime.now();
    }

    //mark as paid
    public void markAsPaid() {
        this.paid = true;
    }

    @Override
    public String toString() {
        return "Order{" +
                "mealName=" + dishName +
                ", table=" + table +
                ", quantity=" + quantity +
                ", orderedTime=" + orderedTime +
                ", fulfilmentTime=" + fulfilmentTime +
                ", paid=" + paid +
                ", listOfOrders=" + listOfOrders +
                '}';
    }
}
