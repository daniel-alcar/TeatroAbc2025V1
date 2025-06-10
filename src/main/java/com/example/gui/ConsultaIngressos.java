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

    private JTextField txtCpf;
    private JButton btnBuscar;
    private JPanel painelResultados;

    public ConsultaIngressos() {
        initComponentes();
    }

    private void initComponentes() {
        setTitle("Consulta de Ingressos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abre maximizada
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cabeçalho com o mesmo estilo da tela principal
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setBackground(new Color(255, 102, 0));
        painelCabecalho.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        JLabel titulo = new JLabel("Consulta de Ingressos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        painelCabecalho.add(titulo);
        
        add(painelCabecalho, BorderLayout.NORTH);

        // Painel principal com layout organizado
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);

        // Painel de entrada de dados
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
        txtCpf.setPreferredSize(new Dimension(250, 30));
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

        // Substituir a área de resultados por um painel
        painelResultados = new JPanel();
        painelResultados.setLayout(new GridBagLayout());
        painelResultados.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(painelResultados);
        scroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            " Resultados ",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(70, 70, 70)));
        scroll.setPreferredSize(new Dimension(800, 400));

        painelPrincipal.add(scroll, BorderLayout.CENTER);

        add(painelPrincipal, BorderLayout.CENTER);

        // Configuração do botão buscar
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

            // Limpar o painel de resultados
            painelResultados.removeAll();
            painelResultados.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);

            if (resultados.isEmpty()) {
                JLabel lblNenhum = new JLabel("Nenhum ingresso encontrado para o CPF: " + formatarCPF(cpfBusca));
                lblNenhum.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                painelResultados.add(lblNenhum, gbc);
            } else {
                JLabel lblTitulo = new JLabel("Ingressos encontrados para " + formatarCPF(cpfBusca) + ":");
                lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
                painelResultados.add(lblTitulo, gbc);

                for (int i = 0; i < resultados.size(); i++) {
                    JPanel painelIngresso = new JPanel(new BorderLayout());
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
                    painelIngresso.add(txtDetalhes, BorderLayout.CENTER);

                    JButton btnImprimir = new JButton("Imprimir");
                    btnImprimir.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    btnImprimir.setBackground(new Color(255, 102, 0));
                    btnImprimir.setForeground(Color.WHITE);
                    btnImprimir.setFocusPainted(false);
                    final int index = i;
                    btnImprimir.addActionListener(e -> imprimirIngresso(resultados.get(index)));
                    painelIngresso.add(btnImprimir, BorderLayout.SOUTH);

                    painelResultados.add(painelIngresso, gbc);
                }
            }

            painelResultados.revalidate();
            painelResultados.repaint();

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