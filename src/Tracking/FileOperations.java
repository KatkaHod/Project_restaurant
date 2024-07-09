package Tracking;

import restaurantExceptions.FileException;
import restaurantExceptions.OrderException;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Tracking.Settings.getDelimiter;

public class FileOperations {

    public FileOperations() {
    }


    // ***** Orders *****

    public void saveOrdersToFile(List<Order> orders,CookBook cookBook, String fileName) throws FileException {
        System.out.println("Saving orders to file: " + fileName + " ... ");

        if (orders.isEmpty()) {
            throw new FileException("The list of orders is empty.");
        }
        if (cookBook == null) {
            throw new FileException("CookBook is null value - empty.");
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
                    throw new FileException("The order contains non-existent food.");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileException("File " + fileName + " not found!" + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new FileException("An error occurred when writing to a file. " + fileName + "!\n" + e.getLocalizedMessage());
        } finally {
            System.out.println("Count of rows: " + lineCounter);
        }
    }


    public void loadOrdersFromFile(List<Order> orders,CookBook cookBook,String fileName) throws OrderException {
        System.out.println("loading the orders from the file: " + fileName + "...");
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


    // ***** CookBook *****

    public void saveCookBookToFile(CookBook cookBook, String fileName) throws FileException {
        System.out.println("Saving the cookbook to a file " + fileName + "...");
        int lineCounter = 0;

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Map.Entry<Integer, Dish> entry : cookBook.getDishes().entrySet()) {
                Dish dish = entry.getValue();
                writer.println(entry.getKey() + getDelimiter() +
                        dish.getTitle() + getDelimiter() +
                        dish.getPrice() + getDelimiter() +
                        dish.getPreparationTime() + getDelimiter() +
                        dish.getImage());
                lineCounter++;
            }
        } catch (FileNotFoundException e) {
            throw new FileException("The file " + fileName + " not found: " + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new FileException("An error occurred when writing to a file: " + fileName + e.getLocalizedMessage());
        } finally {
            System.out.println("Count of rows: " + lineCounter);
        }
    }


    public void loadCookBookFromFile(CookBook cookBook, String fileName) throws FileException {
        System.out.println("Loading the CookBook from the file " + fileName + "...");
        int lineCounter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(getDelimiter());

                if (parts.length != 5)
                    throw new FileException("Incorrect format on line " + (lineCounter + 1) + " in file " + fileName);

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                BigDecimal price = new BigDecimal(parts[2]);
                int preparationTime = Integer.parseInt(parts[3]);
                String image = parts[4];

                cookBook.addDish(new Dish(title, price, preparationTime, image));
                lineCounter++;
            }
        } catch (FileNotFoundException e) {
            throw new FileException(String.format("File not found: %s. %s", fileName, e.getLocalizedMessage()));
        } catch (NumberFormatException e) {
            throw new FileException(String.format("Incorrect number format on line %d: %s", lineCounter, e.getLocalizedMessage()));
        } catch (Exception e) {
            throw new FileException(String.format("An error occurred when loading a menu from file %s (line %d): %s", fileName, lineCounter, e.getLocalizedMessage()));
        } finally {
            System.out.printf("Count of rows: %d%n", lineCounter);
        }
    }

}
