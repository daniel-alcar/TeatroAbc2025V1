package com.example.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class LeitorUsuario {

    public static DefaultTableModel lerArquivo(String usuarios) throws IOException {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] colunas = {"Nome", "CPF", "Data de Nascimento", "Telefone", "Logradouro", "Bairro", "Cidade", "CEP", "UF"};
        modelo.setColumnIdentifiers(colunas);

        try (BufferedReader br = new BufferedReader(new FileReader("TeatroAbc2025V1\\usuarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                modelo.addRow(dados);
            }
        }
        return modelo;
    }
}
