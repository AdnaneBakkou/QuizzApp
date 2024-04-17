package com.example.quizapp_bakkou;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import java.util.HashMap;
import java.util.Objects;

//programme java pour representer une activite de type appcompactactivity qui va retrourener un layout  setContentView(R.layout.activity_main);
public class MainActivity extends AppCompatActivity {
    //Step 1: Declaration
    EditText etMail, etPassword;
    Button bLogin, googleAuth;
    FirebaseAuth auth;

    TextView tvRegister;
    int RC_SIGN_IN = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Step 2: Recuperation des ids


        etMail = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        //Step 3: Association de listeners
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etMail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Email or password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Authentification avec Firebase
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Connexion réussie, mise à jour de l'UI avec les informations de l'utilisateur
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String email = user.getEmail();

                                        Intent intent = new Intent(MainActivity.this, Quiz1.class);
                                        intent.putExtra("userEmail", email); // Passer l'email avec l'intent
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Si la connexion échoue, afficher un message à l'utilisateur
                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 4: Traitement
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });


    }


    }


