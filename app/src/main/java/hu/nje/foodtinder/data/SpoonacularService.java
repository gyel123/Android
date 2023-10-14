package hu.nje.foodtinder.data;
import hu.nje.foodtinder.response.RecipeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpoonacularService {
    @GET("recipes/complexSearch")
    Call<RecipeResponse> searchRecipes(@Query("apiKey") String apiKey,
                                        @Query("query") String query,
                                        @Query("number") int number);
}
// APY KEY : a1b6d09b561e4620a35690b7a15434f6
