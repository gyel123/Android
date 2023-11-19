package hu.nje.foodtinder.Listeners;

import hu.nje.foodtinder.Models.RecipeDetailsResponse;

public interface RecipeListListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
