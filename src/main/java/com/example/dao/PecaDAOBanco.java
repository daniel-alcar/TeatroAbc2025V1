package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Peca;

public class PecaDAOBanco implements IPecaDAO {

    private final Connection connection;

    public PecaDAOBanco(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Peca peca) throws Exception {
        String sql = "INSERT INTO PECA (titulo) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, peca.getTituloPeca()); // ajuste conforme o nome do getter da model
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    peca.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public List<Peca> buscarTodas() throws Exception {
        List<Peca> pecas = new ArrayList<>();
        String sql = "SELECT * FROM peca";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Peca peca = new Peca(rs.getInt("ID_PECA"), rs.getString("titulo"));
                pecas.add(peca);
            }
        }
        return pecas;
    }

    @Override
    public Peca buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM PECA WHERE id_PECA = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Peca(rs.getInt("id_PECA"), rs.getString("titulo"));
                }
            }
        }
        return null;
    }
}
