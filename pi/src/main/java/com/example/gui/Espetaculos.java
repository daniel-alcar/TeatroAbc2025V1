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
import com.example.model.Sessao;

public class Espetaculos extends JFrame {

    private List<Area> areas;

    private JButton btnPatrulha, btnAlladin, btnCisne;

    public Espetaculos() {
        super("Espetáculos");
        setSize(1058, 747);
        setLocation(567, 154);
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

        // Ícones (opcional)
        try {
            btnPatrulha.setIcon(new ImageIcon(getClass().getResource("/Icons/ESPETACULO_PATRULHA.png")));
            btnAlladin.setIcon(new ImageIcon(getClass().getResource("/Icons/Alladin.png")));
            btnCisne.setIcon(new ImageIcon(getClass().getResource("/Icons/cisne.png")));
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagens: " + e.getMessage());
        }

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
        Sessao novaSessao = new Sessao(horario, nomePeca, areas);
        CompraIngresso compraUI = new CompraIngresso(List.of(novaSessao));
        compraUI.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Espetaculos().setVisible(true));
    }
}
