package com.example.model;

public class Peca { 

    private int id;
    private String tituloPeca;

    public Peca(int id, String tituloPeca) {
        this.id = id;
        this.tituloPeca = tituloPeca;
    }

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
