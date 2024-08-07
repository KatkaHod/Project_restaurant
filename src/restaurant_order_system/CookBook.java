package restaurant_order_system;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;


public class CookBook {

    //A map to store dishes with their IDs and a variable to keep track of the next available ID.
    private Map<Integer, Dish> dishes = new HashMap<>();
    private int nextId = 1;


    public void addDish(Dish dish) throws CookBookException {
        try {
            dishes.put(nextId, dish);
            nextId++;
        } catch (Exception e) {
            throw new CookBookException("an error when adding a Dish:\n" + e.getLocalizedMessage());
        }
    }

    public Dish getDish(int id) throws CookBookException {
        Dish dish = dishes.get(id);
        if (dish == null) {
            throw new CookBookException("Dish with ID: " + id + " not found.");
        }
        return dish;
    }

    public void updateDish(int id, Dish dish) throws CookBookException {
        if (dishes.containsKey(id)) {
            try {
                dishes.put(id, dish);
            } catch (Exception e) {
                throw new CookBookException("Error when updating DISH with id: " + id  + e.getLocalizedMessage());
            }
        } else {
            throw new CookBookException("Dish with id not found: " + id);
        }
    }

    public void removeDish(int id) throws CookBookException {
        if (dishes.containsKey(id)) {
            dishes.remove(id);
        } else {
            throw new CookBookException("Dish with id not found: " + id);
        }
    }


    public void updateDishTitle(int id, String title) throws CookBookException {
        if (dishes.containsKey(id)) {
            try {
                dishes.get(id).setTitle(title);
            } catch (Exception e) {
                throw new CookBookException("Error when updating the dish title including id: " + id + e.getLocalizedMessage());
            }
        } else {
            throw new CookBookException("Dish with id not found: " + id);
        }
    }

    public void updateDishPrice(int id, BigDecimal price) throws CookBookException {
        if (dishes.containsKey(id)) {
            try {
                dishes.get(id).setPrice(price);
            } catch (Exception e) {
                throw new CookBookException("Error when updating the dish price with id: " + e.getLocalizedMessage());
            }
        } else {
            throw new CookBookException("Dish with id not found: " + id);
        }
    }

    public void updateDishPreparationTime(int id, int preparationTime) throws CookBookException {
        if (dishes.containsKey(id)) {
            try {
                dishes.get(id).setPreparationTime(preparationTime);
            } catch (Exception e) {
                throw new CookBookException("Error when updating the preparation time with ID: " + id + e.getLocalizedMessage());
            }
        } else {
            throw new CookBookException("Dish with id not found: " + id);
        }
    }

    public void updateDishImage(int id, String image) throws CookBookException {
        if (dishes.containsKey(id)) {
            try {
                dishes.get(id).setImage(image);
            } catch (Exception e) {
                throw new CookBookException("Error when updating the image with ID: " + id + e.getLocalizedMessage());
            }
        } else {
            throw new CookBookException("Dish with id not found: " + id);
        }
    }

    public Map<Integer, Dish> getDishes() {
        return new HashMap<>(dishes);
    }

    public int getDishesSize() {
        return dishes.size();
    }

    public void removeDishes() {
        dishes.clear();
        nextId = 1;
    }

    public Dish getDishById(int id) throws OrderException {
        if (!dishes.containsKey(id)) {
            throw new OrderException("Dish with id not found: " + id);
        }
        return dishes.get(id);
    }

    public int getDishId(Dish dish) throws FileException {
        try {
            for (Map.Entry<Integer, Dish> entry : dishes.entrySet()) {
                if (entry.getValue().equals(dish)) {
                    return entry.getKey();
                }
            }
            throw new FileException("Dish not found");
        } catch (Exception e) {
            throw new FileException("Error when searching for a dish: " + e.getLocalizedMessage());
        }
    }

    public Boolean containsDish(Dish dish) {
        return dishes.containsValue(dish);
    }

    public String getDishInfo(int id) throws CookBookException {
        Dish dish = dishes.get(id);
        if (dish == null) {
            throw new CookBookException(" The Dish with id: " + id + "not found");
        }
        return "Dish information: "
                + id + dish.getTitle() + ", price: " + dish.getPrice() + ", preparation time: " + dish.getPreparationTime() + " minutes, image: " + dish.getImage();
    }

    public String getDishesInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (dishes.isEmpty()) {
            return "The CookBook list is empty";
        } else {
            for (Map.Entry<Integer, Dish> entry : dishes.entrySet()) {
                stringBuilder.append("Dish + ID: ").append(entry.getKey()).append(": ").append(entry.getValue().getTitle()).append(", price: ")
                        .append(entry.getValue().getPrice()).append(" CZK, preparation time: ").append(entry.getValue().getPreparationTime())
                        .append(" minutes, image: ").append(entry.getValue().getImage()).append("\n");
            } return stringBuilder.toString();
        }



    }


}
