package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.view.View;

public class Music_player_Activity extends AppCompatActivity {

    private ImageButton playImageButton, stopImageButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        playImageButton = findViewById(R.id.playImageButton);
        stopImageButton = findViewById(R.id.stopImageButton);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.back_in_black);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    public void MusicButtons(View view) {
        switch (view.getId()) {
            case R.id.playImageButton:
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    playImageButton.setImageResource(R.drawable.ic_stop);
                } else {
                    mediaPlayer.pause();
                    playImageButton.setImageResource(R.drawable.ic_play);
                }
                break;
            case R.id.stopImageButton:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    playImageButton.setImageResource(R.drawable.ic_play);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.back_in_black);
                }
                break;
        }
    }
}