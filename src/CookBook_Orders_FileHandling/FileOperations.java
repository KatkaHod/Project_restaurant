package CookBook_Orders_FileHandling;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static Settings.Settings.getDelimiter;
import CookBook_Orders_FileHandling.CookBook;

public class FileOperations {

    public void saveOrdersToFile(List<Order> orders, String fileName) throws FileExceptions {
        System.out.println("Saving orders to file: " + fileName);

        if (orders.isEmpty()) {
            throw new FileExceptions("The list of orders is empty.");
        }
        int lineCounter = 0;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : orders) {
                if (cookBook.containsDish(order.getDish())) {
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
            throw new FileExceptions("File " + fileName + " not found!\n" + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new FileExceptions("An error occurred when writing to a file. " + fileName + "!\n" + e.getLocalizedMessage());
        } finally {
            System.out.println("Count of rows: " + lineCounter);
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
                //orderTracking.Order order = parseOrder(parts, lineNumber, line);
                //addOrder(order);
            }
        } catch (IOException e) {
            throw new OrderException("Error during file read process: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new OrderException("Unknown category: " + e.getMessage());
        }
    }








}
