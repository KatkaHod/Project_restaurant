package Settings;

public class Settings {
    private static final String DEFAULT_IMAGE = "blank";
    private static final String DELIMITER = "\t";
    private static final String FILE_NAME_ORDERS = "resources/orders.txt";
    private static final String FILE_NAME_COOKBOOK = "resources/cookbook.txt";
    private static final String FILE_NAME_SUMMARY = "resources/summaryTable";

    public static String getDefaultImage() {
        return DEFAULT_IMAGE;
    }

    public static String getFileNameOrders() {
        return FILE_NAME_ORDERS;
    }

    public static String getFileNameCookBook() {
        return FILE_NAME_COOKBOOK;
    }


}
