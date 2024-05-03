package com.example.testproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * code for creating, saving to and loading from database
 */
public class Database extends SQLiteOpenHelper {

    // Class Variables
    public static final String COMBATANT_TABLE = "combatant_table";
    public static final String COL_ID = "id";
    public static final String COL_INITIATIVE = "initiative";
    public static final String COL_HP = "hp";
    public static final String COL_HP_MAX = "hpMax";
    public static final String COL_NAME = "name";
    public static final String COL_CONDITION = "condition";
    public static final String COL_URL = "url";
    public static final String COL_AC = "ac";

    // combatant.db database
    public Database(@Nullable Context context) {
        super(context, "combatant.db", null, 1);
    }

    // Called first time database is accessed - contains code to create database table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement =
                "CREATE TABLE " + COMBATANT_TABLE
                        + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_INITIATIVE + " INTEGER, " + COL_HP + " INTEGER, "
                        + COL_HP_MAX + " INTEGER, " + COL_NAME + " TEXT, " + COL_CONDITION + " TEXT, " + COL_URL + " TEXT, " + COL_AC + " INTEGER)";

        db.execSQL(createTableStatement);
    }

    // called if version number changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Deletes the database and returns an int for confirmation
     */
    public int deleteTable(){

        SQLiteDatabase db = this.getWritableDatabase();
        final int delete = db.delete(COMBATANT_TABLE, "1", null);

        db.close();

        return delete;
    }

    /**
     * Save the given list of combatants into the database
     * @param cList - the list of combatants to save
     */
    public boolean addCombatantList(List<Combatant> cList){

        // Variables
        long insert;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // cycles though the combatants in the list
        // for each combatant, its data is entered ito the db
        for (Combatant c: cList){

            cv.put(COL_ID, c.getId());
            cv.put(COL_INITIATIVE, c.getM_ini());
            cv.put(COL_HP, c.getM_hp());
            cv.put(COL_HP_MAX, c.getM_hpMax());
            cv.put(COL_NAME, c.getM_name());
            cv.put(COL_CONDITION, c.getM_cond());
            cv.put(COL_URL, c.getM_url());
            cv.put(COL_AC, c.getM_ac());


            insert = db.insert(COMBATANT_TABLE, null, cv);
            if (insert == -1){
                return false;
            }
        }

        db.close();
        return true;
    }

    /**
     * loads all combatants from the database and returns them as a list of combatants
     */
    public List<Combatant> loadCombatants(){

        // list to return
        List<Combatant> returnList = new ArrayList<>();

        // get data from database
        String qString = "SELECT * FROM " + COMBATANT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qString, null);

        if (cursor.moveToFirst()){
            // loop through results, create new combatant, add to list
            do{
                int id = cursor.getInt(0);
                int ini = cursor.getInt(1);
                int hp = cursor.getInt(2);
                int hpMax = cursor.getInt(3);
                String name = cursor.getString(4);
                String con = cursor.getString(5);
                String url = cursor.getString(6);
                int ac = cursor.getInt(7);

                Combatant newCom = new Combatant(id, ini, hp, hpMax, name, con, url, ac);
                returnList.add(newCom);

            } while (cursor.moveToNext());
        }
        else{
            // no results
        }

        cursor.close();
        db.close();
        return returnList;
    }

}
