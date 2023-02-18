package com.example.pokedex.adapter;

import android.view.ViewGroup;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView filter;
        TextView type;
        TextView value;

        public MyViewHolder(View view){
            super(view);
            filter = view.findViewById(R.id.textViewOperacao);
            type = view.findViewById(R.id.textViewCategoria);
            value = view.findViewById(R.id.textViewValor);
        }
    }

    @NonNull
    @Override
    public AdapterMain.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMain.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
