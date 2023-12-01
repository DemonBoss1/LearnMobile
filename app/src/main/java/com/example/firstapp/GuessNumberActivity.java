package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        field.setText(text);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GuessNumberActivity.this);
                builder.setMessage("Вы хотите проверить число?");
                builder.setCancelable(false);
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ConvertSpeed();
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Вы отменили действие",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Проверка данных");
                alertDialog.show();

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void ConvertSpeed() {
        String text = field.getText().toString();
        if(!text.equals("")) {
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
        } else {
            info.setText("Ошибка");
            Toast.makeText(
                    getApplicationContext(),
                    "Введите какой-либо текст",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void setVariables() {
        check = findViewById(R.id.check_btn);
        field = findViewById(R.id.text_field);
        info = findViewById(R.id.info_text);
        number = (int) (Math.random() * 100 + 1);
    }
}