package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                editTextText.setText("Hello World!");
            }
        });
    }

    private void CreateToast(){
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
}

