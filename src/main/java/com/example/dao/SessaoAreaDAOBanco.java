package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SessaoAreaDAOBanco implements ISessaoAreaDAO {

    private final Connection connection;

    public SessaoAreaDAOBanco(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void vincularAreaASessao(int sessaoId, int areaId) throws Exception {
        String sql = "INSERT INTO sessao_area (sessao_id, area_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sessaoId);
            stmt.setInt(2, areaId);
            stmt.executeUpdate();
        }
    }
}