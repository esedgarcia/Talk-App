package com.example.talk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private EditText etxt;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this,this);
        etxt = (EditText) findViewById(R.id.etxescribir);
        boton = (Button) findViewById(R.id.btnreproducir);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hablar();
            }

        });

        //ActionBar para mostrar el logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    @Override
    public void onInit(int status) {
      if(status == TextToSpeech.SUCCESS){
        int resultadoTS = tts.setLanguage(Locale.getDefault());
        if(resultadoTS == TextToSpeech.LANG_NOT_SUPPORTED ||
        resultadoTS == TextToSpeech.LANG_MISSING_DATA){
            Log.e("TextToSpeech","Este lenguaje no puede ser utilizado");
        } else{
            boton.setEnabled(true);
            hablar();
        }
      } else{
          Log.e("TextToSpeech","Error al iniciar el lenguaje");
      }
    }

    private void hablar() {
        String texto = etxt.getText().toString();
        tts.speak(texto,TextToSpeech.QUEUE_FLUSH,null);
    }
}