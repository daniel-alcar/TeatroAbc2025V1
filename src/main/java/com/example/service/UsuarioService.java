package com.example.service;

import java.sql.Connection;
import java.util.List;

import com.example.dao.IUsuarioDAO;
import com.example.dao.UsuarioDAOBanco;
import com.example.model.LoginUsuario;
import com.example.model.Usuario;
import com.example.util.ConexaoBanco;

public class UsuarioService {

    private IUsuarioDAO usuarioDAO;

    public UsuarioService() throws Exception {
        Connection conn = ConexaoBanco.getConnection();
        this.usuarioDAO = new UsuarioDAOBanco(conn);
    }

    public UsuarioService(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void cadastrarUsuario(Usuario novoUsuario) throws Exception {
        String cpf = novoUsuario.getCpf();

        if (!validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }

        if (usuarioDAO.cpfExiste(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado: " + cpf);
        }

        try {
            usuarioDAO.salvar(novoUsuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage(), e);
        }
    }

    public boolean validarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) return false;

        cpf = cpf.replaceAll("[^\\d]", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            int dig1 = 11 - (soma % 11);
            if (dig1 >= 10) dig1 = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            int dig2 = 11 - (soma % 11);
            if (dig2 >= 10) dig2 = 0;

            return dig1 == (cpf.charAt(9) - '0') && dig2 == (cpf.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }

    public boolean realizarLogin(String cpf) throws Exception {
        List<Usuario> usuarios = usuarioDAO.buscarTodos();
        LoginUsuario loginUsuario = new LoginUsuario(usuarios);
        return loginUsuario.realizarLogin(cpf);
    }

    public Usuario buscarPorCpf(String cpf) throws Exception {
        return usuarioDAO.buscarPorCPF(cpf);
    }

}
