package etu.demo.piv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import etu.demo.piv1.R;


public class Parcours extends AppCompatActivity {

    private Spinner spinnerLongueur;
    private Spinner spinnerInformation;
    private Spinner spinnerGroupe;
    private RadioGroup radioGroupVisite;
    private RadioButton radioIndividuelle;
    private RadioButton radioGroupe;
    private CheckBox checkboxHistoire;
    private Button buttonCommencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcours);

        spinnerLongueur = findViewById(R.id.spinner_longueur);
        spinnerInformation = findViewById(R.id.spinner_information);
        spinnerGroupe = findViewById(R.id.spinner_groupe);
        radioGroupVisite = findViewById(R.id.radio_group_visite);
        radioIndividuelle = findViewById(R.id.radio_individuelle);
        radioGroupe = findViewById(R.id.radio_groupe);
        checkboxHistoire = findViewById(R.id.checkbox_histoire);
        buttonCommencer = findViewById(R.id.button_commencer);

        ArrayAdapter<CharSequence> longueursAdapter = ArrayAdapter.createFromResource(this,
                R.array.longueurs, android.R.layout.simple_spinner_item);
        longueursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLongueur.setAdapter(longueursAdapter);

        ArrayAdapter<CharSequence> informationsAdapter = ArrayAdapter.createFromResource(this,
                R.array.informations, android.R.layout.simple_spinner_item);
        informationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInformation.setAdapter(informationsAdapter);

        ArrayAdapter<CharSequence> groupesAdapter = ArrayAdapter.createFromResource(this,
                R.array.groupes, android.R.layout.simple_spinner_item);
        groupesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroupe.setAdapter(groupesAdapter);

        radioGroupVisite.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_groupe) {
                    spinnerGroupe.setVisibility(View.VISIBLE);
                } else {
                    spinnerGroupe.setVisibility(View.GONE);
                }
            }
        });

        buttonCommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String longueurSelectionnee = spinnerLongueur.getSelectedItem().toString();
                String informationSelectionnee = spinnerInformation.getSelectedItem().toString();
                boolean estIndividuelle = radioIndividuelle.isChecked();
                String groupeSelectionne = spinnerGroupe.getSelectedItem().toString();
                boolean estHistoire = checkboxHistoire.isChecked();

                String message = "Vous avez choisi une visite de " + longueurSelectionnee +
                        " d'une quantité d'information " + informationSelectionnee;

                if (estIndividuelle) {
                    message += " en visite individuelle.";
                } else {
                    message += " en visite de groupe avec le groupe " + groupeSelectionne + ".";
                }

                if (estHistoire) {
                    message += " Mode histoire activé.";
                    // Créez une intention pour lancer l'activité HistoireActivity
                    Intent intent = new Intent(Parcours.this, HistoireActivity.class);
                    startActivity(intent);
                } else {
                    // Créez une intention pour lancer l'activité VisiteActivity
                    Intent intent = new Intent(Parcours.this, VisiteActivity.class);
                    startActivity(intent);
                }

                Toast.makeText(Parcours.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }
}
