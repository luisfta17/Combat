package com.example.codeclan.combat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity{
    TextView player_hp;
    TextView player_adrenaline;
    TextView android_hp;
    TextView android_adrenaline;
    Button basic_att_bttn;
    Button special_att_bttn;
    Handler handler = new Handler();
    Ninja ninja = new Ninja("Player 1");
    Knight knight = new Knight("Android");
    CharSequence win = "You win! - game will restart in a second";
    CharSequence lose = "You lose :-( - game will restart in a second";
    int duration = Toast.LENGTH_LONG;

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
        if (ninja.isAlive() && knight.isAlive()){
            ninja.basicAttack(knight);
            refresh();
            handler.postDelayed(checkingDeadPlayers, 1);
            if (ninja.isAlive() && knight.isAlive()){
                handler.postDelayed(actionBackDelayed, 1500);
                handler.postDelayed(checkingDeadPlayers, 1700);
            }
        }
    }

    public void onSpecialAttBttnClicked(View view){
        if (ninja.getAdrenaline() > 50){
            if (ninja.isAlive() && knight.isAlive()){
                ninja.specialAttack(knight);
                refresh();
                handler.postDelayed(checkingDeadPlayers, 1);
                if (knight.isAlive() && ninja.isAlive()){
                    handler.postDelayed(actionBackDelayed, 1500);
                    handler.postDelayed(checkingDeadPlayers, 1700);
                }
            }
            refresh();
        }
    }


    public void refresh(){
        player_hp.setText("HP: " + Double.toString(ninja.getHp()));
        player_adrenaline.setText("Adrenaline: " + Double.toString(ninja.getAdrenaline()));
        android_hp.setText("HP: " + Double.toString(knight.getHp()));
        android_adrenaline.setText("Adrenaline: " + Double.toString(knight.getAdrenaline()));
    }

    public void showToast(){
        Context context = getApplicationContext();
        if(ninja.isAlive()){
            Toast toast = Toast.makeText(context, win, duration);
            toast.show();
            handler.postDelayed(restartGame, 3000);
        } else {
            Toast toast = Toast.makeText(context, lose, duration);
            toast.show();
            handler.postDelayed(restartGame, 3000);
        }
    }

    private Runnable actionBackDelayed = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "Action back with delay");
            knight.actionBack(ninja);
            refresh();
        }
    };

    private Runnable restartGame = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "Restart game with delay");
            ninja.setHp(ninja.getMaxHp());
            knight.setHp(knight.getMaxHp());
            ninja.setAdrenaline(0);
            knight.setAdrenaline(0);
            refresh();
        }
    };

    private Runnable checkingDeadPlayers = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "Checking for dead players");
            if (!ninja.isAlive() || !knight.isAlive()){
                showToast();
            }
        }
    };



}
