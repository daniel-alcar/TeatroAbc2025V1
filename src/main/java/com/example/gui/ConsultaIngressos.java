package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.example.model.CarregarReservas;

public class ConsultaIngressos extends JFrame {

    private JTextField txtCpf;
    private JButton btnBuscar;
    private JTextArea areaResultados;

    private Map<String, Map<String, Map<Integer, String>>> reservas;

    public ConsultaIngressos(Map<String, Map<String, Map<Integer, String>>> reservas) {
        this.reservas = reservas;
        initComponentes();
    }

    public ConsultaIngressos() {
        this.reservas = CarregarReservas.carregarReservasDeArquivo("RESERVAS.TXT");
        initComponentes();
    }

    private void initComponentes() {
        setTitle("Consulta de Ingressos por CPF");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Consulta de Ingressos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel painelEntrada = new JPanel();
        painelEntrada.setBorder(new EmptyBorder(10, 20, 10, 20));
        painelEntrada.setLayout(null);

        JLabel lblCpf = new JLabel("Digite o CPF:");
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCpf.setBounds(10, 10, 100, 25);
        painelEntrada.add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(120, 10, 250, 25);
        painelEntrada.add(txtCpf);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(380, 10, 90, 25);
        painelEntrada.add(btnBuscar);

        add(painelEntrada, BorderLayout.CENTER);

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBorder(new EmptyBorder(10, 20, 20, 20));
        scroll.setPreferredSize(new Dimension(450, 250));
        add(scroll, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarReservas());
    }

    private void buscarReservas() {
        String cpfBusca = txtCpf.getText().trim();

        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um CPF.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> resultados = new ArrayList<>();

        for (Entry<String, Map<String, Map<Integer, String>>> entrySessao : reservas.entrySet()) {
            String sessao = entrySessao.getKey();
            Map<String, Map<Integer, String>> mapaAreas = entrySessao.getValue();

            for (Entry<String, Map<Integer, String>> entryArea : mapaAreas.entrySet()) {
                String area = entryArea.getKey();
                Map<Integer, String> poltronas = entryArea.getValue();

                for (Entry<Integer, String> entryPoltrona : poltronas.entrySet()) {
                    Integer poltrona = entryPoltrona.getKey();
                    String cpf = entryPoltrona.getValue();

                    if (cpfBusca.equals(cpf)) {
                        resultados.add("Sessão: " + sessao + ", Área: " + area + ", Poltrona: " + poltrona);
                    }
                }
            }
        }

        if (resultados.isEmpty()) {
            areaResultados.setText("Nenhuma reserva encontrada para o CPF: " + cpfBusca);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Reservas encontradas para o CPF ").append(cpfBusca).append(":\n\n");
            for (String res : resultados) {
                sb.append(res).append("\n");
            }
            areaResultados.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ConsultaIngressos consulta = new ConsultaIngressos();
            consulta.setVisible(true);
        });
    }
}
