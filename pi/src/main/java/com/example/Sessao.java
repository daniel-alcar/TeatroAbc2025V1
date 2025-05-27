package com.example;

import java.util.List;
import java.util.Map;

public class Sessao {                  
 
    private String periodo;
    private String peca;
    private List<Area> areas;
    private String nomeArea;
    private Area area;

    public Sessao(String periodo, String peca, List<Area> area) {
      
        this.periodo = periodo;
        this.peca = peca;
        this.areas = area;
    }

    public int coletaQtdPoltronasReservadas() {       
        int qtdPoltronasOcupadas = 0;

        for (Area area : areas) {
            
            for (Map.Entry<Integer, Boolean> poltrona : area.getPoltronas().entrySet()) {
              
                if (poltrona.getValue()) {
                 
                    qtdPoltronasOcupadas++;
             
                }
            }
        }

        return qtdPoltronasOcupadas;

    }
    
    
    

    public boolean reservarPoltrona(String nomeArea, int poltrona) {
        
        for (Area area : areas) {
           
            if (area.getTitulo().contentEquals(nomeArea)) {
              
                return area.reservarPoltrona(poltrona);

            }
        }

        return false; 
    }
    


    
    public boolean verificarDisponibilidade(String frisa_1, int numeroPoltrona) {
        
    for (Area area : areas) {
        if (area.getTitulo().equalsIgnoreCase(nomeArea)) {

            Boolean disponivel = area.getPoltronas().get(numeroPoltrona);
            return disponivel != null && !disponivel; // Retorna true se está disponível
        }
    }
    return false; 

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sessão: ").append(periodo)
          .append(", Peça: ").append(peca)
          .append(", Áreas: ");

        for (Area area : areas) {
            sb.append("\n  ").append(area);
        }
        return sb.toString();
    }

    public Peca getPeca() {

        return null;

    }

    public Area getArea() {
        return this.area;   
    }


}
