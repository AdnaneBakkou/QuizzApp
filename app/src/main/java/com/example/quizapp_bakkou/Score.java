package com.example.quizapp_bakkou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Score extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {
    Button bLogout, bTry;
    ProgressBar progressBar;
    TextView tvScore;
    int score;
    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        progressBar = findViewById(R.id.progressBar);
        bLogout = findViewById(R.id.bLogout);
        bTry = findViewById(R.id.bTry);
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);  // Retrieve the score
        int progress = 100 * score / 5;  // Assuming the total possible score is 5
        progressBar.setMax(100);  // Set the maximum progress to 100
        progressBar.setProgress(progress);  // Set the calculated progress
// Assuming 'score' is an integer variable holding the score value
         tvScore = findViewById(R.id.tvScore);
        tvScore.setText("Votre score est : " + progress + "%");

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Score.this, MainActivity.class));
            }
        });
        bTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Score.this, Quiz1.class));
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false to not consume the event and let the default behavior occur
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull android.location.Location location) {
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.clear(); // Efface tous les marqueurs et overlays de la carte

        // Chargement de l'image Bitmoji en tant que Bitmap
        Bitmap bitmojiImage = BitmapFactory.decodeResource(getResources(), R.drawable.bitt);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmojiImage);

        // Création et ajout du marqueur avec l'icône personnalisée
        mMap.addMarker(new MarkerOptions()
                .position(myLocation)
                .title("Vous êtes ici")
                .icon(icon)
                .anchor(0.5f, 1.0f)); // Définit le point d'ancrage au centre bas de l'icône

        // Centre et zoome la caméra sur la nouvelle position du marqueur
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            } else {
                Toast.makeText(this, "Permission de localisation refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
