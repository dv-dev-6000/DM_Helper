package com.example.testproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * The code for the Dice Roller Page
 */
public class DiceRoller extends AppCompatActivity {

    // Class Variables
    Random rng = new Random();
    Animation rotateAnimation;
    Button m_rollIt;
    RadioGroup m_dieGroup;
    EditText m_dQuant, m_dMod;
    TextView m_dResult;
    ImageView m_dieImage;
    int m_currDie, m_currQuant, m_currMod, m_dieToRoll;

    /**
     * The Roll it method takes the below three parameters and returns the result of specified dice roll
     *
     * @param dType - the type of dice to be rolled
     * @param dQuant - the quantity of dice to roll
     * @param dMod - The amount to add or subtract from the roll
     * @return - returns the final value of the roll
     */
    public String RollIt(int dType, int dQuant, int dMod){

        // initiate value at 0
        int value = 0;

        // generate random number for base dice rolls
        for (int i = 0; i < dQuant; i++){

            value = value + rng.nextInt(dType)+1;

        }

        // add modifier
        value = value + dMod;

        // return value as string
        return Integer.toString(value);
    }

    /**
     * Initiates the rotation animation for the dice roll
     */
    private void RotateAnimation(){
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        m_dieImage.startAnimation(rotateAnimation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);

        // Define Variables
        m_rollIt = findViewById(R.id.btn_rollRoll);
        m_dieGroup = findViewById(R.id.radioGroup);
        m_dQuant = findViewById(R.id.et_rollQuant);
        m_dMod = findViewById(R.id.et_rollMod);
        m_dResult = findViewById(R.id.tv_result);
        m_dieImage = findViewById(R.id.iv_dImage);

        // set default die image as d20
        m_dieImage.setImageResource(R.drawable.d20_img);
        // set default die to roll as 20
        m_dieToRoll = 20;

        final int m_4id = findViewById(R.id.rad_d4).getId();
        final int m_6id = findViewById(R.id.rad_d6).getId();
        final int m_8id = findViewById(R.id.rad_d8).getId();
        final int m_10id = findViewById(R.id.rad_d10).getId();
        final int m_12id = findViewById(R.id.rad_d12).getId();
        final int m_20id = findViewById(R.id.rad_d20).getId();
        final int m_100id = findViewById(R.id.rad_d100).getId();

        /**
         * radio group click listener - used to determine the die selected
         */
        m_dieGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                // find the id for the selected dice type
                m_currDie = m_dieGroup.getCheckedRadioButtonId();

                // compare to find a match and set values accordingly
                if (m_currDie == m_4id){
                    m_dieToRoll = 4;
                    m_dieImage.setImageResource(R.drawable.d4_img);
                }
                else if (m_currDie == m_6id){
                    m_dieToRoll = 6;
                    m_dieImage.setImageResource(R.drawable.d6_img);
                }
                else if (m_currDie == m_8id){
                    m_dieToRoll = 8;
                    m_dieImage.setImageResource(R.drawable.d8_img);
                }
                else if (m_currDie == m_10id){
                    m_dieToRoll = 10;
                    m_dieImage.setImageResource(R.drawable.d10_img);
                }
                else if (m_currDie == m_12id){
                    m_dieToRoll = 12;
                    m_dieImage.setImageResource(R.drawable.d12_img);
                }
                else if (m_currDie == m_20id){
                    m_dieToRoll = 20;
                    m_dieImage.setImageResource(R.drawable.d20_img);
                }
                else if (m_currDie == m_100id){
                    m_dieToRoll = 100;
                    m_dieImage.setImageResource(R.drawable.d10_img);
                }
                else{
                    m_dieToRoll = 0;
                }
            }
        });

        /**
         * Click Listener for Roll It! button
         * initiates the dice roll
         */
        m_rollIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // find the id for the selected dice type
                m_currDie = m_dieGroup.getCheckedRadioButtonId();

                // set values for dice quantity and modifiers
                // checks for values given by user or uses default value
                if (m_dQuant.getText().length() > 0){

                    m_currQuant = Integer.parseInt(m_dQuant.getText().toString());
                }else{ m_currQuant = 0; }
                if (m_dMod.getText().length() > 0){

                    m_currMod = Integer.parseInt(m_dMod.getText().toString());
                }else{ m_currMod = 0; }

                // roll the dice and display result
                m_dResult.setText(RollIt(m_dieToRoll, m_currQuant, m_currMod));

                // initiate animation
                RotateAnimation();

            }
        });

    }

    /**
     * Set up Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.roller_menu,menu);

        return true;
    }

    /**
     * logic for menu option clicks
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;

        /**
         * Home Selected - navigate to landing page
         * Tracker Selected - navigate to tracker page
         */
        switch(item.getItemId()){
            case R.id.rMenu_home:
                intent = new Intent( DiceRoller.this, LandingPage.class);
                startActivity(intent);
                break;
            case R.id.rMenu_tracker:
                intent = new Intent( DiceRoller.this, MainActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}