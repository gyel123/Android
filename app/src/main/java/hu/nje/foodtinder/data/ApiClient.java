package hu.nje.foodtinder.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://api.spoonacular.com/";
    private static SpoonacularService apiService;

    public static SpoonacularService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(SpoonacularService.class);
        }
        return apiService;
    }
}
