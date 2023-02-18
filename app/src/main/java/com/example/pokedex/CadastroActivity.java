package com.example.pokedex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class CadastroActivity extends AppCompatActivity {

    private Bitmap bit;
    ImageView image;
    TextView txtNome, txtHabilidade, txtTipo;
    private final int REQUEST_CAMERA_CODE = 4;

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