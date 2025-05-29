package com.example.gui;


import com.example.model.LoginUsuario;
import com.example.model.Usuario;
import com.formdev.flatlaf.FlatLightLaf;

import java.util.List;

import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    private List<Usuario> usuarios;
    private LoginUsuario loginUsuario;

    public Login() {
        initComponents();
        loginUsuario = new LoginUsuario(usuarios);
    }

    /**
     * Método gerado pelo NetBeans para inicializar componentes da GUI.
     * NÃO MODIFICAR MANUALMENTE
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonLogar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jBNovoCadastro = new javax.swing.JButton();
        JFtxtCpf = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Edivania\\Downloads\\user login.png")); // Ícone do usuário
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Digite seu CPF:");

        jButtonLogar.setBackground(new java.awt.Color(255, 102, 0));
        jButtonLogar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonLogar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogar.setText("LOGAR");
        jButtonLogar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Não é cadastrado?");

        jBNovoCadastro.setBackground(new java.awt.Color(51, 51, 51));
        jBNovoCadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBNovoCadastro.setForeground(new java.awt.Color(255, 255, 255));
        jBNovoCadastro.setIcon(new javax.swing.ImageIcon("C:\\Users\\Edivania\\Downloads\\adicionar user (1).png"));
        jBNovoCadastro.setText("Criar novo cadastro");
        jBNovoCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBNovoCadastro.setIconTextGap(12);
        jBNovoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNovoCadastroActionPerformed(evt);
            }
        });

        try {
            JFtxtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        JFtxtCpf.setFont(new java.awt.Font("Segoe UI", 0, 16));
        JFtxtCpf.setBorder(null);
        JFtxtCpf.setForeground(new java.awt.Color(0, 0, 0));
        JFtxtCpf.setBackground(new java.awt.Color(255, 255, 255));

        // Layout do painel
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBNovoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonLogar, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                            .addComponent(JFtxtCpf))))
                .addContainerGap(139, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBNovoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBounds(830, 250, 543, 510);
    }// </editor-fold>                        

    // Evento de clique do botão "LOGAR"
    private void jButtonLogarActionPerformed(java.awt.event.ActionEvent evt) { 
                new Espetaculos().setVisible(true);                                           
        loginUser();
        limpar();
        
    }                                           

    // Evento de clique do botão "Criar novo cadastro"
    private void jBNovoCadastroActionPerformed(java.awt.event.ActionEvent evt) {                                              
        new CadastroUsuario().setVisible(true);
    }                                             

    /**
     * Método principal para execução da aplicação.
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();

        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variáveis de componentes da GUI
    private javax.swing.JFormattedTextField JFtxtCpf;
    private javax.swing.JButton jBNovoCadastro;
    private javax.swing.JButton jButtonLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;

    /**
     * Realiza o processo de login, removendo a máscara do CPF para validação.
     */
    public void loginUser() {
        String cpfSemMascara = JFtxtCpf.getText().replaceAll("[^\\d]", ""); // Remove máscara do CPF

        if (loginUsuario.RealizarLogin(cpfSemMascara)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            new Espetaculos().setVisible(true); // Abre a tela de espetáculos após login
            this.dispose(); // Fecha a tela atual de login
        } else {
            JOptionPane.showMessageDialog(this, "CPF não encontrado. Login falhou.");
        }
    }

    /**
     * Limpa o campo de CPF após a tentativa de login.
     */
    public void limpar() {
        JFtxtCpf.setText("");
    }
}
