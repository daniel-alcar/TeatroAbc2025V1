package com.example.gui;

import com.example.model.Sessao;
import com.example.model.VendaIngresso;
import com.example.model.Estatisticas;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tela para compra de ingressos da Frisa 3
 * @author Edivania
 */
public class Frisa_3 extends JFrame {

    private Estatisticas estatisticas = new Estatisticas();
    private Sessao sessao;

    // Componentes Swing
    private JPanel panelMain;
    private JPanel panelHeader;
    private JLabel labelTitle;
    private JLabel labelSubtitle;
    private JLabel labelInstruction;
    private JTextField txtNumeroPoltrona;
    private JButton btnComprarIngresso;

    // Construtor com Sessao
    public Frisa_3(Sessao sessao) {
        this.sessao = sessao;
        initializeComponents();
        configureFrame();
        addListeners();
    }

    // Construtor padrão
    public Frisa_3() {
        initializeComponents();
        configureFrame();
        addListeners();
    }

    private void initializeComponents() {
        panelMain = new JPanel();
        panelHeader = new JPanel();
        labelTitle = new JLabel("FRISA 03");
        labelSubtitle = new JLabel("Poltronas de 1 - 5");
        labelInstruction = new JLabel("Digite o número da Poltrona desejada:");
        txtNumeroPoltrona = new JTextField();
        btnComprarIngresso = new JButton("Comprar Ingresso");

        // Configura cores e fontes
        panelMain.setBackground(Color.WHITE);

        panelHeader.setBackground(new Color(51, 51, 51));

        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        labelTitle.setForeground(Color.WHITE);

        labelSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        labelSubtitle.setForeground(Color.WHITE);

        labelInstruction.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtNumeroPoltrona.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnComprarIngresso.setBackground(new Color(255, 102, 0));
        btnComprarIngresso.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnComprarIngresso.setForeground(Color.WHITE);
        btnComprarIngresso.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        // Layout panelHeader
        GroupLayout headerLayout = new GroupLayout(panelHeader);
        panelHeader.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                    .addContainerGap(327, Short.MAX_VALUE)
                    .addGroup(headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelTitle)
                        .addComponent(labelSubtitle))
                    .addGap(314, 314, 314))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                    .addComponent(labelTitle)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSubtitle, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );

        // Layout panelMain
        GroupLayout mainLayout = new GroupLayout(panelMain);
        panelMain.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(panelHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                    .addContainerGap(274, Short.MAX_VALUE)
                    .addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                            .addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(btnComprarIngresso, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelInstruction))
                            .addGap(258, 258, 258))
                        .addGroup(GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                            .addComponent(txtNumeroPoltrona, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                            .addGap(322, 322, 322))))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(mainLayout.createSequentialGroup()
                    .addComponent(panelHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(labelInstruction)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumeroPoltrona, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnComprarIngresso, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18))
        );
    }

    private void configureFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setContentPane(panelMain);
        setBounds(828, 665, 792, 232);
        setLocationRelativeTo(null);
    }

    private void addListeners() {
        btnComprarIngresso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarIngressoAction();
            }
        });
    }

    private void comprarIngressoAction() {
        VendaIngresso venda = new VendaIngresso();

        try {
            int numeroPoltrona = Integer.parseInt(txtNumeroPoltrona.getText());

            if (sessao.reservarPoltrona("Frisa 3", numeroPoltrona)) {
                System.out.println("Poltrona disponível. Ingresso comprado!");
                // Aqui você pode colocar a lógica para abrir a tela de imprimir ingresso
            } else {
                System.out.println("Poltrona já reservada.");
            }

            estatisticas.exibirEstatisticas();
        } catch (NumberFormatException ex) {
            System.out.println("Erro: O número da poltrona deve ser um valor inteiro.");
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new Frisa_3().setVisible(true));
    }
}
