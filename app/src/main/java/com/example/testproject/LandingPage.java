package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This is the Home Page where users will be directed on startign the app
 */
public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        // Variables
        Button m_goTracker = findViewById(R.id.btn_tracker);
        Button m_goRoller = findViewById(R.id.btn_roller);

        /**
         * An On Click Listener for the tracker button - takes the user to the initiative tracker
         */
        m_goTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LandingPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * An On Click Listener for the roller button - takes the user to the dice roller
         */
        m_goRoller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LandingPage.this, DiceRoller.class);
                startActivity(intent);
            }
        });
    }
}