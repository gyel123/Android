package hu.nje.foodtinder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hu.nje.foodtinder.Listeners.RecipeCliclListener;
import hu.nje.foodtinder.Models.Recipe;
import hu.nje.foodtinder.R;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {

    Context context;
    List<Recipe> list;
    RecipeCliclListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeCliclListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_time.setText(list.get(position).readyInMinutes +" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);
        holder.textView_recipe.setText(list.get(position).instructions);

        holder.random_list_container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView  textView_title, textView_time,textView_recipe;
    ImageView imageView_food;


    public RandomRecipeViewHolder(@NonNull View itemView){
        super (itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_time = itemView.findViewById(R.id.textView_time);
        imageView_food = itemView.findViewById(R.id.imageView_food);
        textView_recipe = itemView.findViewById(R.id.textView_recipe);
    }

}
