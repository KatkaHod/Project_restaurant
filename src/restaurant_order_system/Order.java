package restaurant_order_system;

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



    public Order(Dish dish, int quantity, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, int tableNumber, boolean paid) throws OrderException {
        this.dish = dish;
        setQuantity(quantity);
        setOrderedTime(orderedTime);
        this.fulfilmentTime = fulfilmentTime;
        setTableNumber(tableNumber);
        this.paid = paid;
    }
    public Order(Dish dish, int quantity, int tableNumber) throws OrderException {
        this(dish, quantity, LocalDateTime.now(), LocalDateTime.now(), tableNumber, false);
    }

    public Order() {
        this.dish = null;
        this.quantity = 0;
        this.orderedTime = null;
        this.fulfilmentTime = null;
        this.tableNumber = 0;
        this.paid = false;
    }

    public Dish getDish() {
        return dish;
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
        if (fulfilmentTime.isBefore(orderedTime)) {
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
