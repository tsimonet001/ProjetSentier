package etu.demo.piv1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EspeceActivity extends AppCompatActivity {
    RecyclerView rcvEspece;
    String[] titres, descriptions;
    int[] images = { R.drawable.adm, R.drawable.ac, R.drawable.ab, R.drawable.ag, R.drawable.bo, R.drawable.cdp, R.drawable.cf,
            R.drawable.cc, R.drawable.edb ,R.drawable.fdo, R.drawable.go, R.drawable.hx, R.drawable.lm, R.drawable.ln
            
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espece);
        rcvEspece = findViewById(R.id.rcv_espece);
        titres = getResources().getStringArray(R.array.titres_especes);
        descriptions = getResources().getStringArray(R.array.descriptions_especes);

        EspeceAdapter adapter = new EspeceAdapter(getApplicationContext(),
                titres, descriptions, images);

        rcvEspece.setHasFixedSize(true);//true pour prendre tout l'espace restant dans l
        rcvEspece.setAdapter(adapter);
        rcvEspece.setLayoutManager(new LinearLayoutManager(this));
    }
}
