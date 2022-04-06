package com.example.disneyblindtest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public final static String Mes_Prefs = "Mes_Prefs";
    public Map tReponse = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("Mes_Prefs", Activity.MODE_PRIVATE);
        String language = prefs.getString("Language", "");

        //tReponse = ReponseManager.getAll(this,String.valueOf(language));
        //Log.i(MainActivity.TAG,tReponse.toString());

//------------------------------//Récupération des différents éléments de l'activité//---------------------------------------------//

        //Récupération de l'ID du bouton nouvelPartie
        Button nouvelPartie = findViewById(R.id.nouvelpartie);
        //Récupération de l'ID du bouton difficulté
        Button btndifficulte = findViewById(R.id.choixDifficulte);
        //Récupération de l'ID du bouton langues
        Button langues = findViewById(R.id.langue);

        //Déclaration du médiaplayer qui joue la petite musique des boutons d'accueil (nouvelle partie/continuer/langues)
            final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.rep_fairy);

//------------------------------//Traitements effectués à l'ouverture de l'activité//---------------------------------------------//

        //onclick sur le bouton nouvelPartie pour mettre en marche le player et aller vers l'activité ChoixJoueur
            nouvelPartie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    Log.i (MainActivity.TAG, "clickchoixjoeur");
                    Intent intent = new Intent(MainActivity.this, ChoixJoueur.class);
                    startActivity(intent);
                }
            });

        //onclick sur le bouton difficulte pour mettre en marche le player et aller à l'écran de choix de la difficulté
            btndifficulte.setOnClickListener(v -> {
                mediaPlayer.start();
                Log.i (MainActivity.TAG, "clickdifficulte");
                Intent intent = new Intent(MainActivity.this, Difficulte.class);

                startActivity(intent);
            });

        //onclick sur le bouton nouvelPartie pour mettre en marche le player et aller vers l'activité Langues
            langues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    Log.i (MainActivity.TAG, "clickLangues");
                    Intent intent = new Intent(MainActivity.this, Langues.class);

                    startActivity(intent);
                }
        });
    }

    //Méthodes applicatives//

    /**Fonction executée au lancement, elle va récupérer la dernière langue choisie dans le fichier préférences
     *
     */
    public void loadLocale() {
        Log.i(TAG, "loadLocale");
        SharedPreferences prefs = getSharedPreferences("Mes_Prefs", Activity.MODE_PRIVATE);
        String language = prefs.getString("Language", "");
        changeLang(language);
    }

    /**Fonction qui adapte la langue en fonction de celle choisie dans les préférences
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

    /**
     * Quitte l'application
     * @param view
     */
    public void clickQuitter(View view) {
        Log.i(MainActivity.TAG, "clicQuitter");
        finish();
        System.exit(0);
    }
}
