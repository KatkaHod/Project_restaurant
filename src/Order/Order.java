package Order;

import CookBook.Dish;
import Settings.Settings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements Comparable<Order> {


    // Mandatory - Override the compareTo method to enable comparison of Order objects
    @Override
    public int compareTo(Order o) {
        return 0;
    }

    private Dish dish;
    private int quantity;
    private int tableNumber;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private boolean paid;


    public Order(Dish dishID, int quantity, int tableNumber ) throws OrderException {
        this.dish = dish;
        this.quantity = quantity;
        if (tableNumber <= 0 || tableNumber > 20) {
            throw new OrderException("Available tables in the restaurant are 1,2,3,4,5,6,7,8,9,10. The table cannot be 0 or greater than 10. Provided table number: " + tableNumber);
        }
        this.tableNumber = tableNumber;
        this.orderedTime = LocalDateTime.now();
        this.fulfilmentTime = null; // The order has not yet been processed
        this.paid = false; // orderTracking.Order not yet paid
    }



    // **** Get and Set methods ****

    public void setQuantity(int quantity) throws OrderException {
        if(quantity <= 0) {
            throw new OrderException("Quantity must be greater than 0! Provided quantity: " + quantity);
        }
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }



    public void setOrderedTime(LocalDateTime orderedTime) throws OrderException {
        if (orderedTime == null) {
            throw new OrderException("Ordered time cannot be zero" + orderedTime);
        }
        this.orderedTime = orderedTime;
    }
    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }


    public void setFulfilmentTime(LocalDateTime fulfilmentTime) throws OrderException {
        if (fulfilmentTime.isBefore(orderedTime))
        {
            throw new OrderException("The fulfilment time must not be earlier than the order time. Provided time: " + fulfilmentTime.format(DateTimeFormatter.ofPattern(Settings.getDateFormat())) + ".");
        }
        this.fulfilmentTime = fulfilmentTime;
    }
    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }



    public void setTableNumber(int tableNumber) throws OrderException {
        if (tableNumber <= 0) {
            throw new OrderException("The number of table must be greater than 0: " + tableNumber);
        }
        this.tableNumber = tableNumber;
    }
    public int getTableNumber() {
        return tableNumber;
    }


    //Other methods

    public void markAsPaid() {
        this.paid = true;
    }
    public boolean isPaid() {
        return paid;
    }

    public BigDecimal getOrderPrice() {
        return dish.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void markAsFulfilled() {
        this.fulfilmentTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return dish.getTitle() + quantity + "(" + getOrderPrice() + "CZK" + ")" + ":" + "\t" +
                orderedTime.format(DateTimeFormatter.ofPattern(Settings.getTimeFormat())) + " - " + (fulfilmentTime != LocalDateTime.MAX ? fulfilmentTime.format(DateTimeFormatter.ofPattern(Settings.getTimeFormat())) : "\t") +
                (paid ? "paid" : " ");
    }
}
