package com.example.disneyblindtest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MusiqueDifficileManager {
    private final static String TAG = "MusiqueManager";


    /**
     * Récupère tous les enregistrements de la table Reponse
     * @param context
     * @return Map
     */
    public static Map<String,String> getAllmusique(Context context ) {
        Log.i(TAG, "getAll");
        HashMap<String,String> tMusiquedif = new HashMap<String,String>();

        // Ouvre une connexion BDD
        DatabaseManager conn = new DatabaseManager(context, DatabaseManager.DATABASE_NAME, null, DatabaseManager.DATABASE_VERSION);

        // J'appelle la BDD en écriture avec getWritableDatabase() pour que la BDD soit créée
        // Je peux modifier ensuite l'appel en lecture avec un getReadableDatabase()
        SQLiteDatabase bdd = conn.getWritableDatabase();
        //SQLiteDatabase bdd = conn.getReadableDatabase();

        // Récupérer la liste des musiques
        String sql = "SELECT " + DatabaseManager.COL_ID_MUSIC_DIFFICILE + ", "
                + DatabaseManager.COL_NOM_MUSIC_DIFFICILE + " FROM "
                + DatabaseManager.TABLE_MUSIC_DIFFICILE + ";" ;


        // Lancer la requête

        Log.i(TAG,"bdd:"+bdd.toString());
        Log.i(TAG,"sql:"+sql);

        Cursor c = bdd.rawQuery(sql, null);

        // Créer un tableau de musique
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String idMus = c.getString(0);
                    String nomMus = c.getString(1);


                    //Ajoute les données au tableau
                    tMusiquedif.put(idMus,nomMus);

                    Log.e("DEBUG", "id : " + idMus + " / rep " + nomMus );
                } while (c.moveToNext());
            }
        }

        // Ferme la connexion BDD
        bdd.close();

        return tMusiquedif;
    }

}

