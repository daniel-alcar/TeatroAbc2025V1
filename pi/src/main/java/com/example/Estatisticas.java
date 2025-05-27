package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estatisticas {

    private List<VendaIngresso> ingressosVendidos;

    public Estatisticas(List<VendaIngresso> ingressosVendidos) {
        this.ingressosVendidos = ingressosVendidos;
    }

    public Estatisticas() {
        this.ingressosVendidos = new ArrayList<>();
    }

    public Peca getPecaMaisVendida() {
        Map<Peca, Integer> vendasPorPeca = new HashMap<>();

        for (VendaIngresso ingresso : ingressosVendidos) {
            Peca peca = (Peca) ingresso.getPeca();
            vendasPorPeca.put(peca, vendasPorPeca.getOrDefault(peca, 0) + 1);
        }

        return Collections.max(vendasPorPeca.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public Peca getPecaMenosVendida() {
        Map<Peca, Integer> vendasPorPeca = new HashMap<>();

        for (VendaIngresso ingresso : ingressosVendidos) {
            Peca peca = (Peca) ingresso.getPeca();
            vendasPorPeca.put(peca, vendasPorPeca.getOrDefault(peca, 0) + 1);
        }

        return Collections.min(vendasPorPeca.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public Sessao getSessaoMaiorOcupacao() {
        Map<Sessao, Integer> ocupacaoPorSessao = new HashMap<>();

        for (VendaIngresso ingresso : ingressosVendidos) {
            Sessao sessao = ingresso.getSessao();
            ocupacaoPorSessao.put(sessao, sessao.coletaQtdPoltronasReservadas());
        }

        return Collections.max(ocupacaoPorSessao.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public Sessao getSessaoMenorOcupacao() {
        Map<Sessao, Integer> ocupacaoPorSessao = new HashMap<>();

        for (VendaIngresso ingresso : ingressosVendidos) {
            Sessao sessao = ingresso.getSessao();
            ocupacaoPorSessao.put(sessao, sessao.coletaQtdPoltronasReservadas());
        }

        return Collections.min(ocupacaoPorSessao.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public Peca getPecaMaisLucrativa() {
        Map<Peca, Double> lucroPorPeca = new HashMap<>();

        for (VendaIngresso ingresso : ingressosVendidos) {
            Peca peca = (Peca) ingresso.getPeca();
            Area area = (Area) ingresso.getArea();
            lucroPorPeca.put(peca, lucroPorPeca.getOrDefault(peca, 0.0) + area.getPreco());
        }

        return Collections.max(lucroPorPeca.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public Peca getPecaMenosLucrativa() {
        Map<Peca, Double> lucroPorPeca = new HashMap<>();

        for (VendaIngresso ingresso : ingressosVendidos) {
            Peca peca = (Peca) ingresso.getPeca();
            Area area = (Area) ingresso.getArea();
            lucroPorPeca.put(peca, lucroPorPeca.getOrDefault(peca, 0.0) + area.getPreco());
        }

        return Collections.min(lucroPorPeca.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public double getLucroMedioPorPeca() {
        Map<Peca, Double> lucroPorPeca = new HashMap<>();

        for (VendaIngresso ingresso : ingressosVendidos) {
            Peca peca = (Peca) ingresso.getPeca();
            Area area = (Area) ingresso.getArea();
            lucroPorPeca.put(peca, lucroPorPeca.getOrDefault(peca, 0.0) + area.getPreco());
        }

        double totalLucro = lucroPorPeca.values().stream().mapToDouble(Double::doubleValue).sum();
        return lucroPorPeca.isEmpty() ? 0.0 : totalLucro / lucroPorPeca.size();
    }

    public void exibirEstatisticas() {
        File estatisticasDados = new File("estatisticas.txt");

        try {
            if (!estatisticasDados.exists()) {
                estatisticasDados.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Arquivo não pode ser criado");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(estatisticasDados))) {
        
            bw.write(getPecaMaisVendida()+ ";");
            bw.write(getPecaMenosVendida()+ ";");
            bw.write(getSessaoMaiorOcupacao()+ ";");
            bw.write(getSessaoMenorOcupacao() + ";");
            bw.write(getPecaMaisLucrativa()+ ";");
            bw.write(getPecaMenosLucrativa()+ ";");
            bw.write(String.format("%.2f", getLucroMedioPorPeca())+ ";");

            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}