package com.example.disneyblindtest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;

public class ChoixJoueur extends AppCompatActivity {

    //Constantes
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_MUSIC = "SHARED_PREF_MUSIC";
    public static final String TAG = "MainActivity";
    //Données membres
    private EditText nomJoueur;
    private String strNomjoueur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Charge les préférences de langues
        loadLocale();

        setContentView(R.layout.activity_choixjoueur);

        //Récupère l'ID du bouton btnCommencer
            Button btnCommencer = findViewById(R.id.commencer);

        //Onclick du bouton commencer si un nom de joueur a été entré va vers l'activité suivante et débute la partie
        // sinon fait apparaitre un toast demandant d'entrer un nom de joueur
            btnCommencer.setOnClickListener(v -> {
                Log.i (MainActivity.TAG, "clickCommencer");
                EditText nomJoueur = findViewById(R.id.nomjoueur);
                String strNomjoueur = nomJoueur.getText().toString();
                if (strNomjoueur.isEmpty()){
                    Toast.makeText(getBaseContext(),"Veuillez entrer un nom de joueur",Toast.LENGTH_SHORT).show();
                }else {
                    saveInfoUser(strNomjoueur,1,0);
                    saveMusic("","","","","","","","","","");
                    Intent intent = new Intent(ChoixJoueur.this, Debut_Partie.class);
                    startActivity(intent);
                }
            });
    }
    //Méthodes applicatives//
    /**
     * Quitte l'application
     * @param view
     */
    public void clickQuitter(View view) {
        Log.i(MainActivity.TAG, "clicQuitter");
        moveTaskToBack(true);
        System.exit(1);
    }

    /**
     * Retour à la page précédente
     * @param v
     */
    public void clickRetour(View v){
        Log.i(MainActivity.TAG,"clickRetour");
        finish();
    }

    /** Fonction executée au lancement, elle va récupérer la dernière langue choisie dans le fichier préférences
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
     * Fonction qui s'exécute au moment du lancement de la partie
     * elle récupère le nom du joueur inscrit le compteur et le score
     * pour les faire suivre tout au long de la partie
     * @param joueur
     * @param compte
     * @param score
     *
     */
    public void saveInfoUser(String joueur,int compte,int score){
        Log.i(TAG,"saveNomJoueur");
        SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("joueur", joueur);
        editor.putInt("compteur",compte);
        editor.putInt("scorejoueur",score);
        editor.apply();

    }

    /**
     * enregistre les musiques choisies dans un string dans les preférences pour les faire suivre au long de la partie
     * @param music1
     * @param music2
     * @param music3
     * @param music4
     * @param music5
     * @param music6
     * @param music7
     * @param music8
     * @param music9
     * @param music10
     */
    public void saveMusic(String music1,String music2,String music3,String music4,
                          String music5,String music6,String music7,String music8,
                          String music9,String music10){
        SharedPreferences chanson = getSharedPreferences(SHARED_PREF_MUSIC, MODE_PRIVATE);
        SharedPreferences.Editor editor = chanson.edit();
        editor.putString("musique1",music1);
        editor.putString("musique2",music2);
        editor.putString("musique3",music3);
        editor.putString("musique4",music4);
        editor.putString("musique5",music5);
        editor.putString("musique6",music6);
        editor.putString("musique7",music7);
        editor.putString("musique8",music8);
        editor.putString("musique9",music9);
        editor.putString("musique10",music10);
        editor.apply();
    }
}