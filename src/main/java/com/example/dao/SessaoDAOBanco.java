package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Area;
import com.example.model.Peca;
import com.example.model.Sessao;

public class SessaoDAOBanco implements ISessaoDAO {

    private final Connection connection;

    public SessaoDAOBanco(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Sessao sessao) throws Exception {
        String sql = "INSERT INTO SESSAO (PERIODO, PECA_ID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sessao.getPeriodo());
            stmt.setInt(2, sessao.getPeca().getId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    sessao.setId(rs.getInt(1)); // ID gerado automaticamente
                }
            }
        }
    }

    @Override
    public List<Sessao> buscarTodas() throws Exception {
        List<Sessao> sessoes = new ArrayList<>();
        String sql = "SELECT S.ID_SESSAO, S.PERIODO, S.PECA_ID, P.TITULO " +
                     "FROM SESSAO S " +
                     "JOIN PECA P ON S.PECA_ID = P.ID_PECA";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int sessaoId = rs.getInt("ID_SESSAO");
                String periodo = rs.getString("PERIODO");
                int pecaId = rs.getInt("PECA_ID");
                String tituloPeca = rs.getString("TITULO");

                Peca peca = new Peca(pecaId, tituloPeca);

                // Buscar áreas associadas à sessão
                List<Area> areas = buscarAreasPorSessao(sessaoId);

                Sessao sessao = new Sessao(sessaoId, periodo, peca, areas);
                sessoes.add(sessao);
            }
        }
        return sessoes;
    }

    @Override
    public Sessao buscarPorId(int id) throws Exception {
        String sql = "SELECT S.ID_SESSAO, S.PERIODO, S.PECA_ID, P.TITULO " +
                     "FROM SESSAO S " +
                     "JOIN PECA P ON S.PECA_ID = P.ID_PECA " +
                     "WHERE S.ID_SESSAO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int sessaoId = rs.getInt("ID_SESSAO");
                    String periodo = rs.getString("PERIODO");
                    int pecaId = rs.getInt("PECA_ID");
                    String tituloPeca = rs.getString("TITULO");

                    Peca peca = new Peca(pecaId, tituloPeca);

                    List<Area> areas = buscarAreasPorSessao(sessaoId);

                    return new Sessao(sessaoId, periodo, peca, areas);
                }
            }
        }
        return null;
    }

    // Método auxiliar para buscar as áreas associadas a uma sessão
    private List<Area> buscarAreasPorSessao(int sessaoId) throws Exception {
        List<Area> areas = new ArrayList<>();
        String sql = "SELECT A.ID_AREA, A.TITULO, A.CAPACIDADE, A.PRECO " +
                    "FROM AREA A " +
                    "JOIN SESSAO_AREA SA ON A.ID_AREA = SA.AREA_ID " +
                    "WHERE SA.SESSAO_ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sessaoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                        String TITULO = rs.getString("TITULO");
                        int CAPACIDADE = rs.getInt("CAPACIDADE");
                        double PRECO = rs.getDouble("PRECO");
                    Area area = new Area(TITULO, CAPACIDADE, PRECO);
                    area.setId(rs.getInt("ID_AREA"));
                    areas.add(area);
                }
            }
        }
        return areas;
    }

}
