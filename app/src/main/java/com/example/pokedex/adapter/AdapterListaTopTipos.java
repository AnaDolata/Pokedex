package com.example.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.model.TopPokemons;

import java.util.List;

public class AdapterListaTopTipos extends RecyclerView.Adapter<AdapterListaTopTipos.MyViewHolder> {

    private List<TopPokemons> listTopTipos;

    public AdapterListaTopTipos (List<TopPokemons> list) {
        this.listTopTipos = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tipo;
        TextView quantidade;

        public MyViewHolder(View view){
            super(view);
            tipo = view.findViewById(R.id.textViewTipo);
            quantidade = view.findViewById(R.id.textViewQuantidade);
        }
    }

    @NonNull
    @Override
    public AdapterListaTopTipos.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_top_tipos, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaTopTipos.MyViewHolder holder, int position) {

        TopPokemons obj = listTopTipos.get(position);
        holder.tipo.setText(obj.getItem());
        holder.quantidade.setText(Integer.toString(obj.getQuantidade()));
    }

    @Override
    public int getItemCount() {
        return listTopTipos.size();
    }
}
