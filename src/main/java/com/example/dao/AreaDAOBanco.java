package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.model.Area;

public class AreaDAOBanco implements IAreaDAO {

    private final Connection connection;

    public AreaDAOBanco(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Area area) throws Exception {
        String sql = "INSERT INTO AREA (titulo, capacidade, preco) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, area.getTitulo());
            stmt.setInt(2, area.getCapacidadeMaxima());
            stmt.setDouble(3, area.getPreco());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    area.setId(rs.getInt("ID_AREA"));
                }
            }
        }
    }

    @Override
    public List<Area> buscarTodas() throws Exception {
        List<Area> areas = new ArrayList<>();
        String sql = "SELECT * FROM area";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Area area = new Area(
                        rs.getString("titulo"),
                        rs.getInt("capacidade"),
                        rs.getDouble("preco")
                );
                area.setId(rs.getInt("ID_AREA"));
                area.setPoltronas(new HashMap<>());
                areas.add(area);
            }
        }
        return areas;
    }

    @Override
    public Area buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM area WHERE ID_AREA = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Area area = new Area(
                            rs.getString("titulo"),
                            rs.getInt("capacidade"),
                            rs.getDouble("preco")
                    );
                    area.setId(rs.getInt("ID_AREA"));
                    area.setPoltronas(new HashMap<>());
                    return area;
                }
            }
        }
        return null;
    }
}
