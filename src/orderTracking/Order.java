package orderTracking;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Dish mealName;
    private int table;
    private int quantity;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime; //the time - order is completed
    private boolean paid;
    private List<Order> listOfOrders = new java.util.ArrayList<>();


    public Order(Dish mealName, int quantity) {
        this.mealName = mealName;
        this.quantity = quantity;
        this.orderedTime = LocalDateTime.now();
        this.fulfilmentTime = null; // The order has not yet been processed
        this.paid = false; // Order not yet paid
    }

    public void markAsFulfilled() {
        this.fulfilmentTime = LocalDateTime.now();
    }

    public void markAsPaid() {
        this.paid = true;
    }

    @Override
    public String toString() {
        return "Order{" +
                "mealName=" + mealName +
                ", table=" + table +
                ", quantity=" + quantity +
                ", orderedTime=" + orderedTime +
                ", fulfilmentTime=" + fulfilmentTime +
                ", paid=" + paid +
                ", listOfOrders=" + listOfOrders +
                '}';
    }
}
