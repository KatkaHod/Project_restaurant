import Tracking.*;
import restaurantExceptions.CookBookException;
import restaurantExceptions.DishException;
import restaurantExceptions.FileException;
import restaurantExceptions.OrderException;
import restaurantManager.RestaurantManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Orders orders = new Orders();
        CookBook cookBook = new CookBook();
        RestaurantManager restaurantManager = new RestaurantManager();


        //1. Load from the file - It is empty at the moment
        String fileOrders = Settings.getFileNameOrders();
        String fileCookBook = Settings.getFileNameCookBook();
        FileOperations fileOperations = new FileOperations();

        try {
            fileOperations.loadCookBookFromFile(cookBook,fileCookBook);
            fileOperations.loadOrdersFromFile(orders.getOrders(),cookBook,fileOrders);
        } catch (FileException | OrderException e) {
            System.out.println("Error: " + e.getMessage());
        }


        //2. Add dishes to CookBook
        try {
            cookBook.addDish(new Dish("Chicken fillet 150 g", BigDecimal.valueOf(250), 45, "Chicken-fillet "));
            cookBook.addDish(new Dish("Fries 150 g", BigDecimal.valueOf(120), 20, "Fries"));
            cookBook.addDish(new Dish("Trout on wine 200 g", BigDecimal.valueOf(400), 45,"Trout-on-wine "));
            cookBook.addDish(new Dish("Kofola drink 0,5 l", BigDecimal.valueOf(50), 5, "Kofola"));
        } catch (CookBookException | DishException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //3. Create new orders for table 15 and table 2 - Drinks for both orders are be served immediately
        try {
            orders.addOrder(new Order(cookBook.getDish(1),2,15));
            orders.addOrder(new Order(cookBook.getDish(2),2,15));
            orders.addOrder(new Order(cookBook.getDish(4),2, LocalDateTime.now(), LocalDateTime.now(),15, Boolean.FALSE));

            orders.addOrder(new Order(cookBook.getDish(1),3,2));
            orders.addOrder(new Order(cookBook.getDish(2),4,2));
            orders.addOrder(new Order(cookBook.getDish(3),1,2));
            orders.addOrder(new Order(cookBook.getDish(4),4, LocalDateTime.now(), LocalDateTime.now(),2, Boolean.FALSE));
        } catch (OrderException | CookBookException e) {
            System.out.println("Error: " + e.getMessage());
        }












    }





}