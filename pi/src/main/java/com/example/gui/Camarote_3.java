package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

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

import com.example.model.Estatisticas;
import com.example.model.Sessao;
import com.example.model.VendaIngresso;
import com.formdev.flatlaf.FlatLightLaf;

public class Camarote_3 extends JFrame {

    private Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;
    private JTextField txtNumeroPoltrona;
    private JButton btnComprar;

    public Camarote_3(Sessao sessao) {
        this.sessao = sessao;
        initUI();
    }

    public Camarote_3() {
        initUI();
    }

    private void initUI() {
        setTitle("Camarote 3");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setSize(800, 240);
        setLocation(828, 665);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Top panel (cabeçalho)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 51, 51));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("CAMAROTE 03");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Poltronas de 1 - 10");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(lblTitulo);
        headerPanel.add(lblSubtitulo);
        headerPanel.add(Box.createVerticalStrut(10));

        // Centro
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel lblNumeroPoltrona = new JLabel("Digite o número da Poltrona desejada:");
        lblNumeroPoltrona.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNumeroPoltrona.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtNumeroPoltrona = new JTextField(10);
        txtNumeroPoltrona.setMaximumSize(new Dimension(200, 30));
        txtNumeroPoltrona.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNumeroPoltrona.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnComprar = new JButton("Comprar Ingresso");
        btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnComprar.setBackground(new Color(255, 102, 0));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFocusPainted(false);
        btnComprar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnComprar.setBorder(BorderFactory.createBevelBorder(1));
        btnComprar.addActionListener(e -> comprarIngresso());

        centerPanel.add(lblNumeroPoltrona);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(txtNumeroPoltrona);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(btnComprar);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void comprarIngresso() {
        VendaIngresso venda = new VendaIngresso();
        try {
            int numeroPoltrona = Integer.parseInt(txtNumeroPoltrona.getText());

            if (sessao.reservarPoltrona("Camarote 3", numeroPoltrona)) {
                JOptionPane.showMessageDialog(this, "Poltrona disponível. Ingresso comprado!");
                // TODO: Abrir tela de imprimir ingresso
            } else {
                JOptionPane.showMessageDialog(this, "Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: O número da poltrona deve ser um valor inteiro.");
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new Camarote_3().setVisible(true));
    }
}
