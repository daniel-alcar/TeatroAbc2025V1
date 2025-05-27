package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLightLaf;

public class Plateia_B extends JFrame {

    private Estatisticas estatisticas = new Estatisticas();

    private Sessao sessao;

    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField jTxtNumeroPoltronaB;
    private JButton jBComprarIngressoB;

    public Plateia_B(Sessao sessao) {
        this.sessao = sessao;
        initComponents();
    }

    public Plateia_B() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(792, 232);
        setLocationRelativeTo(null);

        // Painel principal branco
        jPanel1 = new JPanel(null); // null layout para posicionamento manual
        jPanel1.setBackground(Color.WHITE);

        // Painel superior escuro
        jPanel2 = new JPanel(null);
        jPanel2.setBackground(new Color(51, 51, 51));
        jPanel2.setBounds(0, 0, 792, 70);
        jPanel1.add(jPanel2);

        // Label "PLATEIA B"
        jLabel1 = new JLabel("PLATEIA B");
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setBounds(10, 5, 300, 40);
        jPanel2.add(jLabel1);

        // Label "Poltronas de 1 - 100"
        jLabel2 = new JLabel("Poltronas de 1 - 100");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jLabel2.setBounds(15, 45, 200, 20);
        jPanel2.add(jLabel2);

        // Label instrução
        jLabel3 = new JLabel("Digite o número da Poltrona desejada:");
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel3.setBounds(274, 80, 300, 25);
        jPanel1.add(jLabel3);

        // Campo texto para número da poltrona
        jTxtNumeroPoltronaB = new JTextField();
        jTxtNumeroPoltronaB.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jTxtNumeroPoltronaB.setBounds(274, 110, 132, 38);
        jPanel1.add(jTxtNumeroPoltronaB);

        // Botão comprar ingresso
        jBComprarIngressoB = new JButton("Comprar Ingresso");
        jBComprarIngressoB.setBackground(new Color(255, 102, 0));
        jBComprarIngressoB.setForeground(Color.WHITE);
        jBComprarIngressoB.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jBComprarIngressoB.setBounds(274, 155, 260, 62);
        jBComprarIngressoB.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBComprarIngressoB.addActionListener(this::jBComprarIngressoBActionPerformed);
        jPanel1.add(jBComprarIngressoB);

        // Adiciona painel principal à janela
        add(jPanel1);
    }

    private void jBComprarIngressoBActionPerformed(ActionEvent evt) {
        VendaIngresso venda = new VendaIngresso();

        try {
            int numeroPoltrona = Integer.parseInt(jTxtNumeroPoltronaB.getText());

            if (sessao != null && sessao.reservarPoltrona("Plateia B", numeroPoltrona)) {
                System.out.println("Poltrona disponível. Ingresso comprado!");
                // Aqui pode abrir tela para imprimir ingresso
            } else {
                System.out.println("Poltrona já reservada ou sessão nula.");
            }

            estatisticas.exibirEstatisticas();
        } catch (NumberFormatException e) {
            System.out.println("Erro: O número da poltrona deve ser um valor inteiro.");
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {
            new Plateia_B().setVisible(true);
        });
    }
}
