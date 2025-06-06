package com.example.dao;

import com.example.model.Area;
import com.example.model.Peca;
import com.example.model.Sessao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAOBanco implements ISessaoDAO {

    private final Connection connection;

    public SessaoDAOBanco(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Sessao sessao, int pecaId) throws Exception {
        String sql = "INSERT INTO SESSAO (PERIODO, PECA_ID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sessao.getPeriodo());
            stmt.setInt(2, pecaId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Sessao> buscarTodas() throws Exception {
        List<Sessao> sessoes = new ArrayList<>();
        String sql = "SELECT s.id, s.periodo, p.id AS peca_id, p.titulo FROM sessao s JOIN peca p ON s.peca_id = p.id";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int sessaoId = rs.getInt("id");
                String periodo = rs.getString("periodo");

                int pecaId = rs.getInt("peca_id");
                String tituloPeca = rs.getString("titulo");
                Peca peca = new Peca(pecaId, tituloPeca);

                Sessao sessao = new Sessao(sessaoId, periodo, peca, new ArrayList<Area>()); // Áreas vazias inicialmente
                sessoes.add(sessao);
            }
        }
        return sessoes;
    }

    @Override
    public Sessao buscarPorId(int id) throws Exception {
        String sql = "SELECT s.id, s.periodo, p.id AS peca_id, p.titulo FROM sessao s JOIN peca p ON s.peca_id = p.id WHERE s.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int sessaoId = rs.getInt("id");
                    String periodo = rs.getString("periodo");
                    int pecaId = rs.getInt("peca_id");
                    String tituloPeca = rs.getString("titulo");

                    Peca peca = new Peca(pecaId, tituloPeca);
                    return new Sessao(sessaoId, periodo, peca, new ArrayList<>());
                }
            }
        }
        return null;
    }
}
