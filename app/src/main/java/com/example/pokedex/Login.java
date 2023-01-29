package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

  EditText user, password;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    user = findViewById(R.id.editTextUser);
    password = findViewById(R.id.editTextPassword);
  }

  public void verify(View view){
    //consultar BD
    //ver se não tá vazio
    Intent i = new Intent(Login.this, MainActivity.class);
    startActivity(i);
    finish();
  }
}