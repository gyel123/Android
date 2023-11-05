package hu.nje.foodtinder.Listeners;

import hu.nje.foodtinder.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
