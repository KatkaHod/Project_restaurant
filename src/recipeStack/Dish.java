package recipeStack;

import java.math.BigDecimal;
import java.time.Duration;
import java.net.MalformedURLException;
import java.net.URL;

public class Dish {

    private int dishID;
    private String dishName;
    private BigDecimal price;
    private  Duration preparationTime;
    private  String imageUrl;

    public Dish(int id, String dishName, BigDecimal price, Duration preparationTime, String imageUrl) {
        this.dishID = id;
        this.dishName = dishName;
        this.price = price;
        this.preparationTime = preparationTime;
        this.imageUrl = imageUrl;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) throws DishException {
        if(dishName == null) {
            throw new DishException("The title is not allowed to be a null value");
        }
        this.dishName = dishName;
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

    public Duration getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Duration preparationTime) throws DishException {
        if(preparationTime.isNegative() || preparationTime.isZero()) {
            throw new DishException("The duration of a recipe cannot be zero or a negative number. Enter a positive value.");
        }
        this.preparationTime = preparationTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = (imageUrl == null || imageUrl.isEmpty()) ? "blank" : imageUrl;
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
        return "Dish{" +
                "id=" + dishID +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                ", preparationTime=" + preparationTime +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
