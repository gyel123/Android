package hu.nje.foodtinder.view;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//own
import hu.nje.foodtinder.Adapters.RandomRecipeAdapter;
import hu.nje.foodtinder.Listeners.RandomRecipeResponseListener;
import hu.nje.foodtinder.Listeners.RecipeCliclListener;
import hu.nje.foodtinder.R;
import hu.nje.foodtinder.Response.RandomRecipeApiResponse;
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

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);

        dialog.show();

        final OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                    recyclerView.setVisibility(View.VISIBLE);
                    findViewById(R.id.fragment_container).setVisibility(View.GONE);
                    reloadData();
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
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes,recipeCliclListener);
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

    private final RecipeCliclListener recipeCliclListener = new RecipeCliclListener() {
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
}




