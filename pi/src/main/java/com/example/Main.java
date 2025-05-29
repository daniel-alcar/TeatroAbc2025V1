package com.example;

import javax.swing.SwingUtilities;

import com.example.gui.TelaPrincipal;
import com.formdev.flatlaf.FlatLightLaf; 

public class Main {
    public static void main(String[] args) {

        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {

            TelaPrincipal tela = new TelaPrincipal(); 
            tela.setVisible(true);
        });
    }
}