package recipeStack;

import java.util.ArrayList;
import java.util.List;

public class CookBook {
    private List <Dish> cookBook = new java.util.ArrayList<>();



    public List<Dish> getCookBook() {
        return new ArrayList<>(cookBook);
    }

    public void addDishToCookBook(Dish newRecipe) {
        cookBook.add(newRecipe);
    }


    //***Add*** multiple dishes at once
    public void addDishesToCookBook(List<Dish> newRecipes){
        this.cookBook.addAll(newRecipes);
    }


    //***remove*** dish from the arrayList
    public void removeDishFromCookBook(int index) {
        if (index >= 0 && index < cookBook.size()) {
            cookBook.remove(index);
        } else {
            System.err.println("Invalid index." + index);
        }
    }

    //***update*** the cookBook
    public static void updateCookBook(ArrayList<Dish> cookBook, int index, Dish updatedCookBook) {
        if (index >= 0 && index < cookBook.size()) {
            cookBook.set(index, updatedCookBook);
            System.out.println("The recipe on index " + index + "has been successfully updated.");
        } else {
            System.out.println("Invalid index." + index);
        }
    }

    public Dish getDishViaIndex(int index) {
        if (index >= 0 && index < cookBook.size()) {
            return cookBook.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index. Provided index: " + index);
        }
    }

    @Override
    public String toString() {
        return "CookBook{" +
                "cookBook=" + cookBook +
                '}';
    }
}
