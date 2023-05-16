package etu.demo.piv1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;
import java.util.ArrayList;
import java.util.List;

public class VisiteActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMapClickListener {

    private ImageView imageViewPlan;
    private Button buttonQRCode;
    private Button buttonEspece;
    private Button buttonAide;

    private Polygon zone1Polygon;
    private MapView mapView;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private Marker marker1, marker2, marker3, marker4, marker5, marker6, marker7, marker8, marker9, marker10,
    marker11, marker12, marker13, marker14, marker15, marker16, marker17, marker18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visite);

        // Initialiser la vue MapView
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Initialiser le LocationManager pour obtenir la position de l'appareil
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Vérifier les permissions pour accéder à la localisation de l'appareil
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Les permissions sont déjà accordées
        } else {
            // Demander les permissions
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        imageViewPlan = findViewById(R.id.image_view_plan);
        buttonQRCode = findViewById(R.id.button_qr_code);
        buttonEspece = findViewById(R.id.button_espece);
        buttonAide = findViewById(R.id.button_aide);

        // Chargez l'image du plan à partir de la ressource drawable
        imageViewPlan.setImageResource(R.drawable.plan);

        // Définissez les actions pour les boutons
        buttonQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigez l'utilisateur vers une nouvelle activité pour afficher la liste des QR codes
                Intent intent = new Intent(VisiteActivity.this, QRCodeActivity.class);
                startActivity(intent);
            }
        });

        buttonEspece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigez l'utilisateur vers une nouvelle activité pour afficher la liste des espèces
                Intent intent = new Intent(VisiteActivity.this, EspeceActivity.class);
                startActivity(intent);
            }
        });

        buttonAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Affichez une boîte de dialogue pour afficher l'aide
                AlertDialog.Builder builder = new AlertDialog.Builder(VisiteActivity.this);
                builder.setTitle("Aide");
                builder.setMessage("Voici les consignes à suivre : \n" +
                        "\n"+
                        "- Renseignez-vous sur les conditions météorologiques avant de partir et emportez des vêtements et des chaussures appropriés.\n" +
                        "\n"+
                        "- Informez une personne de confiance de votre itinéraire et de l'heure de votre retour prévue.\n" +
                        "\n"+
                        "- Évitez de partir seul(e) si possible.\n" +
                        "\n"+
                        "- Restez sur les sentiers balisés et évitez de prendre des raccourcis à travers la forêt.\n" +
                        "\n"+
                        "- Évitez de toucher ou de manger des plantes et des champignons que vous ne connaissez pas.\n" +
                        "\n"+
                        "- Gardez une distance de sécurité avec les animaux sauvages et ne les nourrissez pas.\n" +
                        "\n"+
                        "- Emportez de l'eau et des collations pour vous hydrater et vous nourrir.\n" +
                        "\n"+
                        "- Assurez-vous d'avoir une trousse de premiers soins avec vous, comprenant notamment un désinfectant, des pansements et un téléphone portable chargé en cas d'urgence.\n" +
                        "\n"+
                        "- Respectez la nature et ne laissez pas de déchets derrière vous.\n" +
                        "\n"+
                        "- Évitez de faire du feu et respectez les règles locales en matière de feux de camp.\n" +
                        "\n"+
                        "Il est important de se rappeler que la sécurité en forêt dépend en grande partie de la prudence et de la préparation du visiteur. Soyez conscient de votre environnement et soyez prêt à faire face à toute situation d'urgence qui pourrait survenir.");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
        // Initialiser les boutons de zoom
        Button buttonZoomIn = findViewById(R.id.buttonZoomIn);
        buttonZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        Button buttonZoomOut = findViewById(R.id.buttonZoomOut);
        buttonZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Les permissions ont été accordées
            } else {
                // Les permissions ont été refusées, afficher un message d'erreur ou fermer l'application
            }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Changer le type de carte en satellite
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Activer la couche My Location
        googleMap.setMyLocationEnabled(true);

        // Obtenir la dernière position connue de l'appareil
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }

        // Ajouter les marqueurs des individus
        marker1 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.331226, -0.704411)).title("Départ Zone 1"));
        marker2 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.331000, -0.704186)).title("Milieu Zone 1"));
        marker3 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.330043, -0.705115)).title("Fin Zone 1"));
        marker4 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.330752, -0.704464)).title("Départ Zone 2"));
        marker5 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.330237, -0.704185)).title("Zone 2.1"));
        marker6=  googleMap.addMarker(new MarkerOptions().position(new LatLng(43.329834, -0.704559)).title("Zone 2.2"));
        marker7 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.329564, -0.704504)).title("Zone 2.3"));
        marker8 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.329491, -0.704567)).title("Zone 2.4"));
        marker9 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.329936, -0.705110)).title("Zone 2.6"));
        marker10 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.329629, -0.704980)).title("Zone 2.5"));
        marker11 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.329602, -0.704342)).title("Zone 3"));
        marker12 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.329378, -0.704571)).title("Zone 3 "));
        marker13 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.328431, -0.700926)).title("Zone 3"));
        marker14 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.328759, -0.700912)).title("Zone 3"));
        marker15 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.328650, -0.703664)).title("Zone 4"));
        marker16=  googleMap.addMarker(new MarkerOptions().position(new LatLng(43.328381, -0.703830)).title("Zone 4"));
        marker17 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.328108, -0.702956)).title("Zone 4"));
        marker18 = googleMap.addMarker(new MarkerOptions().position(new LatLng(43.328358, -0.702377)).title("Zone 4"));


        // Masquer les marqueurs
        marker1.setVisible(false);
        marker2.setVisible(false);
        marker3.setVisible(false);
        marker4.setVisible(false);
        marker5.setVisible(false);
        marker6.setVisible(false);
        marker7.setVisible(false);
        marker8.setVisible(false);
        marker9.setVisible(false);
        marker10.setVisible(false);
        marker11.setVisible(false);
        marker12.setVisible(false);
        marker13.setVisible(false);
        marker14.setVisible(false);
        marker15.setVisible(false);
        marker16.setVisible(false);
        marker17.setVisible(false);
        marker18.setVisible(false);



        // Ajouter un click listener pour la zone
        googleMap.setOnMapClickListener(this);

        // Mettre à jour la position des marqueurs à chaque changement de position
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);

        // Dessiner une zone entre le marker1, le marker2 et le marker3
        PolygonOptions polygonOptions = new PolygonOptions()
                .add(marker1.getPosition())
                .add(marker2.getPosition())
                .add(marker3.getPosition())
                .strokeColor(Color.RED)
                .fillColor(Color.argb(50, 255, 0, 0));
        zone1Polygon = googleMap.addPolygon(polygonOptions);

        PolygonOptions polygonOptions2 = new PolygonOptions()
                .add(marker4.getPosition())
                .add(marker5.getPosition())
                .add(marker6.getPosition())
                .add(marker7.getPosition())
                .add(marker8.getPosition())
                .add(marker10.getPosition())
                .add(marker9.getPosition())
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(50, 0, 255, 0)); // Modifier la valeur de la couleur ici
        googleMap.addPolygon(polygonOptions2);

        PolygonOptions polygonOptions3 = new PolygonOptions()
                .add(marker11.getPosition())
                .add(marker12.getPosition())
                .add(marker13.getPosition())
                .add(marker14.getPosition())
                .strokeColor(Color.CYAN)
                .fillColor(Color.parseColor("#80ADD8E6")); // Utiliser la couleur bleu clair ici
        googleMap.addPolygon(polygonOptions3);

        PolygonOptions polygonOptions4 = new PolygonOptions()
                .add(marker15.getPosition())
                .add(marker16.getPosition())
                .add(marker17.getPosition())
                .add(marker18.getPosition())
                .strokeColor(Color.parseColor("#FFFFA500"))
                .fillColor(Color.parseColor("#80FFA500")); // Utiliser la couleur bleu clair ici
        googleMap.addPolygon(polygonOptions4);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (isInZone1(latLng)) {
            Toast.makeText(this, "Zone 1", Toast.LENGTH_SHORT).show();
        } else if (isInZone2(latLng)) {
            Toast.makeText(this, "Zone 2", Toast.LENGTH_SHORT).show();
        } else if (isInZone3(latLng)) {
            Toast.makeText(this, "Zone 3", Toast.LENGTH_SHORT).show();
        } else if (isInZone4(latLng)) {
            Toast.makeText(this, "Zone 4", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInZone1(LatLng latLng) {
        return zone1Polygon != null && PolyUtil.containsLocation(latLng, zone1Polygon.getPoints(), false);
    }

    private boolean isInZone2(LatLng latLng) {
        LatLngBounds zone2 = new LatLngBounds(
                new LatLng(43.329378, -0.704571), // Sud-Ouest de la zone 2
                new LatLng(43.330752, -0.704464)  // Nord-Est de la zone 2
        );
        return zone2.contains(latLng);
    }

    private boolean isInZone3(LatLng latLng) {
        LatLngBounds zone3 = new LatLngBounds(
                new LatLng(43.328108, -0.702956), // Sud-Ouest de la zone 3
                new LatLng(43.329602, -0.704342)  // Nord-Est de la zone 3
        );
        return zone3.contains(latLng);
    }

    private boolean isInZone4(LatLng latLng) {
        LatLngBounds zone4 = new LatLngBounds(
                new LatLng(43.328108, -0.703830), // Sud-Ouest de la zone 4
                new LatLng(43.328650, -0.702377)  // Nord-Est de la zone 4
        );
        return zone4.contains(latLng);
    }


    @Override
    public void onLocationChanged(Location location) {
        // Mettre à jour la position de l'utilisateur sur la carte
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
