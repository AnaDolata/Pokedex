package com.example.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;

public class AdapterTipo extends RecyclerView.Adapter<AdapterTipo.MyViewHolder> {

  public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView name;

    public MyViewHolder(View view) {
      super(view);
      name = view.findViewById(R.id.textViewNameType);
    }
  }

  @NonNull
  @Override
  public AdapterTipo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View listItem = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_list_tipo, parent, false);
    return new MyViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(@NonNull AdapterTipo.MyViewHolder holder, int position) {
    holder.name.setText("Pokebola");
  }

  @Override
  public int getItemCount() {
    return 10;
  }
}
