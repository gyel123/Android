package hu.nje.foodtinder.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;
//own
import hu.nje.foodtinder.R;
public class MainActivity extends AppCompatActivity {




    private void loadFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        fragmentTransaction.commit();


    }



    private void loadFragmentAndAddToBackStack(Fragment fragment, String tag) {
        loadFragment(fragment, tag, true);
    }

    private void loadFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }
    private Fragment food_details;
    private Fragment masodikablak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         food_details= new Food_DetailsJ();
         masodikablak= new Masodikablak();


        Button switchButton = findViewById(R.id.switchButton);

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch between fragments
                if (food_details.isVisible()) {
                    switchFragment(masodikablak);
                } else {
                    switchFragment(food_details);
                }
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null); // Optional, for back navigation
        transaction.commit();
    }
}
