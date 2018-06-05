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
    AnimationDrawable samuraiAnimation;
    Button play_bttn;
    MediaPlayer mainTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        samuraiImage = findViewById(R.id.samurai_image);
        samuraiImage.setImageResource(R.drawable.samurai_stands);
        play_bttn = findViewById(R.id.play_btn);
        samuraiAnimation = (AnimationDrawable) samuraiImage.getDrawable();
        samuraiAnimation.start();
        mainTheme = MediaPlayer.create(this, R.raw.maintheme);
        mainTheme.start();
        mainTheme.setLooping(true);
    }


    public void onPlayBttnClicked(View view){
        Intent i=new Intent(MenuActivity.this, GameActivity.class);
        startActivity(i);
        mainTheme.stop();

    }
}
