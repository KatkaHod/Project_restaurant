package orderTracking;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Orders {
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

    public void loadOrdersFromFile(String inputFilename, String delimiter) throws OrderException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(inputFilename)))) {
            String line;
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                lineNumber++;
                line = scanner.nextLine();
                String[] parts = line.split(delimiter);
                //Order order = parseOrder(parts, lineNumber, line);
                //addOrder(order);
            }
        } catch (IOException e) {
            throw new OrderException("Error during file read process: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new OrderException("Unknown category: " + e.getMessage());
        }
    }

    public void saveOrdersToFile(String filename, String delimiter) throws OrderException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Order order : listOrders) {
                writer.println(
                        order.getDishID() + delimiter
                                + order.getQuantity() + delimiter
                                + order.getTableNumber() + delimiter
                                + order.getOrderedTime() + delimiter
                                + order.getFulfilmentTime() + delimiter
                                + (order.isPaid() ? "yes" : "no"));
            }
        } catch (IOException e) {
            throw new OrderException("Error when writing to file `" +filename+ "`: " + e.getMessage());
        }
    }


    @Override
    public String toString() {
        return "Orders{" +
                "listOrders=" + listOrders +
                '}';
    }
}
