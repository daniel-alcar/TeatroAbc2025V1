package com.example.gui;


import com.example.model.Estatisticas;
import com.example.model.Sessao;
import com.example.model.VendaIngresso;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * Interface gráfica para comprar ingresso na Frisa 5.
 * @author Edivania
 */
public class Frisa_5 extends javax.swing.JFrame {

    // Instância para estatísticas
    Estatisticas estatisticas = new Estatisticas();

    // Sessão para reserva de poltronas
    private Sessao sessao;

    /**
     * Construtor que recebe uma sessão.
     * @param sessao Objeto da classe Sessao para controlar reservas.
     */
    public Frisa_5(Sessao sessao) {
        this.sessao = sessao;
        initComponents();
    }

    /**
     * Construtor padrão (sem parâmetros).
     */
    Frisa_5() {
        initComponents();
    }

    /**
     * Método gerado automaticamente para inicializar componentes da interface.
     * Não deve ser alterado manualmente.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Declaração e configuração dos componentes visuais
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtNumeroPoltronaFrisa05 = new javax.swing.JTextField();
        jBComprarIngressoFrisa05 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // Título
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FRISA 05");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // Subtítulo
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Poltronas de 1 - 5");

        // Layout do painel superior
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(327, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(314, 314, 314))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel3.setText("Digite o número da Poltrona desejada:");

        jTxtNumeroPoltronaFrisa05.setFont(new java.awt.Font("Segoe UI", 1, 14));

        jBComprarIngressoFrisa05.setBackground(new java.awt.Color(255, 102, 0));
        jBComprarIngressoFrisa05.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jBComprarIngressoFrisa05.setForeground(new java.awt.Color(255, 255, 255));
        jBComprarIngressoFrisa05.setText("Comprar Ingresso");
        jBComprarIngressoFrisa05.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBComprarIngressoFrisa05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBComprarIngressoFrisa05ActionPerformed(evt);
            }
        });

        // Layout do painel principal
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(274, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBComprarIngressoFrisa05, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(258, 258, 258))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTxtNumeroPoltronaFrisa05, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTxtNumeroPoltronaFrisa05, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBComprarIngressoFrisa05, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        // Configuração do layout da janela principal
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(828, 665, 792, 232);
    }

    /**
     * Evento chamado ao clicar no botão "Comprar Ingresso".
     */
    private void jBComprarIngressoFrisa05ActionPerformed(java.awt.event.ActionEvent evt) {
        VendaIngresso venda = new VendaIngresso();

        try {
            int numeroPoltrona = Integer.parseInt(jTxtNumeroPoltronaFrisa05.getText());

            // Verifica se a poltrona está disponível na sessão
            if (sessao.reservarPoltrona("Frisa 5", numeroPoltrona)) {
                System.out.println("Poltrona disponível. Ingresso comprado!");
                // Aqui poderia abrir uma tela para imprimir ingresso
            } else {
                System.out.println("Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();
        } catch (NumberFormatException e) {
            System.out.println("Erro: O número da poltrona deve ser um valor inteiro.");
        }
    }

    /**
     * Método principal para iniciar a interface.
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frisa_5().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jBComprarIngressoFrisa05;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTxtNumeroPoltronaFrisa05;
}
