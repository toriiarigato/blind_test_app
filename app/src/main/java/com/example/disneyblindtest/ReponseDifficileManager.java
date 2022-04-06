package com.example.disneyblindtest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public  class ReponseDifficileManager {

    private final static String TAG = "ReponseDifficileManager";
    private static String selectLang = null;

    /**
     * Récupère tous les enregistrements de la table Reponse
     * @param context
     * @return Map
     */
    public static Map<String,String> getAll(Context context,String lang ) {
        Log.i(TAG, "getAll");
        HashMap<String,String> tReponsesdif = new HashMap<String,String>();

        // Ouvre une connexion BDD
        DatabaseManager conn = new DatabaseManager(context, DatabaseManager.DATABASE_NAME, null, DatabaseManager.DATABASE_VERSION);

        // J'appelle la BDD en écriture avec getWritableDatabase() pour que la BDD soit créée
        // Je peux modifier ensuite l'appel en lecture avec un getReadableDatabase()
        SQLiteDatabase bdd = conn.getWritableDatabase();
        //SQLiteDatabase bdd = conn.getReadableDatabase();

        // Récupérer la liste des reponses
        if (lang.equals("fr")){
            selectLang = "SELECT " + DatabaseManager.COL_ID_REP_DIFFCILE + ", "
                    + DatabaseManager.COL_REP_FR_DIFFCILE + " FROM "
                    + DatabaseManager.TABLE_REPONSE_DIFFCILE + ";" ;
        }else if(lang.equals("en")){
            selectLang = "SELECT " + DatabaseManager.COL_ID_REP_DIFFCILE + ", "
                    + DatabaseManager.COL_REP_EN_DIFFCILE + " FROM "
                    + DatabaseManager.TABLE_REPONSE_DIFFCILE + ";" ;
        }else if (lang.equals("es")){
            selectLang = "SELECT " + DatabaseManager.COL_ID_REP_DIFFCILE + ", "
                    + DatabaseManager.COL_REP_ES_DIFFCILE + " FROM "
                    + DatabaseManager.TABLE_REPONSE_DIFFCILE + ";" ;
        } else {
            selectLang = "SELECT " + DatabaseManager.COL_ID_REP_DIFFCILE + ", "
                    + DatabaseManager.COL_REP_FR_DIFFCILE + " FROM "
                    + DatabaseManager.TABLE_REPONSE_DIFFCILE + ";" ;
        }
        String sql = selectLang ;
        // Lancer la requête


        Log.i(TAG,"bdd:"+bdd.toString());
        Log.i(TAG,"sql:"+selectLang);

        Cursor c = bdd.rawQuery(sql, null);

        // Créer un tableau de reponses
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String idRep = c.getString(0);
                    String rep = c.getString(1);


                    //Ajoute les données au tableau
                    tReponsesdif.put(idRep,rep);

                    Log.e("DEBUG", "id : " + idRep + " / rep " + rep );
                } while (c.moveToNext());
            }
        }

        // Ferme la connexion BDD
        bdd.close();

        return tReponsesdif;
    }



}
