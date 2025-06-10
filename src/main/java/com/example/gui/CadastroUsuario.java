package com.example.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
import javax.swing.text.MaskFormatter;

import com.example.dao.IUsuarioDAO;
import com.example.dao.UsuarioDAOBanco;
import com.example.model.Usuario;
import com.example.service.UsuarioService;
import com.example.util.ConexaoBanco;
import com.formdev.flatlaf.FlatLightLaf;

public class CadastroUsuario extends JFrame {

    private JTextField txtNome, txtLogradouro, txtBairro, txtCep, txtCidade, txtUf;
    private JFormattedTextField txtCpf, txtDataNascimento, txtTelefone;
    private JRadioButton chkFidelidade;
    private JButton btnCadastrar, btnVoltar;

    public CadastroUsuario() {
        super("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        FlatLightLaf.setup();
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        try {
            txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
            txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
            txtTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtNome = new JTextField();
        txtLogradouro = new JTextField();
        txtBairro = new JTextField();
        txtCep = new JTextField();
        txtCidade = new JTextField();
        txtUf = new JTextField();
        txtUf.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (getLength() < 2) {
                    super.insertString(offs, str.toUpperCase(), a);
                }
            }
        });
        chkFidelidade = new JRadioButton("Ativar Fidelidade");

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(txtCpf);
        formPanel.add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnCadastrar = new JButton("Cadastrar");
        btnVoltar = new JButton("Voltar");

        btnCadastrar.addActionListener((ActionEvent e) -> cadastrarUsuario());
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

    private void cadastrarUsuario() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().replaceAll("[^\\d]", "").trim();
        String dataNascimentoStr = txtDataNascimento.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String logradouro = txtLogradouro.getText().trim();
        String bairro = txtBairro.getText().trim();
        String cep = txtCep.getText().trim();
        String cidade = txtCidade.getText().trim();
        String uf = txtUf.getText().trim().toUpperCase();

        // Validação simples
        if (nome.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos Nome, CPF e Data de Nascimento.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do UF
        if (uf.length() != 2) {
            JOptionPane.showMessageDialog(this, "O campo UF deve conter exatamente 2 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Converte a data de nascimento String (dd/MM/yyyy) para LocalDate
        LocalDate dataNascimento;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario novoUsuario = new Usuario(
            nome,
            cpf,
            dataNascimento,
            telefone,
            logradouro,
            bairro,
            cep,
            cidade,
            uf
        );

        try {
            Connection conn = ConexaoBanco.getConnection();
            IUsuarioDAO dao = new UsuarioDAOBanco(conn);
            UsuarioService service = new UsuarioService(dao);
            service.cadastrarUsuario(novoUsuario);

            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            
            new Login().setVisible(true);
            this.dispose();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroUsuario().setVisible(true);
        });
    }
}