package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextText;
    Button buttonOk;
    Button buttonCancel;
    Button buttonLab5;
    Button button_guess_the_number;
    Button do_list;
    Button button_music_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextText = findViewById(R.id.editTextText);
        buttonOk = findViewById(R.id.ButtonOk);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOk.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                editTextText.setText("Hello World!");
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOk.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                editTextText.setText("");
            }
        });
    }

    private void CreateToast(){
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.button_music_player:
                intent = new Intent(getApplicationContext(), Music_player_Activity.class);
                startActivity(intent);
                break;
            case R.id.do_list:
                intent = new Intent(getApplicationContext(), DoListActivity.class);
                startActivity(intent);
                break;
            case R.id.button_guess_the_number:
                String text = editTextText.getText().toString();
                intent = new Intent(getApplicationContext(), GuessNumberActivity.class);
                intent.putExtra("text",text);
                startActivity(intent);
                break;
            case R.id.ButtonLab5:
                intent = new Intent(getApplicationContext(), Lab5Activity.class);
                startActivity(intent);
                break;
            case R.id.button_message:
                intent = new Intent(getApplicationContext(), MessengerActivity.class);
                startActivity(intent);
                break;
        }
    }
}

