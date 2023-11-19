package hu.nje.foodtinder.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hu.nje.foodtinder.Listeners.RecipeClickListener;
import hu.nje.foodtinder.Models.Meal;
import hu.nje.foodtinder.R;


public class SavedRecipesAdapter extends RecyclerView.Adapter<SavedRecipesViewHolder> {

    private List<Meal> meals;
    Context context;
    RecipeClickListener listener;

    public SavedRecipesAdapter( Context context, List<Meal> meals, RecipeClickListener listener) {
        this.meals = meals;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SavedRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_recipe, parent, false);
        return new SavedRecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedRecipesViewHolder holder, int position) {
        holder.recipeTitleTextView.setText(meals.get(position).title);
        Picasso.get().load(meals.get(position).imageUrl).into(holder.recipeImageView);

        Meal meal = meals.get(position);
        holder.recipeTitleTextView.setText(meal.title);
        Picasso.get().load(meal.imageUrl).into(holder.recipeImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(meal.id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
class SavedRecipesViewHolder extends RecyclerView.ViewHolder {

    TextView recipeTitleTextView;
    ImageView recipeImageView;

    public SavedRecipesViewHolder(@NonNull View itemView) {
        super(itemView);
        recipeTitleTextView = itemView.findViewById(R.id.recipeTitleTextView);
        recipeImageView = itemView.findViewById(R.id.recipeImageView);
    }
}


