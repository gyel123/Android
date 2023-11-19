package hu.nje.foodtinder.view;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
//own
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hu.nje.foodtinder.Adapters.RandomRecipeAdapter;
import hu.nje.foodtinder.Listeners.RandomRecipeResponseListener;
import hu.nje.foodtinder.Listeners.RecipeClickListener;
import hu.nje.foodtinder.Models.Meal;
import hu.nje.foodtinder.R;
import hu.nje.foodtinder.Response.RandomRecipeApiResponse;
import hu.nje.foodtinder.data.FoodTinderDatabase;
import hu.nje.foodtinder.data.RequestManager;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button savedRecipes = findViewById(R.id.button_savedRecipes);
        savedRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeListFragment fragment = new RecipeListFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();

                recyclerView.setVisibility(View.GONE); // Assuming recyclerView is declared and accessible in your activity
                findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
            }
        });


        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);

        dialog.show();

        final OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    findViewById(R.id.fragment_container).setVisibility(View.GONE);
                }
            }
        };

        getOnBackPressedDispatcher().addCallback(this,backPressedCallback);

    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes,recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    public void reloadData(){
        dialog.show();
        manager.getRandomRecipes(randomRecipeResponseListener);
    }

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            RecipeDetailsFragment fragment = RecipeDetailsFragment.newInstance(id);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, null)
                    .addToBackStack(null)
                    .commit();

            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        }
    };

    public void saveMealToDatabase(int id, String title, String imageUrl) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Meal meal = new Meal();
                meal.id = id;
                meal.title = title;
                meal.imageUrl = imageUrl;

                FoodTinderDatabase db = Room.databaseBuilder(getApplicationContext(),
                        FoodTinderDatabase.class, "meal_database").build();
                db.mealDao().insert(meal);
            }
        });
    }
}




