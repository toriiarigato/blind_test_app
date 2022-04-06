package com.example.disneyblindtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DatabaseManager extends SQLiteOpenHelper {

    //CONSTANTES

        //CREATE BDD
        public static final String DATABASE_NAME = "blindTest.db";
        public static final int DATABASE_VERSION = 1;
        private static final String TAG = "DatabaseManager";

        //TABLE MUSIQUE FACILE (PAR DEFAUT)
        public static final String TABLE_MUSIC = "tablemusique";
        public static final String COL_ID_MUSIC = "idmusique";
        public static final String COL_NOM_MUSIC = "nommusique";

        //TABLE MUSIQUE INTERMEDIAIRE
        public static final String TABLE_MUSIC_INTERMEDIAIRE = "tablemusiqueIntermediaire";
        public static final String COL_ID_MUSIC_INTERMEDIAIRE = "idmusiqueIntermediaire";
        public static final String COL_NOM_MUSIC_INTERMEDIAIRE = "nommusiqueIntermediaire";

        //TABLE MUSIQUE DIFFICILE
        public static final String TABLE_MUSIC_DIFFICILE = "tablemusiqueDifficile";
        public static final String COL_ID_MUSIC_DIFFICILE = "idmusiqueDifficile";
        public static final String COL_NOM_MUSIC_DIFFICILE = "nommusiqueDifficile";


        //TABLE REPONSE (FACILE PAR DEFAUT)
        public static final String TABLE_REPONSE = "tablereponse";
        public static final String COL_ID_REP = "idreponse";
        public static final String COL_REP_FR = "reponsefr";
        public static final String COL_REP_EN = "reponseeng";
        public static final String COL_REP_ES = "reponseesp";

        //TABLE REPONSE INTERMEDIAIRE
        public static final String TABLE_REPONSE_INTERMEDIAIRE = "tablereponseIntermediaire";
        public static final String COL_ID_REP_INTERMEDIAIRE = "idreponseIntermediaire";
        public static final String COL_REP_FR_INTERMEDIAIRE = "reponsefrIntermediaire";
        public static final String COL_REP_EN_INTERMEDIAIRE = "reponseengIntermediaire";
        public static final String COL_REP_ES_INTERMEDIAIRE = "reponseespIntermediaire";

        //TABLE REPONSE DIFFICILE
        public static final String TABLE_REPONSE_DIFFCILE = "tablereponseDifficile";
        public static final String COL_ID_REP_DIFFCILE = "idreponseDifficile";
        public static final String COL_REP_FR_DIFFCILE = "reponsefrDifficile";
        public static final String COL_REP_EN_DIFFCILE = "reponseengDifficile";
        public static final String COL_REP_ES_DIFFCILE = "reponseespDifficile";

        //TABLE SCORE
        public static final String TABLE_SCORE = "tablescore";
        public static final String COL_NOM_JOUEUR = "nomjoueur";
        public static final String COL_SCORE_JOUEUR = "scorejoueur";

        //Requetes LDD (CREATE, DROP, UPDATE...)
        public static final String CREATE_TABLE_MUSIC =
                "CREATE TABLE " + TABLE_MUSIC + " (" +
                COL_ID_MUSIC + " TEXT PRIMARY KEY, " +
                COL_NOM_MUSIC + " TEXT);";

        public static final String CREATE_TABLE_MUSIC_INTERMEDIAIRE =
                "CREATE TABLE " + TABLE_MUSIC_INTERMEDIAIRE + " (" +
                        COL_ID_MUSIC_INTERMEDIAIRE + " TEXT PRIMARY KEY, " +
                        COL_NOM_MUSIC_INTERMEDIAIRE + " TEXT);";

        public static final String CREATE_TABLE_MUSIC_DIFFICILE =
                "CREATE TABLE " + TABLE_MUSIC_DIFFICILE + " (" +
                        COL_ID_MUSIC_DIFFICILE + " TEXT PRIMARY KEY, " +
                        COL_NOM_MUSIC_DIFFICILE + " TEXT);";


        public static final String CREATE_TABLE_REPONSE =
                "CREATE TABLE " + TABLE_REPONSE + " (" +
                        COL_ID_REP + " TEXT PRIMARY KEY, " +
                        COL_REP_FR + " TEXT, " +
                        COL_REP_EN + " TEXT, " +
                        COL_REP_ES + " TEXT);";

        public static final String CREATE_TABLE_REPONSE_INTERMEDIAIRE =
                "CREATE TABLE " + TABLE_REPONSE_INTERMEDIAIRE + " (" +
                        COL_ID_REP_INTERMEDIAIRE + " TEXT PRIMARY KEY, " +
                        COL_REP_FR_INTERMEDIAIRE + " TEXT, " +
                        COL_REP_EN_INTERMEDIAIRE + " TEXT, " +
                        COL_REP_ES_INTERMEDIAIRE + " TEXT);";

        public static final String CREATE_TABLE_REPONSE_DIFFICILE =
                "CREATE TABLE " + TABLE_REPONSE_DIFFCILE + " (" +
                        COL_ID_REP_DIFFCILE + " TEXT PRIMARY KEY, " +
                        COL_REP_FR_DIFFCILE + " TEXT, " +
                        COL_REP_EN_DIFFCILE + " TEXT, " +
                        COL_REP_ES_DIFFCILE + " TEXT);";

        public static final String CREATE_TABLE_SCORE =
                "CREATE TABLE " + TABLE_SCORE+ " (" +
                        COL_NOM_JOUEUR + " TEXT PRIMARY KEY, " +
                        COL_SCORE_JOUEUR + " REAL);";

        public static final String DROP_TABLE_MUSIQUE = "DROP TABLE IF EXISTS " + TABLE_MUSIC + ";";
        public static final String DROP_TABLE_MUSIQUE_INTERMEDIAIRE = "DROP TABLE IF EXISTS " + TABLE_MUSIC_INTERMEDIAIRE + ";";
        public static final String DROP_TABLE_MUSIQUE_DIFFICILE = "DROP TABLE IF EXISTS " + TABLE_MUSIC_DIFFICILE + ";";

        public static final String DROP_TABLE_REPONSE = "DROP TABLE IF EXISTS " + TABLE_REPONSE + ";";
        public static final String DROP_TABLE_REPONSE_INTERMEDIAIRE = "DROP TABLE IF EXISTS " + TABLE_REPONSE_INTERMEDIAIRE + ";";
        public static final String DROP_TABLE_REPONSE_DIFFICILE = "DROP TABLE IF EXISTS " + TABLE_REPONSE_DIFFCILE + ";";

    public static final String DROP_TABLE_SCORE = "DROP TABLE IF EXISTS " + TABLE_SCORE + ";";

    // Requêtes de peuplement

    //INSERT TABLE MUSIC
        public static final String INSERT_ALADIN =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('ALADIN','aladin_ce_reve_bleu');";
        public static final String INSERT_BAMBI =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('BAMBI','bambi_petite_pluie_d_avril');";
        public static final String INSERT_BELLEBETE =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('BELLEBETE','la_belle_et_la_bete_cest_la_fete');";
        public static final String INSERT_BLANCHENEIGE =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('BLANCHENEIGE','blanche_neige_un_jour_mon_prince_viendra');";
        public static final String INSERT_BOISDORMANT =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('BOISDORMANT','la_belle_au_bois_dormant');";
        public static final String INSERT_CENDRILLON =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('CENDRILLON','cendrillon_l_amour');";
        public static final String INSERT_DUMBO =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('DUMBO','dumbo');";
        public static final String INSERT_HERCULE =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('HERCULE','hercule_de_zero_en_hero');";
        public static final String INSERT_LIVREJUNGLE =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('LIVREJUNGLE','le_livre_de_la_jungle_il_en_faut_peu_pour_etre_heureux');";
        public static final String INSERT_MULAN =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('MULAN','mulan_comme_un_homme');";
        public static final String INSERT_PETERPAN =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('PETERPAN','peter_pan_tu_tenvole');";
        public static final String INSERT_PETITESIRENE =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('PETITESIRENE','la_petite_sirene_sous_locean');";
        public static final String INSERT_POCAHONTAS =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('POCAHONTAS','pocahontas_lair_du_vent');";
        public static final String INSERT_PRINCESSEGRENOUILLE =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('PRINCESSEGRENOUILLE','la_princesse_et_la_grenouille_au_bout_du_reve');";
        public static final String INSERT_QUASIMODO =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('QUASIMODO','quasimodo_en_bas');";
        public static final String INSERT_RAIPONCE =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('RAIPONCE','raiponce_moi_jai_un_reve');";
        public static final String INSERT_REBELLE =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('REBELLE','rebelle_je_vol');";
        public static final String INSERT_REINEDESNEIGES =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('REINEDESNEIGES','la_reine_des_neiges_liberee_delivree');";
        public static final String INSERT_ROILION =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('ROILION','le_roi_lion_l_histoire_de_la_vie');";
        public static final String INSERT_VAINA =
                "INSERT INTO " + TABLE_MUSIC + " (" + COL_ID_MUSIC + "," + COL_NOM_MUSIC + ") VALUES ('VAINA','vaiana_le_bleu_lumiere');";


    //INSERT TABLE MUSIC INTERMEDIAIRE
    public static final String INSERT_ALADIN_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('ALADININTER','aladin_je_vole');";
    public static final String INSERT_ALICE_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('ALICEINTER','alice_au_pays_des_merveilles_un_joyeux_non_anniversaire');";
    public static final String INSERT_CENDRILLON_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('CENDRILLONINTER','cendrillon_la_chanson_des_souris');";
    public static final String INSERT_DINGOMAX_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('DINGOMAXINTER','dingo_et_max_sur_l_autoroute');";
    public static final String INSERT_FREREOURS_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('FREREOURSINTER','frere_des_ours_mon_frere_ours');";
    public static final String INSERT_HERCULE_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('HERCULEINTER','hercule_le_monde_qui_est_le_mien');";
    public static final String INSERT_PETITESIRENE_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('PETITESIRENEINTER','la_petite_sirene_partir_la_bas');";
    public static final String INSERT_PLANETETRESOR_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('PLANETETRESORINTER','la_planete_au_tresor_un_homme_libre');";
    public static final String INSERT_REINEDESNEIGES_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('REINEDESNEIGESINTER','la_reine_des_neiges_l_amour_est_un_cadeau');";
    public static final String INSERT_LIVREJUNGLE_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('LIVREJUNGLEINTER','le_livre_de_la_jungle_un_homme_comme_vous');";
    public static final String INSERT_ROILION2_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('ROILION2INTER','le_roi_lion_2_l_un_des_notres');";
    public static final String INSERT_ROILION_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('ROILIONINTER','le_roi_lion_soyez_prete');";
    public static final String INSERT_ARISTOCHAT_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('ARISTOCHATINTER','les_aristochats_omalley_le_chat_de_gouttiere');";
    public static final String INSERT_LILOSTITCH_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('LILOSTITCHINTER','lilo_et_stitch_hawaian_roller_coster_ride');";
    public static final String INSERT_MERLIN_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('MERLININTER','merlin_l_enchanteur_higitus_figitus');";
    public static final String INSERT_MULAN_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('MULANINTER','mulan_honneur_a_tous');";
    public static final String INSERT_PETERPAN_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('PETERPANINTER','peter_pan_la_vie_d_un_pirate');";
    public static final String INSERT_PINOCCHIO_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('PINOCCHIOINTER','pinocchio_sifflez_vite_vite');";
    public static final String INSERT_RAIPONCE_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('RAIPONCEINTER','raiponce_n_ecoute_que_moi');";
    public static final String INSERT_ROBINBOIS_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('ROBINBOISINTER','robin_des_bois_wisthle_stop');";
    public static final String INSERT_TARZAN_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('TARZANINTER','tarzan_toujours_dans_mon_coeur');";
    public static final String INSERT_VAIANA_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('VAIANAINTER','vaiana_notre_terre');";
    public static final String INSERT_PRINCESSEGRENOUILLE_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('PRINCESSEGRENOUILLEINTER','la_princesse_et_la_grenouille_evangeline');";
    public static final String INSERT_POCAHONTAS_INTER =
            "INSERT INTO " + TABLE_MUSIC_INTERMEDIAIRE + " (" + COL_ID_MUSIC_INTERMEDIAIRE + "," + COL_NOM_MUSIC_INTERMEDIAIRE + ") VALUES ('POCAHONTASINTER','pocahontas_des_sauvages');";

    //INSERT TABLE MUSIC DIFFICILE
    public static final String INSERT_ALADIN_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('ALADINDIFI','aladin_nuit_d_arabie');";
    public static final String INSERT_ALICE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('ALICEDIFI','alice_au_pays_des_merveilles_pays_du_merveilleux');";
    public static final String INSERT_ATLANTIDE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('ATLANTIDEDIFI','atlantide_empire_perdu');";
    public static final String INSERT_BAMBI_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('BAMBIDIFI','bambi_l_amour_est_un_chant_eternel');";
    public static final String INSERT_BASIL_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('BASILDIFI','basile_detective_prive_le_genie_du_mal');";
    public static final String INSERT_BERNARDETBIANCA_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('BERNARDETBIANCADIFI','bernard_et_bianca_courage_petite_soeur');";
    public static final String INSERT_COCO_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('COCODIFI','coco_ne_m_oublie_pas');";
    public static final String INSERT_DINGOMAX_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('DINGOMAXDIFI','dingo_et_max_apres_demain');";
    public static final String INSERT_FREREOURS_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('FREREOURSDIFI','frere_des_ours_les_grands_esprits');";
    public static final String INSERT_HERCULE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('HERCULEDIFI','hercule_le_gospel_pur_1');";
    public static final String INSERT_KUZKO_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('KUZKODIFI','kuzko_kuzko');";
    public static final String INSERT_BELLEBETE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('BELLEBETEDIFI','la_belle_et_la_bete_bonjour');";
    public static final String INSERT_BELLECLOCHARD_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('BELLECLOCHARDDIFI','la_belle_et_le_clochard_la_chanson_de_noel');";
    public static final String INSERT_LAHAUT_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('LAHAUTDIFI','la_haut');";
    public static final String INSERT_REINENEIGE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('REINENEIGESDIFI','la_reine_des_neiges_le_coeur_de_glace');";
    public static final String INSERT_ROILION2_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('ROILION2DIFI','le_roi_lion_2_il_vit_en_toi');";
    public static final String INSERT_LESTROISCABALLEROS_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('LESTOIRSCABALLEROSDIFI','les_3_caballeros_los_tres_caballeros');";
    public static final String INSERT_ARISTOCHATS_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('ARISTOCHATSDIFI','les_aristochats_les_aristocats');";
    public static final String INSERT_LILOSTITCH_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('LILOSTITCHDIFI','lilo_et_stitch_he_mele_no_lilo');";
    public static final String INSERT_MONSTRECOMPAGNIE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('MONSTRECOMPAGNIEDIFI','monstre_et_compagnie');";
    public static final String INSERT_MULAN2_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('MULAN2DIFI','mulan_2_premiere_lecons');";
    public static final String INSERT_MULAN_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('MULANDIFI','mulan_intro');";
    public static final String INSERT_OLIVERCOMPAGNIE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('OLIVERCOMPAGNIEDIFI','oliver_et_compagnie_il_etait_une_fois_a_nycity');";
    public static final String INSERT_PETERPAN_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('PETERPANDIFI','peter_pan_la_deuxieme_petite_etoile');";
    public static final String INSERT_PINOCCHIO_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('PINOCCHIODIFI','pinocchio_la_fee_bleu');";
    public static final String INSERT_POCAHONTAS_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('POCAHONTASDIFI','pocahontas_virginia_compagnie');";
    public static final String INSERT_QUASIMODO_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('QUASIMODODIFI','quasimodo_les_cloches_de_notre_dame');";
    public static final String INSERT_RAIPONCE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('RAIPONCEDIFI','raiponce_fleur_aux_petales_d_or');";
    public static final String INSERT_RATATOUILLE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('RATATOUILLEDIFI','ratatouille_le_festin');";
    public static final String INSERT_ROXROUKY_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('ROXROUKYDIFI','rox_et_rouky_quand_on_est_deux_copains');";
    public static final String INSERT_TARAM_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('TARAMDIFI','taram_et_le_chaudron_magique');";
    public static final String INSERT_TOYSTORY2_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('TOYSTORY2DIFI','toy_story_2_quand_elle_m_aimait');";
    public static final String INSERT_TOYSTORY_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('TOYSTORYDIFI','toy_story_je_suis_ton_ami');";
    public static final String INSERT_VICEVERSA_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('VICEVERSADIFI','vice_versa');";
    public static final String INSERT_WALLE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('WALLEDIFI','wall_e');";
    public static final String INSERT_VOLT_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('VOLTDIFI','volt_un_chien_un_chat_et_un_rongeur');";
    public static final String INSERT_ZOOTOPIE_DIFI =
            "INSERT INTO " + TABLE_MUSIC_DIFFICILE + " (" + COL_ID_MUSIC_DIFFICILE + "," + COL_NOM_MUSIC_DIFFICILE + ") VALUES ('ZOOTOPIEDIFI','zootopie_try_everything');";

    //INSERT TABLE REPONSE
        public static final String INSERT_ALADINREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('ALADIN','ALADIN','ALADIN','ALADIN');";
        public static final String INSERT_BAMBIREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('BAMBI','BAMBI','BAMBI','BAMBI');";
        public static final String INSERT_BELLEBETEREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('BELLEBETE','LA BELLE ET LA BETE','THE BEAUTY AND THE BEAST','LA BELLA Y LA BESTIA');";
        public static final String INSERT_BLANCHENEIGEREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('BLANCHENEIGE','BLANCHE NEIGE','WHITE SNOW','BLANCA NIEVE');";
        public static final String INSERT_BOISDORMANTREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('BOISDORMANT','LA BELLE AU BOIS DORMANT','SLEEPING BEAUTY','BELLAS DURMIENTES');";
        public static final String INSERT_CENDRILLONREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('CENDRILLON','CENDRILLON','CINDERELLA','CENICIENTA');";
        public static final String INSERT_DUMBOREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('DUMBO','DUMBO','DUMBO','DUMBO');";
        public static final String INSERT_HERCULEREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('HERCULE','HERCULE','HERCULE','HERCULE');";
        public static final String INSERT_LIVREJUNGLEREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('LIVREJUNGLE','LE LIVRE DE LA JUNGLE','JUNGLE BOOK','EL LIBRO DE LA SELVA');";
        public static final String INSERT_MULANREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('MULAN','MULAN','MULAN','MULAN');";
        public static final String INSERT_PETERPANREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('PETERPAN','PETER PAN','PETER PAN','PETER PAN');";
        public static final String INSERT_PETITESIRENEREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('PETITESIRENE','LA PETITE SIRENE','THE LITTLE MERMAID','SIRENITA');";
        public static final String INSERT_POCAHONTASREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('POCAHONTAS','POCAHONTAS','POCAHONTAS','POCAHONTAS');";
        public static final String INSERT_PRINCESSEGRENOUILLEREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('PRINCESSEGRENOUILLE','LA PRINCESSE ET LA GRENOUILLE','THE PRINCESS AND THE FROG','LA PRINCESA Y LA RANA');";
        public static final String INSERT_QUASIMODOREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('QUASIMODO','QUASIMODO','QUASIMODO','QUASIMODO');";
        public static final String INSERT_RAIPONCEREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('RAIPONCE','RAIPONCE','RAPUNZEL','RAPUNZEL');";
        public static final String INSERT_REBELLEREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('REBELLE','REBELLE','BRAVE','BRAVA');";
        public static final String INSERT_REINEDESNEIGESREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('REINEDESNEIGES','LA REINE DES NEIGES','FROZEN','LA REINA DES LAS NIEVES');";
        public static final String INSERT_ROILIONREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('ROILION','LE ROI LION','THE LION KING','EL REY LEON');";
        public static final String INSERT_VAIANAREP =
                "INSERT INTO " + TABLE_REPONSE + " (" + COL_ID_REP + "," + COL_REP_FR + "," + COL_REP_EN + "," + COL_REP_ES + ") VALUES ('VAIANA','VAIANA','MOANA','MOANA');";


        //INSERT TABLE REPONSE INTERMEDIAIRE

        public static final String INSERT_ALADINREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('ALADININTER','ALADIN','ALADIN','ALADIN');";
        public static final String INSERT_ALICEREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('ALICEINTER','ALICE AU PAYS DES MERVEILLES','ALICE IN WONDERLAND','ALICIA EN EL PAIS DE LAS MARAVILLAS');";
        public static final String INSERT_CENDRILLONREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('CENDRILLONINTER','CENDRILLON','CINDERELLA','CENICIENTA');";
        public static final String INSERT_DINGOMAXREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('DINGOMAXINTER','DINGO ET MAX','A GOOFY MOVIE','GOOFY E HIJO');";
        public static final String INSERT_FREREOURSREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('FREREOURSINTER','FRERE DES OURS','BROTHER BEAR','TIERA DES OSOS');";
        public static final String INSERT_HERCULEREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('HERCULEINTER','HERCULE','HERCULE','HERCULE');";
        public static final String INSERT_PETITESIRENEREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('PETITESIRENEINTER','LA PETITE SIRENE','THE LITTLE MERMAID','SIRENITA');";
        public static final String INSERT_PLANETETRESORREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('PLANETETRESORINTER','LA PLANETE AU TRESOR','TRESURE PLANET','EL PLANETA DE TRESORO');";
        public static final String INSERT_PRINCESSEGRENOUILLEREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('PRINCESSEGRENOUILLEINTER','LA PRINCESSE ET LA GRENOUILLE','PRINCESS AND THE FROG','LA PRINCESA Y LA RANA');";
        public static final String INSERT_REINEDESNEIGESREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('REINEDESNEIGESINTER','LA REINE DES NEIGES','FROZEN','LA REINA DES LAS NIEVES');";
        public static final String INSERT_LIVREJUNGLEREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('LIVREJUNGLEINTER','LE LIVRE DE LA JUNGLE','JUNGLE BOOK','EL LIBRO DE LA SELVA');";
        public static final String INSERT_ROILION2REP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('ROILION2INTER','LE ROI LION 2','THE LION KING 2','EL REY LEON 2');";
        public static final String INSERT_ROILIONREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('ROILIONINTER','LE ROI LION','THE LION KING','EL REY LEON');";
        public static final String INSERT_ARISTOCHATREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('ARISTOCHATINTER','LES ARISTOCHATS','ARISTOCATS','LOS ARITOGATOS');";
        public static final String INSERT_LILOSTITCHREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('LILOSTITCHINTER','LILO ET STITCH','LILO AND STITCH','LILO Y STITCH');";
        public static final String INSERT_MERLINREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('MERLININTER','MERLIN','THE SWORD IN THE STONE','MERLIN EL ENCANTADOR');";
        public static final String INSERT_MULANREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('MULANINTER','MULAN','MULAN','MULAN');";
        public static final String INSERT_PERTERPANREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('PETERPANINTER','PETER PAN','PETER PAN','PETER PAN');";
        public static final String INSERT_PINOCCHIOREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('PINOCCHIOINTER','PINOCCHIO','PINOCCHIO','PINOCCHIO');";
        public static final String INSERT_POCAHONTASREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('POCAHONTASINTER','POCAHONTAS','POCAHONTAS','POCAHONTAS');";
        public static final String INSERT_RAIPONCEREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('RAIPONCEINTER','RAIPONCE','RAPUNZEL','RAPUNZEL');";
        public static final String INSERT_ROBINBOISREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('ROBINBOISINTER','ROBIN DES BOIS','ROBIN HOOD','ALADIN');";
        public static final String INSERT_TARZANREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('TARZANINTER','TARZAN','TARZAN','TARZAN');";
        public static final String INSERT_VAIANAREP_INTER =
                "INSERT INTO " + TABLE_REPONSE_INTERMEDIAIRE + " (" + COL_ID_REP_INTERMEDIAIRE + "," + COL_REP_FR_INTERMEDIAIRE + "," + COL_REP_EN_INTERMEDIAIRE + "," + COL_REP_ES_INTERMEDIAIRE + ") VALUES ('VAIANAINTER','VAIANA','MOANA','MOANA');";

    //INSERT TABLE REPONSE DIFFICILE

    public static final String INSERT_ALADINREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('ALADINDIFI','ALADIN','ALADIN','ALADIN');";
    public static final String INSERT_ALICEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('ALICEDIFI','ALICE AU PAYS DES MERVEILLES','ALICE IN WONDERLAND','ALICIA EN EL PAIS DE LAS MARAVILLAS');";
    public static final String INSERT_ALTLANTIDEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('ATLANTIDEDIFI','ATLANTIDE','ATLANTIDE','ATLANTIDE');";
    public static final String INSERT_BAMBIREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('BAMBIDIFI','BAMBI','BAMBI','BAMBI');";
    public static final String INSERT_BASILREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('BASILDIFI','BASIL DETECTIVE PRIVÉ','THE GREAT MOUSE DETECTIVE','DETECTIVE PRIVADO BASIL');";
    public static final String INSERT_BERNARDBIANCAREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('BERNARDETBIANCADIFI','BERNARD ET BIANCA','THE RESCUERS','LOS RESCATADORES');";
    public static final String INSERT_COCOREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('COCODIFI','COCO','COCO','COCO');";
    public static final String INSERT_DINGOMAXREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('DINGOMAXDIFI','DINGO ET MAX','A GOOFY MOVIE','GOOFY Y HIJO');";
    public static final String INSERT_FREREOURSREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('FREREOURSDIFI','FRERE DES OURS','BROTHER BEAR','TIERA DES OSOS');";
    public static final String INSERT_HERCULEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('HERCULEDIFI','HERCULE','HERCULE','HERCULE');";
    public static final String INSERT_KUZKOREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('KUZKODIFI','KUZKO','KUZKO','KUZKO');";
    public static final String INSERT_BELLEBETEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('BELLEBETEDIFI','LA BELLE ET LA BETE','THE BEAUTY AND THE BEAST','LA BELLA Y LA BESTIA');";
    public static final String INSERT_BELLECLOCHARDREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('BELLECLOCHARDDIFI','LA BELLE ET LE CLOCHARD','LADY AND THE TRAMP','LA DAMA Y EL VAGABUNDO');";
    public static final String INSERT_LAHAUTREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('LAHAUTDIFI','LA HAUT','UP','DARA EL');";
    public static final String INSERT_REINENEIGESREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('REINENEIGESDIFI','LA REINE DES NEIGES','FROZEN','LA REINA DES LAS NIEVES');";
    public static final String INSERT_ROILION2REP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('ROILION2DIFI','LE ROI LION 2','LION KING 2','EL REY LEON 2');";
    public static final String INSERT_LESTROISCABALLEROSREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('LESTOIRSCABALLEROSDIFI','LES 3 CABALLEROS','THE 3 CABALLEROS','LOS 3 CABAELLEROS');";
    public static final String INSERT_ARISTOCHATSREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('ARISTOCHATSDIFI','LES ARISTOCHATS','ARISTOCATS','LOS ARITOGATOS');";
    public static final String INSERT_LILOSTITCHREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('LILOSTITCHDIFI','LILO ET STITCH','LILO AND STITCH','LILO Y STITCH');";
    public static final String INSERT_MONSTRECOMPAGNIEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('MONSTRECOMPAGNIEDIFI','MONSTRES ET COMPAGNIE','MONSTER INC','MONSTRUOS SA');";
    public static final String INSERT_MULAN2REP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('MULAN2DIFI','MULAN 2','MULAN 2','MULAN 2');";
    public static final String INSERT_MULANREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('MULANDIFI','MULAN','MULAN','MULAN');";
    public static final String INSERT_OLIVERCOMPAGNIEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('OLIVERCOMPAGNIEDIFI','OLIVER ET COMPAGNIE','OLIVER AND COMPAGNY','OLIVER Y SU PANDILLA');";
    public static final String INSERT_PETERPANREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('PETERPANDIFI','PETER PAN','PETER PAN','PETER PAN');";
    public static final String INSERT_PINOCCHIOREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('PINOCCHIODIFI','PINOCCHIO','PINOCCHIO','PINOCCHIO');";
    public static final String INSERT_POCAHONTASREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('POCAHONTASDIFI','POCAHONTAS','POCAHONTAS','POCAHONTAS');";
    public static final String INSERT_QUASIMODOREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('QUASIMODODIFI','QUASIMODO','QUASIMODO','QUASIMODO');";
    public static final String INSERT_RAIPONCEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('RAIPONCEDIFI','RAIPONCE','RAPUNZEL','RAPUNZEL');";
    public static final String INSERT_RATATOUILLEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('RATATOUILLEDIFI','RATATOUILLE','RATATOUILLE','RATATOUILLE');";
    public static final String INSERT_ROXROUKYREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('ROXROUKYDIFI','ROX ET ROUKY','THE FOX AND THE HOUND','TOD Y TOBY');";
    public static final String INSERT_TARAMREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('TARAMDIFI','TARAM ET LE CHAUDRON MAGIQUE','THE BLACK CAULDRON','EL CALDERO MAGICO');";
    public static final String INSERT_TOYSTRORY2REP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('TOYSTORY2DIFI','TOYSTORY 2 ','TOYSTORY 2 ','TOYSTORY 2');";
    public static final String INSERT_TOYSTRORYREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('TOYSTORYDIFI','TOYSTORY ','TOYSTORY ','TOYSTORY');";
    public static final String INSERT_VICEVERSAREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('VICEVERSADIFI','VICE VERSA','INSIDE OUT','DEL REVES');";
    public static final String INSERT_VOLTREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('VOLTDIFI','VOLT','VOLT','VOLT');";
    public static final String INSERT_WALLEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('WALLEDIFI','WALL-E ','WALL-E','WALL-E');";
    public static final String INSERT_ZOOTOPIEREP_DIFI =
            "INSERT INTO " + TABLE_REPONSE_DIFFCILE + " (" + COL_ID_REP_DIFFCILE + "," + COL_REP_FR_DIFFCILE + "," + COL_REP_EN_DIFFCILE + "," + COL_REP_ES_DIFFCILE + ") VALUES ('ZOOTOPIEDIFI','ZOOTOPIE','ZOOTOPIA','ZOOTOPIA');";






    public DatabaseManager (@Nullable Context context,
                           @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(TAG, "ConvertSQLite");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "onCreate() Création BDD");
            // Création des tables
            db.execSQL(CREATE_TABLE_MUSIC);
            db.execSQL(CREATE_TABLE_MUSIC_INTERMEDIAIRE);
            db.execSQL(CREATE_TABLE_MUSIC_DIFFICILE);

            db.execSQL(CREATE_TABLE_REPONSE);
            db.execSQL(CREATE_TABLE_REPONSE_INTERMEDIAIRE);
            db.execSQL(CREATE_TABLE_REPONSE_DIFFICILE);

            db.execSQL(CREATE_TABLE_SCORE);

            // Peuplement de la base
            Log.i(TAG, "Peuplement de la base");
            db.execSQL(INSERT_ALADIN);
            db.execSQL(INSERT_ALADINREP);
            db.execSQL(INSERT_BAMBI);
            db.execSQL(INSERT_BAMBIREP);
            db.execSQL(INSERT_BELLEBETE);
            db.execSQL(INSERT_BELLEBETEREP);
            db.execSQL(INSERT_BLANCHENEIGE);
            db.execSQL(INSERT_BLANCHENEIGEREP);
            db.execSQL(INSERT_BOISDORMANT);
            db.execSQL(INSERT_BOISDORMANTREP);
            db.execSQL(INSERT_CENDRILLON);
            db.execSQL(INSERT_CENDRILLONREP);
            db.execSQL(INSERT_DUMBO);
            db.execSQL(INSERT_DUMBOREP);
            db.execSQL(INSERT_HERCULE);
            db.execSQL(INSERT_HERCULEREP);
            db.execSQL(INSERT_LIVREJUNGLE);
            db.execSQL(INSERT_LIVREJUNGLEREP);
            db.execSQL(INSERT_MULAN);
            db.execSQL(INSERT_MULANREP);
            db.execSQL(INSERT_PETERPAN);
            db.execSQL(INSERT_PETERPANREP);
            db.execSQL(INSERT_PETITESIRENE);
            db.execSQL(INSERT_PETITESIRENEREP);
            db.execSQL(INSERT_POCAHONTAS);
            db.execSQL(INSERT_POCAHONTASREP);
            db.execSQL(INSERT_PRINCESSEGRENOUILLE);
            db.execSQL(INSERT_PRINCESSEGRENOUILLEREP);
            db.execSQL(INSERT_QUASIMODO);
            db.execSQL(INSERT_QUASIMODOREP);
            db.execSQL(INSERT_RAIPONCE);
            db.execSQL(INSERT_RAIPONCEREP);
            db.execSQL(INSERT_REBELLE);
            db.execSQL(INSERT_REBELLEREP);
            db.execSQL(INSERT_REINEDESNEIGES);
            db.execSQL(INSERT_REINEDESNEIGESREP);
            db.execSQL(INSERT_ROILION);
            db.execSQL(INSERT_ROILIONREP);
            db.execSQL(INSERT_VAINA);
            db.execSQL(INSERT_VAIANAREP);
            db.execSQL(INSERT_ALADIN_INTER);
            db.execSQL(INSERT_ALADINREP_INTER);
            db.execSQL(INSERT_ALICE_INTER);
            db.execSQL(INSERT_ALICEREP_INTER);
            db.execSQL(INSERT_CENDRILLON_INTER);
            db.execSQL(INSERT_CENDRILLONREP_INTER);
            db.execSQL(INSERT_DINGOMAX_INTER);
            db.execSQL(INSERT_DINGOMAXREP_INTER);
            db.execSQL(INSERT_FREREOURS_INTER);
            db.execSQL(INSERT_FREREOURSREP_INTER);
            db.execSQL(INSERT_HERCULE_INTER);
            db.execSQL(INSERT_HERCULEREP_INTER);
            db.execSQL(INSERT_PETITESIRENE_INTER);
            db.execSQL(INSERT_PETITESIRENEREP_INTER);
            db.execSQL(INSERT_PLANETETRESOR_INTER);
            db.execSQL(INSERT_PLANETETRESORREP_INTER);
            db.execSQL(INSERT_PRINCESSEGRENOUILLE_INTER);
            db.execSQL(INSERT_PRINCESSEGRENOUILLEREP_INTER);
            db.execSQL(INSERT_REINEDESNEIGES_INTER);
            db.execSQL(INSERT_REINEDESNEIGESREP_INTER);
            db.execSQL(INSERT_LIVREJUNGLE_INTER);
            db.execSQL(INSERT_LIVREJUNGLEREP_INTER);
            db.execSQL(INSERT_ROILION2_INTER);
            db.execSQL(INSERT_ROILION2REP_INTER);
            db.execSQL(INSERT_ROILION_INTER);
            db.execSQL(INSERT_ROILIONREP_INTER);
            db.execSQL(INSERT_ARISTOCHAT_INTER);
            db.execSQL(INSERT_ARISTOCHATREP_INTER);
            db.execSQL(INSERT_LILOSTITCH_INTER);
            db.execSQL(INSERT_LILOSTITCHREP_INTER);
            db.execSQL(INSERT_MERLIN_INTER);
            db.execSQL(INSERT_MERLINREP_INTER);
            db.execSQL(INSERT_MULAN_INTER);
            db.execSQL(INSERT_MULANREP_INTER);
            db.execSQL(INSERT_PETERPAN_INTER);
            db.execSQL(INSERT_PERTERPANREP_INTER);
            db.execSQL(INSERT_PINOCCHIO_INTER);
            db.execSQL(INSERT_PINOCCHIOREP_INTER);
            db.execSQL(INSERT_POCAHONTAS_INTER);
            db.execSQL(INSERT_POCAHONTASREP_INTER);
            db.execSQL(INSERT_RAIPONCE_INTER);
            db.execSQL(INSERT_RAIPONCEREP_INTER);
            db.execSQL(INSERT_ROBINBOIS_INTER);
            db.execSQL(INSERT_ROBINBOISREP_INTER);
            db.execSQL(INSERT_TARZAN_INTER);
            db.execSQL(INSERT_TARZANREP_INTER);
            db.execSQL(INSERT_VAIANA_INTER);
            db.execSQL(INSERT_VAIANAREP_INTER);
            db.execSQL(INSERT_ALADIN_DIFI);
            db.execSQL(INSERT_ALADINREP_DIFI);
            db.execSQL(INSERT_ALICE_DIFI);
            db.execSQL(INSERT_ALICEREP_DIFI);
            db.execSQL(INSERT_ATLANTIDE_DIFI);
            db.execSQL(INSERT_ALTLANTIDEREP_DIFI);
            db.execSQL(INSERT_BAMBI_DIFI);
            db.execSQL(INSERT_BAMBIREP_DIFI);
            db.execSQL(INSERT_BASIL_DIFI);
            db.execSQL(INSERT_BASILREP_DIFI);
            db.execSQL(INSERT_BERNARDETBIANCA_DIFI);
            db.execSQL(INSERT_BERNARDBIANCAREP_DIFI);
            db.execSQL(INSERT_COCO_DIFI);
            db.execSQL(INSERT_COCOREP_DIFI);
            db.execSQL(INSERT_DINGOMAX_DIFI);
            db.execSQL(INSERT_DINGOMAXREP_DIFI);
            db.execSQL(INSERT_FREREOURS_DIFI);
            db.execSQL(INSERT_FREREOURSREP_DIFI);
            db.execSQL(INSERT_HERCULE_DIFI);
            db.execSQL(INSERT_HERCULEREP_DIFI);
            db.execSQL(INSERT_KUZKO_DIFI);
            db.execSQL(INSERT_KUZKOREP_DIFI);
            db.execSQL(INSERT_BELLEBETE_DIFI);
            db.execSQL(INSERT_BELLEBETEREP_DIFI);
            db.execSQL(INSERT_BELLECLOCHARD_DIFI);
            db.execSQL(INSERT_BELLECLOCHARDREP_DIFI);
            db.execSQL(INSERT_LAHAUT_DIFI);
            db.execSQL(INSERT_LAHAUTREP_DIFI);
            db.execSQL(INSERT_REINENEIGE_DIFI);
            db.execSQL(INSERT_REINENEIGESREP_DIFI);
            db.execSQL(INSERT_ROILION2_DIFI);
            db.execSQL(INSERT_ROILION2REP_DIFI);
            db.execSQL(INSERT_LESTROISCABALLEROS_DIFI);
            db.execSQL(INSERT_LESTROISCABALLEROSREP_DIFI);
            db.execSQL(INSERT_ARISTOCHATS_DIFI);
            db.execSQL(INSERT_ARISTOCHATSREP_DIFI);
            db.execSQL(INSERT_LILOSTITCH_DIFI);
            db.execSQL(INSERT_LILOSTITCHREP_DIFI);
            db.execSQL(INSERT_MONSTRECOMPAGNIE_DIFI);
            db.execSQL(INSERT_MONSTRECOMPAGNIEREP_DIFI);
            db.execSQL(INSERT_MULAN2_DIFI);
            db.execSQL(INSERT_MULAN2REP_DIFI);
            db.execSQL(INSERT_MULAN_DIFI);
            db.execSQL(INSERT_MULANREP_DIFI);
            db.execSQL(INSERT_OLIVERCOMPAGNIE_DIFI);
            db.execSQL(INSERT_OLIVERCOMPAGNIEREP_DIFI);
            db.execSQL(INSERT_PETERPAN_DIFI);
            db.execSQL(INSERT_PETERPANREP_DIFI);
            db.execSQL(INSERT_PINOCCHIO_DIFI);
            db.execSQL(INSERT_PINOCCHIOREP_DIFI);
            db.execSQL(INSERT_POCAHONTAS_DIFI);
            db.execSQL(INSERT_POCAHONTASREP_DIFI);
            db.execSQL(INSERT_QUASIMODO_DIFI);
            db.execSQL(INSERT_QUASIMODOREP_DIFI);
            db.execSQL(INSERT_RAIPONCE_DIFI);
            db.execSQL(INSERT_RAIPONCEREP_DIFI);
            db.execSQL(INSERT_RATATOUILLE_DIFI);
            db.execSQL(INSERT_RATATOUILLEREP_DIFI);
            db.execSQL(INSERT_ROXROUKY_DIFI);
            db.execSQL(INSERT_ROXROUKYREP_DIFI);
            db.execSQL(INSERT_TARAM_DIFI);
            db.execSQL(INSERT_TARAMREP_DIFI);
            db.execSQL(INSERT_TOYSTORY_DIFI);
            db.execSQL(INSERT_TOYSTRORYREP_DIFI);
            db.execSQL(INSERT_TOYSTORY2_DIFI);
            db.execSQL(INSERT_TOYSTRORY2REP_DIFI);
            db.execSQL(INSERT_VICEVERSA_DIFI);
            db.execSQL(INSERT_VICEVERSAREP_DIFI);
            db.execSQL(INSERT_VOLT_DIFI);
            db.execSQL(INSERT_VOLTREP_DIFI);
            db.execSQL(INSERT_WALLE_DIFI);
            db.execSQL(INSERT_WALLEREP_DIFI);
            db.execSQL(INSERT_ZOOTOPIE_DIFI);
            db.execSQL(INSERT_ZOOTOPIEREP_DIFI);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + " , wich will destroy all old data");
        db.execSQL(DROP_TABLE_MUSIQUE);
        db.execSQL(DROP_TABLE_MUSIQUE_INTERMEDIAIRE);
        db.execSQL(DROP_TABLE_MUSIQUE_DIFFICILE);

        db.execSQL(DROP_TABLE_REPONSE);
        db.execSQL(DROP_TABLE_REPONSE_INTERMEDIAIRE);
        db.execSQL(DROP_TABLE_REPONSE_DIFFICILE);

        db.execSQL(DROP_TABLE_SCORE);

        // On relance la création de la BDD
        onCreate(db);

    }
}
