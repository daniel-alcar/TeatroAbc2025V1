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
import com.example.model.Usuario;
import com.example.model.VendaIngresso;

public class VendaIngressoDAOBanco implements IVendaIngressoDAO {

    private final Connection connection;
    private final IUsuarioDAO usuarioDAO;
    private final AreaDAOBanco areaDAO;
    private final PecaDAOBanco pecaDAO;
    private final SessaoDAOBanco sessaoDAO;

    public VendaIngressoDAOBanco(Connection connection) {
        this.connection = connection;
        this.usuarioDAO = new UsuarioDAOBanco(connection);
        this.areaDAO = new AreaDAOBanco(connection);
        this.pecaDAO = new PecaDAOBanco(connection);
        this.sessaoDAO = new SessaoDAOBanco(connection);
    }

    @Override
    public void salvar(VendaIngresso venda) throws Exception {

        // Validações para evitar erro de FK
        if (usuarioDAO.buscarPorCPF(venda.getUsuario().getCpf()) == null) {
            throw new IllegalArgumentException("Usuário não cadastrado no banco.");
        }
        if (areaDAO.buscarPorId(venda.getArea().getId()) == null) {
            throw new IllegalArgumentException("Área inválida.");
        }
        if (pecaDAO.buscarPorId(venda.getPeca().getId()) == null) {
            throw new IllegalArgumentException("Peça inválida.");
        }
        if (sessaoDAO.buscarPorId(venda.getSessao().getId()) == null) {
            throw new IllegalArgumentException("Sessão inválida.");
        }

        String sql = "INSERT INTO VENDAINGRESSO (CPF_USUARIO, AREA_ID, POLTRONA, PECA_ID, SESSAO_ID) VALUES (?, ?, ?, ?, ?)";
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
        String sql = "SELECT * FROM VENDAINGRESSO";

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
        String sql = "SELECT * FROM VENDAINGRESSO WHERE CPF_USUARIO = ?";

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
        Usuario usuario = usuarioDAO.buscarPorCPF(rs.getString("CPF_USUARIO"));
        Area area = areaDAO.buscarPorId(rs.getInt("AREA_ID"));
        Peca peca = pecaDAO.buscarPorId(rs.getInt("PECA_ID"));
        Sessao sessao = sessaoDAO.buscarPorId(rs.getInt("SESSAO_ID"));
        int poltrona = rs.getInt("POLTRONA");

        return new VendaIngresso(usuario, area, poltrona, peca, sessao);
    }
}