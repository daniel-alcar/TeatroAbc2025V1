package com.example.dao;

import com.example.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VendaIngressoDAOBanco implements IVendaIngressoDAO {

    private final Connection connection;

    public VendaIngressoDAOBanco(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(VendaIngresso venda) throws Exception {
        String sql = "INSERT INTO venda_ingresso (USUARIO_CPF, AREA_ID, POLTRONA, PECA_ID, SESSAO_ID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, venda.getUsuario().getCpf());
            stmt.setInt(2, venda.getArea().getId());
            stmt.setInt(3, venda.getPoltrona());
            stmt.setInt(4, venda.getPeca().getId());
            stmt.setInt(5, venda.getSessao().getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<VendaIngresso> buscarTodas() throws Exception {
        List<VendaIngresso> vendas = new ArrayList<>();
        String sql = "SELECT * FROM VENDA_INGRESSO";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                VendaIngresso venda = reconstruirVenda(rs);
                vendas.add(venda);
            }
        }

        return vendas;
    }

    @Override
    public List<VendaIngresso> buscarPorCPF(String cpf) throws Exception {
        List<VendaIngresso> vendas = new ArrayList<>();
        String sql = "SELECT * FROM VENDA_INGRESSO WHERE USUARIO_CPF = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VendaIngresso venda = reconstruirVenda(rs);
                    vendas.add(venda);
                }
            }
        }

        return vendas;
    }

    private VendaIngresso reconstruirVenda(ResultSet rs) throws Exception {
        IUsuarioDAO usuarioDAO = new UsuarioDAOBanco(connection);
        AreaDAOBanco areaDAO = new AreaDAOBanco(connection);
        PecaDAOBanco pecaDAO = new PecaDAOBanco(connection);
        SessaoDAOBanco sessaoDAO = new SessaoDAOBanco(connection);

        Usuario usuario = usuarioDAO.buscarPorCPF(rs.getString("usuario_cpf"));
        Area area = areaDAO.buscarPorId(rs.getInt("area_id"));
        Peca peca = pecaDAO.buscarPorId(rs.getInt("peca_id"));
        Sessao sessao = sessaoDAO.buscarPorId(rs.getInt("sessao_id"));
        int poltrona = rs.getInt("poltrona");

        return new VendaIngresso(usuario, area, poltrona, peca, sessao);
    }
}
