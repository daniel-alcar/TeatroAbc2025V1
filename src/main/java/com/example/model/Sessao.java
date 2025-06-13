package com.example.model;

import java.util.List;
import java.util.Map;

public class Sessao {

    private int id;
    private String periodo;
    private Peca peca;
    private List<Area> areas;

    public Sessao(int id, String periodo, Peca peca, List<Area> areas) {
        this.id = id;
        this.periodo = periodo;
        this.peca = peca;
        this.areas = areas;
    }

    public Sessao(String periodo, Peca peca, List<Area> areas) {
        this(0, periodo, peca, areas); // ID = 0 por padrão
    }

    public int getId() {
        return id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Peca getPeca() {
        return peca;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public int coletaQtdPoltronasReservadas() {
        int totalReservadas = 0;
        for (Area area : areas) {
            for (Map.Entry<Integer, Boolean> entrada : area.getPoltronas().entrySet()) {
                if (entrada.getValue()) {
                    totalReservadas++;
                }
            }
        }
        return totalReservadas;
    }

    public boolean reservarPoltrona(String nomeArea, int numeroPoltrona) {
        for (Area area : areas) {
            if (area.getTitulo().equalsIgnoreCase(nomeArea)) {
                return area.reservarPoltrona(numeroPoltrona);
            }
        }
        return false;
    }

    public boolean verificarDisponibilidade(String nomeArea, int numeroPoltrona) {
        for (Area area : areas) {
            if (area.getTitulo().equalsIgnoreCase(nomeArea)) {
                Boolean disponivel = area.getPoltronas().get(numeroPoltrona);
                return disponivel != null && !disponivel;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sessão: ").append(periodo)
          .append(", Peça: ").append(peca.getTituloPeca())
          .append(", Áreas:");
        for (Area area : areas) {
            sb.append("\n  ").append(area);
        }
        return sb.toString();
    }

    public void setId(int id) {
        this.id = id;
    }
}
