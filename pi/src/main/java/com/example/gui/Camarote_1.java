package com.example.gui;

import com.example.model.Estatisticas;
import com.example.model.Sessao;
import com.example.model.VendaIngresso;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Camarote_1 extends JFrame {

    private Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;

    private JTextField txtNumeroPoltrona;
    private JButton btnComprar;

    public Camarote_1(Sessao sessao) {
        this.sessao = sessao;
        initComponents();
    }

    public Camarote_1() {
        initComponents();
    }

    private void initComponents() {
        // Configurações da janela
        setTitle("Camarote 1");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 250);
        setLocationRelativeTo(null);
        setUndecorated(true); // igual ao NetBeans

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        getContentPane().add(painelPrincipal);

        // Cabeçalho
        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.setBackground(new Color(51, 51, 51));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("CAMAROTE 01");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Poltronas de 1 - 10");
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelTopo.add(lblTitulo);
        painelTopo.add(Box.createVerticalStrut(5));
        painelTopo.add(lblSubtitulo);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);

        // Conteúdo central
        JPanel painelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelCentro.setBackground(Color.WHITE);

        JLabel lblPoltrona = new JLabel("Digite o número da Poltrona desejada:");
        lblPoltrona.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelCentro.add(lblPoltrona);

        txtNumeroPoltrona = new JTextField(10);
        txtNumeroPoltrona.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        painelCentro.add(txtNumeroPoltrona);

        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        // Rodapé com botão
        btnComprar = new JButton("Comprar Ingresso");
        btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setBackground(new Color(255, 102, 0));
        btnComprar.setFocusPainted(false);
        btnComprar.setPreferredSize(new Dimension(240, 50));

        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                comprarIngresso();
            }
        });

        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(Color.WHITE);
        painelBotao.add(btnComprar);

        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);
    }

    private void comprarIngresso() {
        VendaIngresso venda = new VendaIngresso();

        try {
            int numeroPoltrona = Integer.parseInt(txtNumeroPoltrona.getText());

            if (sessao != null && sessao.reservarPoltrona("Camarote 1", numeroPoltrona)) {
                System.out.println("Poltrona disponível. Ingresso comprado!");
                // Aqui pode abrir tela de imprimir ingresso
            } else {
                System.out.println("Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();

        } catch (NumberFormatException e) {
            System.out.println("Erro: O número da poltrona deve ser um valor inteiro.");
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new Camarote_1().setVisible(true));
    }
}
