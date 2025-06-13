package com.example.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;
    private Image backgroundImage;
    private JTextField txtNome, txtLogradouro, txtBairro, txtCep, txtCidade, txtUf;
    private JFormattedTextField txtCpf, txtDataNascimento, txtTelefone;
    private JRadioButton chkFidelidade;
    private JButton btnCadastrar, btnVoltar;

    public CadastroUsuario() {
        super("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        FlatLightLaf.setup();
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 20, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        formPanel.setOpaque(false);

        try {
            txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
            txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
            txtTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color labelColor = new Color(255, 102, 0); 

        txtNome = createStyledTextField(fieldFont);
        txtLogradouro = createStyledTextField(fieldFont);
        txtBairro = createStyledTextField(fieldFont);
        txtCep = createStyledTextField(fieldFont);
        txtCidade = createStyledTextField(fieldFont);
        txtUf = createStyledTextField(fieldFont);
        txtCpf.setFont(fieldFont);
        txtDataNascimento.setFont(fieldFont);
        txtTelefone.setFont(fieldFont);

        txtUf.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (getLength() < 2) {
                    super.insertString(offs, str.toUpperCase(), a);
                }
            }
        });

        chkFidelidade = new JRadioButton("Ativar Fidelidade");
        chkFidelidade.setFont(fieldFont);
        chkFidelidade.setForeground(labelColor);

        addFormField(formPanel, "Nome:", txtNome, labelFont, labelColor);
        addFormField(formPanel, "CPF:", txtCpf, labelFont, labelColor);
        addFormField(formPanel, "Data de Nascimento (dd/MM/yyyy):", txtDataNascimento, labelFont, labelColor);
        addFormField(formPanel, "Telefone:", txtTelefone, labelFont, labelColor);
        addFormField(formPanel, "Logradouro:", txtLogradouro, labelFont, labelColor);
        addFormField(formPanel, "Bairro:", txtBairro, labelFont, labelColor);
        addFormField(formPanel, "CEP:", txtCep, labelFont, labelColor);
        addFormField(formPanel, "Cidade:", txtCidade, labelFont, labelColor);
        addFormField(formPanel, "UF:", txtUf, labelFont, labelColor);
        addFormField(formPanel, "Fidelidade:", chkFidelidade, labelFont, labelColor);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);

        btnCadastrar = createStyledButton("Cadastrar", new Color(255, 102, 0));
        btnVoltar = createStyledButton("Voltar", new Color(51, 51, 51));

        btnCadastrar.addActionListener((ActionEvent e) -> cadastrarUsuario());
        btnVoltar.addActionListener((ActionEvent e) -> {
            new Login().setVisible(true);
            dispose();
        });

        buttonPanel.add(btnCadastrar);
        buttonPanel.add(btnVoltar);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private JTextField createStyledTextField(Font font) {
        JTextField field = new JTextField();
        field.setFont(font);
        field.setPreferredSize(new Dimension(200, 30));
        return field;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void addFormField(JPanel panel, String labelText, javax.swing.JComponent field, Font labelFont, Color labelColor) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(labelColor);
        panel.add(label);
        panel.add(field);
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

        if (nome.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos Nome, CPF e Data de Nascimento.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (uf.length() != 2) {
            JOptionPane.showMessageDialog(this, "O campo UF deve conter exatamente 2 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

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