package com.example.model;

import java.util.List;

public class LoginUsuario {

    private List<Usuario> usuarios;

    public LoginUsuario(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean RealizarLogin(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}