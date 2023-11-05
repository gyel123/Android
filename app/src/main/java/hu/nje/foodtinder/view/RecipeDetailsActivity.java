package hu.nje.foodtinder.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hu.nje.foodtinder.Listeners.RecipeDetailsListener;
import hu.nje.foodtinder.Models.RecipeDetailsResponse;
import hu.nje.foodtinder.R;
import hu.nje.foodtinder.data.RequestManager;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView textView_mealName, textView_mealSummary;
    ImageView imageView_mealImage;

    RequestManager manager;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.GetRecipeDetails(recipeDetailsListener,id);
        dialog= new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();
    }

    private void findViews() {
        textView_mealName = findViewById(R.id.textView_mealName);
        textView_mealSummary = findViewById(R.id.textView_mealSummary);
        imageView_mealImage = findViewById(R.id.imageView_mealImage);
    }
    private final RecipeDetailsListener recipeDetailsListener =new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_mealName.setText(response.title);
            textView_mealSummary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_mealImage);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    };
}