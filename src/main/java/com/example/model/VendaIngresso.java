package com.example.model;

public class VendaIngresso {

    private int idIngresso;
    private Usuario usuario;
    private Area area;
    private int poltrona;
    private Peca peca;
    private Sessao sessao;

    public VendaIngresso() {
        // Construtor vazio necessário para frameworks e DAOs
    }

    public VendaIngresso(Usuario usuario, Area area, int poltrona, Peca peca, Sessao sessao) {
        this.usuario = usuario;
        this.area = area;
        this.poltrona = poltrona;
        this.peca = peca;
        this.sessao = sessao;
    }

    // Getters e Setters
    public int getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

}
