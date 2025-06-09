package com.example.dao;

public interface IEstatisticaDAO {
    // peça mais vendida
    String[] pecaMaisV() throws Exception;
    // peça menos vendida
    String[] pecaMenosV() throws Exception;
    // sessao maior ocupacao
    String[] sessaoMaisOcupada() throws Exception;
    // sessao menor ocupacao
    String[] sessaoMenosOcupada() throws Exception;
    // peça mais lucrativa
    String[] pecaMaisLucrativa() throws Exception;
     // peça menos lucrativa
    String[] pecaMenosLucrativa() throws Exception;
    // Faturamento médio do teatro com todas as áreas por peça
    String[] faturamentoMedio() throws Exception;
}
