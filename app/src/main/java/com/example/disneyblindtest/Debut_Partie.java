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
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Debut_Partie extends AppCompatActivity {

    private ImageView playButton;
    private ImageView pause_Button;
    public static final String TAG = "MainActivity";
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_MUSIC = "SHARED_PREF_MUSIC";
    private String joueurName = null;
    private int count = 1;
    private int scorejoueur = 0;
    private Map tReponses = null;
    private Map tReponsesinter = null;
    private Map tReponsesdif = null;
    private Map tMusique = null;
    private Map tMusiqueinter = null;
    private Map tMusiquedif = null;
    private String choixDifficult = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_debut_partie);

//------------------------------//Récupération des différents éléments de l'activité//---------------------------------------------//

        //Récupère les ID des différents bouton de réponses
        Button rep1 = findViewById(R.id.reponse1);
        Button rep2 = findViewById(R.id.reponse2);
        Button rep3 = findViewById(R.id.reponse3);
        Button rep4 = findViewById(R.id.reponse4);
        //Log.i(MainActivity.TAG, String.valueOf(count));

        //Récupère l'ID de l'image playbutton pour mettre en marche le player(il est en visible par défaut)
        playButton = findViewById(R.id.playbutton);
        //Récupère l'ID de l'image pausebutton qui permettra de mettre en pause le player(il est en gone par défaut)
        pause_Button = findViewById(R.id.pause_button);

        //Récupère les préférences entrées dans le fichier SHARED_PREF_USER_INFO dans l'activité ChoixJoueur
        SharedPreferences preferences = getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE);
        SharedPreferences prefs = getSharedPreferences("Mes_Prefs", Activity.MODE_PRIVATE);
        String language = prefs.getString("Language", "");
        String choixDifficult = prefs.getString("Difficulte","");
        Log.i(TAG,"difficulté : " + choixDifficult);


        //Récupère l'ID du TextView où implémenter le nom du joueur dans le layout
        TextView nomUser = findViewById(R.id.nomUser);

        //Récupère l'ID du TextView où implémenter le numéro de l'extrait dans le layout
        TextView numExtrait = findViewById(R.id.numeroExtrait);

        //Récupère le nom du joueur et le compteur dans les préférences pour les implémenter dans les données membres de l'activité
        this.joueurName = preferences.getString("joueur", "");
        this.count = preferences.getInt("compteur", 1);
        Log.i(TAG,"joueur" + joueurName);


