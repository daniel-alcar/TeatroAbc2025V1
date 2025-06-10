package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.example.dao.SessaoDAOBanco;
import com.example.model.Sessao;
import com.example.util.ConexaoBanco;

public class Espetaculos extends JFrame {

    private JButton btnPatrulha, btnAlladin, btnCisne;

    public Espetaculos() {
    super("Espetáculos");
    setExtendedState(JFrame.MAXIMIZED_BOTH); 
    setUndecorated(false); 
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        btnPatrulha = new JButton("Patrulha Canina");
        btnAlladin = new JButton("Alladin");
        btnCisne = new JButton("Cisne Negro");

        try {
            btnPatrulha.setIcon(new ImageIcon(getClass().getResource("/icons/ESPETACULO_PATRULHA.png")));
            btnAlladin.setIcon(new ImageIcon(getClass().getResource("/icons/Alladin.png")));
            btnCisne.setIcon(new ImageIcon(getClass().getResource("/icons/cisne.png")));
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagens: " + e.getMessage());
        }

        btnPatrulha.setBorder(null);
        btnAlladin.setBorder(null);
        btnCisne.setBorder(null);

        btnPatrulha.addActionListener((ActionEvent e) -> abrirSessao("Manhã", "Patrulha Canina"));
        btnAlladin.addActionListener((ActionEvent e) -> abrirSessao("Tarde", "Aladdin"));
        btnCisne.addActionListener((ActionEvent e) -> abrirSessao("Noite", "Cisne Negro"));

        panel.add(btnPatrulha);
        panel.add(btnAlladin);
        panel.add(btnCisne);

        add(panel, BorderLayout.CENTER);
    }

    private void abrirSessao(String horario, String nomePeca) {
        try {
            Connection connection = ConexaoBanco.getConnection();
            SessaoDAOBanco sessaoDAO = new SessaoDAOBanco(connection);
            List<Sessao> sessoes = sessaoDAO.buscarTodas();

            Sessao novaSessao = sessoes.stream()
                .filter(s -> s.getPeriodo().equals(horario) && s.getPeca().getTituloPeca().equals(nomePeca))
                .findFirst()
                .orElse(null);

            if (novaSessao == null) {
                JOptionPane.showMessageDialog(this, "Sessão não encontrada no banco!");
                return;
            }

            CompraIngresso compraUI = new CompraIngresso(List.of(novaSessao), connection);
            compraUI.setVisible(true);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
