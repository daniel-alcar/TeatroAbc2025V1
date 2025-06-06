package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.example.model.Area;
import com.example.model.Peca;
import com.example.model.Sessao;

public class Espetaculos extends JFrame {

    private List<Area> areas;

    private JButton btnPatrulha, btnAlladin, btnCisne;

    public Espetaculos() {
    super("Espetáculos");
    setExtendedState(JFrame.MAXIMIZED_BOTH); 
    setUndecorated(false); 
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    initAreas();
    initUI();
    }

    private void initAreas() {
        areas = new ArrayList<>();
        areas.add(new Area("Platea A", 25, 40.0));
        areas.add(new Area("Platea B", 100, 60.0));
        areas.add(new Area("Camarote 01", 10, 80.0));
        areas.add(new Area("Camarote 02", 10, 80.0));
        areas.add(new Area("Camarote 03", 10, 80.0));
        areas.add(new Area("Camarote 04", 10, 80.0));
        areas.add(new Area("Camarote 05", 10, 80.0));
        areas.add(new Area("Frisa 1", 5, 120.0));
        areas.add(new Area("Frisa 2", 5, 120.0));
        areas.add(new Area("Frisa 3", 5, 120.0));
        areas.add(new Area("Frisa 4", 5, 120.0));
        areas.add(new Area("Frisa 5", 5, 120.0));
        areas.add(new Area("Frisa 6", 5, 120.0));
        areas.add(new Area("Balcão", 50, 250.0));
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        btnPatrulha = new JButton("Patrulha Canina");
        btnAlladin = new JButton("Alladin");
        btnCisne = new JButton("Cisne Negro");

        // Carregar ícones - ajuste o caminho conforme sua estrutura de projeto
        try {
            btnPatrulha.setIcon(new ImageIcon(getClass().getResource("/icons/ESPETACULO_PATRULHA.png")));
            btnAlladin.setIcon(new ImageIcon(getClass().getResource("/icons/Alladin.png")));
            btnCisne.setIcon(new ImageIcon(getClass().getResource("/icons/cisne.png")));
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagens: " + e.getMessage());
        }

        // Remover borda padrão para um visual mais limpo
        btnPatrulha.setBorder(null);
        btnAlladin.setBorder(null);
        btnCisne.setBorder(null);

        btnPatrulha.addActionListener((ActionEvent e) -> abrirSessao("Manhã", "Patrulha Canina"));
        btnAlladin.addActionListener((ActionEvent e) -> abrirSessao("Tarde", "Alladin"));
        btnCisne.addActionListener((ActionEvent e) -> abrirSessao("Noite", "Cisne Negro"));

        panel.add(btnPatrulha);
        panel.add(btnAlladin);
        panel.add(btnCisne);

        add(panel, BorderLayout.CENTER);
    }

    private void abrirSessao(String horario, String nomePeca) {
        Peca peca = new Peca(nomePeca); // Corrigido aqui
        Sessao novaSessao = new Sessao(horario, peca, areas); // Agora funciona
        CompraIngresso compraUI = new CompraIngresso(List.of(novaSessao));
        compraUI.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Espetaculos().setVisible(true));
    }
}
