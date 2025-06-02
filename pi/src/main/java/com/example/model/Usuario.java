package com.example.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String telefone;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    public static List<Usuario> usuarios = new ArrayList<>(); // LISTA PARA ARMAZENAR USUARIOS CADASTRADOS

    public Usuario(String nome, String cpf, String dataNascimento, String telefone, String logradouro, String bairro, String cep, String cidade, String uf) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Usuario() {
        // Construtor vazio
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

public String salvar() {

    try {
        FileWriter fw = new FileWriter("usuarios.txt", true);
        PrintWriter pw = new PrintWriter(fw);

        String cpfSemMascara = cpf.replaceAll("[^\\d]", "");

        pw.print(nome + ";" + cpfSemMascara + ";" + dataNascimento + ";" + telefone + ";" + logradouro + ";" + bairro + ";" + cep + ";" + cidade + ";" + uf + "\n");

        pw.flush();
        pw.close();
        fw.close();

        

    } catch (IOException ex) {
        Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
    }

    return "Cadastrado com sucesso";
}


    public boolean validarCPF(String cpf) {

        if (cpf == null || cpf.isEmpty()) {

            return false;

        }

        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {

            return false;
        }

        try {

            // calcular o primeiro digito verificador:
            int soma = 0;

            for (int i = 0; i < 9; i++) {

                soma += (cpf.charAt(i) - '0') * (10 - i);

            }

            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) {

                primeiroDigito = 0;

            }

            // calcular o segundo digito verificador:
            soma = 0;

            for (int i = 0; i < 10; i++) {

                soma += (cpf.charAt(i) - '0') * (11 - i);

            }

            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) {

                segundoDigito = 0;

            }
            return primeiroDigito == (cpf.charAt(9) - '0') && segundoDigito == (cpf.charAt(10) - '0');

        } catch (Exception e) {

            return false;

        }

    }

    public void cadastraUsuario(Usuario novoUsuario) {

        String cpfSemMascara = novoUsuario.getCpf().replaceAll("[^\\d]", "");

        if (validarCPF(cpfSemMascara)) {
            for (Usuario usuario : usuarios) {
                if (usuario.getCpf().equals(cpfSemMascara)) {
                    throw new IllegalArgumentException("CPF já cadastrado: " + cpfSemMascara);
                }
            }
            novoUsuario.setCpf(cpfSemMascara); // Salvar o CPF sem máscara
            usuarios.add(novoUsuario);
        } else {
            throw new IllegalArgumentException("CPF inválido: " + cpfSemMascara);
        }
    }

    public static List<Usuario> carregarUsuarios() {
    List<Usuario> lista = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\danie\\OneDrive\\Documentos\\TeatroAbc2025V1\\usuarios.txt"))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            if (dados.length == 9) {
                Usuario u = new Usuario(
                    dados[0], 
                    dados[1],
                    dados[2], 
                    dados[3], 
                    dados[4], 
                    dados[5], 
                    dados[6], 
                    dados[7], 
                    dados[8]  
                );
                lista.add(u);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return lista;
}

}
