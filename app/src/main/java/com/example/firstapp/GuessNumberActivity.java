package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GuessNumberActivity extends AppCompatActivity {
    Button check;
    EditText field;
    TextView info;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_number);
        setVariables();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int curNum = Integer.parseInt(field.getText().toString());
                    if (curNum == number) {
                        info.setText("Вы правильный ответ!");
                    } else if (curNum > number) {
                        info.setText(curNum + " - слишком большое число!");
                    } else if (curNum < number) {
                        info.setText(curNum + " - слишком маленькое число!");
                    }
                }
                catch (Exception e){
                    info.setText("Введите число!");
                }
            }
        });
    }
    private void setVariables() {
        check = findViewById(R.id.check_btn);
        field = findViewById(R.id.text_field);
        info = findViewById(R.id.info_text);
        number = (int) (Math.random() * 100 + 1);
    }
}