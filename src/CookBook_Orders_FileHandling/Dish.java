
package CookBook_Orders_FileHandling;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

public class Dish {
    private String title;
    private BigDecimal price;
    private  int preparationTime;
    private  String image;


    public Dish(String title, BigDecimal price, int preparationTime, String image) throws DishException {
        setTitle(title);
        setPrice(price);
        setPreparationTime(preparationTime);
        setImage(image);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws DishException {
        if(title == null) {
            throw new DishException("The title is not allowed to be a null value");
        }
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) throws DishException {
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            throw  new DishException("The value must be positive!");
        }
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) throws DishException {
        if(preparationTime <=0) {
            throw new DishException("The duration of a recipe cannot be zero or a negative number. Enter a positive value.");
        }
        this.preparationTime = preparationTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        this.image = (imageUrl == null || imageUrl.isEmpty()) ? "blank" : imageUrl;
    }


    public static boolean checkURL(String urlString) {
        try {
            URL url = new URL(urlString);
            return true; //URL is valid
        } catch (MalformedURLException e) {
            return false; //URL is not valid
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ","
                + "price:" + price + "CZK" + ", "
                + "preparation time: " + preparationTime + "minutes" + ", "
                + "image: " + image;
    }


}
