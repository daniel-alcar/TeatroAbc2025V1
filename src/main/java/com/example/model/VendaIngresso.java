package com.example.model;

import java.util.List;

public class VendaIngresso {

    private int idIngresso;  // para pesquisar o ingresso
    private Usuario usuario;    // pegar dados do usuario cliente
    private Area area;          // para determinar qual setor do teatro deseja 
    private int poltrona;       // para selecionar o numero da poltrona, serve para verificar a disponibilidade
    private Peca peca;          // peca que deseja assistir 
    private Sessao sessao;      // turno que deseja assistir a peça

    private static int idContador = 0;

    public VendaIngresso(Usuario usuario, Area area, int poltrona, Peca peca, Sessao sessao) {

        if (!area.verificarDisponibilidade(poltrona)) {

            throw new IllegalArgumentException("A poltrona selecionada já está ocupada.");

        }

        this.idIngresso = idContador++;
        this.usuario = usuario;
        this.area = area;
        this.poltrona = poltrona;
        this.peca = peca;
        this.sessao = sessao;

        area.reservarPoltrona(poltrona);
    }

    // Metodo para gerar o comprovante que será visivel para o usuario  
    public VendaIngresso(Usuario usuario, Area area, int poltrona, Peca peca, Sessao sessao, List<VendaIngresso> ingressosVendidos) {

        if (!area.verificarDisponibilidade(poltrona)) {

            throw new IllegalArgumentException("A poltrona selecionada já está ocupada.");

        }

        this.idIngresso = idContador++;
        this.usuario = usuario;
        this.area = area;
        this.poltrona = poltrona;
        this.peca = peca;
        this.sessao = sessao;

        area.reservarPoltrona(poltrona);
        ingressosVendidos.add(this);

        Estatisticas estatisticas = new Estatisticas(ingressosVendidos);
        estatisticas.exibirEstatisticas();

    }

    public VendaIngresso(Peca peca, Sessao sessao, Area plateiaA) {

    this.sessao = sessao;

    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ID: " + idIngresso;
    }

    public void getPoltrona(String text) {
    }

}
