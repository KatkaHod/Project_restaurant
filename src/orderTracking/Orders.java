package orderTracking;

import java.time.LocalDateTime;

public class Orders {
    private RecipeStack mealName;
    private int table;
    private int quantity;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime; //the time - order is completed
    private boolean paid;


    public Orders(String mealName, int quantity) {
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

}
