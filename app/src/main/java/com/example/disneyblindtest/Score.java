package com.example.disneyblindtest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class Score extends AppCompatActivity {

    //Constantes
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    public static final String TAG = "MainActivity";
    //Données membres
    private String joueurName = null;
    private int scoreJoueur = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_score);


//------------------------------//Récupération des différents éléments de l'activité//---------------------------------------------//

        //Récupère les préférences entrées dans le fichier SHARED_PREF_USER_INFO dans l'activité ChoixJoueur
        SharedPreferences preferences = getSharedPreferences("SHARED_PREF_USER_INFO",MODE_PRIVATE);
        SharedPreferences prefs = getSharedPreferences("Mes_Prefs", Activity.MODE_PRIVATE);
        String language = prefs.getString("Language", "");

        //Récupère l'ID du TextView où implémenter le nom du joueur dans le layout
        TextView score = findViewById(R.id.score);

        //Récupère l'ID du TextView où implémenter le numéro de l'extrait dans le layout
        TextView resultat = findViewById(R.id.resultat);

        //Récupère le nom du joueur et le compteur dans les préférences pour les implémenter dans les données membres de l'activité
        this.joueurName = preferences.getString("joueur","");
        this.scoreJoueur = preferences.getInt("scorejoueur",0);

        //Déclaration des différents message de score
        String score0 = null;
        String score1 = null;
        String score2 = null;
        String score3 = null;
        String score4 = null;
        if (language.equals("fr")){
            Log.i(TAG,"score en français");
            score0 = "Oh non " + joueurName + " révise tes classiques !";
            score1 = "C'est un bon début " + joueurName + " mais peut mieux faire...";
            score2 = "Choisissez " + joueurName + " soit vous êtes nul soit vous êtes bon ";
            score3 = "Pas loin de la perfection " + joueurName + " trop fort ! ";
            score4 = joueurName + " vous devez connaître Walt Disney en personne car c'est un SANS FAUTE! BRAVO ";
        }else if(language.equals("en")){
            Log.i(TAG,"score en anglais");
            score0 = "Oh no " + joueurName + " WORK HARDER !";
            score1 = "It's a good start " + joueurName + " but should be better...";
            score2 = "Choose " + joueurName + " be a crap or have some clap  ";
            score3 = "Not far frome perfect " + joueurName + " you are killing it ! ";
            score4 = joueurName + " you must know WALT DISNEY in person cause it's a PERFECT ! ";
        }else if(language.equals("es")){
            Log.i(TAG,"score en espagnole");
            score0 = "Oh no " + joueurName + " Téneis que trabajar más !";
            score1 = "ésta es la base. " + joueurName + " puede hacerlo mejor...";
            score2 = "Elija !" + joueurName + " o eres terrible o es demasiado bueno ";
            score3 = "Eso no está lejos de la perfection " + joueurName + " Muy alto ! ";
            score4 = joueurName + " Tal vez lo conocía Walt Disney se sacó un puntaje perfecto !. ";
        }else{
            Log.i(TAG,"score en par defaut");
            score0 = "Oh non " + joueurName + " révise tes classiques !";
            score1 = "C'est un bon début " + joueurName + " mais peut mieux faire...";
            score2 = "CHoisissez " + joueurName + " soit vous êtes nul soit vous êtes bon ";
            score3 = "Pas loin de la perfection " + joueurName + " trop fort ! ";
            score4 = joueurName + " vous devez connaître Walt Disney en personne car c'est un SANS FAUTE! BRAVO ";
        }


//------------------------------//Traitements effectués à l'ouverture de l'activité//---------------------------------------------//

        //Ajoute le nom du joueur dans le
        //
        // TextView pour l'afficher dans l'interface utilisateur
        score.setText(scoreJoueur+" /10");
        Log.i(MainActivity.TAG,String.valueOf(scoreJoueur));

        //Met à jour le textView du numéro d'extrait avec score

        if (scoreJoueur >0 && scoreJoueur < 3){
            resultat.setText(score0);

        }else if(scoreJoueur>2 && scoreJoueur <5){
            resultat.setText(score1);

        }else if(scoreJoueur >4 && scoreJoueur <7){
            resultat.setText(score2);

        }else if (scoreJoueur>6 && scoreJoueur <10){
            resultat.setText(score3);
        }else if (scoreJoueur==10){
            resultat.setText(score4);

        }
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
     * elle récupère le nom du joueur inscrit et le compteur
     * pour les faire suivre tout au long de la partie
     * @param joueur
     * @param compte
     *
     */
    public void saveInfoUser(String joueur,int compte){
        Log.i(TAG,"saveNomJoueur");
        SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("joueur", joueur);
        editor.putInt("compteur",compte);
        editor.apply();

    }
}