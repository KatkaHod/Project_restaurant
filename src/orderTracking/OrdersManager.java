package orderTracking;

import java.util.ArrayList;
import java.util.List;

public class OrdersManager {
    private List<Order> listOrders = new java.util.ArrayList<>();



    public List<Order> getListOrders() {
        return new ArrayList<>(listOrders);
    }


    public void addOrder(Order newOrder) {
        listOrders.add(newOrder);
    }

    public Order getOrderViaIndex(int index) {
        if (index >= 0 && index < listOrders.size()) {
            return listOrders.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index. Provided index: " + index);
        }
    }

    @Override
    public String toString() {
        return "OrdersManager{" +
                "listOrders=" + listOrders +
                '}';
    }
}
