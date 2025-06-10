package com.example.dao;

import java.util.List;

public interface IConsultaDAO {
    List<String> buscarIngressoCPF(String cpf) throws Exception;
}
