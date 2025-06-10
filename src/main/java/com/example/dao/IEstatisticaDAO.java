package com.example.dao;

import java.util.List;

public interface IEstatisticaDAO {
    String[] pecaMaisV() throws Exception;
    String[] pecaMenosV() throws Exception;
    String[] sessaoMaisOcupada() throws Exception;
    String[] sessaoMenosOcupada() throws Exception;
    String[] pecaMaisLucrativa() throws Exception;
    String[] pecaMenosLucrativa() throws Exception;
    List<String[]> faturamentoMedio() throws Exception;
}
