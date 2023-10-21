package hu.nje.foodtinder.data;
import hu.nje.foodtinder.adapters.RecipeAdapter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISpoonacularService {
    @GET("recipes/complexSearch")
    Call<RecipeAdapter> searchRecipes(@Query("apiKey") String apiKey,
                                      @Query("query") String query,
                                      @Query("number") int number);
}
