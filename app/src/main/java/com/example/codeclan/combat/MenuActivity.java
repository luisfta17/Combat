package com.example.codeclan.combat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    ImageView samuraiImage;
    ImageView ninjaImage;
    AnimationDrawable samuraiAnimation;
    AnimationDrawable ninjaAnimation;
    Button play_bttn;
    Button play_bttn2;
    MediaPlayer mainTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        samuraiImage = findViewById(R.id.samurai_image);
        ninjaImage = findViewById(R.id.ninja_image);
        samuraiImage.setImageResource(R.drawable.samurai_stands);
        ninjaImage.setImageResource(R.drawable.ninja_alert);
        play_bttn = findViewById(R.id.play_btn);
        play_bttn2 = findViewById(R.id.play_bttn2);
        samuraiAnimation = (AnimationDrawable) samuraiImage.getDrawable();
        samuraiAnimation.start();
        ninjaAnimation = (AnimationDrawable) ninjaImage.getDrawable();
        ninjaAnimation.start();
        mainTheme = MediaPlayer.create(this, R.raw.maintheme);
        mainTheme.start();
        mainTheme.setLooping(true);
    }


    public void onPlayBttnClicked(View view){
        Intent i=new Intent(MenuActivity.this, GameActivity.class);
        startActivity(i);
        if (mainTheme.isPlaying()){
            mainTheme.stop();
        }
    }

    public void onPlayBttn2Clicked(View view){
        Intent i=new Intent(MenuActivity.this, GameTwoActivity.class);
        startActivity(i);
        if (mainTheme.isPlaying()){
            mainTheme.stop();
        }
    }
}
