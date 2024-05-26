package orderTracking;

import java.math.BigDecimal;
import java.time.Duration;
import java.net.MalformedURLException;
import java.net.URL;

public class Dish {
    private String mealName;
    private BigDecimal price;
    private  Duration preparationTime;
    private  String imageUrl;

    public Dish(String mealName, BigDecimal price, Duration preparationTime, String imageUrl) {
        if(mealName == null) {
            throw new NullPointerException("The title is not allowed to be a null value");
        }
        this.mealName = mealName;

        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            throw  new IllegalArgumentException("The value must be positive!");
        }
        this.price = price;

        if(preparationTime.isNegative() || preparationTime.isZero()) {
            throw new IllegalArgumentException("The duration of a recipe cannot be zero or a negative number. Enter a positive value.");
        }
        this.preparationTime = preparationTime;

        // Check if imageUrl is null or empty, if so, set it to "blank"
        this.imageUrl = (imageUrl == null || imageUrl.isEmpty()) ? "blank" : imageUrl;
    }


    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        if(mealName == null) {
            throw new NullPointerException("The title is not allowed to be a null value");
        }
        this.mealName = mealName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            throw  new IllegalArgumentException("The value must be positive!");
        }
        this.price = price;
    }

    public Duration getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Duration preparationTime) {
        if(preparationTime.isNegative() || preparationTime.isZero()) {
            throw new IllegalArgumentException("The duration of a recipe cannot be zero or a negative number. Enter a positive value.");
        }
        this.preparationTime = preparationTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
                "title='" + mealName + '\'' +
                ", price=" + price +
                ", preparationTime=" + preparationTime +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
