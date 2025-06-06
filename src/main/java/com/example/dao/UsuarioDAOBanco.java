package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Usuario;

public class UsuarioDAOBanco implements IUsuarioDAO {
    private final Connection connection;

    public UsuarioDAOBanco(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuarios (nome, cpf, DATANASCIMENTO, telefone, logradouro, bairro, cep, cidade, uf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getLogradouro());
            stmt.setString(6, usuario.getBairro());
            stmt.setString(7, usuario.getCep());
            stmt.setString(8, usuario.getCidade());
            stmt.setString(9, usuario.getUf());
            stmt.executeUpdate();
        }
    }

    @Override
    public Usuario buscarPorCPF(String cpf) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("DATANASCIMENTO").toLocalDate(),
                        rs.getString("telefone"),
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("cidade"),
                        rs.getString("uf")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<Usuario> carregarTodos() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("DATANASCIMENTO").toLocalDate(),
                    rs.getString("telefone"),
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cep"),
                    rs.getString("cidade"),
                    rs.getString("uf")
                ));
            }
        }
        return usuarios;
    }

    @Override
    public boolean cpfExiste(String cpf) throws Exception {
        String sql = "SELECT 1 FROM usuarios WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public List<Usuario> buscarTodos() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("DATANASCIMENTO").toLocalDate(),
                    rs.getString("telefone"),
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cep"),
                    rs.getString("cidade"),
                    rs.getString("uf")
                ));
            }
        }
        return usuarios;
    }

}
