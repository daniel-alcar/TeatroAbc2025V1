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
        String sql = "INSERT INTO area (titulo, capacidade_maxima, preco) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, area.getTitulo());
            stmt.setInt(2, area.getCapacidadeMaxima());
            stmt.setDouble(3, area.getPreco());
            stmt.executeUpdate();
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
                        rs.getInt("capacidade_maxima"),
                        rs.getDouble("preco")
                );
                area.setId(rs.getInt("id")); // se sua classe Area tiver o campo id
                area.setPoltronas(new HashMap<>()); // inicializa vazio
                areas.add(area);
            }
        }
        return areas;
    }

    @Override
    public Area buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM area WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Area area = new Area(
                            rs.getString("titulo"),
                            rs.getInt("capacidade_maxima"),
                            rs.getDouble("preco")
                    );
                    area.setId(id); // novamente, se tiver ID
                    area.setPoltronas(new HashMap<>());
                    return area;
                }
            }
        }
        return null;
    }
}
