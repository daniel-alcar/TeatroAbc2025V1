package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

public class Camarote_2 extends JFrame {

    private Sessao sessao;
    private Estatisticas estatisticas;

    private JPanel painelPrincipal;
    private JPanel painelTopo;
    private JLabel tituloLabel;
    private JLabel subtituloLabel;
    private JLabel numeroPoltronaLabel;
    private JTextField campoNumeroPoltrona;
    private JButton botaoComprar;

    public Camarote_2(Sessao sessao) {
        this.sessao = sessao;
        this.estatisticas = new Estatisticas();
        inicializarComponentes();
        configurarJanela();
    }

    // Construtor sem sessão (caso necessário)
    public Camarote_2() {
        this(null);
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);

        // Painel superior
        painelTopo = new JPanel(new GridLayout(2, 1));
        painelTopo.setBackground(new Color(51, 51, 51));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        tituloLabel = new JLabel("CAMAROTE 02", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        tituloLabel.setForeground(Color.WHITE);

        subtituloLabel = new JLabel("Poltronas de 1 - 10", SwingConstants.CENTER);
        subtituloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtituloLabel.setForeground(Color.WHITE);

        painelTopo.add(tituloLabel);
        painelTopo.add(subtituloLabel);

        // Painel do conteúdo
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        painelConteudo.setBackground(Color.WHITE);

        numeroPoltronaLabel = new JLabel("Digite o número da Poltrona desejada:");
        numeroPoltronaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        numeroPoltronaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoNumeroPoltrona = new JTextField();
        campoNumeroPoltrona.setMaximumSize(new Dimension(200, 35));
        campoNumeroPoltrona.setFont(new Font("Segoe UI", Font.BOLD, 14));
        campoNumeroPoltrona.setHorizontalAlignment(JTextField.CENTER);
        campoNumeroPoltrona.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoComprar = new JButton("Comprar Ingresso");
        botaoComprar.setBackground(new Color(255, 102, 0));
        botaoComprar.setForeground(Color.WHITE);
        botaoComprar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botaoComprar.setFocusPainted(false);
        botaoComprar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoComprar.setBorder(BorderFactory.createBevelBorder(1));
        botaoComprar.addActionListener(this::comprarIngresso);

        painelConteudo.add(numeroPoltronaLabel);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
        painelConteudo.add(campoNumeroPoltrona);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));
        painelConteudo.add(botaoComprar);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(painelConteudo, BorderLayout.CENTER);

        add(painelPrincipal);
    }

    private void comprarIngresso(ActionEvent evt) {
        try {
            int numero = Integer.parseInt(campoNumeroPoltrona.getText());

            if (sessao != null && sessao.reservarPoltrona("Camarote 2", numero)) {
                JOptionPane.showMessageDialog(this, "Poltrona disponível. Ingresso comprado!");
                // TODO: Abrir tela de impressão de ingresso, se necessário
            } else {
                JOptionPane.showMessageDialog(this, "Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: O número da poltrona deve ser um valor inteiro.");
        }
    }

    private void configurarJanela() {
        setTitle("Camarote 02");
        setSize(600, 300);
        setLocationRelativeTo(null); // Centraliza
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new Camarote_2().setVisible(true));
    }
}
