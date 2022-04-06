package com.example.disneyblindtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class Difficulte extends AppCompatActivity {
    // Constantes
    private final static String TAG = "Difficulte";
    public final static String Mes_Prefs = "Mes_Prefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_difficulte);


//------------------------------//Récupération des différents éléments de l'activité//---------------------------------------------//

        //Récupération des ID des bouton de changement de difficulté
        Button btnFacile = findViewById(R.id.btnfacile);
        Button btnMoyen = findViewById(R.id.btnmoyen);
        Button btnDifficile = findViewById(R.id.btnDifficile);

//------------------------------//Traitements effectués à l'ouverture de l'activité//---------------------------------------------//

        /**
         * Fonction qui change la difficulte en moyen
         */
        btnMoyen.setOnClickListener(v -> {
            Log.i(TAG, "clicMoyen");
            String strbtnMoyen = "intermediaire";
            String difficulte = loaddifficulte();

            SharedPreferences mesPrefs = getSharedPreferences(Difficulte.Mes_Prefs, MODE_PRIVATE);
            difficulte = mesPrefs.getString("Difficulte", "");

            if (!strbtnMoyen.equals(difficulte)) {
                savedifficulte(strbtnMoyen);


                Intent intent = new Intent(Difficulte.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(Difficulte.this,"Vous êtes déjà en intermédiaire", Toast.LENGTH_SHORT).show();
            }


        });
        /**
         * Fonction qui change la difficulte en facile
         */
        btnFacile.setOnClickListener(v -> {
            Log.i(TAG, "clicfacile");
            String strbtnFacile= "facile";
            String difficulte = loaddifficulte();

            SharedPreferences mesPrefs = getSharedPreferences(Difficulte.Mes_Prefs, MODE_PRIVATE);
            difficulte = mesPrefs.getString("Difficulte", "");

            if (!strbtnFacile.equals(difficulte)) {
                savedifficulte(strbtnFacile);


                Intent intent = new Intent(Difficulte.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(Difficulte.this,"Vous êtes déjà en facile", Toast.LENGTH_SHORT).show();
            }


        });
        /**
         * Fonction qui change la difficulte en difficile
         */
        btnDifficile.setOnClickListener(v -> {
            Log.i(TAG, "clicdifficile");
            String strbtnDifficile= "difficile";
            String difficulte = loaddifficulte();

            SharedPreferences mesPrefs = getSharedPreferences(Difficulte.Mes_Prefs, MODE_PRIVATE);
            difficulte = mesPrefs.getString("Difficulte", "");

            if (!strbtnDifficile.equals(difficulte)) {
                savedifficulte(strbtnDifficile);


                Intent intent = new Intent(Difficulte.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(Difficulte.this,"Vous êtes déjà en difficile", Toast.LENGTH_SHORT).show();
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
        Configuration config = new Configuration();
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

    /**
     * Fonction qui enregistre la difficulte dans les préférences
     * @param difficulte la difficulte choisie
     */
    public void savedifficulte(String difficulte) {
        Log.i(TAG, "savedifficulte");
        SharedPreferences prefs = getSharedPreferences("Mes_Prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Difficulte", difficulte);
        editor.apply();
    }

    /** Fonction executée au lancement, elle va récupérer la dernière langue choisie dans le fichier préférences
     *
     */
    public String loaddifficulte() {
        Log.i(TAG, "loaddifficulte");
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF_DIFFICULTE", Activity.MODE_PRIVATE);
        String difficulte = prefs.getString("Difficulte", "");

        return difficulte;

    }
}