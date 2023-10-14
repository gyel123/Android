package hu.nje.foodtinder;

import hu.nje.foodtinder.data.ApiClient;
import hu.nje.foodtinder.response.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    public void searchRecipes(String apiKey, String query, int number) {
        Call<RecipeResponse> call = ApiClient.getApiService().searchRecipes(apiKey, query, number);

        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()) {
                    // Handle data
                    RecipeResponse recipesResponse = response.body();
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                // Handle failure somehow

            }


        });
    }
}
