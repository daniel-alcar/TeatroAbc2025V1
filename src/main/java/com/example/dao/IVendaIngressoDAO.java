package com.example.dao;

import java.util.List;

import com.example.model.VendaIngresso;

public interface IVendaIngressoDAO {
    void salvar(VendaIngresso venda) throws Exception;
    List<VendaIngresso> buscarTodas() throws Exception;
    List<VendaIngresso> buscarPorCPF(String cpf) throws Exception;
}