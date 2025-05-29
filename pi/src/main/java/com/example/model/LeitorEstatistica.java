package com.example.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class LeitorEstatistica {

    public static DefaultTableModel lerArquivo(String nomeArquivo) throws IOException {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] colunas = {
            "Peça com mais vendas", "Peça com menos vendas",
            "Sessão com maior ocupação", "Sessão com menor ocupação",
            "Peça mais lucrativa", "Peça menos lucrativa",
            "Lucro médio do Teatro"
        };
        modelo.setColumnIdentifiers(colunas);

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                modelo.addRow(dados);
            }
        }
        return modelo;
    }
}
