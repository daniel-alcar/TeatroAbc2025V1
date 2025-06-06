package com.example.model;

public class Peca { // Classe para definir características da Peça

    private int id;
    private String tituloPeca;

    // Construtor com ID
    public Peca(int id, String tituloPeca) {
        this.id = id;
        this.tituloPeca = tituloPeca;
    }

    // Construtor sem ID (útil em formulários ou criação inicial)
    public Peca(String tituloPeca) {
        this.tituloPeca = tituloPeca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloPeca() {
        return tituloPeca;
    }

    public void setTituloPeca(String tituloPeca) {
        this.tituloPeca = tituloPeca;
    }

    @Override
    public String toString() {
        return "Peça: " + tituloPeca;
    }

    public String getNome() {
        return tituloPeca;
    }
}
