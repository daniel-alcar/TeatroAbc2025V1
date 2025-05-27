package com.example;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

public class Plateia_A extends JFrame {

    private Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;
    private JTextField campoPoltrona;
    private JButton botaoComprar;

    public Plateia_A(Sessao sessao) {
        this.sessao = sessao;
        configurarJanela();
    }

    public Plateia_A() {
        configurarJanela();
    }

    private void configurarJanela() {
        setTitle("Plateia A");
        setSize(600, 300);
        setLocation(400, 300);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());

        painelPrincipal.add(criarCabecalho(), BorderLayout.NORTH);
        painelPrincipal.add(criarFormulario(), BorderLayout.CENTER);

        setContentPane(painelPrincipal);
    }

    private JPanel criarCabecalho() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(51, 51, 51));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("PLATEIA A");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Poltronas de 1 - 25");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createVerticalStrut(5));
        painel.add(subtitulo);

        return painel;
    }

    private JPanel criarFormulario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        painel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Digite o número da Poltrona desejada:");
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoPoltrona = new JTextField();
        campoPoltrona.setMaximumSize(new Dimension(200, 30));
        campoPoltrona.setFont(new Font("Segoe UI", Font.BOLD, 14));
        campoPoltrona.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoComprar = new JButton("Comprar Ingresso");
        botaoComprar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        botaoComprar.setBackground(new Color(255, 102, 0));
        botaoComprar.setForeground(Color.WHITE);
        botaoComprar.setFocusPainted(false);
        botaoComprar.setBorder(BorderFactory.createBevelBorder(1));
        botaoComprar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoComprar.addActionListener(this::comprarIngresso);

        painel.add(label);
        painel.add(Box.createVerticalStrut(10));
        painel.add(campoPoltrona);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botaoComprar);

        return painel;
    }

    private void comprarIngresso(ActionEvent evt) {
        new Ingresso_Alladin().setVisible(true);  // Exibe a próxima tela

        VendaIngresso venda = new VendaIngresso();

        try {
            int numeroPoltrona = Integer.parseInt(campoPoltrona.getText());

            if (sessao != null && sessao.reservarPoltrona("Plateia A", numeroPoltrona)) {
                System.out.println("Poltrona disponível. Ingresso comprado!");
                // Lógica de exibição do ingresso aqui
            } else {
                System.out.println("Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número válido de poltrona.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getEstatisticasDataFromFile() {
        StringBuilder texto = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("estatisticas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                texto.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto.toString();
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new Plateia_A().setVisible(true));
    }
}
