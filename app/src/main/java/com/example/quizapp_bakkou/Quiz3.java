package com.example.quizapp_bakkou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz3 extends AppCompatActivity {
    //declaration
    RadioGroup rg;
    RadioButton rb;
Button bNext;
int score;
String RepCorrect="Non";
// fin declaration
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);
        // chercher id de groupe radio
        rg=(RadioGroup) findViewById(R.id.rg);
        // chercher button next
        bNext = findViewById(R.id.bNext);

        Intent intent = getIntent();
        // get score de l'activite precedente sinon 0
        score = intent.getIntExtra("score",0);
//Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb = findViewById(rg.getCheckedRadioButtonId());
                // si on a pas checher un radio => toast
                if(rg.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(),"Merci de choisir une reponse",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(getApplicationContext(),rb.getText().toString(),Toast.LENGTH_SHORT).show();
                    // if reponse correte score +1
                    if(rb.getText().toString().equals(RepCorrect)){
                        score+=1;
                    }

                    Intent intent = new Intent(Quiz3.this,Quiz4.class);
                    // prendre le score pour l'activite suivante par putExtra
                    intent.putExtra("score",score);
                    // start activity qui prende intent qui a comme params le quiz suviant
                    startActivity(intent);
                    //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    //annimavtion de passage ..
                    overridePendingTransition(R.anim.exit,R.anim.entry);
                    finish();
                }

            }
        });

    }
}
