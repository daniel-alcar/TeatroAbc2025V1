package com.example.dao;

import java.util.List;

import com.example.model.Usuario;

public interface IUsuarioDAO {
    void salvar(Usuario usuario) throws Exception;
    Usuario buscarPorCPF(String cpf) throws Exception;
    List<Usuario> carregarTodos() throws Exception;
    boolean cpfExiste(String cpf) throws Exception;
    List<Usuario> buscarTodos() throws Exception;
}
