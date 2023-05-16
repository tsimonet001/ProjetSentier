package etu.demo.piv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PageAccueil extends AppCompatActivity {
    private Button buttonAdministrateur;
    private Button buttonSite;
    private Button buttonParcours;
    private Button buttonQuizz;
    private ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagedacceuil);

        buttonAdministrateur = findViewById(R.id.administrateur);
        buttonSite = findViewById(R.id.site);
        buttonParcours = findViewById(R.id.parcours);
        buttonQuizz = findViewById(R.id.quizz);
        imageViewLogo = findViewById(R.id.imageViewlogo);

        imageViewLogo.setImageResource(R.drawable.logo);

        buttonAdministrateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigez l'utilisateur vers une nouvelle activité pour afficher la liste des QR codes
                Intent intent = new Intent(PageAccueil.this, Administrateur.class);
                startActivity(intent);
            }
        });

        buttonSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigez l'utilisateur vers une nouvelle activité pour afficher la liste des QR codes
                Intent intent = new Intent(PageAccueil.this, Site.class);
                startActivity(intent);
            }
        });

        buttonParcours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigez l'utilisateur vers une nouvelle activité pour afficher la liste des QR codes
                Intent intent = new Intent(PageAccueil.this, Parcours.class);
                startActivity(intent);
            }
        });

        buttonQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigez l'utilisateur vers une nouvelle activité pour afficher la liste des QR codes
                Intent intent = new Intent(PageAccueil.this, Quizz.class);
                startActivity(intent);
            }
        });
    }
}
