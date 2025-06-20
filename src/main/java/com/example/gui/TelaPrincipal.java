package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLightLaf;

public class TelaPrincipal extends JFrame {
    private JLabel backgroundLabel;
    private Image originalImage;

    public TelaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(255, 102, 0));
        panelMenu.setPreferredSize(new Dimension(266, 789));
        panelMenu.setLayout(null);

        JButton jButtonUsuario = new JButton("Cadastro");
        jButtonUsuario.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jButtonUsuario.setForeground(Color.WHITE);
        jButtonUsuario.setBackground(new Color(255, 102, 0));
        jButtonUsuario.setBorder(null);
        jButtonUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        jButtonUsuario.setIconTextGap(15);
        URL iconUser = getClass().getResource("/Icons/user.png");
        if (iconUser != null) jButtonUsuario.setIcon(new ImageIcon(iconUser));
        jButtonUsuario.setBounds(10, 100, 250, 80);
        jButtonUsuario.addActionListener(evt -> new Login().setVisible(true));
        panelMenu.add(jButtonUsuario);

        JButton jButtonEspetaculo = new JButton("Espetáculos");
        jButtonEspetaculo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jButtonEspetaculo.setForeground(Color.WHITE);
        jButtonEspetaculo.setBackground(new Color(255, 102, 0));
        jButtonEspetaculo.setBorder(null);
        jButtonEspetaculo.setHorizontalAlignment(SwingConstants.LEFT);
        jButtonEspetaculo.setIconTextGap(14);
        URL iconEspetaculo = getClass().getResource("/Icons/espetaculo.png");
        if (iconEspetaculo != null) jButtonEspetaculo.setIcon(new ImageIcon(iconEspetaculo));
        jButtonEspetaculo.setBounds(10, 200, 250, 80);
        jButtonEspetaculo.addActionListener(evt -> new Login().setVisible(true));
        panelMenu.add(jButtonEspetaculo);

        JButton jButtonRelatorios = new JButton("Relatórios");
        jButtonRelatorios.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jButtonRelatorios.setForeground(Color.WHITE);
        jButtonRelatorios.setBackground(new Color(255, 102, 0));
        jButtonRelatorios.setBorder(null);
        jButtonRelatorios.setHorizontalAlignment(SwingConstants.LEFT);
        jButtonRelatorios.setIconTextGap(14);
        URL iconRelatorio = getClass().getResource("/Icons/relatorio.png");
        if (iconRelatorio != null) jButtonRelatorios.setIcon(new ImageIcon(iconRelatorio));
        jButtonRelatorios.setBounds(10, 300, 250, 80);
        jButtonRelatorios.addActionListener(evt -> new RelatorioUsuario().setVisible(true));
        panelMenu.add(jButtonRelatorios);


        JButton jButtonBuscarIngresso = new JButton("Buscar Ingresso");
        jButtonBuscarIngresso.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jButtonBuscarIngresso.setForeground(Color.WHITE);
        jButtonBuscarIngresso.setBackground(new Color(255, 102, 0));
        jButtonBuscarIngresso.setBorder(null);
        jButtonBuscarIngresso.setHorizontalAlignment(SwingConstants.LEFT);
        jButtonBuscarIngresso.setIconTextGap(14);
        URL iconBuscar = getClass().getResource("/Icons/buscar.png");
        if (iconBuscar != null) jButtonBuscarIngresso.setIcon(new ImageIcon(iconBuscar));
        jButtonBuscarIngresso.setBounds(10, 400, 250, 80);
        jButtonBuscarIngresso.addActionListener(evt -> new ConsultaIngressos().setVisible(true));
        panelMenu.add(jButtonBuscarIngresso);


        backgroundLabel = new JLabel();
        URL bg = getClass().getResource("/Icons/backgroundnovo.png");
        if (bg != null) {
            originalImage = new ImageIcon(bg).getImage();
            updateBackgroundImage();
        }
        backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(backgroundLabel, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelMenu, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundImage();
            }
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    private void updateBackgroundImage() {
        if (originalImage != null) {
            int width = getContentPane().getWidth();
            int height = getContentPane().getHeight();
            
            if (width > 0 && height > 0) {
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                backgroundLabel.setIcon(new ImageIcon(scaledImage));
            }
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup(); 
        EventQueue.invokeLater(() -> {
            TelaPrincipal frame = new TelaPrincipal();
            frame.setVisible(true);
            frame.updateBackgroundImage();
        });
    }
}