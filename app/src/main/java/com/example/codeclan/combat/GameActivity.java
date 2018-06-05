package com.example.codeclan.combat;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
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
    ImageView ninjaCruz;
    ImageView knightCruz;
    AnimationDrawable ninjaCruzAnimation;
    AnimationDrawable knightCruzAnimation;
    AnimationDrawable samuraiAnimations;
    AnimationDrawable knightAnimations;
    TextView player_hp;
    TextView player_adrenaline;
    TextView android_hp;
    TextView android_adrenaline;
    TextView player1_damage_done;
    TextView android_damage_done;
    Button basic_att_bttn;
    Button special_att_bttn;
    ProgressBar player_health_bar;
    ProgressBar android_health_bar;
    ProgressBar player_adrenaline_bar;
    ProgressBar android_adrenaline_bar;
    Handler handler = new Handler();
    MediaPlayer battleMusic;
    Ninja ninja = new Ninja("Player 1");
    Knight knight = new Knight("Android");
    CharSequence win = "You win! - game will restart in a second";
    CharSequence lose = "You lose :-( - game will restart in a second";
    CharSequence notEnough = "You don't have enough Adrenaline!";
    int duration = Toast.LENGTH_LONG;
    int shortMessage = Toast.LENGTH_SHORT;
    long lastAttackTime;
    long currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        samurai = findViewById(R.id.ninja);
        knightImage = findViewById(R.id.knight);
        ninjaCruz = findViewById(R.id.ninja_cruz);
        knightCruz = findViewById(R.id.knight_cruz);
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
        player1_damage_done = findViewById(R.id.player1_damage_received);
        android_damage_done = findViewById(R.id.android_damage_received);
        player_health_bar.setMax((int) ninja.getMaxHp());
        android_health_bar.setMax((int) knight.getMaxHp());
        player_adrenaline_bar.setMax((int) ninja.getMaxAdrenaline());
        android_adrenaline_bar.setMax((int) knight.getMaxAdrenaline());
        player_health_bar.setScaleY(4f);
        android_health_bar.setScaleY(4f);
        player_adrenaline_bar.setScaleY(4f);
        android_adrenaline_bar.setScaleY(4f);
        battleMusic = MediaPlayer.create(this, R.raw.battletheme);
        battleMusic.start();
        battleMusic.setLooping(true);

        // Refresh method to update hud status
        refresh();
    }

    public void onBasicAttBttnClicked(View view){
        currentTime = System.currentTimeMillis();
        if(currentTime  >= lastAttackTime  + 3000 ){
            refresh();
            if (ninja.isAlive() && knight.isAlive()){
                double initialHP = knight.getHp();
                ninja.basicAttack(knight);
                androidShowDamageReceived(initialHP);
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
    }

    public void onSpecialAttBttnClicked(View view){
        currentTime = System.currentTimeMillis();
        if(currentTime  >= lastAttackTime  + 3000 ) {
            refresh();
            if (ninja.getAdrenaline() > 50) {
                if (ninja.isAlive() && knight.isAlive()) {
                    double initialHP = knight.getHp();
                    ninja.specialAttack(knight);
                    androidShowDamageReceived(initialHP);
                    samuraiAttackAnimation();
                    handler.postDelayed(samuraiStandsWithDelay, 1000);
                    handler.postDelayed(knightGetHitWithDelay, 400);
                    handler.postDelayed(refreshWithDelay, 1400);
                    handler.postDelayed(checkingDeadPlayers, 1700);
                    if (knight.isAlive() && ninja.isAlive()) {
                        handler.postDelayed(actionBackDelayed, 2400);
                        handler.postDelayed(checkingDeadPlayers, 4500);
                    }
                }
            } else {
                showNotEnoughAdrenaline();
            }
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

    public void ninjaCruzShinesAnimation(){
        ninjaCruz.setImageResource(R.drawable.cruz);
        ninjaCruzAnimation = (AnimationDrawable) ninjaCruz.getDrawable();
        ninjaCruzAnimation.start();
    }

    public void knightCruzShinesAnimation(){
        knightCruz.setImageResource(R.drawable.cruz);
        knightCruzAnimation = (AnimationDrawable) knightCruz.getDrawable();
        knightCruzAnimation.start();
    }

    public void knightDiesAnimation(){
        knightImage.setImageResource(R.drawable.knight_dies);
        knightAnimations = (AnimationDrawable) knightImage.getDrawable();
        knightAnimations.start();
    }

    public void player1ShowDamageReceived(double initialHp){
        double total = initialHp - ninja.getHp();
        if (total < 51){
            android_damage_done.setText(Double.toString(total));
        } else {
            android_damage_done.setTextColor(Color.RED);
            android_damage_done.setText("CRITICAL " + Double.toString(total));
        }
    }

    public void androidShowDamageReceived(double initialHp){
        double total = initialHp - knight.getHp();
        if (total < 51){
            player1_damage_done.setText(Double.toString(total));

        } else {
            player1_damage_done.setTextColor(Color.RED);
            player1_damage_done.setText("CRITICAL " + Double.toString(total));
        }
    }



    public void refresh(){
        player_hp.setText("HP: ");
        player_adrenaline.setText("Adrenaline: " );
        android_hp.setText("HP: ");
        android_adrenaline.setText("Adrenaline: ");
        player1_damage_done.setTextColor(Color.rgb(255,187,51));
        android_damage_done.setTextColor(Color.rgb(255,187,51));
        player1_damage_done.setText("");
        android_damage_done.setText("");
        player_health_bar.setProgress(((int) ninja.getHp()));
        android_health_bar.setProgress(((int) knight.getHp()));
        player_adrenaline_bar.setProgress(((int) ninja.getAdrenaline()));
        android_adrenaline_bar.setProgress(((int) knight.getAdrenaline()));
        lastAttackTime = System.currentTimeMillis();
        samuraiStandsAnimation();
        knightStandsAnimation();
        ninjaCruz.setVisibility(View.INVISIBLE);
        knightCruz.setVisibility(View.INVISIBLE);
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
            double initialHP = ninja.getHp();
            knight.actionBack(ninja);
            player1ShowDamageReceived(initialHP);
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
                handler.postDelayed(ninjaCruzWithDelay, 600);
                showToast();
            } if(!knight.isAlive()){
                knightDiesAnimation();
                handler.postDelayed(knightCruzWithDelay, 600);
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

    private Runnable ninjaCruzWithDelay = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "shows ninja cruz");
           ninjaCruzShinesAnimation();
           ninjaCruz.setVisibility(View.VISIBLE);
        }
    };

    private Runnable knightCruzWithDelay = new Runnable() {
        @Override
        public void run() {
            Log.d("Handlers", "shows samurai cruz");
           knightCruzShinesAnimation();
            knightCruz.setVisibility(View.VISIBLE);
        }
    };
    

}
