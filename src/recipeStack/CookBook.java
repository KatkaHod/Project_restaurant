package recipeStack;

import java.util.ArrayList;
import java.util.List;

public class CookBook {
    private List <Dish> cookBook = new java.util.ArrayList<>();


    //return new array list (copy)
    public List<Dish> getDishList() {
        return new ArrayList<>(cookBook);
    }

    //add a recipe to the Arraylist
    public void addRecipe(Dish newRecipe) {
        cookBook.add(newRecipe);
    }

    //***Add*** multiple recipes at once
    public void addRecipes(List<Dish> newRecipes){
        this.cookBook.addAll(newRecipes);
    }


    //***remove*** recipe from the arrayList
    public void removeRecipe(int index) {
        if (index >= 0 && index < cookBook.size()) {
            cookBook.remove(index);
        } else {
            System.err.println("Invalid index.");
        }
    }

    //***update*** the list of recipes
    public static void updateRecipe(ArrayList<Dish> listOfRecipes, int index, Dish updatedRecipe) {
        if (index >= 0 && index < listOfRecipes.size()) {
            listOfRecipes.set(index, updatedRecipe);
            System.out.println("The recipe on index " + index + "has been successfully updated.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    //get a recipe through the index
    public Dish getRecipeViaIndex(int index) {
        if (index >= 0 && index < cookBook.size()) {
            return cookBook.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index. Provided index: " + index);
        }
    }

    @Override
    public String toString() {
        return "CookBook{" +
                "listOfRecipes=" + cookBook +
                '}';
    }
}
