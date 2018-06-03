package com.example.codeclan.combat;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity{
    ImageView samurai;
    ImageView knightImage;
    AnimationDrawable samuraiAnimations;
    AnimationDrawable knightAnimations;
    TextView player_hp;
    TextView player_adrenaline;
    TextView android_hp;
    TextView android_adrenaline;
    Button basic_att_bttn;
    Button special_att_bttn;
    ProgressBar player_health_bar;
    ProgressBar android_health_bar;
    ProgressBar player_adrenaline_bar;
    ProgressBar android_adrenaline_bar;
    Handler handler = new Handler();
    Ninja ninja = new Ninja("Player 1");
    Knight knight = new Knight("Android");
    CharSequence win = "You win! - game will restart in a second";
    CharSequence lose = "You lose :-( - game will restart in a second";
    CharSequence notEnough = "You don't have enough Adrenaline!";
    int duration = Toast.LENGTH_LONG;
    int shortMessage = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        samurai = findViewById(R.id.ninja);
        knightImage = findViewById(R.id.knight);
        player_hp = findViewById(R.id.hp_counter);
        player_adrenaline = findViewById(R.id.adrenaline_counter);
        android_adrenaline = findViewById(R.id.android_adrenaline);
        android_hp = findViewById(R.id.android_hp);
        basic_att_bttn = findViewById(R.id.basic_att_bttn);
        special_att_bttn = findViewById(R.id.special_att_bttn);
        player_health_bar = findViewById(R.id.player_health_bar);
        android_health_bar = findViewById(R.id.android_health_bar);
        player_adrenaline_bar = findViewById(R.id.player_adrenaline_bar);
        android_adrenaline_bar = findViewById(R.id.android_adrenaline_bar);
        player_health_bar.setMax((int) ninja.getMaxHp());
        android_health_bar.setMax((int) knight.getMaxHp());
        player_adrenaline_bar.setMax((int) ninja.getMaxAdrenaline());
        android_adrenaline_bar.setMax((int) knight.getMaxAdrenaline());
        player_health_bar.setScaleY(4f);
        android_health_bar.setScaleY(4f);
        player_adrenaline_bar.setScaleY(4f);
        android_adrenaline_bar.setScaleY(4f);
        ninja.setHp(50);
        // Refresh method to update hud status
        refresh();
    }

    public void onBasicAttBttnClicked(View view){
        if (ninja.isAlive() && knight.isAlive()){
            ninja.basicAttack(knight);
            samuraiAttackAnimation();
            handler.postDelayed(samuraiStandsWithDelay, 1000);
            handler.postDelayed(knightGetHitWithDelay, 400);
            handler.postDelayed(refreshWithDelay, 1400);
            handler.postDelayed(checkingDeadPlayers, 1700);
            if (ninja.isAlive() && knight.isAlive()){
                handler.postDelayed(actionBackDelayed, 2400);
                handler.postDelayed(checkingDeadPlayers, 4500);
            }
        }
    }

    public void onSpecialAttBttnClicked(View view){
        if (ninja.getAdrenaline() > 50){
            if (ninja.isAlive() && knight.isAlive()){
                ninja.specialAttack(knight);
                samuraiAttackAnimation();
                handler.postDelayed(samuraiStandsWithDelay, 1000);
                handler.postDelayed(knightGetHitWithDelay, 400);
                handler.postDelayed(refreshWithDelay, 1400);
                handler.postDelayed(checkingDeadPlayers, 1700);
                if (knight.isAlive() && ninja.isAlive()){
                    handler.postDelayed(actionBackDelayed, 2400);
                    handler.postDelayed(checkingDeadPlayers, 4500);
                }
            }
        } else {
            showNotEnoughAdrenaline();
        }
    }


    public void samuraiDiesAnimation(){
        samurai.setImageResource(R.drawable.samurai_dies);
        samuraiAnimations = (AnimationDrawable) samurai.getDrawable();
        samuraiAnimations.start();
    }

    public void samuraiGetsHitAnimation(){
        samurai.setImageResource(R.drawable.samurai_gethit);
        samuraiAnimations = (AnimationDrawable) samurai.getDrawable();
        samuraiAnimations.start();
    }

    public void samuraiAttackAnimation(){
        samurai.setImageResource(R.drawable.samurai_attack);
        samuraiAnimations = (AnimationDrawable) samurai.getDrawable();
        samuraiAnimations.start();
    }

    public void samuraiStandsAnimation(){
        samurai.setImageResource(R.drawable.samurai_stands);
        samuraiAnimations = (AnimationDrawable) samurai.getDrawable();
        samuraiAnimations.start();
    }

    public void knightAttackAnimation(){
        knightImage.setImageResource(R.drawable.knight_attack);
        knightAnimations = (AnimationDrawable) knightImage.getDrawable();
        knightAnimations.start();
    }

    public void knightGetHitAnimation(){
        knightImage.setImageResource(R.drawable.knight_get_hit);
        knightAnimations = (AnimationDrawable) knightImage.getDrawable();
        knightAnimations.start();
    }

    public void knightStandsAnimation(){
        knightImage.setImageResource(R.drawable.knight_stands);
        knightAnimations = (AnimationDrawable) knightImage.getDrawable();
        knightAnimations.start();
    }

    public void knightDiesAnimation(){
        knightImage.setImageResource(R.drawable.knight_dies);
        knightAnimations = (AnimationDrawable) knightImage.getDrawable();
        knightAnimations.start();
    }



    public void refresh(){
        player_hp.setText("HP: ");
        player_adrenaline.setText("Adrenaline: " );
        android_hp.setText("HP: ");
        android_adrenaline.setText("Adrenaline: ");
        player_health_bar.setProgress(((int) ninja.getHp()));
        android_health_bar.setProgress(((int) knight.getHp()));
        player_adrenaline_bar.setProgress(((int) ninja.getAdrenaline()));
        android_adrenaline_bar.setProgress(((int) knight.getAdrenaline()));
        samuraiStandsAnimation();
        knightStandsAnimation();
    }

    public void showToast(){
        Context context = getApplicationContext();
        if(ninja.isAlive()){
            Toast toast = Toast.makeText(context, win, duration);
            toast.show();
            handler.postDelayed(restartGame, 5000);
        } else {
            Toast toast = Toast.makeText(context, lose, duration);
            toast.show();
            handler.postDelayed(restartGame, 5000);
        }
    }

    public void showNotEnoughAdrenaline(){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, notEnough, shortMessage);
        toast.show();
    }

    private Runnable actionBackDelayed = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "Action back with delay");
            knight.actionBack(ninja);
            handler.postDelayed(samuraiGetHitWithDelay, 650);
            knightAttackAnimation();
            handler.postDelayed(knightStandsWithDelay, 900);
            handler.postDelayed(refreshWithDelay, 1650);
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
            if (!ninja.isAlive()){
                samuraiDiesAnimation();
                showToast();
            } if(!knight.isAlive()){
                knightDiesAnimation();
                showToast();
            }
        }
    };


    private Runnable refreshWithDelay = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "Refresh with delay");
            refresh();

        }
    };

    private Runnable samuraiGetHitWithDelay = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "samurai get hit animation with delay");
            samuraiGetsHitAnimation();
        }
    };

    private Runnable samuraiStandsWithDelay = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "samurai stands animation with delay");
            samuraiStandsAnimation();
        }
    };

    private Runnable knightStandsWithDelay = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "Knight stands animation with delay");
            knightStandsAnimation();
        }
    };

    private Runnable knightGetHitWithDelay = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "knight get hit animation with delay");
            knightGetHitAnimation();
        }
    };
    

}
