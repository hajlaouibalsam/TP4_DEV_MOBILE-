 package com.example.mesuredeniveaudeglycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mesuredeniveaudeglycemie.R;
import com.example.mesuredeniveaudeglycemie.controller.Controller;

 public class MainActivity extends AppCompatActivity {
     private EditText etValeur;
     private TextView tvAge, tvReponse;
     private SeekBar sbAge;
     private RadioButton rbIsFasting, rbIsNotFasting;
     private Button btnConsulter;
     //private Controller controller = Controller.getInstance();
     private Controller controller = new Controller();
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         init();

         sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 Log.i("Information", "onProgressChanged "+progress);
                 tvAge.setText("Votre âge : "+ progress);
             }
             @Override
             public void onStartTrackingTouch(SeekBar seekBar){}
             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {}
         });
         btnConsulter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.i("Information", "button cliqué");
                 int age;
                 float valeurMesuree;
                 boolean verifAge = false, verifValeur = false;
                 if(sbAge.getProgress()!=0)
                     verifAge = true;
                 else
                     Toast.makeText(MainActivity.this, "Veuillez saisir votre age !",
                             Toast.LENGTH_SHORT).show();
                 if(!etValeur.getText().toString().isEmpty())
                     verifValeur = true;
                 else
                     Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !",
                             Toast.LENGTH_LONG).show();
                 if(verifAge && verifValeur)
                 {
                     age = sbAge.getProgress();
                     valeurMesuree = Float.valueOf(etValeur.getText().toString());

                     controller.createPatient(age, valeurMesuree, rbIsFasting.isChecked());
                     tvReponse.setText(controller.getReponse());
                 }
             }
         });
     }
     private void init()
     {
         sbAge = findViewById(R.id.sbAge);
         tvAge = findViewById(R.id.tvAge);
         etValeur = findViewById(R.id.etValeur);
         rbIsFasting = findViewById(R.id.rbIsFasting);
         rbIsNotFasting = findViewById(R.id.rbIsNotFasting);
         btnConsulter = findViewById(R.id.btnConsulter);
         tvReponse = findViewById(R.id.tvResult);
     }
 }