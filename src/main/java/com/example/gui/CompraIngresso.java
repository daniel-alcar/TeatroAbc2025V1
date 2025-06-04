package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.example.model.Area;
import com.example.model.Sessao;

public class CompraIngresso extends JFrame {

    private JComboBox<String> comboSessao;
    private JComboBox<String> comboArea;
    private JComboBox<Integer> comboPoltrona;
    private JButton btnFinalizar;

    // Mapa: Sessao -> Area -> Poltrona -> CPF comprador (null se disponível)
    private Map<String, Map<String, Map<Integer, String>>> reservas;

    private List<Sessao> sessoes;

    private static final String ARQUIVO_RESERVAS = "reservas.txt";

    public CompraIngresso(List<Sessao> sessoes) {
        this.sessoes = sessoes;

        reservas = new HashMap<>();

        initComponentes();
        inicializarReservas();
        carregarReservasDoArquivo();
        atualizarAreas();
        atualizarPoltronas();
    }

    private void initComponentes() {
        setTitle("Compra de Ingresso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Compra de Ingresso");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(new EmptyBorder(15, 0, 20, 0));
        titulo.setForeground(Color.BLACK);
        add(titulo, BorderLayout.NORTH);

        // Painel central com GridBagLayout
        JPanel painelCentral = new JPanel();
        painelCentral.setBorder(new EmptyBorder(10, 30, 10, 30));
        painelCentral.setLayout(new GridBagLayout());
        painelCentral.setBackground(new Color(250, 245, 240)); // tom neutro claro
        add(painelCentral, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels e combos
        JLabel lblSessao = new JLabel("Selecione a Sessão:");
        lblSessao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSessao.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCentral.add(lblSessao, gbc);

        comboSessao = new JComboBox<>();
        comboSessao.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        painelCentral.add(comboSessao, gbc);

        JLabel lblArea = new JLabel("Selecione a Área:");
        lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblArea.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCentral.add(lblArea, gbc);

        comboArea = new JComboBox<>();
        comboArea.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        painelCentral.add(comboArea, gbc);

        JLabel lblPoltrona = new JLabel("Selecione a Poltrona:");
        lblPoltrona.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPoltrona.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCentral.add(lblPoltrona, gbc);

        comboPoltrona = new JComboBox<>();
        comboPoltrona.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        painelCentral.add(comboPoltrona, gbc);

        // Botão finalizar no rodapé
        btnFinalizar = new JButton("Finalizar Compra");
        btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnFinalizar.setBackground(new Color(255, 140, 0)); // laranja
        btnFinalizar.setForeground(Color.BLACK);
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setPreferredSize(new Dimension(180, 40));
        JPanel painelBotao = new JPanel();
        painelBotao.setBorder(new EmptyBorder(10, 0, 20, 0));
        painelBotao.add(btnFinalizar);
        add(painelBotao, BorderLayout.SOUTH);

        // Preencher comboSessao com os períodos das sessões
        for (Sessao s : sessoes) {
            comboSessao.addItem(s.getPeriodo());
        }

        // Quando trocar sessão, atualizar áreas e poltronas
        comboSessao.addActionListener(e -> {
            atualizarAreas();
            atualizarPoltronas();
        });

        // Quando trocar área, atualizar poltronas
        comboArea.addActionListener(e -> atualizarPoltronas());

        // Botão finalizar reserva a poltrona selecionada
        btnFinalizar.addActionListener(e -> finalizarCompra());
    }

    private void inicializarReservas() {
        // Inicializa mapa reservas: todas as poltronas marcadas como disponíveis (null)
        for (Sessao sessao : sessoes) {
            String periodo = sessao.getPeriodo();
            Map<String, Map<Integer, String>> mapaAreas = new HashMap<>();
            reservas.put(periodo, mapaAreas);

            for (Area area : sessao.getAreas()) {
                Map<Integer, String> poltronas = new HashMap<>();
                int capacidade = area.getCapacidadeMaxima();

                for (int i = 1; i <= capacidade; i++) {
                    poltronas.put(i, null);
                }
                mapaAreas.put(area.getTitulo(), poltronas);
            }
        }
    }

    private void carregarReservasDoArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_RESERVAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Formato: sessao;area;poltrona;cpf
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    String sessao = partes[0];
                    String area = partes[1];
                    int poltrona = Integer.parseInt(partes[2]);
                    String cpf = partes[3];

                    Map<String, Map<Integer, String>> mapaAreas = reservas.get(sessao);
                    if (mapaAreas != null) {
                        Map<Integer, String> poltronas = mapaAreas.get(area);
                        if (poltronas != null && poltronas.containsKey(poltrona)) {
                            poltronas.put(poltrona, cpf);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Não foi possível carregar reservas do arquivo: " + e.getMessage());
        }
    }

    private void salvarReservaNoArquivo(String sessao, String area, int poltrona, String cpf) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("reservas.txt", true))) {
            bw.write(sessao + ";" + area + ";" + poltrona + ";" + cpf);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar reserva no arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarAreas() {
        comboArea.removeAllItems();
        String sessaoSelecionada = (String) comboSessao.getSelectedItem();

        if (sessaoSelecionada == null) return;

        Map<String, Map<Integer, String>> mapaAreas = reservas.get(sessaoSelecionada);
        if (mapaAreas == null) return;

        for (String area : mapaAreas.keySet()) {
            comboArea.addItem(area);
        }
    }

    private void atualizarPoltronas() {
        comboPoltrona.removeAllItems();

        String sessao = (String) comboSessao.getSelectedItem();
        String area = (String) comboArea.getSelectedItem();

        if (sessao == null || area == null) return;

        Map<String, Map<Integer, String>> mapaSessao = reservas.get(sessao);
        if (mapaSessao == null) return;

        Map<Integer, String> poltronas = mapaSessao.get(area);
        if (poltronas == null) return;

        boolean temDisponivel = false;
        for (Map.Entry<Integer, String> entry : poltronas.entrySet()) {
            if (entry.getValue() == null) { // poltrona não reservada
                comboPoltrona.addItem(entry.getKey());
                temDisponivel = true;
            }
        }

        if (!temDisponivel) {
            comboPoltrona.addItem(-1); // indica sem poltronas disponíveis
        }
    }

    private void finalizarCompra() {
        String sessao = (String) comboSessao.getSelectedItem();
        String area = (String) comboArea.getSelectedItem();
        Integer poltrona = (Integer) comboPoltrona.getSelectedItem();

        if (sessao == null || area == null || poltrona == null || poltrona == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma poltrona válida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<Integer, String> poltronas = reservas.get(sessao).get(area);

        if (poltronas.get(poltrona) != null) {
            JOptionPane.showMessageDialog(this, "Poltrona já reservada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cpf = JOptionPane.showInputDialog(this, "Digite seu CPF para confirmar a reserva:");

        if (cpf == null || cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        poltronas.put(poltrona, cpf.trim());
        salvarReservaNoArquivo(sessao, area, poltrona, cpf.trim());

        JOptionPane.showMessageDialog(this,
            "Compra finalizada com sucesso!\nSessão: " + sessao + "\nÁrea: " + area + "\nPoltrona: " + poltrona + "\nCPF: " + cpf);

        // Fecha esta janela
        this.dispose();

        // Abre a TelaPrincipal
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
    }

}
