package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class EditEntity extends AppCompatActivity {

    MyApplication myApplication = (MyApplication) this.getApplication();
    List<Combatant> combatantList;
    int editID;
    int currEntityPos=0;

    Button btn_addHP, btn_rmvHP, btn_editOK, btn_rmvEntity;
    TextView tv_entityName, tv_entityHP, tv_entityMaxHP;
    ImageView iv_image;
    EditText et_dmg, et_heal, et_cond;

    Combatant combatant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entity);

        // get reference to global list
        combatantList = myApplication.getCombatantList();

        // Assign Variables
        btn_addHP = findViewById(R.id.btn_healing);
        btn_rmvHP = findViewById(R.id.btn_dmg);
        btn_editOK = findViewById(R.id.btn_editOk);
        btn_rmvEntity = findViewById(R.id.btn_editDelete);

        tv_entityName = findViewById(R.id.tv_editName);
        tv_entityHP = findViewById(R.id.tv_editHp);
        tv_entityMaxHP = findViewById(R.id.tv_editHpMax);

        et_dmg = findViewById(R.id.et_editDmg);
        et_heal = findViewById(R.id.et_editHeal);
        et_cond = findViewById(R.id.et_editCon);

        iv_image = findViewById(R.id.iv_editPic);

        // Get Combatant Data
        Intent intent = getIntent();
        editID = intent.getIntExtra("id", -1);

        // set empty combatant
        combatant = null;

        // Set Autofill Fields
        if (editID >= 0){
            // find combatant with matching id
            int tmp = 0;
            for (Combatant c: combatantList){
                if (c.getId() == editID){
                    combatant = c;
                    currEntityPos=tmp;
                }
                tmp++;
            }
            // update edit page with combatant info
            tv_entityName.setText(combatant.getM_name());
            tv_entityHP.setText(String.valueOf(combatant.getM_hp()));
            tv_entityMaxHP.setText(String.valueOf(combatant.getM_hpMax()));
            et_cond.setText(combatant.getM_cond());

            switch (combatant.getM_url()){
                case "boss":
                    iv_image.setImageResource(R.drawable.boss_img);
                    break;
                case "enemy":
                    iv_image.setImageResource(R.drawable.enemy_img);
                    break;
                case "player":
                    iv_image.setImageResource(R.drawable.player_img);
                    break;
                case "neutral":
                    iv_image.setImageResource(R.drawable.neutral_img);
                    break;
                default:
                    Glide.with(EditEntity.this).load(combatant.getM_url()).into(iv_image);
                    break;
            }

        }
        else{
            // ERROR - return to main activity

        }

        //Toast toast = Toast.makeText(this, "Current Position: " + currEntityPos, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 250);
        //toast.show();


        // Remove HP Button Logic
        btn_rmvHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_dmg.getText().length() > 0){

                    int currHP = combatant.getM_hp();
                    int toRemove = Integer.parseInt(et_dmg.getText().toString());
                    int result = currHP-toRemove;

                    if (result > 0){
                        combatant.setM_hp(result);
                    }
                    else{
                        combatant.setM_hp(0);
                        tv_entityHP.setTextColor(Color.RED);
                    }

                    tv_entityHP.setText(String.valueOf(combatant.getM_hp()));

                    et_dmg.setText("0");
                    et_dmg.clearFocus();
                }
            }
        });

        // Add HP Button Logic
        btn_addHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_heal.getText().length() > 0){

                    int limit = combatant.getM_hpMax();
                    int currHP = combatant.getM_hp();
                    int toAdd = Integer.parseInt(et_heal.getText().toString());
                    int result = currHP+toAdd;

                    if (result > limit){
                        combatant.setM_hp(limit);
                    }
                    else{
                        combatant.setM_hp(result);
                    }

                    tv_entityHP.setTextColor(Color.WHITE);
                    tv_entityHP.setText(String.valueOf(combatant.getM_hp()));

                    et_heal.setText("0");
                    et_heal.clearFocus();
                }
            }
        });

        // Delete Entity Logic
        btn_rmvEntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // remove the combatant from the global list
                combatantList.remove(combatant);

                if (currEntityPos < myApplication.currInitPos){

                    myApplication.currInitPos--;
                }
                else if (myApplication.currInitPos > combatantList.size()-1){

                    myApplication.currInitPos = 0;
                }

                Intent intent = new Intent( EditEntity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // OK Button Logic
        btn_editOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                combatant.setM_cond(String.valueOf(et_cond.getText()));

                Intent intent = new Intent( EditEntity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}