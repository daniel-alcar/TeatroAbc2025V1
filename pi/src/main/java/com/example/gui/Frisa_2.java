package com.example.gui;

import com.example.model.Estatisticas;
import com.example.model.Sessao;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frisa_2 extends JFrame {

    private Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;

    private JTextField txtNumeroPoltrona;
    private JButton btnComprarIngresso;

    public Frisa_2(Sessao sessao) {
        this.sessao = sessao;
        initialize();
    }

    public Frisa_2() {
        initialize();
    }

    private void initialize() {
        // Configurações básicas do JFrame
        setTitle("Frisa 02");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(792, 232);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout());

        // Painel superior (header)
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(51, 51, 51));
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblTitulo = new JLabel("FRISA 02");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubTitulo = new JLabel("Poltronas de 1 - 5");
        lblSubTitulo.setForeground(Color.WHITE);
        lblSubTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSubTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(lblTitulo);
        panelHeader.add(Box.createVerticalStrut(5));
        panelHeader.add(lblSubTitulo);

        add(panelHeader, BorderLayout.NORTH);

        // Painel central para entrada de dados
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(Color.WHITE);
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel lblInstrucao = new JLabel("Digite o número da Poltrona desejada:");
        lblInstrucao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblInstrucao.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtNumeroPoltrona = new JTextField();
        txtNumeroPoltrona.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtNumeroPoltrona.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtNumeroPoltrona.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnComprarIngresso = new JButton("Comprar Ingresso");
        btnComprarIngresso.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnComprarIngresso.setBackground(new Color(255, 102, 0));
        btnComprarIngresso.setForeground(Color.WHITE);
        btnComprarIngresso.setFocusPainted(false);
        btnComprarIngresso.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnComprarIngresso.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnComprarIngresso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarIngressoAction();
            }
        });

        panelCenter.add(lblInstrucao);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(txtNumeroPoltrona);
        panelCenter.add(Box.createVerticalStrut(15));
        panelCenter.add(btnComprarIngresso);

        add(panelCenter, BorderLayout.CENTER);
    }

    private void comprarIngressoAction() {
        if (sessao == null) {
            JOptionPane.showMessageDialog(this, "Sessão não foi inicializada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int numeroPoltrona = Integer.parseInt(txtNumeroPoltrona.getText().trim());

            if (sessao.reservarPoltrona("Frisa 2", numeroPoltrona)) {
                System.out.println("Poltrona disponível. Ingresso comprado!");

                // Se precisar abrir tela para imprimir ingresso, faça aqui
                // Exemplo:
                // new TelaImprimirIngresso().setVisible(true);

            } else {
                System.out.println("Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {
            new Frisa_2().setVisible(true);
        });
    }
}
