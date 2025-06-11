package com.example.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.example.dao.IUsuarioDAO;
import com.example.dao.UsuarioDAOBanco;
import com.example.model.Usuario;
import com.example.service.UsuarioService;
import com.example.util.ConexaoBanco;
import com.formdev.flatlaf.FlatLightLaf;

public class Login extends javax.swing.JFrame {

    private UsuarioService usuarioService;
    private Image backgroundImage;
    private static final int MIN_WIDTH = 543;
    private static final int MIN_HEIGHT = 510;

    public Login() {
        initComponents();
        try {
            Connection conn = ConexaoBanco.getConnection();
            IUsuarioDAO dao = new UsuarioDAOBanco(conn);
            usuarioService = new UsuarioService(dao);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setUndecorated(true);

        jPanel2 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        // Carregar imagem de fundo
        URL bgUrl = getClass().getResource("/Icons/backgroudlogin.jpg");
        if (bgUrl != null) {
            backgroundImage = new ImageIcon(bgUrl).getImage();
        } else {
            System.out.println("Não foi possível carregar a imagem de fundo");
        }

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonLogar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jBNovoCadastro = new javax.swing.JButton();
        JFtxtCpf = new javax.swing.JFormattedTextField();

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        URL userIconUrl = getClass().getResource("/Icons/user.png");
        if (userIconUrl != null) {
            ImageIcon userIcon = new ImageIcon(userIconUrl);
            Image scaledImage = userIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            jLabel1.setIcon(new ImageIcon(scaledImage));
        }
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel3.setForeground(new java.awt.Color(255, 102, 0));
        jLabel3.setText("Digite seu CPF:");

        jButtonLogar.setBackground(new java.awt.Color(255, 102, 0));
        jButtonLogar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jButtonLogar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogar.setText("LOGAR");
        jButtonLogar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonLogar.addActionListener(evt -> loginUser());

        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Não é cadastrado?");

        jBNovoCadastro.setBackground(new java.awt.Color(51, 51, 51));
        jBNovoCadastro.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jBNovoCadastro.setForeground(new java.awt.Color(255, 255, 255));
        URL addUserIconUrl = getClass().getResource("/Icons/add-user.png");
        if (addUserIconUrl != null) {
            ImageIcon addUserIcon = new ImageIcon(addUserIconUrl);
            Image scaledImage = addUserIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            jBNovoCadastro.setIcon(new ImageIcon(scaledImage));
        }
        jBNovoCadastro.setText("Criar novo cadastro");
        jBNovoCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBNovoCadastro.setIconTextGap(12);
        jBNovoCadastro.addActionListener(evt -> {
            new CadastroUsuario().setVisible(true);
            this.dispose();
        });

        try {
            JFtxtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        JFtxtCpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JFtxtCpf.setBorder(null);
        JFtxtCpf.setForeground(new java.awt.Color(0, 0, 0));
        JFtxtCpf.setBackground(new java.awt.Color(255, 255, 255));

        // Layout
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap(50, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(JFtxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jBNovoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(JFtxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBNovoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(830, 250, MIN_WIDTH, MIN_HEIGHT);
        setLocationRelativeTo(null);
    }

    private void loginUser() {
        String cpf = JFtxtCpf.getText().replaceAll("[^\\d]", ""); // Remove pontuação

        if (cpf.isEmpty() || cpf.length() != 11) {
            JOptionPane.showMessageDialog(this, "CPF inválido. Insira um CPF com 11 dígitos.");
            return;
        }

        try {
            Usuario usuario = usuarioService.buscarPorCpf(cpf);
            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                new Espetaculos().setVisible(true);
                limpar();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "CPF não encontrado. Por favor, cadastre-se.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao tentar fazer login: " + ex.getMessage());
        }
    }

    private void limpar() {
        JFtxtCpf.setText("");
    }

    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    private javax.swing.JFormattedTextField JFtxtCpf;
    private javax.swing.JButton jBNovoCadastro;
    private javax.swing.JButton jButtonLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
}
