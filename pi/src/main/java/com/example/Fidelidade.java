package com.example;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Fidelidade extends JFrame {

    private JPanel jPanel1;

    public Fidelidade() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(974, 725);
        setLocationRelativeTo(null); // centraliza a janela na tela

        // Criando painel principal
        jPanel1 = new JPanel();
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null); // layout manual para posicionar componentes manualmente depois, se quiser

        // Define o tamanho do painel para ocupar toda a janela
        jPanel1.setBounds(0, 0, 974, 725);

        // Como o JFrame usa layout default (BorderLayout), adicionamos o painel ao CENTER
        add(jPanel1);

        // Como usamos setBounds no painel, vamos fixar o tamanho do JFrame
        setSize(974, 725);
    }

    public static void main(String[] args) {
        // Você pode trocar o LookAndFeel aqui, se quiser
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Nimbus LookAndFeel não disponível, usando padrão.");
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Fidelidade().setVisible(true);
        });
    }
}
