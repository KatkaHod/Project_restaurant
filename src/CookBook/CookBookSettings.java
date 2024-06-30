package CookBook;

public class CookBookSettings {
    private static final String inputCookBook = "resources/CookBook.txt";
    private static final String DELIMITER = "\t";

    public static String getInputCookBook() {
        return inputCookBook;
    }

    public static String getDelimiter() {
        return DELIMITER;
    }
}
