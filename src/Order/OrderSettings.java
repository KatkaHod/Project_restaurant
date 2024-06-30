package Order;

public class OrderSettings {

    private static final String inputOrders = "resources/orders.txt";
    private static final String DELIMITER = "\t";

    public static String getInputOrders() {
        return inputOrders;
    }

    public static String getDelimiter() {
        return DELIMITER;
    }

}
