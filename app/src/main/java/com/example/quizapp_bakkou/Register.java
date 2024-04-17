package com.example.quizapp_bakkou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {


EditText etName, etMail , etPassword , etPassword1;
Button bRegister;
FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        auth = FirebaseAuth.getInstance();
        etName = findViewById(R.id.etName) ;
        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        etPassword1 = findViewById(R.id.etPassword1);
        bRegister = findViewById(R.id.bRegister);

// show password

        final EditText etPassword = findViewById(R.id.etPassword);
        etPassword.setOnTouchListener(new View.OnTouchListener() {
            boolean isPasswordVisible = false;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int rightDrawable = 2; // L'icône à droite est à l'index 2
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[rightDrawable].getBounds().width())) {
                        isPasswordVisible = !isPasswordVisible;
                        etPassword.setInputType(isPasswordVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                        etPassword.setSelection(etPassword.getText().length()); // Pour replacer le curseur à la fin du texte
                        return true;
                    }
                }
                return false;
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, email, password, password1;
                email = String.valueOf(etMail.getText());
                name = String.valueOf(etName.getText());
                password = String.valueOf(etPassword.getText());
                password1 = String.valueOf(etPassword1.getText());

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Register.this, "Enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(Register.this, "Confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Account created", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(Register.this, "auth failed", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });
    }}