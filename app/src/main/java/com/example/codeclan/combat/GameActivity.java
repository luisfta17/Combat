package com.example.codeclan.combat;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    TextView player_hp;
    TextView player_adrenaline;
    TextView android_hp;
    TextView android_adrenaline;
    Button basic_att_bttn;
    Button special_att_bttn;
    Ninja ninja = new Ninja("Player 1");
    Knight knight = new Knight("Android");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        player_hp = findViewById(R.id.hp_counter);
        player_adrenaline = findViewById(R.id.adrenaline_counter);
        android_adrenaline = findViewById(R.id.android_adrenaline);
        android_hp = findViewById(R.id.android_hp);
        basic_att_bttn = findViewById(R.id.basic_att_bttn);
        special_att_bttn = findViewById(R.id.special_att_bttn);
        // Refresh method to update hud status
        refresh();
    }

    public void onBasicAttBttnClicked(View view){
        if (ninja.isAlive()){
            ninja.basicAttack(knight);
        }
        refresh();
        if (knight.isAlive()){
            knight.actionBack(ninja);
        }
        refresh();
    }
    

    public void refresh(){
        player_hp.setText("HP: " + Double.toString(ninja.getHp()));
        player_adrenaline.setText("Adrenaline: " + Double.toString(ninja.getAdrenaline()));
        android_hp.setText("HP: " + Double.toString(knight.getHp()));
        android_adrenaline.setText("Adrenaline: " + Double.toString(knight.getAdrenaline()));
    }


}
