package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class AddEntity extends AppCompatActivity {

    // object variables
    Random rng = new Random();
    Button btn_cancel, btn_ok;
    List<Combatant> combatantList;
    MyApplication myApplication = (MyApplication) this.getApplication();

    private EditText m_name, m_con, m_url, m_ini, m_hp, m_hpMax, m_ac;
    private RadioButton m_boss, m_enemy, m_neutral, m_player;
    private RadioGroup m_radGroup;
    private CheckBox m_chkBoxURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entity);

        // get reference to global list
        combatantList = myApplication.getCombatantList();

        // Define Fields
        m_name = findViewById(R.id.et_name);
        m_con = findViewById(R.id.et_con);
        m_url = findViewById(R.id.et_url);
        m_hp = findViewById(R.id.et_hp);
        m_hpMax = findViewById(R.id.et_hpMax);
        m_ini = findViewById(R.id.et_ini);
        m_boss = findViewById(R.id.rad_enemyBoss);
        m_enemy = findViewById(R.id.rad_enemy);
        m_neutral = findViewById(R.id.rad_neutral);
        m_player = findViewById(R.id.rad_player);
        m_chkBoxURL = findViewById(R.id.chkbx_addURL);
        m_radGroup = findViewById(R.id.radGroup);
        m_ac = findViewById(R.id.et_ac);

        // create repositioned toast notification for debug reasons
        //Toast toast = Toast.makeText(this, "Combatant ID: " + editID, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 250);
        //toast.show();


        // Add URL Click Listener
        // Toggles the visibility of the radio buttons based on the state of the "add image URL" check box
        //-------------------------------------------------------------------------------------
        m_chkBoxURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (m_chkBoxURL.isChecked()){
                    m_radGroup.setVisibility(View.INVISIBLE);
                }
                else{
                    m_radGroup.setVisibility(View.VISIBLE);;
                }
            }
        });
        //-------------------------------------------------------------------------------------


        // Return to main activity via Cancel button
        //-------------------------------------------------------------------------------------
        btn_cancel = findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent( AddEntity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //-------------------------------------------------------------------------------------


        // functionality for OK button
        //-------------------------------------------------------------------------------------
        btn_ok = findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                //region process & validate form info

                int _ini, _hp, _maxHP, _ac;
                String _name, _con, _url;

                // check ac field and define value
                if (m_ac.getText().length() > 0){
                    // sets value
                    _ac = Integer.parseInt(m_ac.getText().toString());
                }
                else{
                    // defines default value if field is blank
                    _ac = rng.nextInt(10)+10;
                }

                // check initiative field and define value
                if (m_ini.getText().length() > 0){
                    // sets value
                    _ini = Integer.parseInt(m_ini.getText().toString());
                }
                else{
                    // defines default value if field is blank
                    _ini = rng.nextInt(20)+1;
                }

                // check HP field and define value
                if (m_hp.getText().length() > 0){
                    // sets value
                    _hp = Integer.parseInt(m_hp.getText().toString());
                }
                else{
                    // defines default value if field is blank
                    _hp = 25;
                }

                // check maxHP field and define value
                if (m_hpMax.getText().length() > 0){
                    // sets value
                    _maxHP = Integer.parseInt(m_hpMax.getText().toString());
                }
                else{
                    // defines default value if field is blank
                    _maxHP = 25;
                }

                // check Name field and define value
                if (m_name.getText().length() > 0){
                    // sets value
                    _name = m_name.getText().toString();
                }
                else{
                    // defines default value if field is blank
                    _name = "Unnamed Combatant";
                }

                // check Condition field and define value
                if (m_con.getText().length() > 0){
                    // sets value
                    _con = m_con.getText().toString();
                }
                else{
                    // defines default value if field is blank
                    _con = "N/A";
                }

                // checks for URL entry or Radio Buttons
                if (m_chkBoxURL.isChecked()){

                    // check URL field and define value
                    if (m_url.getText().length() > 0){
                        // sets value
                        _url = m_url.getText().toString();
                    }
                    else{
                        // defines default value if field is blank
                        _url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/1200px-Icon-round-Question_mark.svg.png";
                    }

                }
                else{

                    if (m_boss.isChecked()){
                        _url = "boss";
                    }
                    else if (m_enemy.isChecked()){
                        _url = "enemy";
                    }
                    else if (m_player.isChecked()){
                        _url = "player";
                    }
                    else{
                        _url = "neutral";
                    }
                }


                //endregion

                // create combatant object
                Combatant newCom = new Combatant(myApplication.getNextID(),_ini, _hp, _maxHP, _name, _con, _url, _ac);

                // add object to global list
                combatantList.add(newCom);

                // go back to main activity
                Intent intent = new Intent( AddEntity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //-------------------------------------------------------------------------------------
    }
}