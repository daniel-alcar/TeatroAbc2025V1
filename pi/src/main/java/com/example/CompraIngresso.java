package com.example;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CompraIngresso extends JFrame {

    private Sessao sessao;
    private Area area;

    private JPanel contentPanel;
    private JButton jButtonA, jButtonB, jButtonCamarote, jButtonFrisa, jButton1;
    private JComboBox<String> jComboBox1, jComboBox2;

    public CompraIngresso(Sessao sessao) {
        this.sessao = sessao;
        initUI();
    }

    private void initUI() {
        setTitle("Compra de Ingresso");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1052, 745);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);
        add(contentPanel);

        JPanel sidePanel = createSidePanel();
        JPanel mainPanel = createMainPanel();

        contentPanel.add(sidePanel, BorderLayout.WEST);
        contentPanel.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(260, 0));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(new Color(51, 51, 51));

        JLabel setorLabel = createLabel("Setor", 36, Color.ORANGE);
        sidePanel.add(setorLabel);

        jButtonA = createButton("Plateia A", "R$ 40,00");
        jButtonA.addActionListener(e -> new Plateia_A().setVisible(true));
        sidePanel.add(jButtonA);

        jButtonB = createButton("Plateia B", "R$ 60,00");
        jButtonB.addActionListener(e -> new Plateia_B().setVisible(true));
        sidePanel.add(jButtonB);

        jButtonCamarote = createButton("Camarote", "R$ 80,00");
        jButtonCamarote.addActionListener(e -> selecaoCamarote());
        sidePanel.add(jButtonCamarote);

        jComboBox2 = new JComboBox<>(new String[]{"Camarote 01", "Camarote 02", "Camarote 03", "Camarote 04", "Camarote 05"});
        sidePanel.add(jComboBox2);

        jButtonFrisa = createButton("Frisa", "R$ 120,00");
        jButtonFrisa.addActionListener(e -> selecaoFrisa());
        sidePanel.add(jButtonFrisa);

        jComboBox1 = new JComboBox<>(new String[]{"Frisa 01", "Frisa 02", "Frisa 03", "Frisa 04", "Frisa 05", "Frisa 06"});
        sidePanel.add(jComboBox1);

        jButton1 = createButton("Balcão Nobre", "R$ 250,00");
        jButton1.addActionListener(e -> new Balcao_Nobre().setVisible(true));
        sidePanel.add(jButton1);

        return sidePanel;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel imagemLabel = new JLabel();
        imagemLabel.setHorizontalAlignment(JLabel.CENTER);
        imagemLabel.setIcon(new ImageIcon(getClass().getResource("/Icons/PLATEIA.png"))); // ajuste o caminho conforme necessário
        panel.add(imagemLabel, BorderLayout.CENTER);

        return panel;
    }

    private JButton createButton(String text, String preco) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 51, 51));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        button.setIcon(new ImageIcon(getClass().getResource("/Icons/poltrona.png"))); // ajuste o caminho conforme necessário
        button.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel precoLabel = new JLabel(preco);
        precoLabel.setForeground(Color.ORANGE);
        precoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(new Color(51, 51, 51));
        wrapper.add(button);
        wrapper.add(precoLabel);

        return button;
    }

    private JLabel createLabel(String text, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }

    private void selecaoFrisa() {
        String selecao = (String) jComboBox1.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Selecionado: " + selecao);
    }

    private void selecaoCamarote() {
        String selecao = (String) jComboBox2.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Selecionado: " + selecao);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CompraIngresso(null).setVisible(true));
    }
}