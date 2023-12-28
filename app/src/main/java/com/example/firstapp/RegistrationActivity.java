package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextPassword;
    private static RegistrationActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        activity = this;
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startDoList();
    }
    private void init(){
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
    }
    private boolean checkEditText(){
        boolean isEmpty = false;
        if(editTextLogin.getText().toString().isEmpty()){
            Toast.makeText(this, "E-mail empty", Toast.LENGTH_LONG).show();
            isEmpty = true;
        }else if(editTextLogin.getText().toString().indexOf('@')==-1){
            Toast.makeText(this, "Entry e-mail", Toast.LENGTH_LONG).show();
            isEmpty = true;
        }
        if(editTextPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Password empty", Toast.LENGTH_LONG).show();
            isEmpty = true;
        } else if(editTextPassword.getText().toString().length()<5){
            Toast.makeText(this, "Password < 5 char", Toast.LENGTH_LONG).show();
            isEmpty = true;
        }
        return !isEmpty;
    }

    public void loginInDatabase(View view) {
        if(checkEditText()) {
            DataBase.Login(editTextLogin.getText().toString(), editTextPassword.getText().toString());

        }
    }
    public static void loginComplete(){
        activity.startDoList();
    }

    public void registrationInDatabase(View view) {
        if(checkEditText()) {
            DataBase.Registration(editTextLogin.getText().toString(), editTextPassword.getText().toString());
        }
    }
    public void startDoList(){
        //if(DataBase.checkUser().isEmailVerified()){
        if(DataBase.checkUser() != null){
            Intent intent = new Intent(this, DoListActivity.class);
            startActivity(intent);
        }else{
            //Toast.makeText(this, "Проверти вашу почту для подтверждения E-mail адреса", Toast.LENGTH_LONG).show();
        }
    }
}