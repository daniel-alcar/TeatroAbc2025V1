package com.example.dao;

import java.util.List;

import com.example.model.Sessao;

public interface ISessaoDAO {
    void salvar(Sessao sessao, int pecaId) throws Exception;
    List<Sessao> buscarTodas() throws Exception;
    Sessao buscarPorId(int id) throws Exception;
}