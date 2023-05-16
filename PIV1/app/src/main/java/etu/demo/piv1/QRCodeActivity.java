package etu.demo.piv1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QRCodeActivity extends AppCompatActivity {
    RecyclerView rcvqrcode;
    String[] titres;
    int[] images = {R.drawable.blaireau, R.drawable.chevreuil, R.drawable.chevreuil2, R.drawable.chevreuil3, R.drawable.fouine2, R.drawable.airelledesmarais, R.drawable.ancoliecommune,
            R.drawable.asphodeleblanc, R.drawable.aulneglutineux, R.drawable.boutondor, R.drawable.cardaminedespres, R.drawable.chevrefeuille, R.drawable.cirsecommun, R.drawable.epiairedesbois,
            R.drawable.feuillescataloguega, R.drawable.fichescatalogue, R.drawable.fichesibgn, R.drawable.fichesoiseaux, R.drawable.foindodeur, R.drawable.grandeortie, R.drawable.houx,
            R.drawable.laiteronmaraicher, R.drawable.laurier, R.drawable.millepertuis, R.drawable.pissenlit, R.drawable.pulmonaire, R.drawable.raiponceenepi, R.drawable.ronce};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        rcvqrcode = findViewById(R.id.rcv_qrcode);
        titres = getResources().getStringArray(R.array.titres_qrcodes);

        QRCodeAdapter adapter = new QRCodeAdapter(getApplicationContext(),
                titres,images);

        rcvqrcode.setHasFixedSize(true);//true pour prendre tout l'espace restant dans l
        rcvqrcode.setAdapter(adapter);
        rcvqrcode.setLayoutManager(new LinearLayoutManager(this));
    }
}
