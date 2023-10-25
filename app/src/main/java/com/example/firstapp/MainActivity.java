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
    Button buttonLab5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextText = findViewById(R.id.editTextText);
        buttonOk = findViewById(R.id.ButtonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOk.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                editTextText.setText("Hello World!");
            }
        });
        buttonLab5 = findViewById(R.id.ButtonLab5);

        buttonLab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Lab5Activity.class);
                startActivity(intent);
            }
        });
    }

    private void CreateToast(){
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
}

