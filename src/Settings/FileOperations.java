package Settings;

import Order.OrderException;

import java.io.*;
import java.time.*;
import java.util.Map;
import java.math.BigDecimal;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Order.Order;
import Order.Orders;
import Order.OrderException;
import static Settings.Settings.getDelimiter;

public class FileOperations {


    //Orders

    public void saveOrdersToFile(String filename, String delimiter) throws OrderException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Order order : orders) {
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
