package com.example.pokedex.adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;
import com.google.gson.Gson;

import java.util.BitSet;
import java.util.List;

public class AdapterListaTodos extends RecyclerView.Adapter<AdapterListaTodos.MyViewHolder> {

  private List<Pokemon> list;

  public AdapterListaTodos(List<Pokemon> list){ this.list = list; }

  public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView name;
    ImageView image;


    public MyViewHolder(View view) {
      super(view);
      name = view.findViewById(R.id.textViewName);
      image = view.findViewById(R.id.imageViewPokemon);
    }
  }

  @NonNull
  @Override
  public AdapterListaTodos.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View listItem = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_list_lista_todos, parent, false);
    return new MyViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(@NonNull AdapterListaTodos.MyViewHolder holder, int position) {
    Pokemon obj = list.get(position);
    holder.name.setText(obj.getNome());
    String jsonString = "{'image': 'base64-encoded-byte-array'}";
    Gson gson = new Gson();
    Pokemon myObject = gson.fromJson(jsonString, Pokemon.class);
    //holder.image.setImageResource(R.drawable.img);

    if(myObject != null) {
      byte[] img = Base64.decode(myObject.getFoto(), Base64.DEFAULT);
      Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
     holder.image.setImageBitmap(bitmap);
   }
  }

  @Override
  public int getItemCount() {
    return list.size();
  }
}
