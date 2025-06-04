package com.example.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CarregarReservas {
    
    public static Map<String, Map<String, Map<Integer, String>>> carregarReservasDeArquivo(String caminhoArquivo) {
        Map<String, Map<String, Map<Integer, String>>> reservas = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                // Supondo formato: sessao|area|poltrona|cpf
                String[] partes = linha.split("\\|");
                if (partes.length != 4) continue; // linha inválida

                String sessao = partes[0];
                String area = partes[1];
                Integer poltrona = Integer.parseInt(partes[2]);
                String cpf = partes[3];

                reservas.putIfAbsent(sessao, new HashMap<>());
                Map<String, Map<Integer, String>> mapaAreas = reservas.get(sessao);

                mapaAreas.putIfAbsent(area, new HashMap<>());
                Map<Integer, String> poltronas = mapaAreas.get(area);

                poltronas.put(poltrona, cpf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reservas;
    }
}
