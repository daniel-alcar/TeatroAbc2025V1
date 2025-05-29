package com.example.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

public class CadastroUsuario extends JFrame {

    private JTextField txtNome, txtLogradouro, txtBairro, txtCep, txtCidade, txtUf;
    private JFormattedTextField txtCpf, txtDataNascimento, txtTelefone;
    private JRadioButton chkFidelidade;
    private JButton btnCadastrar, btnVoltar;

    public CadastroUsuario() {
        super("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        FlatLightLaf.setup();
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        try {
            txtCpf = new JFormattedTextField(new javax.swing.text.MaskFormatter("###.###.###-##"));
            txtDataNascimento = new JFormattedTextField(new javax.swing.text.MaskFormatter("##/##/####"));
            txtTelefone = new JFormattedTextField(new javax.swing.text.MaskFormatter("(##)#####-####"));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtNome = new JTextField();
        txtLogradouro = new JTextField();
        txtBairro = new JTextField();
        txtCep = new JTextField();
        txtCidade = new JTextField();
        txtUf = new JTextField();
        chkFidelidade = new JRadioButton("Ativar Fidelidade");

        // Adiciona os campos ao formulário
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(txtCpf);
        formPanel.add(new JLabel("Data de Nascimento:"));
        formPanel.add(txtDataNascimento);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(txtTelefone);
        formPanel.add(new JLabel("Logradouro:"));
        formPanel.add(txtLogradouro);
        formPanel.add(new JLabel("Bairro:"));
        formPanel.add(txtBairro);
        formPanel.add(new JLabel("CEP:"));
        formPanel.add(txtCep);
        formPanel.add(new JLabel("Cidade:"));
        formPanel.add(txtCidade);
        formPanel.add(new JLabel("UF:"));
        formPanel.add(txtUf);
        formPanel.add(new JLabel("Fidelidade:"));
        formPanel.add(chkFidelidade);

        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnCadastrar = new JButton("Cadastrar");
        btnVoltar = new JButton("Voltar");

        btnCadastrar.addActionListener((ActionEvent e) -> {
            cadastrarDados();
        });

        btnVoltar.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Voltando ao menu...");
            dispose();
        });

        buttonPanel.add(btnCadastrar);
        buttonPanel.add(btnVoltar);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void cadastrarDados() {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String nascimento = txtDataNascimento.getText();
        String telefone = txtTelefone.getText();
        String logradouro = txtLogradouro.getText();
        String bairro = txtBairro.getText();
        String cep = txtCep.getText();
        String cidade = txtCidade.getText();
        String uf = txtUf.getText();
        boolean fidelidade = chkFidelidade.isSelected();

        JOptionPane.showMessageDialog(this, "Usuário cadastrado:\n" + nome);
        limparCampos();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtDataNascimento.setText("");
        txtTelefone.setText("");
        txtLogradouro.setText("");
        txtBairro.setText("");
        txtCep.setText("");
        txtCidade.setText("");
        txtUf.setText("");
        chkFidelidade.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroUsuario().setVisible(true);
        });
    }
}
