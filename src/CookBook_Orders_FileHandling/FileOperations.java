package CookBook_Orders_FileHandling;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static Settings.Settings.getDelimiter;

public class FileOperations {


    public void saveOrdersToFile(List<Order> orders,CookBook cookBook, String fileName) throws FileExceptions {
        System.out.println("Saving orders to file: " + fileName);

        if (orders.isEmpty()) {
            throw new FileExceptions("The list of orders is empty.");
        }
        if (cookBook == null) {
            throw new FileExceptions("CookBook is null value - empty.");
        }

        int lineCounter = 0;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : orders) {
                if (cookBook.containsDish(order.getDish()))   {
                    writer.println(
                            order.getTableNumber() + getDelimiter() +
                                    cookBook.getDishId(order.getDish()) + getDelimiter() +
                                    order.getQuantity() + getDelimiter() +
                                    order.getOrderedTime() + getDelimiter() +
                                    (order.getFulfilmentTime() != null ? order.getFulfilmentTime() : "null") + getDelimiter() +
                                    (order.isPaid() ? "paid" : "not paid yet"));
                    lineCounter++;
                } else {
                    throw new FileExceptions("The order contains non-existent food.");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileExceptions("File " + fileName + " not found!" + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new FileExceptions("An error occurred when writing to a file. " + fileName + "!\n" + e.getLocalizedMessage());
        } finally {
            System.out.println("Count of rows: " + lineCounter);
        }
    }


    public void loadOrdersFromFile(List<Order> orders,CookBook cookBook,String fileName) throws OrderException {
        System.out.println("loading the orders from the file: " + fileName);
        orders.clear();
        int lineCounter = 0;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            String line;

            while (scanner.hasNextLine()) {

                line = scanner.nextLine();
                String[] parts = line.split(getDelimiter());

                if (parts.length != 6) throw new OrderException("Wrong format in the line of the file from which I read the orders: " + (lineCounter + 1));

                int tableNumber = Integer.parseInt(parts[0]);
                int dishId = Integer.parseInt(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                LocalDateTime orderedTime = LocalDateTime.parse(parts[3]);
                LocalDateTime fulfilmentTime;

                fulfilmentTime = "null".equals(parts[4]) ? null : LocalDateTime.parse(parts[4]);

                if (fulfilmentTime != null && fulfilmentTime.isBefore(orderedTime)) {
                    throw new OrderException("The time of fulfilment must not be earlier than the order date.");
                }

                boolean isPaid;

                switch (parts[5]) {
                    case "paid" -> isPaid = true;
                    case "not paid" -> isPaid = false;
                    default -> throw new OrderException(String.format("Error format on line %d in file %s!", lineCounter + 1, fileName));
                }

                if (cookBook.getDishes().containsKey(dishId)) {
                    orders.add(new Order(
                            cookBook.getDishById(dishId),
                            quantity,
                            orderedTime,
                            fulfilmentTime != null ? fulfilmentTime : null,
                            tableNumber,
                            isPaid
                    ));

                    lineCounter++;

                } else {
                    throw new OrderException("The order contains non exist dish");
                }

            }
        } catch (IOException e) {
            throw new OrderException(String.format("Error during file read process: %s", e.getMessage()));
        } catch (IllegalArgumentException e) {
            throw new OrderException(String.format("Unknown category: %s", e.getMessage()));
        }
    }








}
