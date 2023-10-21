package hu.nje.foodtinder.adapters;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.nje.foodtinder.R;
import hu.nje.foodtinder.model.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{
    private List<Recipe> recipes;
    private IOnRecipeClickListener listener;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Check for where we want to display it
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_details, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position);
        holder.bind(currentRecipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setOnRecipeClickListener(IOnRecipeClickListener listener) {
        this.listener = listener;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeTitle;

        RecipeViewHolder(View itemView) {
            super(itemView);
            //check where to display the recipetitle
            recipeTitle = itemView.findViewById(R.id.recipeName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onRecipeClick(recipes.get(getAdapterPosition()));
                    }
                }
            });
        }

        void bind(Recipe recipe) {
            recipeTitle.setText(recipe.getTitle());
        }
    }
    public interface IOnRecipeClickListener {
        void onRecipeClick (Recipe recipe);
    }
}
