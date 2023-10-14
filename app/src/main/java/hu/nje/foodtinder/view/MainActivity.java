package hu.nje.foodtinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gomb referenciájának megszerzése az azonosító alapján
        Button buttonNavigate = findViewById(R.id.buttonSaveRecipes);

        // Gomb kattintáskezelőjének beállítása
        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Átnavigálás a fragmentre
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new irfragment(v)) // MyFragment helyettesítése a megfelelő fragmenttel
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}



