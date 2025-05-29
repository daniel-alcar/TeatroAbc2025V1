package com.example.gui;

import com.example.model.Estatisticas;
import com.example.model.Sessao;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frisa_1 extends JFrame {

    private Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;
    private JTextField txtNumeroPoltrona;
    private JButton btnComprarIngresso;

    public Frisa_1(Sessao sessao) {
        this.sessao = sessao;
        initUI();
    }

    public Frisa_1() {
        initUI();
    }

    private void initUI() {
        // Janela principal
        setTitle("Frisa 01");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(792, 232);
        setLocation(828, 665);
        setUndecorated(true);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        // Painel de título
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 51, 51));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel lblTitulo = new JLabel("FRISA 01");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Poltronas de 1 - 5");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(lblTitulo);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(lblSubtitulo);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Painel de entrada e botão
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblInstrucao = new JLabel("Digite o número da Poltrona desejada:");
        lblInstrucao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblInstrucao.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtNumeroPoltrona = new JTextField();
        txtNumeroPoltrona.setMaximumSize(new Dimension(200, 30));
        txtNumeroPoltrona.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNumeroPoltrona.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnComprarIngresso = new JButton("Comprar Ingresso");
        btnComprarIngresso.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnComprarIngresso.setBackground(new Color(255, 102, 0));
        btnComprarIngresso.setForeground(Color.WHITE);
        btnComprarIngresso.setFocusPainted(false);
        btnComprarIngresso.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnComprarIngresso.setBorder(BorderFactory.createBevelBorder(1));

        btnComprarIngresso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarIngresso();
            }
        });

        centerPanel.add(lblInstrucao);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(txtNumeroPoltrona);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(btnComprarIngresso);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void comprarIngresso() {
        if (sessao == null) {
            JOptionPane.showMessageDialog(this, "Sessão não foi inicializada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int numeroPoltrona = Integer.parseInt(txtNumeroPoltrona.getText().trim());

            if (sessao.reservarPoltrona("Frisa 1", numeroPoltrona)) {
                JOptionPane.showMessageDialog(this, "Poltrona disponível. Ingresso comprado!");
                new Ingresso_Alladin().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Poltrona já reservada. Escolha outra.", "Reserva Indisponível", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> {
            new Frisa_1().setVisible(true);
        });
    }
}