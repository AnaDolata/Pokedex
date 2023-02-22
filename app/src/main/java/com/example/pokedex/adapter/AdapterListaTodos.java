package com.example.pokedex.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;
import com.google.gson.Gson;

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
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Pokemon pokemon = list.get(position);
    holder.name.setText(pokemon.getNome());
    String base64 = pokemon.getFoto();
    byte[] byteArr = Base64.decode(base64, 0);
    Bitmap bitmap = BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
    holder.image.setImageBitmap(bitmap);
  }

  @Override
  public int getItemCount() {
    return list.size();
  }
}
