import Tracking.CookBook;
import Tracking.FileOperations;
import Tracking.Order;
import Tracking.Settings;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Instance of class Order and cookBook
        List<Order> orders = new ArrayList<>();
        CookBook cookBook = new CookBook();

        //1. Load from the file - at this moment it is empty

        String fileOrders = Settings.getFileNameOrders();
        String fileCookBook = Settings.getFileNameCookBook();

        //???????????
        FileOperations.loadOrdersFromFile(orders,cookBook,fileOrders);















    }
}