package com.example.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.model.TopPokemons;

import java.util.List;

public class AdapterListaTopHabilidades extends RecyclerView.Adapter<AdapterListaTopHabilidades.MyViewHolder> {

    private List<TopPokemons> listTopHabilidades;

    public AdapterListaTopHabilidades(List<TopPokemons> list) {
        this.listTopHabilidades = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tipo;
        TextView quantidade;

        public MyViewHolder(View view){
            super(view);
            tipo = view.findViewById(R.id.textViewHabilidade);
            quantidade = view.findViewById(R.id.textViewQuantidade);
        }
    }

    @NonNull
    @Override
    public AdapterListaTopHabilidades.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_top_habilidades, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaTopHabilidades.MyViewHolder holder, int position) {

        TopPokemons obj = listTopHabilidades.get(position);
        holder.tipo.setText(obj.getItem());
        holder.quantidade.setText(Integer.toString(obj.getQuantidade()));
    }

    @Override
    public int getItemCount() {
        return listTopHabilidades.size();
    }
}
