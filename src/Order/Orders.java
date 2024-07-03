package Order;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private List<Order> orders = new java.util.ArrayList<>();

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void addOrder(Order newOrder) {
        orders.add(newOrder);
    }

    public Order getOrderViaIndex(int index) {
        if (index >= 0 && index < orders.size()) {
            return orders.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index. Provided index: " + index);
        }
    }

    public void printOrders() {
        orders.forEach(order -> System.out.println(order.toString()));
    }

}
