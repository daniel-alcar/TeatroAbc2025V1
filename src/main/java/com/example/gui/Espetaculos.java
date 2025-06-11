package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;
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

    private static final int MIN_WIDTH = 1024;
    private static final int MIN_HEIGHT = 768;
    private Image backgroundImage;
    private JButton btnPatrulha, btnAlladin, btnCisne;

    public Espetaculos() {
        super("Espetáculos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Carregar imagem de fundo
        URL bgUrl = getClass().getResource("/Icons/backgroudlogin.jpg");
        if (bgUrl != null) {
            backgroundImage = new ImageIcon(bgUrl).getImage();
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        buttonPanel.setOpaque(false);

        // Configuração dos botões
        btnPatrulha = createStyledButton("Patrulha Canina", "/Icons/ESPETACULO_PATRULHA.png");
        btnAlladin = createStyledButton("Alladin", "/Icons/Alladin.png");
        btnCisne = createStyledButton("Cisne Negro", "/Icons/cisne.png");

        btnPatrulha.addActionListener((ActionEvent e) -> abrirSessao("Manhã", "Patrulha Canina"));
        btnAlladin.addActionListener((ActionEvent e) -> abrirSessao("Tarde", "Aladdin"));
        btnCisne.addActionListener((ActionEvent e) -> abrirSessao("Noite", "Cisne Negro"));

        buttonPanel.add(btnPatrulha);
        buttonPanel.add(btnAlladin);
        buttonPanel.add(btnCisne);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 102, 0));
        button.setBorder(null);
        button.setPreferredSize(new Dimension(300, 400));
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setIconTextGap(20);

        try {
            URL iconUrl = getClass().getResource(iconPath);
            if (iconUrl != null) {
                ImageIcon originalIcon = new ImageIcon(iconUrl);
                Image scaledImage = originalIcon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
        }

        return button;
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
