package com.example;

import com.formdev.flatlaf.FlatLightLaf;

/**
 * Interface para compra de ingressos no Camarote 04
 * @author Edivania
 */
public class Camarote_4 extends javax.swing.JFrame {

    private Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;

    /**
     * Construtor que recebe uma sessão para controle de poltronas
     * @param sessao sessão atual do teatro
     */
    public Camarote_4(Sessao sessao) {
        this.sessao = sessao;
        initComponents();
    }

    /**
     * Construtor padrão
     */
    public Camarote_4() {
        initComponents();
    }

    /**
     * Código gerado automaticamente pelo NetBeans para inicialização dos componentes gráficos
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtNumeroPoltronaCamarote04 = new javax.swing.JTextField();
        jBComprarIngressoCamarote04 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CAMAROTE 04");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Poltronas de 1 - 10");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(331, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(309, 309, 309))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel3.setText("Digite o número da Poltrona desejada:");

        jTxtNumeroPoltronaCamarote04.setFont(new java.awt.Font("Segoe UI", 1, 14));

        jBComprarIngressoCamarote04.setBackground(new java.awt.Color(255, 102, 0));
        jBComprarIngressoCamarote04.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jBComprarIngressoCamarote04.setForeground(new java.awt.Color(255, 255, 255));
        jBComprarIngressoCamarote04.setText("Comprar Ingresso");
        jBComprarIngressoCamarote04.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBComprarIngressoCamarote04.addActionListener(evt -> comprarIngressoAction());

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
                                .addComponent(jBComprarIngressoCamarote04, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGap(258, 258, 258))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jTxtNumeroPoltronaCamarote04, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(322, 322, 322))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTxtNumeroPoltronaCamarote04, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBComprarIngressoCamarote04, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18))
        );

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
    }// </editor-fold>

    /**
     * Método chamado ao clicar no botão Comprar Ingresso
     */
    private void comprarIngressoAction() {
        VendaIngresso venda = new VendaIngresso();

        try {
            int numeroPoltrona = Integer.parseInt(jTxtNumeroPoltronaCamarote04.getText().trim());

            if (sessao.reservarPoltrona("Camarote 4", numeroPoltrona)) {
                System.out.println("Poltrona disponível. Ingresso comprado!");
                // TODO: Lógica para abrir tela de impressão do ingresso
            } else {
                System.out.println("Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();
        } catch (NumberFormatException e) {
            System.out.println("Erro: O número da poltrona deve ser um valor inteiro.");
        }
    }

    public static void main(String args[]) {
        FlatLightLaf.setup();

        java.awt.EventQueue.invokeLater(() -> new Camarote_4().setVisible(true));
    }

    private javax.swing.JButton jBComprarIngressoCamarote04;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTxtNumeroPoltronaCamarote04;
}
