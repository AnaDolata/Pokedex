package com.example.pokedex.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.apiPokemon.RetrofitConfig;
import com.example.pokedex.model.Pokemon;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarDetalhes extends AppCompatActivity {

  EditText nome, tipo, habilidade;
  ImageView imagem;
  Bitmap bitmap;
  String idPokemon;
  String stringIMG;

  String recebeIMG;

  private final int REQUEST_CAMERA_CODE = 4;

  @SuppressLint({"MissingInflatedId", "ResourceType"})
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listar_detalhes);

    nome = findViewById(R.id.editTextNome);
    tipo = findViewById(R.id.editTextTipo);
    habilidade = findViewById(R.id.editTextHabilidade);
    imagem = findViewById(R.id.imageViewPhoto);

    imagem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CAMERA_CODE);
      }
    });

    Intent it = getIntent();
    if(it != null){
      Bundle params = it.getExtras();
      if(params != null) {
        String pokId = params.getString("id");
        idPokemon = pokId;
        Log.i("pokemon id", pokId);

        Call<Pokemon> call = new RetrofitConfig().getPKService().getPokemonById(Long.parseLong(pokId));
        call.enqueue(new Callback<Pokemon>() {
          @Override
          public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
            if(response.isSuccessful()) {
              Pokemon pokemon = response.body();
              nome.setText(pokemon.getNome());
              tipo.setText(pokemon.getTipo());
              habilidade.setText(pokemon.getHabilidades());
              String jsonString = "{'image': 'base64-encoded-byte-array'}";
              Gson gson = new Gson();
              Pokemon myObject = gson.fromJson(jsonString, Pokemon.class);
              byte[] img = Base64.decode(myObject.getFoto(), Base64.DEFAULT);
              Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
              imagem.setImageBitmap(bitmap);
            }
          }

          @Override
          public void onFailure(Call<Pokemon> call, Throwable t) {

          }
        });
      }
    }
  }

  public void save(View view){
  try {

      String novoNome = nome.getText().toString();
      String novaHabilidade = habilidade.getText().toString();
      String novoTipo = tipo.getText().toString();
      Pokemon pokemon = new Pokemon();

      if(bitmap != null) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte img[] = stream.toByteArray();
        Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
        stringIMG = Base64.encodeToString(img, Base64.DEFAULT);
        pokemon.setFoto(stringIMG);
      }

      pokemon.setTipo(novoTipo);
      pokemon.setHabilidades(novaHabilidade);
      pokemon.setNome(novoNome);

    Call<Void> call = new RetrofitConfig().getPKService().updatePokemon(Long.parseLong(idPokemon), pokemon);

    call.enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
          Toast.makeText(ListarDetalhes.this, "Pokemon atualizado!", Toast.LENGTH_SHORT).show();
          Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
          startActivity(i);
          finish();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(ListarDetalhes.this, "Erro!", Toast.LENGTH_SHORT).show();
      }
    });

  }catch (Exception e){
    Toast.makeText(ListarDetalhes.this, "Escolha uma foto!", Toast.LENGTH_SHORT).show();
  }

  }

  public void delete(View view){
    Call<Void> call = new RetrofitConfig().getPKService().deletePokemon(Long.parseLong(idPokemon));
    call.enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()){
          Toast.makeText(ListarDetalhes.this, "Pokemon excluído!", Toast.LENGTH_SHORT).show();
          Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
          startActivity(i);
          finish();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {

      }
    });
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(resultCode == RESULT_OK){
      if(requestCode == REQUEST_CAMERA_CODE){
        bitmap = (Bitmap) data.getExtras().get("data");
        imagem.setImageBitmap(bitmap);
      }
    }
  }
}