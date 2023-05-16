package etu.demo.piv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoireActivity extends AppCompatActivity {
    private Button buttonvisite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histoire);
        buttonvisite = findViewById(R.id.button_visite);
        buttonvisite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigez l'utilisateur vers une nouvelle activité pour afficher la liste des espèces
                Intent intent = new Intent(HistoireActivity.this, VisiteActivity.class);
                startActivity(intent);
            }
        });
    }
}
