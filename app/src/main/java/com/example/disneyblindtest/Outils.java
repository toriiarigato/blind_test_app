package com.example.disneyblindtest;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;

public class Outils {

    public static final String TAG = "MainActivity";
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";

    /**
     * Fonction qui s'exécute au moment du lancement de la partie
     * elle récupère le nom du joueur inscrit
     * pour le suivre le long de la partie
     * @param joueur
     *
     */
    /*public static void saveInfoUser(String joueur,int compte){
        Log.i(TAG,"saveNomJoueur");
        SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("joueur", joueur);
        editor.putInt("compteur",compte);
        editor.apply();

    }*/

}
