package com.example.testproject;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class used to store data with global access
 */
public class MyApplication extends Application {

    // Global Class Variables
    private static List<Combatant> CombatantList = new ArrayList<Combatant>();
    private static int nextID = -1;
    public static int currInitPos = 0;

    public MyApplication() {

    }

    // returns the global combatant list
    public static List<Combatant> getCombatantList() {
        return CombatantList;
    }

    // generates a unique combatant id
    public static int getNextID() {
        nextID++;
        return nextID;
    }

    // sets the global combatant list and resets next id value
    public static void setCombatantList(List<Combatant> combatantList) {
        CombatantList = combatantList;
        nextID = CombatantList.size()-1;
    }

}
