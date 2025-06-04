package com.example.dao;

import java.util.List;

import com.example.model.Usuario;

public interface IUsuarioDAO {
    void salvar(Usuario usuario) throws Exception;
    List<Usuario> carregarTodos();
    boolean cpfExiste(String cpf);
    List<Usuario> buscarTodos() throws Exception;
}