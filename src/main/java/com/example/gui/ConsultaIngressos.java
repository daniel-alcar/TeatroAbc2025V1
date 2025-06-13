package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.example.dao.ConsultaIngressoBanco;
import com.example.util.ConexaoBanco;

public class ConsultaIngressos extends JFrame {

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;
    private JTextField txtCpf;
    private JButton btnBuscar;
    private JPanel painelResultados;
    private JScrollPane scroll;

    public ConsultaIngressos() {
        initComponentes();
    }

    private void initComponentes() {
        setTitle("Consulta de Ingressos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setBackground(new Color(255, 102, 0));
        painelCabecalho.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        JLabel titulo = new JLabel("Consulta de Ingressos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        painelCabecalho.add(titulo);
        
        add(painelCabecalho, BorderLayout.NORTH);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);

        JPanel painelBusca = new JPanel(new GridBagLayout());
        painelBusca.setBackground(Color.WHITE);
        painelBusca.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            " Buscar por CPF ",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(70, 70, 70)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelBusca.add(lblCpf, gbc);

        txtCpf = new JTextField(20);
        txtCpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtCpf.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelBusca.add(txtCpf, gbc);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBuscar.setBackground(new Color(255, 102, 0));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setPreferredSize(new Dimension(120, 35));
        gbc.gridx = 2;
        gbc.gridy = 0;
        painelBusca.add(btnBuscar, gbc);

        painelPrincipal.add(painelBusca, BorderLayout.NORTH);

        painelResultados = new JPanel();
        painelResultados.setLayout(new GridBagLayout());
        painelResultados.setBackground(Color.WHITE);

        scroll = new JScrollPane(painelResultados);
        scroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            " Resultados ",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(70, 70, 70)));
        
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getHorizontalScrollBar().setUnitIncrement(16);
        scroll.setWheelScrollingEnabled(true);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setAutoscrolls(true);

        painelPrincipal.add(scroll, BorderLayout.CENTER);

        add(painelPrincipal, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscarReservas());
    }

    private void buscarReservas() {
        String cpfBusca = txtCpf.getText().trim();

        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, digite um CPF para consulta.", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = ConexaoBanco.getConnection()) {
            ConsultaIngressoBanco dao = new ConsultaIngressoBanco(conn);
            List<String> resultados = dao.buscarIngressoCPF(cpfBusca);

            painelResultados.removeAll();
            painelResultados.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.weightx = 1.0;

            if (resultados.isEmpty()) {
                JLabel lblNenhum = new JLabel("Nenhum ingresso encontrado para o CPF: " + formatarCPF(cpfBusca));
                lblNenhum.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                painelResultados.add(lblNenhum, gbc);
            } else {
                JLabel lblTitulo = new JLabel("Ingressos encontrados para " + formatarCPF(cpfBusca) + ":");
                lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
                painelResultados.add(lblTitulo, gbc);

                for (int i = 0; i < resultados.size(); i++) {
                    JPanel painelIngresso = new JPanel(new BorderLayout(10, 5));
                    painelIngresso.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
                    painelIngresso.setBackground(Color.WHITE);

                    JLabel lblIngresso = new JLabel("Ingresso #" + (i + 1));
                    lblIngresso.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    lblIngresso.setBorder(new EmptyBorder(10, 10, 10, 10));
                    painelIngresso.add(lblIngresso, BorderLayout.NORTH);

                    JTextArea txtDetalhes = new JTextArea(resultados.get(i));
                    txtDetalhes.setEditable(false);
                    txtDetalhes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    txtDetalhes.setLineWrap(true);
                    txtDetalhes.setWrapStyleWord(true);
                    txtDetalhes.setBackground(Color.WHITE);
                    txtDetalhes.setRows(3); // Limita o número de linhas visíveis
                    painelIngresso.add(txtDetalhes, BorderLayout.CENTER);

                    JButton btnImprimir = new JButton("Imprimir");
                    btnImprimir.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    btnImprimir.setBackground(new Color(255, 102, 0));
                    btnImprimir.setForeground(Color.WHITE);
                    btnImprimir.setFocusPainted(false);
                    btnImprimir.setPreferredSize(new Dimension(120, 35));
                    final int index = i;
                    btnImprimir.addActionListener(e -> imprimirIngresso(resultados.get(index)));
                    painelIngresso.add(btnImprimir, BorderLayout.SOUTH);

                    painelResultados.add(painelIngresso, gbc);
                }
            }

            painelResultados.revalidate();
            painelResultados.repaint();
            scroll.getViewport().setViewPosition(new java.awt.Point(0, 0)); // Volta ao topo

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erro ao consultar banco de dados:\n" + ex.getMessage(),
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void imprimirIngresso(String dadosIngresso) {
        SwingUtilities.invokeLater(() -> {
            ImpressaoIngresso impressao = new ImpressaoIngresso(dadosIngresso);
            impressao.setVisible(true);
        });
    }
    
    private String formatarCPF(String cpf) {
        // Formata o CPF para exibição: 000.000.000-00
        if (cpf.length() == 11) {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + 
                   cpf.substring(6, 9) + "-" + cpf.substring(9);
        }
        return cpf;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultaIngressos consulta = new ConsultaIngressos();
            consulta.setVisible(true);
        });
    }
}