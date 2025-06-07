package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.example.dao.UsuarioDAOBanco;
import com.example.dao.VendaIngressoDAOBanco;
import com.example.model.Area;
import com.example.model.Peca;
import com.example.model.Sessao;
import com.example.model.Usuario;
import com.example.model.VendaIngresso;

public class CompraIngresso extends JFrame {

    private JComboBox<String> comboSessao;
    private JComboBox<String> comboArea;
    private JComboBox<Integer> comboPoltrona;
    private JButton btnFinalizar;

    private List<Sessao> sessoes;
    private Connection connection;
    private VendaIngressoDAOBanco vendaDAO;

    private Map<String, Sessao> mapSessoes = new HashMap<>();
    private Map<String, Area> mapAreas = new HashMap<>();

    public CompraIngresso(List<Sessao> sessoes, Connection connection) {
        this.sessoes = sessoes;
        this.connection = connection;
        this.vendaDAO = new VendaIngressoDAOBanco(connection);

        initComponentes();
        atualizarAreas();
        atualizarPoltronas();
    }

    private void initComponentes() {
        setTitle("Compra de Ingresso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Compra de Ingresso");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(new EmptyBorder(15, 0, 20, 0));
        titulo.setForeground(Color.BLACK);
        add(titulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setBorder(new EmptyBorder(10, 30, 10, 30));
        painelCentral.setLayout(new GridBagLayout());
        painelCentral.setBackground(new Color(250, 245, 240));
        add(painelCentral, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Sessão
        JLabel lblSessao = new JLabel("Selecione a Sessão:");
        lblSessao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCentral.add(lblSessao, gbc);

        comboSessao = new JComboBox<>();
        for (Sessao s : sessoes) {
            comboSessao.addItem(s.getPeriodo());
            mapSessoes.put(s.getPeriodo(), s);
        }
        comboSessao.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        painelCentral.add(comboSessao, gbc);

        // Área
        JLabel lblArea = new JLabel("Selecione a Área:");
        lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCentral.add(lblArea, gbc);

        comboArea = new JComboBox<>();
        comboArea.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        painelCentral.add(comboArea, gbc);

        // Poltrona
        JLabel lblPoltrona = new JLabel("Selecione a Poltrona:");
        lblPoltrona.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCentral.add(lblPoltrona, gbc);

        comboPoltrona = new JComboBox<>();
        comboPoltrona.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        painelCentral.add(comboPoltrona, gbc);

        // Botão Finalizar
        btnFinalizar = new JButton("Finalizar Compra");
        btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnFinalizar.setBackground(new Color(255, 140, 0));
        btnFinalizar.setForeground(Color.BLACK);
        btnFinalizar.setPreferredSize(new Dimension(180, 40));
        JPanel painelBotao = new JPanel();
        painelBotao.setBorder(new EmptyBorder(10, 0, 20, 0));
        painelBotao.add(btnFinalizar);
        add(painelBotao, BorderLayout.SOUTH);

        comboSessao.addActionListener(e -> {
            atualizarAreas();
            atualizarPoltronas();
        });

        comboArea.addActionListener(e -> atualizarPoltronas());

        btnFinalizar.addActionListener(e -> finalizarCompra());
    }

    private void atualizarAreas() {
        comboArea.removeAllItems();
        mapAreas.clear();

        String sessaoSelecionada = (String) comboSessao.getSelectedItem();
        if (sessaoSelecionada == null) return;

        Sessao sessao = mapSessoes.get(sessaoSelecionada);
        if (sessao != null) {
            for (Area a : sessao.getAreas()) {
                comboArea.addItem(a.getTitulo());
                mapAreas.put(a.getTitulo(), a);
            }
        }
    }

    private void atualizarPoltronas() {
        comboPoltrona.removeAllItems();
        String sessaoSelecionada = (String) comboSessao.getSelectedItem();
        String areaSelecionada = (String) comboArea.getSelectedItem();
        if (sessaoSelecionada == null || areaSelecionada == null) return;

        try {
            Sessao sessao = mapSessoes.get(sessaoSelecionada);
            Area area = mapAreas.get(areaSelecionada);

            if (sessao == null || area == null) return;

            List<VendaIngresso> vendas = vendaDAO.buscarTodas();
            Set<Integer> ocupadas = new HashSet<>();
            for (VendaIngresso v : vendas) {
                if (v.getSessao().getId() == sessao.getId() &&
                    v.getArea().getId() == area.getId()) {
                    ocupadas.add(v.getPoltrona());
                }
            }

            for (int i = 1; i <= area.getCapacidadeMaxima(); i++) {
                if (!ocupadas.contains(i)) {
                    comboPoltrona.addItem(i);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar poltronas: " + e.getMessage());
        }
    }

    private void finalizarCompra() {
        String sessaoSelecionada = (String) comboSessao.getSelectedItem();
        String areaSelecionada = (String) comboArea.getSelectedItem();
        Integer poltronaSelecionada = (Integer) comboPoltrona.getSelectedItem();
 
        System.out.println("ID da área selecionada: " + areaSelecionada + " | Título: " + areaSelecionada);
 
        if (sessaoSelecionada == null || areaSelecionada == null || poltronaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione todos os campos.");
            return;
        }

        String cpf = JOptionPane.showInputDialog(this, "Digite o CPF do comprador:");
        if (cpf == null || cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF é obrigatório.");
            return;
        }

        try {
            UsuarioDAOBanco usuarioDAO = new UsuarioDAOBanco(connection);
            Usuario usuario = usuarioDAO.buscarPorCPF(cpf);
            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
                return;
            }

            Sessao sessao = mapSessoes.get(sessaoSelecionada);
            Area area = mapAreas.get(areaSelecionada);

            if (sessao == null || area == null) {
                JOptionPane.showMessageDialog(this, "Erro interno: sessão ou área inválida.");
                return;
            }

            Peca peca = sessao.getPeca();
            VendaIngresso venda = new VendaIngresso(usuario, area, poltronaSelecionada, peca, sessao);
            vendaDAO.salvar(venda);

            JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!");
            atualizarPoltronas();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar compra: " + ex.getMessage());
        }
    }
}
