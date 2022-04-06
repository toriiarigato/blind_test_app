package com.example.disneyblindtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;


public class Langues extends AppCompatActivity {
    // Constantes
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_option);

//------------------------------//Récupération des différents éléments de l'activité//---------------------------------------------//

        //Récupération des ID des bouton de changement de langue
        Button btnFran = findViewById(R.id.btnFran);
        Button btnEng = findViewById(R.id.btnEng);
        Button btnEsp = findViewById(R.id.btnEsp);



//------------------------------//Traitements effectués à l'ouverture de l'activité//---------------------------------------------//

        /**
         * Fonction qui change la langue et la configuration de l'application en anglais si l'événement est déclenché
         */
        btnEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "clicENG");
                Locale locale = new Locale("en");
                String strLocale = locale.toString();
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                SharedPreferences mesPrefs = getSharedPreferences(MainActivity.Mes_Prefs, MODE_PRIVATE);
                String language = mesPrefs.getString("Language", "");

                if (!language.equals(strLocale)) {
                    SharedPreferences.Editor edMesPrefs = mesPrefs.edit();
                    edMesPrefs.putString("Language", strLocale).apply();

                    Intent intent = new Intent(Langues.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(Langues.this,"You are already in english", Toast.LENGTH_SHORT).show();
                }


            }
        });
        /**
         * Fonction qui change la langue et la configuration de l'application en français si l'événement est déclenché
         */
        btnFran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "clicFRA");
                Locale locale = new Locale("fr");
                String strLocale = locale.toString();
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                SharedPreferences mesPrefs = getSharedPreferences(MainActivity.Mes_Prefs, MODE_PRIVATE);
                String language = mesPrefs.getString("Language", "");

                if (!language.equals(strLocale)) {
                    SharedPreferences.Editor edMesPrefs = mesPrefs.edit();
                    edMesPrefs.putString("Language", strLocale).apply();

                    Intent intent = new Intent(Langues.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(Langues.this,"Vous êtes déjà en français", Toast.LENGTH_SHORT).show();
                }


            }
        });
        /**
         * Fonction qui change la langue et la configuration de l'application en anglais si l'événement est déclenché
         */
        btnEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "clicESP");
                Locale locale = new Locale("es");
                String strLocale = locale.toString();
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                SharedPreferences mesPrefs = getSharedPreferences(MainActivity.Mes_Prefs, MODE_PRIVATE);
                String language = mesPrefs.getString("Language", "");

                if (!language.equals(strLocale)) {
                    SharedPreferences.Editor edMesPrefs = mesPrefs.edit();
                    edMesPrefs.putString("Language", strLocale).apply();

                    Intent intent = new Intent(Langues.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(Langues.this,"ya estas en español", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    /**
     * Quitte l'application
     * @param view
     */
    public void clickQuitter(View view) {
        Log.i(MainActivity.TAG, "clicQuitter");
        System.exit(0);
    }

    /**
     * Retour à la page précédente
     * @param v
     */
    public void clickRetour(View v){
        Log.i(MainActivity.TAG,"clickRetour");
        finish();
    }

    //Méthodes applicatives//

    /** Fonction executée au lancement, elle va récupérer la dernière langue choisie dans le fichier préférences
     *
     */
    public void loadLocale() {
        Log.i(TAG, "loadLocale");
        SharedPreferences prefs = getSharedPreferences("Mes_Prefs", Activity.MODE_PRIVATE);
        String language = prefs.getString("Language", "");
        changeLang(language);
    }

    /** Fonction qui adapte la langue en fonction de celle choisie dans les préférences
     * @param lang la langue présente dans les préférences
     */
    public void changeLang(String lang) {
        Log.i(TAG, "changeLang");
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

    }
    /**
     * Fonction qui enregistre la langue dans les préférences
     * @param lang la langue traitée
     */
    public void saveLocale(String lang) {
        Log.i(TAG, "saveLocale");
        SharedPreferences prefs = getSharedPreferences("Mes_Prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Language", lang);
        editor.apply();
    }
}