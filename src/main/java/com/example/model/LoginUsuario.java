package com.example.model;

import java.util.List;

public class LoginUsuario {

    private List<Usuario> usuarios;

    public LoginUsuario(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean realizarLogin(String cpf) {
        String cpfSemMascara = cpf.replaceAll("[^\\d]", "");
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpfSemMascara)) {
                return true;
            }
        }
        return false;
    }
}
