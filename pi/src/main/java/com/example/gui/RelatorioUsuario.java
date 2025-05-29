package com.example.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.example.model.LeitorEstatistica;
import com.example.model.LeitorUsuario;

public class RelatorioUsuario extends JFrame {

    private JTable tabelaUsuarios;
    private JTable tabelaEstatisticas;

    public RelatorioUsuario() {
        super("Relatório de Usuários");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        painelPrincipal.add(criarCabecalho("Usuários Cadastrados", "icons/user.png"));
        painelPrincipal.add(new JScrollPane(criarTabelaUsuarios()));

        painelPrincipal.add(criarCabecalho("Estatísticas Teatro", "icons/stats.png"));
        painelPrincipal.add(new JScrollPane(criarTabelaEstatisticas()));

        add(painelPrincipal, BorderLayout.CENTER);
    }

    private JTable criarTabelaUsuarios() {
        tabelaUsuarios = new JTable();
        try {
            DefaultTableModel modelo = LeitorUsuario.lerArquivo("usuarios.txt");
            tabelaUsuarios.setModel(modelo);
        } catch (IOException e) {
            mostrarErro("Erro ao carregar usuários: " + e.getMessage());
        }
        return tabelaUsuarios;
    }

    private JTable criarTabelaEstatisticas() {
        tabelaEstatisticas = new JTable();
        try {
            DefaultTableModel modelo = LeitorEstatistica.lerArquivo("estatisticas.txt");
            tabelaEstatisticas.setModel(modelo);
        } catch (IOException e) {
            mostrarErro("Erro ao carregar estatísticas: " + e.getMessage());
        }
        return tabelaEstatisticas;
    }

    private JPanel criarCabecalho(String titulo, String iconePath) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(255, 102, 0));
        painel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Color.WHITE);

        JLabel icone = new JLabel(new ImageIcon(iconePath));

        painel.add(label, BorderLayout.WEST);
        painel.add(icone, BorderLayout.EAST);

        return painel;
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RelatorioUsuario().setVisible(true));
    }
}
