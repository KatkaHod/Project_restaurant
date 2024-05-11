package orderTracking;

import java.util.ArrayList;
import java.util.List;

public class RecipeStackManager {
    private List <RecipeStack> listOfRecipes = new java.util.ArrayList<>();


    //return new array list (copy)
    public List<RecipeStack> getPlantsList() {
        return new ArrayList<>(listOfRecipes);
    }

    //add a recipe to the Arraylist
    public void addRecipe(RecipeStack newRecipe) {
        listOfRecipes.add(newRecipe);
    }

    //***Add*** multiple recipes at once
    public void addRecipes(List<RecipeStack> newRecipes){
        this.listOfRecipes.addAll(newRecipes);
    }


    //***remove*** recipe from the arrayList
    public void removeRecipe(int index) {
        if (index >= 0 && index < listOfRecipes.size()) {
            listOfRecipes.remove(index);
        } else {
            System.err.println("Invalid index.");
        }
    }

    //***update*** the list of recipes
    public static void updateRecipe(ArrayList<RecipeStack> listOfRecipes, int index, RecipeStack updatedRecipe) {
        if (index >= 0 && index < listOfRecipes.size()) {
            listOfRecipes.set(index, updatedRecipe);
            System.out.println("The recipe on index " + index + "has been successfully updated.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    //get a recipe through the index
    public RecipeStack getRecipeViaIndex(int index) {
        if (index >= 0 && index < listOfRecipes.size()) {
            return listOfRecipes.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index. Provided index: " + index);
        }
    }


    @Override
    public String toString() {
        return "RecipeStackManager{" +
                "listOfRecipes=" + listOfRecipes +
                '}';
    }
}
