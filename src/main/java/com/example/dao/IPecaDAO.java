package com.example.dao;

import java.util.List;

import com.example.model.Peca;

public interface IPecaDAO {
    void salvar(Peca peca) throws Exception;
    List<Peca> buscarTodas() throws Exception;
    Peca buscarPorId(int id) throws Exception;
}
