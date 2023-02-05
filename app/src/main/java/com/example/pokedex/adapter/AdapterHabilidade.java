package com.example.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;

public class AdapterHabilidade extends RecyclerView.Adapter<AdapterHabilidade.MyViewHolder> {

  public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView name;

    public MyViewHolder(View view){
      super(view);
      name = view.findViewById(R.id.textViewAbility);
    }
  }

  @NonNull
  @Override
  public AdapterHabilidade.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View listItem = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_list_habilidade, parent, false);
    return new AdapterHabilidade.MyViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(@NonNull AdapterHabilidade.MyViewHolder holder, int position) {
    holder.name.setText("Fogo");
  }

  @Override
  public int getItemCount() {
    return 10;
  }
}
