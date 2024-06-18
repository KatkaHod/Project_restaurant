package orderTracking;

import recipeStack.Dish;

import java.time.LocalDateTime;

public class Order implements Comparable<Order> {

    private static int lastOrderId = 0; //Static variable storing the last used ID
    private int orderId;
    private Dish dishID;
    private int tableNumber;
    private int quantity;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private boolean paid;


    public Order(Dish dishID, int quantity, int tableNumber ) throws OrderException {
        this.orderId = generateNewOrderId();
        this.dishID = dishID;
        this.quantity = quantity;
        if (tableNumber <= 0 || tableNumber > 20) {
            throw new OrderException("Available tables in the restaurant are 1,2,3,4,5,6,7,8,9,10. The table cannot be 0 or greater than 10. Provided table number: " + tableNumber);
        }
        this.tableNumber = tableNumber;
        this.orderedTime = LocalDateTime.now();
        this.fulfilmentTime = null; // The order has not yet been processed
        this.paid = false; // Order not yet paid
    }


    private static synchronized int generateNewOrderId() {
        return ++lastOrderId;
    }

    public void markAsFulfilled() {
        this.fulfilmentTime = LocalDateTime.now();
    }
    public void markAsPaid() {
        this.paid = true;
    }


    public void setQuantity(int quantity) throws OrderException {
        if(quantity <= 0) {
            throw new OrderException("Quantity must be greater than 0! Provided quantity: " + quantity);
        }
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTableNumber(int tableNumber) throws OrderException {
        if (tableNumber <= 0 || tableNumber > 20) {
            throw new OrderException("Available tables in the restaurant are 1,2,3,4,5,6,7,8,9,10. The table cannot be 0 or greater than 10. Provided table number: " + tableNumber);
        }
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public int compareTo(Order o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "mName=" + dishID +
                ", table=" + tableNumber +
                ", quantity=" + quantity +
                ", orderedTime=" + orderedTime +
                ", fulfilmentTime=" + fulfilmentTime +
                ", paid=" + paid +
                '}';
    }

}
