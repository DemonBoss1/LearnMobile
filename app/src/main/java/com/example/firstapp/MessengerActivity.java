package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MessengerActivity extends AppCompatActivity {

    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        text = findViewById(R.id.message_text);

        Intent intent = getIntent();
        String str = intent.getStringExtra("text");
        text.setText(str);
    }
    public void sendMessage(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.button_send_message:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, text.getText().toString());
                startActivity(intent);
                break;
            case R.id.button_search:
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, text.getText().toString());
                startActivity(intent);
                break;
            case R.id.dialingNumber:
                intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
                break;
        }
    }
}