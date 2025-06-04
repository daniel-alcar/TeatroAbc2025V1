package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Usuario;

public class UsuarioDAOBanco implements IUsuarioDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/TEATRO_ABC";
    private static final String USUARIO = "root";
    private static final String SENHA = "08072002";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    @Override
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (NOME, CPF, DATANASCIMENTO, TELEFONE, LOGRADOURO, BAIRRO, CEP, CIDADE, UF) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf().replaceAll("[^\\d]", ""));

            LocalDate dataNasc = usuario.getDataNascimento();
            if (dataNasc != null) {
                stmt.setDate(3, Date.valueOf(dataNasc));
            } else {
                stmt.setDate(3, null);
            }

            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getLogradouro());
            stmt.setString(6, usuario.getBairro());
            stmt.setString(7, usuario.getCep());
            stmt.setString(8, usuario.getCidade());
            stmt.setString(9, usuario.getUf());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário no banco de dados", e);
        }
    }

    @Override
    public boolean cpfExiste(String cpf) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE cpf = ?";
        String cpfLimpo = cpf.replaceAll("[^\\d]", "");

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpfLimpo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar CPF no banco de dados", e);
        }

        return false;
    }

    @Override
    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getString("NOME"),
                    rs.getString("CPF"),
                    rs.getDate("DATANASCIMENTO") != null ? rs.getDate("DATANASCIMENTO").toLocalDate() : null,
                    rs.getString("TELEFONE"),
                    rs.getString("LOGRADOURO"),
                    rs.getString("BAIRRO"),
                    rs.getString("CEP"),
                    rs.getString("CIDADE"),
                    rs.getString("UF")
                );
                usuarios.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar usuários do banco de dados", e);
        }

        return usuarios;
    }

    @Override
    public List<Usuario> carregarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
