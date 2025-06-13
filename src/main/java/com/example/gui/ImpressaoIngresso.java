package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ImpressaoIngresso extends JFrame implements Printable {
    private String dadosIngresso;
    private JTextArea areaImpressao;

    public ImpressaoIngresso(String dadosIngresso) {
        this.dadosIngresso = dadosIngresso;
        initComponentes();
    }

    private void initComponentes() {
        setTitle("Impressão de Ingresso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cabeçalho
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setBackground(new Color(255, 102, 0));
        painelCabecalho.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        JLabel titulo = new JLabel("Visualização do Ingresso");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        painelCabecalho.add(titulo);
        
        add(painelCabecalho, BorderLayout.NORTH);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);

        areaImpressao = new JTextArea(dadosIngresso);
        areaImpressao.setEditable(false);
        areaImpressao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        areaImpressao.setLineWrap(true);
        areaImpressao.setWrapStyleWord(true);
        areaImpressao.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            " Conteúdo do Ingresso ",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(70, 70, 70)));

        painelPrincipal.add(areaImpressao, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new GridBagLayout());
        painelBotoes.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnImprimir.setBackground(new Color(255, 102, 0));
        btnImprimir.setForeground(Color.WHITE);
        btnImprimir.setFocusPainted(false);
        btnImprimir.addActionListener(e -> imprimirIngresso());

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnFechar.setBackground(new Color(100, 100, 100));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setFocusPainted(false);
        btnFechar.addActionListener(e -> dispose());

        gbc.gridx = 0;
        painelBotoes.add(btnImprimir, gbc);
        gbc.gridx = 1;
        painelBotoes.add(btnFechar, gbc);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        add(painelPrincipal, BorderLayout.CENTER);
    }

    private void imprimirIngresso() {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            if (job.printDialog()) {
                job.print();
            }
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int print(java.awt.Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        String[] linhas = dadosIngresso.split("\n");
        int y = 100;
        
        for (String linha : linhas) {
            graphics.drawString(linha, 100, y);
            y += 20;
        }

        return PAGE_EXISTS;
    }
} 