package com.example.model;



public class Peca {                 // Classe para definir caracteristicas da Peça
    
    private String tituloPeca;  

    public Peca(String tituloPeca) {
        
        this.tituloPeca = tituloPeca;

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

    String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    
}
