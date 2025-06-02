package com.example;

import javax.swing.SwingUtilities;

import com.example.gui.TelaPrincipal;
import com.example.model.Usuario;
import com.formdev.flatlaf.FlatLightLaf; 

public class Main {
    public static void main(String[] args) {

        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {
            Usuario.usuarios = Usuario.carregarUsuarios();
            TelaPrincipal tela = new TelaPrincipal(); 
            tela.setVisible(true);
        });
    }
}