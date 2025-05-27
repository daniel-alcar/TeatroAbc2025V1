package com.example;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Camarote_5 extends JFrame {
    
    Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField jTxtNumeroPoltronaCamarote05;
    private JButton jBComprarIngressoCamarote05;

    public Camarote_5(Sessao sessao) {
        this.sessao = sessao;
        initComponents();
    }
    
    public Camarote_5() {
        initComponents();
    }
    
    private void initComponents() {
        // Configurações do JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);
        setSize(792, 232);
        setLocationRelativeTo(null); // centraliza tela
        
        // jPanel1 - painel branco
        jPanel1 = new JPanel(null);
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBounds(0, 0, 792, 232);
        add(jPanel1);
        
        // jPanel2 - painel cinza escuro topo
        jPanel2 = new JPanel(null);
        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBounds(0, 0, 792, 90);
        jPanel1.add(jPanel2);
        
        // jLabel1 - título "CAMAROTE 05"
        jLabel1 = new JLabel("CAMAROTE 05");
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 36));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setBounds(320, 10, 300, 40);
        jPanel2.add(jLabel1);
        
        // jLabel2 - subtítulo "Poltronas de 1 - 10"
        jLabel2 = new JLabel("Poltronas de 1 - 10");
        jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setBounds(320, 50, 200, 25);
        jPanel2.add(jLabel2);
        
        // jLabel3 - texto da instrução
        jLabel3 = new JLabel("Digite o número da Poltrona desejada:");
        jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jLabel3.setBounds(274, 110, 300, 25);
        jPanel1.add(jLabel3);
        
        // jTxtNumeroPoltronaCamarote05 - input do número da poltrona
        jTxtNumeroPoltronaCamarote05 = new JTextField();
        jTxtNumeroPoltronaCamarote05.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jTxtNumeroPoltronaCamarote05.setBounds(274, 140, 132, 38);
        jPanel1.add(jTxtNumeroPoltronaCamarote05);
        
        // jBComprarIngressoCamarote05 - botão comprar ingresso
        jBComprarIngressoCamarote05 = new JButton("Comprar Ingresso");
        jBComprarIngressoCamarote05.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        jBComprarIngressoCamarote05.setBackground(new java.awt.Color(255, 102, 0));
        jBComprarIngressoCamarote05.setForeground(new java.awt.Color(255, 255, 255));
        jBComprarIngressoCamarote05.setBounds(274, 180, 260, 62);
        jPanel1.add(jBComprarIngressoCamarote05);

        // Evento do botão
        jBComprarIngressoCamarote05.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jBComprarIngressoCamarote05ActionPerformed(evt);
            }
        });
    }

    private void jBComprarIngressoCamarote05ActionPerformed(ActionEvent evt) {
        VendaIngresso venda = new VendaIngresso();

        try {
            int numeroPoltrona = Integer.parseInt(jTxtNumeroPoltronaCamarote05.getText());  // Converte o texto para número

            // Verifica se a poltrona está disponível
            if (sessao != null && sessao.reservarPoltrona("Camarote 5", numeroPoltrona)) {
                System.out.println("Poltrona disponível. Ingresso comprado!");
                
                // Aqui poderia abrir a tela de impressão do ingresso, etc.
            } else {
                System.out.println("Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();  // Exibe estatísticas, caso necessário
        } catch (NumberFormatException e) {
            System.out.println("Erro: O número da poltrona deve ser um valor inteiro.");
        }
    }

    public static void main(String args[]) {
        FlatLightLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Camarote_5().setVisible(true);
            }
        });
    }
}
