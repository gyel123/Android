package hu.nje.foodtinder.Listeners;

import hu.nje.foodtinder.Response.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);
}
