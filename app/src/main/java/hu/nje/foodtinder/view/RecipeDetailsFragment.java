package hu.nje.foodtinder.view;


import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hu.nje.foodtinder.Listeners.RecipeDetailsListener;
import hu.nje.foodtinder.Models.RecipeDetailsResponse;
import hu.nje.foodtinder.R;
import hu.nje.foodtinder.data.RequestManager;

public class RecipeDetailsFragment extends Fragment {
    int id;
    TextView textView_mealName, textView_mealSummary;
    ImageView imageView_mealImage;

    RequestManager manager;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        textView_mealName = view.findViewById(R.id.textView_mealName);
        textView_mealSummary = view.findViewById(R.id.textView_mealSummary);
        imageView_mealImage = view.findViewById(R.id.imageView_mealImage);

        Bundle args = getArguments();
        if (args != null) {
            id = Integer.parseInt(args.getString("id"));
        }
        manager = new RequestManager(getContext());
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading Details...");
        dialog.show();

        manager.GetRecipeDetails(recipeDetailsListener, id);

        return view;

    }
    private final RecipeDetailsListener recipeDetailsListener =new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            if (dialog != null) {
                dialog.dismiss();
            }
            textView_mealName.setText(response.title);
            textView_mealSummary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_mealImage);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
        }
    };
    public static RecipeDetailsFragment newInstance(String recipeId) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putString("id", recipeId);
        fragment.setArguments(args);
        return fragment;
    }
}