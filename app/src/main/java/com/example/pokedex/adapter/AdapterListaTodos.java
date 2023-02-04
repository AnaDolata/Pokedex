package com.example.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;

public class AdapterListaTodos extends RecyclerView.Adapter<AdapterListaTodos.MyViewHolder> {

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
    holder.name.setText("blablalalalal");
    holder.image.setImageResource(R.drawable.img);
  }

  @Override
  public int getItemCount() {
    return 2;
  }
}
