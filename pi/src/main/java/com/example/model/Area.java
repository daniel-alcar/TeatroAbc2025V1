package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class Area {

    private String titulo;  
    private int capacidadeMaxima;      
    private double preco;  
    private Map<Integer, Boolean> poltronas; 

    public Area(String titulo, int capacidade, double preco) {
        
        this.titulo = titulo;
        this.capacidadeMaxima = capacidade;
        this.preco = preco;
        this.poltronas = new HashMap<>(); 
    }


    public boolean verificarDisponibilidade(int numeroPoltrona) {
        validarCapacidadeMaxima(numeroPoltrona);
        

        Boolean ocupada = poltronas.get(numeroPoltrona);
        return ocupada == null || !ocupada; 
    }


    public boolean reservarPoltrona(int numeroPoltrona) {

        if (!verificarDisponibilidade(numeroPoltrona)) {
            return false;
        }
        
        validarCapacidadeMaxima(numeroPoltrona);
        

        poltronas.put(numeroPoltrona, true);
        return true;
    }


    public boolean marcarPoltronaReservada(int numeroPoltrona) {
        if (!verificarDisponibilidade(numeroPoltrona)) {
                return false; 
            }

        poltronas.put(numeroPoltrona, true);
        return true;
    }
    

    private void validarCapacidadeMaxima(int numeroPoltrona) {
        if (numeroPoltrona <= 0 || numeroPoltrona > capacidadeMaxima) {
            throw new IllegalArgumentException("Número de poltrona inválido.");
        }
    }
    
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Map<Integer, Boolean> getPoltronas() {
        return poltronas;
    }

    public void setPoltronas(Map<Integer, Boolean> poltronas) {
        this.poltronas = poltronas;
    }
    
   
    
}