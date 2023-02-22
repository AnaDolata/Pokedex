package com.example.pokedex.model;

public class TopPokemons {

    private String item;
    private int quantidade;

    public TopPokemons() {
    }

    public TopPokemons(String item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
