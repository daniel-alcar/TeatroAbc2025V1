package com.example.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.example.model.Estatisticas;
import com.example.model.Sessao;
import com.example.model.VendaIngresso;
import com.formdev.flatlaf.FlatLightLaf;

public class Balcao_Nobre extends JFrame {

    private Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;

    private JTextField jTxtNumeroPoltronaBalcaoNobre;
    private JButton jBComprarIngressoBalcaoNobre;

    public Balcao_Nobre(Sessao sessao) {
        this.sessao = sessao;
        initComponents();
    }

    public Balcao_Nobre() {
        initComponents();
    }

    private void initComponents() {
        // Configura o Frame
        setTitle("Balcão Nobre");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza

        // Painel principal
        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(Color.WHITE);
        jPanel1.setLayout(new BorderLayout());

        // Painel superior (cabeçalho)
        JPanel jPanel2 = new JPanel();
        jPanel2.setBackground(new Color(51, 51, 51));
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));

        JLabel jLabel1 = new JLabel("BALCÃO NOBRE");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabel2 = new JLabel("Poltronas de 1 - 50");
        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        jPanel2.add(Box.createVerticalStrut(20));
        jPanel2.add(jLabel1);
        jPanel2.add(Box.createVerticalStrut(10));
        jPanel2.add(jLabel2);
        jPanel2.add(Box.createVerticalStrut(20));

        // Painel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        JLabel jLabel3 = new JLabel("Digite o número da Poltrona desejada:");
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        jTxtNumeroPoltronaBalcaoNobre = new JTextField(10);
        jTxtNumeroPoltronaBalcaoNobre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jTxtNumeroPoltronaBalcaoNobre.setMaximumSize(new Dimension(200, 30));
        jTxtNumeroPoltronaBalcaoNobre.setAlignmentX(Component.CENTER_ALIGNMENT);

        jBComprarIngressoBalcaoNobre = new JButton("Comprar Ingresso");
        jBComprarIngressoBalcaoNobre.setBackground(new Color(255, 102, 0));
        jBComprarIngressoBalcaoNobre.setForeground(Color.WHITE);
        jBComprarIngressoBalcaoNobre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jBComprarIngressoBalcaoNobre.setAlignmentX(Component.CENTER_ALIGNMENT);

        jBComprarIngressoBalcaoNobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                comprarIngressoAction(evt);
            }
        });

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(jLabel3);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(jTxtNumeroPoltronaBalcaoNobre);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(jBComprarIngressoBalcaoNobre);
        centerPanel.add(Box.createVerticalStrut(20));

        // Adiciona os painéis ao frame
        jPanel1.add(jPanel2, BorderLayout.NORTH);
        jPanel1.add(centerPanel, BorderLayout.CENTER);

        add(jPanel1);
    }

    private void comprarIngressoAction(ActionEvent evt) {
        VendaIngresso venda = new VendaIngresso();

        try {
            int numeroPoltrona = Integer.parseInt(jTxtNumeroPoltronaBalcaoNobre.getText());

            if (sessao != null && sessao.reservarPoltrona("Balcão Nobre", numeroPoltrona)) {
                JOptionPane.showMessageDialog(this, "Poltrona disponível. Ingresso comprado!");
                // Aqui pode abrir a tela de imprimir ingresso, se quiser.
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

        SwingUtilities.invokeLater(() -> {
            new Balcao_Nobre().setVisible(true);
        });
    }
}
