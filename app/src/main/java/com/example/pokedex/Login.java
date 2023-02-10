package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokedex.apiPokemon.RetrofitConfigAuth;
import com.example.pokedex.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

  EditText user, password;
  AlertDialog alerta;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    user = findViewById(R.id.editTextUser);
    password = findViewById(R.id.editTextPassword);
  }

  public void verify(View view){
    if(user.length() == 0 || password.length() == 0){
      Toast.makeText(this, "Digite usuário e senha", Toast.LENGTH_SHORT).show();
    }else {
      Usuario usuario = new Usuario();
      usuario.setLogin(user.getText().toString());
      usuario.setSenha(password.getText().toString());

      Call<Usuario> call = new RetrofitConfigAuth().getAuthService().auth(usuario);

      call.enqueue(new Callback<Usuario>() {
        @Override
        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
          if(response.isSuccessful()) {
            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
            finish();
          }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("Usuário ou Senha incorretos");
            builder.setTitle("Atenção");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
              }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
          }
        }

        @Override
        public void onFailure(Call<Usuario> call, Throwable t) {
          Toast.makeText(Login.this, "Usuário ou Senha incorretos", Toast.LENGTH_SHORT).show();
        }
      });
    }
  }
}