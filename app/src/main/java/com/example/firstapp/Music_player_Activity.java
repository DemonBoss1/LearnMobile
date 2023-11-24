package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.view.View;
import android.widget.SeekBar;

public class Music_player_Activity extends AppCompatActivity {

    private ImageButton playImageButton, stopImageButton;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        playImageButton = findViewById(R.id.playImageButton);
        stopImageButton = findViewById(R.id.stopImageButton);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.back_in_black);

        seekBar = findViewById(R.id.seekBar_luminosite);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float volume=i/100f;
                mediaPlayer.setVolume(volume,volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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