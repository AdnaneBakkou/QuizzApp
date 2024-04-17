package com.example.quizapp_bakkou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Trouver les boutons par leurs ID
        Button bLogin = findViewById(R.id.bLogin);
        Button bAdd = findViewById(R.id.bAdd);

        // Configurer un écouteur pour le bouton Login
        bLogin.setOnClickListener(v -> {
            Intent loginIntent = new Intent(StartActivity.this, MainActivity.class); // Remplacez LoginActivity.class par la classe de votre activité de connexion
            startActivity(loginIntent);
        });

        // Configurer un écouteur pour le bouton Add Quiz
        bAdd.setOnClickListener(v -> {
            Intent addIntent = new Intent(StartActivity.this, AddQuizz.class); // Remplacez AddQuizz.class par la classe de votre activité d'ajout de quiz
            startActivity(addIntent);
        });
    }
}