//------------------------------//Traitements effectués à l'ouverture de l'activité//---------------------------------------------//

        //Ajoute le nom du joueur dans le TextView pour l'afficher dans l'interface utilisateur
        nomUser.setText(joueurName);

        //Utilise le tableau treponse de la base de donnée pour charger 4 nom en aléatoire et implémenter le text des boutons
        tReponses = ReponseManager.getAll(this, language);
        tReponsesinter = ReponseIntermediaireManager.getAll(this,language);
        tReponsesdif = ReponseDifficileManager.getAll(this,language);
        Log.i(TAG,tReponsesdif.toString());

        //Récupère un tableau avec les pistes musicales directement de la base de données
        tMusique = MusiqueManager.getAllmusique(this);
        tMusiqueinter = MusiqueIntermediaireManager.getAllmusique(this);
        tMusiquedif = MusiqueDifficileManager.getAllmusique(this);
        Log.i(TAG, tMusiquedif.toString());

        //On créer une liste pour stocker les futures valeurs des boutons
        List<String> buttonNameList = new ArrayList<>();

        //On lance un boucle qui nous donnera 4 valeurs à partir du tableau de réponses
        Object randomValues = null;
        Boolean flag = false;
        String choixMusique = "";


        for (int i = 0; i < 4; i++) {
            do {
                Random generator = new Random();
                if(choixDifficult.equals("facile")){
                    Object[] values = tReponses.values().toArray();
                    Object randomValue = values[generator.nextInt(values.length)];
                    //Log.i(TAG,"niveau réponse facile");

                    if (!buttonNameList.contains(randomValue)) {
                        buttonNameList.add(randomValue.toString());

                        flag = true;
                    } else {
                        flag = false;
                    }

                }else if (choixDifficult.equals("intermediaire")){
                    Object[] values = tReponsesinter.values().toArray();
                    Object randomValue = values[generator.nextInt(values.length)];
                    //Log.i(TAG,"niveau réponse intermediaire");


                    if (!buttonNameList.contains(randomValue)) {
                        buttonNameList.add(randomValue.toString());

                        flag = true;
                    } else {
                        flag = false;
                    }

                }else if (choixDifficult.equals("difficile")){
                    Object[] values = tReponsesdif.values().toArray();
                    Object randomValue = values[generator.nextInt(values.length)];
                    //Log.i(TAG,"niveau réponse difficle");


                    if (!buttonNameList.contains(randomValue)) {
                        buttonNameList.add(randomValue.toString());

                        flag = true;
                    } else {
                        flag = false;
                    }
                }else{
                    Object[] values = tReponses.values().toArray();
                    Object randomValue = values[generator.nextInt(values.length)];
                    //Log.i(TAG,"niveau réponse facile");


                    if (!buttonNameList.contains(randomValue)) {
                        buttonNameList.add(randomValue.toString());

                        flag = true;
                    } else {
                        flag = false;
                    }
                }

                //Les valeurs aléatoires sont stockées dans la liste uniquement si elles n'y sont pas déjà

            } while (!flag);

        }

        //A partir du tableau buttonNameList on implémente les 4 boutons avec un setText
        rep1.setText(buttonNameList.get(0));
        rep2.setText(buttonNameList.get(1));
        rep3.setText(buttonNameList.get(2));
        rep4.setText(buttonNameList.get(3));
        Log.i(TAG,"liste des boutons"+buttonNameList.toString());

        //On choisi aléatoirement une musique parmis les réponse des 4 boutons

        //On effectue un random parmis les id des boutons
        ArrayList listchanson = loadmusique();
        Boolean flag2 = false;

        while(flag2.equals(false)) {
            Random generator2 = new Random();
            int index = generator2.nextInt(buttonNameList.size());
            String reponseRand = buttonNameList.get(index);
            String idReponseRand = null;

            //A partir de la valeur du bouton aléatoirement choisie on récupère l'id correspondante dans le tableau des réponses
            if (choixDifficult.equals("facile")){
                 idReponseRand = getSingleKeyFromValue(tReponses, reponseRand);
                //Log.i(TAG,"idreponseRand facile");


            }else if (choixDifficult.equals("intermediaire")){
                 idReponseRand = getSingleKeyFromValue(tReponsesinter, reponseRand);
                //Log.i(TAG,"idreponseRand intermediaire");


            }else if (choixDifficult.equals("difficile")){
                 idReponseRand = getSingleKeyFromValue(tReponsesdif, reponseRand);
                //Log.i(TAG,"idreponseRand difficile");


            }else{
                 idReponseRand = getSingleKeyFromValue(tReponses, reponseRand);
                //Log.i(TAG,"idreponseRand facile");


            }
            Log.i(TAG,"id bouton random" + idReponseRand);

            //A partir de l'id du bouton on recherche la musique correspondante dans le tableau de musique
            if (choixDifficult.equals("facile")){
                choixMusique = getMusicById(tMusique,idReponseRand);
                //Log.i(TAG,"choix musique facile");

            }else if (choixDifficult.equals("intermediaire")){
                choixMusique = getMusicById(tMusiqueinter,idReponseRand);
                //Log.i(TAG,"choix musique inermediaire");


            }else if (choixDifficult.equals("difficile")){
                choixMusique = getMusicById(tMusiquedif,idReponseRand);
                //Log.i(TAG,"choix musique difficile");


            }else{
                choixMusique = getMusicById(tMusique,idReponseRand);
                //Log.i(TAG,"choix musique facile");


            }

            if (!listchanson.contains(choixMusique)){
                flag2 = true;
            }else {
                Log.i(TAG,"doublon");
                flag2 = false;
            }
        }

        Log.i(TAG,"choix musique"+ choixMusique);
            //Les valeurs aléatoires sont stockées dans la liste uniquement si elles n'y sont pas déjà
            if (count==1) {
                saveMusic(choixMusique, String.valueOf(listchanson.get(1)), String.valueOf(listchanson.get(2)),
                        String.valueOf(listchanson.get(3)),String.valueOf(listchanson.get(4)),
                        String.valueOf(listchanson.get(5)),String.valueOf(listchanson.get(6)),
                        String.valueOf(listchanson.get(7)),String.valueOf(listchanson.get(8)),
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG, "la liste des chanson" + String.valueOf(listchanson));
            }else if (count==2) {
                saveMusic(String.valueOf(listchanson.get(0)),choixMusique,String.valueOf(listchanson.get(2)),
                        String.valueOf(listchanson.get(3)),String.valueOf(listchanson.get(4)),
                        String.valueOf(listchanson.get(5)),String.valueOf(listchanson.get(6)),
                        String.valueOf(listchanson.get(7)),String.valueOf(listchanson.get(8)),
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG, "la liste des chanson" + String.valueOf(listchanson));

            }else if (count==3){
                saveMusic(String.valueOf(listchanson.get(0)), String.valueOf(listchanson.get(1)), choixMusique,
                        String.valueOf(listchanson.get(3)), String.valueOf(listchanson.get(4)),
                        String.valueOf(listchanson.get(5)), String.valueOf(listchanson.get(6)),
                        String.valueOf(listchanson.get(7)), String.valueOf(listchanson.get(8)),
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG,"la liste des chanson"+ listchanson);

            }else if (count==4){
                saveMusic(String.valueOf(listchanson.get(0)),String.valueOf(listchanson.get(1)), String.valueOf(listchanson.get(2)),
                        choixMusique,String.valueOf(listchanson.get(4)),
                        String.valueOf(listchanson.get(5)),String.valueOf(listchanson.get(6)),
                        String.valueOf(listchanson.get(7)),String.valueOf(listchanson.get(8)),
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG,"la liste des chanson"+ listchanson);
            }else if (count==5){
                saveMusic(String.valueOf(listchanson.get(0)),String.valueOf(listchanson.get(1)), String.valueOf(listchanson.get(2)),
                        String.valueOf(listchanson.get(3)),choixMusique,
                        String.valueOf(listchanson.get(5)),String.valueOf(listchanson.get(6)),
                        String.valueOf(listchanson.get(7)),String.valueOf(listchanson.get(8)),
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG,"la liste des chanson"+ listchanson);
            }else if (count==6){
                saveMusic(String.valueOf(listchanson.get(0)),String.valueOf(listchanson.get(1)), String.valueOf(listchanson.get(2)),
                        String.valueOf(listchanson.get(3)),String.valueOf(listchanson.get(4)),
                        choixMusique,String.valueOf(listchanson.get(6)),
                        String.valueOf(listchanson.get(7)),String.valueOf(listchanson.get(8)),
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG,"la liste des chanson"+ listchanson);
            }else if (count==7){
                saveMusic(String.valueOf(listchanson.get(0)),String.valueOf(listchanson.get(1)), String.valueOf(listchanson.get(2)),
                        String.valueOf(listchanson.get(3)),String.valueOf(listchanson.get(4)),
                        String.valueOf(listchanson.get(5)),choixMusique,
                        String.valueOf(listchanson.get(7)),String.valueOf(listchanson.get(8)),
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG,"la liste des chanson"+ listchanson);
            }else if (count==8){
                saveMusic(String.valueOf(listchanson.get(0)),String.valueOf(listchanson.get(1)), String.valueOf(listchanson.get(2)),
                        String.valueOf(listchanson.get(3)),String.valueOf(listchanson.get(4)),
                        String.valueOf(listchanson.get(5)),String.valueOf(listchanson.get(6)),
                        choixMusique,String.valueOf(listchanson.get(8)),
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG,"la liste des chanson"+ listchanson);
            }else if (count==9){
                saveMusic(String.valueOf(listchanson.get(0)),String.valueOf(listchanson.get(1)), String.valueOf(listchanson.get(2)),
                        String.valueOf(listchanson.get(3)),String.valueOf(listchanson.get(4)),
                        String.valueOf(listchanson.get(5)),String.valueOf(listchanson.get(6)),
                        String.valueOf(listchanson.get(7)),choixMusique,
                        String.valueOf(listchanson.get(9)));
                Log.i(TAG,"la liste des chanson"+ listchanson);
            }else if (count==10){
                saveMusic(String.valueOf(listchanson.get(0)),String.valueOf(listchanson.get(1)), String.valueOf(listchanson.get(2)),
                        String.valueOf(listchanson.get(3)),String.valueOf(listchanson.get(4)),
                        String.valueOf(listchanson.get(5)),String.valueOf(listchanson.get(6)),
                        String.valueOf(listchanson.get(7)),String.valueOf(listchanson.get(8)),
                        choixMusique);
                Log.i(TAG,"la liste des chanson"+ listchanson);

            }
                //Met à jour le textView du numéro d'extrait avec count
                numExtrait.setText(String.valueOf(count));

                //Déclare le mediaplayer et y ajoute la musique demandée
                int resID=getResources().getIdentifier(choixMusique, "raw", getPackageName());
                final MediaPlayer mediaPlayer = MediaPlayer.create(this, resID);

                //Met en marche le média player au click de l'image playbutton
                // (sa visibilité passe en gone et celle du bouton pausebutton en visible)
                playButton.setOnClickListener(v -> {
                    mediaPlayer.start();
                    playButton.setVisibility(View.GONE);
                    pause_Button.setVisibility(View.VISIBLE);
                });
                //Met en pause le média player au click de l'image pausebutton
                // (sa visibilité passe en gone et celle du bouton playbutton en visible)
                pause_Button.setOnClickListener(v -> {
                    mediaPlayer.pause();
                    playButton.setVisibility(View.VISIBLE);
                    pause_Button.setVisibility(View.GONE);
                });



                // Au click sur les boutons de réponses appel de la même activité avec la prochaine question
                // /les prochaines réponse/ le numéro d'extrait incrémenté/
                // le score incrémenté en cas de bonne réponse
                // si le compteur arrive à 10 le click envoi vers l'activité score (fin de partie)

                //Bouton réponse numéro 1
        String finalChoixMusique = choixMusique;

        rep1.setOnClickListener(v -> {

                    //On récupère le score du joueur stocké dans SHAREPREFERENCES
                    scorejoueur = loadscorejoueur();
                    String idValeurBouton1 = null;
                    String idValeurMusique = null;
                    //On récupère la valeur du bouton 1 et son id à partir du tableau de réponses
                    String valeurBouton1 = buttonNameList.get(0);
                    if (choixDifficult.equals("facile")){
                        idValeurBouton1 = getSingleKeyFromValue(tReponses, valeurBouton1);
                        idValeurMusique = getSingleKeyFromValue(tMusique, finalChoixMusique);
                        //Log.i(TAG,"idvaleurbouton facile");

                    }else if (choixDifficult.equals("intermediaire")){
                        idValeurBouton1 = getSingleKeyFromValue(tReponsesinter, valeurBouton1);
                        idValeurMusique = getSingleKeyFromValue(tMusiqueinter, finalChoixMusique);
                        //Log.i(TAG,"idvaleurbouton intermediaire");

                    }else if (choixDifficult.equals("difficile")){
                        idValeurBouton1 = getSingleKeyFromValue(tReponsesdif, valeurBouton1);
                        idValeurMusique = getSingleKeyFromValue(tMusiquedif, finalChoixMusique);
                        //Log.i(TAG,"idvaleurbouton difficle");

                    }else{
                        idValeurBouton1 = getSingleKeyFromValue(tReponses, valeurBouton1);
                        idValeurMusique = getSingleKeyFromValue(tMusique, finalChoixMusique);
                        //Log.i(TAG,"idvaleurbouton facile");

                    }

                    //On récupère l'id de la musique à partir de sa valeur dans le tableau musique
                    Log.i(TAG,"bouton " + idValeurBouton1);
                    Log.i(TAG,"musique " + idValeurMusique );

                    //On compare ensuite si l'id du bouton cliqué est égale à l'id de la musique
                    //Si oui le score augmente de 1 sinon il conserve sa valeur
                    if (idValeurBouton1.equals(idValeurMusique)){
                        Log.i(TAG,"score +1");
                        scorejoueur = scorejoueur+1;
                        Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                    }else {
                        Log.i(TAG,"perdu");
                        Log.i(TAG,"score"+String.valueOf(this.scorejoueur));
                    }
                    //Le média player est en pause au moment où l'on clique pour passer à la prochaine question
                    mediaPlayer.pause();
                    if (this.count < 10) {
                        this.count++;
                        Log.i(MainActivity.TAG, String.valueOf(count));
                        Intent intent = new Intent(Debut_Partie.this, Debut_Partie.class);
                        saveInfoUser(joueurName, count,scorejoueur);
                        startActivity(intent);

                    } else if (this.count ==10) {
                        saveInfoUser(joueurName, count,scorejoueur);
                        if (idValeurBouton1.equals(idValeurMusique)){
                            Log.i(TAG,"score +1");
                            scorejoueur = scorejoueur+1;
                            Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                        }else {
                            Log.i(TAG,"perdu");
                            Log.i(TAG,"score"+String.valueOf(this.scorejoueur));
                        }
                        Intent intent = new Intent(Debut_Partie.this, Score.class);
                        this.count = 0;
                        startActivity(intent);
                    }
                });
                //Bouton réponse numéro 2
        String finalChoixMusique1 = choixMusique;
        rep2.setOnClickListener(v -> {
                    scorejoueur = loadscorejoueur();
            String idValeurBouton2 = null;
            String idValeurMusique = null;

            String valeurBouton2 = buttonNameList.get(1);
            if (choixDifficult.equals("facile")){
                idValeurBouton2 = getSingleKeyFromValue(tReponses, valeurBouton2);
                idValeurMusique = getSingleKeyFromValue(tMusique, finalChoixMusique1);

            }else if (choixDifficult.equals("intermediaire")){
                idValeurBouton2 = getSingleKeyFromValue(tReponsesinter, valeurBouton2);
                idValeurMusique = getSingleKeyFromValue(tMusiqueinter, finalChoixMusique1);

            }else if (choixDifficult.equals("difficile")){
                idValeurBouton2 = getSingleKeyFromValue(tReponsesdif, valeurBouton2);
                idValeurMusique = getSingleKeyFromValue(tMusiquedif, finalChoixMusique1);

            }else{
                idValeurBouton2 = getSingleKeyFromValue(tReponses, valeurBouton2);
                idValeurMusique = getSingleKeyFromValue(tMusique, finalChoixMusique1);

            }
                Log.i(TAG,"bouton " + idValeurBouton2);
                Log.i(TAG,"musique " + idValeurMusique );

                if (idValeurBouton2.equals(idValeurMusique)){
                    Log.i(TAG,"score +1");
                    scorejoueur = scorejoueur+1;
                    Log.i(TAG,"score"+String.valueOf(this.scorejoueur));
                }else {
                    Log.i(TAG,"perdu");
                    Log.i(TAG,"score"+String.valueOf(this.scorejoueur));
                }
                mediaPlayer.pause();
                if (this.count < 10) {
                    this.count++;
                    Log.i(MainActivity.TAG, String.valueOf(count));
                    Intent intent = new Intent(Debut_Partie.this, Debut_Partie.class);
                    saveInfoUser(joueurName, count,scorejoueur);
                    startActivity(intent);

                } else if (this.count ==10) {
                    saveInfoUser(joueurName, count,scorejoueur);
                    if (idValeurBouton2.equals(idValeurMusique)){
                        Log.i(TAG,"score +1");
                        scorejoueur = scorejoueur+1;
                        Log.i(TAG,"score"+String.valueOf(this.scorejoueur));
                    }else {
                        Log.i(TAG,"perdu");
                        Log.i(TAG,"score"+String.valueOf(this.scorejoueur));
                    }
                    Intent intent = new Intent(Debut_Partie.this, Score.class);
                    this.count = 0;
                    startActivity(intent);

                    }
                });
                //Bouton réponse numéro 3
        String finalChoixMusique2 = choixMusique;
        rep3.setOnClickListener(v -> {
                    scorejoueur = loadscorejoueur();
            String idValeurBouton3 = null;
            String idValeurMusique = null;


            String valeurBouton3 = buttonNameList.get(2);
            if (choixDifficult.equals("facile")){
                idValeurBouton3 = getSingleKeyFromValue(tReponses, valeurBouton3);
                idValeurMusique = getSingleKeyFromValue(tMusique, finalChoixMusique2);

            }else if (choixDifficult.equals("intermediaire")){
                idValeurBouton3 = getSingleKeyFromValue(tReponsesinter, valeurBouton3);
                idValeurMusique = getSingleKeyFromValue(tMusiqueinter, finalChoixMusique2);

            }else if (choixDifficult.equals("difficile")){
                idValeurBouton3 = getSingleKeyFromValue(tReponsesdif, valeurBouton3);
                idValeurMusique = getSingleKeyFromValue(tMusiquedif, finalChoixMusique2);

            }else{
                idValeurBouton3 = getSingleKeyFromValue(tReponses, valeurBouton3);
                idValeurMusique = getSingleKeyFromValue(tMusique, finalChoixMusique2);

            }
                    Log.i(TAG,"bouton " + idValeurBouton3);
                    Log.i(TAG,"musique " + idValeurMusique );

                    if (idValeurBouton3.equals(idValeurMusique)){
                        Log.i(TAG,"score +1");
                        scorejoueur = scorejoueur+1;
                        Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                    }else {
                        Log.i(TAG,"perdu");
                        Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                    }
                    mediaPlayer.pause();
                    if (this.count < 10) {
                        this.count++;
                        Log.i(MainActivity.TAG, String.valueOf(count));
                        Intent intent = new Intent(Debut_Partie.this, Debut_Partie.class);
                        saveInfoUser(joueurName, count,scorejoueur);
                        startActivity(intent);

                    } else if (this.count ==10) {
                        saveInfoUser(joueurName, count,scorejoueur);
                        if (idValeurBouton3.equals(idValeurMusique)){
                            Log.i(TAG,"score +1");
                            scorejoueur = scorejoueur+1;
                            Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                        }else {
                            Log.i(TAG,"perdu");
                        }
                        Intent intent = new Intent(Debut_Partie.this, Score.class);
                        this.count = 0;
                        startActivity(intent);

                    }
                });
                //Bouton réponse numéro 4
        String finalChoixMusique3 = choixMusique;
        rep4.setOnClickListener(v -> {
                    scorejoueur = loadscorejoueur();
            String idValeurBouton4 = null;
            String idValeurMusique = null;


            String valeurBouton4 = buttonNameList.get(3);
            if (choixDifficult.equals("facile")){
                idValeurBouton4 = getSingleKeyFromValue(tReponses, valeurBouton4);
                idValeurMusique = getSingleKeyFromValue(tMusique, finalChoixMusique3);

            }else if (choixDifficult.equals("intermediaire")){
                idValeurBouton4 = getSingleKeyFromValue(tReponsesinter, valeurBouton4);
                idValeurMusique = getSingleKeyFromValue(tMusiqueinter, finalChoixMusique3);

            }else if (choixDifficult.equals("difficile")){
                idValeurBouton4 = getSingleKeyFromValue(tReponsesdif, valeurBouton4);
                idValeurMusique = getSingleKeyFromValue(tMusiquedif, finalChoixMusique3);

            }else{
                idValeurBouton4 = getSingleKeyFromValue(tReponses, valeurBouton4);
                idValeurMusique = getSingleKeyFromValue(tMusique, finalChoixMusique3);

            }
                    Log.i(TAG,"bouton " + idValeurBouton4);
                    Log.i(TAG,"musique " + idValeurMusique );

                    if (idValeurBouton4.equals(idValeurMusique)){
                        Log.i(TAG,"score +1");
                        scorejoueur = scorejoueur+1;
                        Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                    }else {
                        Log.i(TAG,"perdu");
                        Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                    }
                    mediaPlayer.pause();
                    if (this.count < 10) {
                        this.count++;
                        Log.i(MainActivity.TAG, String.valueOf(count));
                        Intent intent = new Intent(Debut_Partie.this, Debut_Partie.class);
                        saveInfoUser(joueurName, count,scorejoueur);
                        startActivity(intent);

                    } else if (this.count==10) {
                        saveInfoUser(joueurName, count,scorejoueur);
                        if (idValeurBouton4.equals(idValeurMusique)){
                            Log.i(TAG,"score +1");
                            scorejoueur = scorejoueur+1;
                            Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                        }else {
                            Log.i(TAG,"perdu");
                            Log.i(TAG,"score"+String.valueOf(this.scorejoueur));

                        }
                        Intent intent = new Intent(Debut_Partie.this, Score.class);
                        this.count = 0;
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
        String difficult = prefs.getString("Difficulte", "");
        changeLang(language,difficult);
    }

    /**Fonction qui adapte la langue en fonction de celle choisie dans les préférences
     * @param lang la langue présente dans les préférences
     */
    public void changeLang(String lang,String difficult) {
        Log.i(TAG, "changeLang");
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang,difficult);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

    }
    /**
     * Fonction qui enregistre la langue dans les préférences
     * @param lang la langue traitée
     */
    public void saveLocale(String lang,String difficult) {
        Log.i(TAG, "saveLocale");
        SharedPreferences prefs = getSharedPreferences("Mes_Prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Language", lang);
        editor.putString("Difficulte",difficult);
        editor.apply();
    }

    /**
     * Fonction qui s'exécute au moment du lancement de la partie
     * elle récupère le nom du joueur inscrit
     * pour le suivre le long de la partie
     * @param joueur
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

    /**Fonction executée au lancement, elle va récupérer les pistes de musique déjà passées dans le fichier préférences
     *
     */
    public ArrayList loadmusique() {
        Log.i(TAG, "load musique");
        SharedPreferences chanson = getSharedPreferences("SHARED_PREF_MUSIC", Activity.MODE_PRIVATE);
        ArrayList listMusic = new ArrayList();
        String music1 = chanson.getString("musique1","");
        String music2 = chanson.getString("musique2","");
        String music3 = chanson.getString("musique3","");
        String music4 = chanson.getString("musique4","");
        String music5 = chanson.getString("musique5","");
        String music6 = chanson.getString("musique6","");
        String music7 = chanson.getString("musique7","");
        String music8 = chanson.getString("musique8","");
        String music9 = chanson.getString("musique9","");
        String music10 = chanson.getString("musique10","");
        listMusic.add(music1);
        listMusic.add(music2);
        listMusic.add(music3);
        listMusic.add(music4);
        listMusic.add(music5);
        listMusic.add(music6);
        listMusic.add(music7);
        listMusic.add(music8);
        listMusic.add(music9);
        listMusic.add(music10);

        return listMusic;
    }

    /**
     * récupère l'id d'un enregistrement à partir de sa valeur
     * @param map
     * @param value
     * @return
     */
    public static String getSingleKeyFromValue(Map<String, String> map, String value) {

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                String idReponse = String.valueOf(entry.getKey());
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Récupère la valeur d'un enregistrement à partir de son id
     * @param map
     * @param key
     * @return
     */
    public static String getMusicById(Map<String,String> map,String key){
        for (Map.Entry<String, String> entry : map.entrySet()){
            if (entry.getKey().equals(key)){
                String choixMusique = String.valueOf(entry.getValue());
                return entry.getValue();
            }
        }
        return null;
    }

    /**Fonction executée au lancement, elle va récupérer le score du joueur dans le fichier préférences
     *
     */
    public int loadscorejoueur() {
        Log.i(TAG, "load score joueur");
        SharedPreferences prefs = getSharedPreferences(SHARED_PREF_USER_INFO, Activity.MODE_PRIVATE);
        int score = prefs.getInt("scorejoueur",0);
        return score;
    }


}