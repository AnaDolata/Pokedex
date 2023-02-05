package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ListarDetalhes extends AppCompatActivity {

  EditText nome, tipo, habilidade;
  ImageView imagem;
  Bitmap bitmap;

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
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        i.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(i, 1);
      }
    });

    //Intent it = getIntent();
    //if(it != null){
      //String id = it.getStringExtra("id");
      //if(id != null) {
        //int id1 = Integer.parseInt(id);
        //pega o id no banco e set nos campos
        nome.setText("nome");
        tipo.setText("tipo");
        habilidade.setText("habildadessss");
        imagem.setImageResource(R.drawable.img);
      //}
    //}
  }

  public void save(View view){
    //atualizar a imagem tbmm - no método de baixo
    Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
    startActivity(i);
    finish();
  }

  public void delete(View view){
    //deletar da base
    Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
    startActivity(i);
    finish();
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    InputStream stream = null;
    if (requestCode == 1 && resultCode == RESULT_OK) {
      try {
        if (bitmap != null) {
          bitmap.recycle();
        }
        stream = getContentResolver().openInputStream(data.getData());
        bitmap = BitmapFactory.decodeStream(stream);
        imagem.setImageBitmap(bitmap);
        //salvar no banco aqui
      }
      catch(FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        if (stream != null)
          try {
            stream.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
    }
  }
}