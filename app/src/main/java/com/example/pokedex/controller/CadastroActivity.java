package com.example.pokedex.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.example.pokedex.R;
import com.example.pokedex.apiPokemon.RetrofitConfig;
import com.example.pokedex.model.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    private Bitmap bit;
    ImageView image;
    TextView txtNome, txtHabilidade, txtTipo;
    private final int REQUEST_CAMERA_CODE = 4;
    String stringIMG;
    String nome, habilidade, tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        image = (ImageView) findViewById(R.id.txtImagem);
        txtNome = (TextView) findViewById(R.id.txtNomeCadastro);
        txtHabilidade = (TextView) findViewById(R.id.txtHabilidadeCadastro);
        txtTipo = (TextView) findViewById(R.id.txtTipoCadastro);

    }

    public void cadastrar(View view){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte img[] = stream.toByteArray();

        nome = txtNome.getText().toString();
        habilidade = txtHabilidade.getText().toString();
        tipo = txtTipo.getText().toString();
        stringIMG = img.toString();

        Pokemon pokemon = new Pokemon();
        pokemon.setFoto(stringIMG);
        pokemon.setNome(nome);
        pokemon.setHabilidades(habilidade);
        pokemon.setTipo(tipo);

        Call<Void> call = new RetrofitConfig().getPKService().createPokemon(pokemon);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getBaseContext(),"Pokemon Inserido com Sucesso",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Erro ao inserir Pokemon",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void tirarFoto(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CAMERA_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CAMERA_CODE){
                bit = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bit);
            }
        }
    }
}