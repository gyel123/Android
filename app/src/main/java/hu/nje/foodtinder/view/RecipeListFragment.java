package hu.nje.foodtinder.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hu.nje.foodtinder.Adapters.SavedRecipesAdapter;
import hu.nje.foodtinder.Listeners.RecipeClickListener;
import hu.nje.foodtinder.Models.Meal;
import hu.nje.foodtinder.R;
import hu.nje.foodtinder.data.FoodTinderDatabase;

public class RecipeListFragment extends Fragment {

    private RecyclerView recyclerView;
    private SavedRecipesAdapter adapter;
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            // Logic to navigate to the RecipeDetailsFragment
            RecipeDetailsFragment detailsFragment = RecipeDetailsFragment.newInstance(id);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, detailsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewRecipeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Context context = getContext();
        if (context != null) {
           FoodTinderDatabase db = Room.databaseBuilder(context, FoodTinderDatabase.class, "meal_database").build();
        }
        loadSavedMeals();

        return view;
    }




    private void loadSavedMeals() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                FoodTinderDatabase db = Room.databaseBuilder(getContext(),
                        FoodTinderDatabase.class, "meal_database").build();
                List<Meal> meals = db.mealDao().getAllMeals();

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // This code will run on the main thread.
                        adapter = new SavedRecipesAdapter(getContext(), meals, recipeClickListener);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });

    }



}
