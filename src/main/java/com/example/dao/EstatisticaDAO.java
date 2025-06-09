package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstatisticaDAO implements IEstatisticaDAO {

    private final Connection connection;
    
    public EstatisticaDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public String[] pecaMaisV() throws Exception {
        String sql = """
            SELECT 
                P.TITULO,
                COUNT(*) AS TOTAL
            FROM VENDAINGRESSO V
            JOIN PECA P ON V.PECA_ID = P.ID_PECA
            GROUP BY P.TITULO
            ORDER BY TOTAL DESC
            LIMIT 1;
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new String[] { rs.getString("TITULO"), String.valueOf(rs.getInt("TOTAL")) };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] pecaMenosV() throws Exception {
        String sql = """
            SELECT 
                P.TITULO,
                COUNT(*) AS TOTAL
            FROM VENDAINGRESSO V
            JOIN PECA P ON V.PECA_ID = P.ID_PECA
            GROUP BY P.TITULO
            ORDER BY TOTAL ASC
            LIMIT 1;
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new String[] { rs.getString("TITULO"), String.valueOf(rs.getInt("TOTAL")) };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] sessaoMaisOcupada() throws Exception {
        String sql = """
            SELECT 
                S.PERIODO,
                COUNT(*) AS SESSAO_TOTAL
            FROM VENDAINGRESSO V
            JOIN SESSAO S ON V.SESSAO_ID = S.ID_SESSAO
            GROUP BY S.PERIODO
            ORDER BY SESSAO_TOTAL DESC
            LIMIT 1;
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new String[] { rs.getString("PERIODO"), String.valueOf(rs.getInt("SESSAO_TOTAL")) };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] sessaoMenosOcupada() throws Exception {
        String sql = """
            SELECT 
                S.PERIODO,
                COUNT(*) AS SESSAO_TOTAL
            FROM VENDAINGRESSO V
            JOIN SESSAO S ON V.SESSAO_ID = S.ID_SESSAO
            GROUP BY S.PERIODO
            ORDER BY SESSAO_TOTAL ASC
            LIMIT 1;
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new String[] { rs.getString("PERIODO"), String.valueOf(rs.getInt("SESSAO_TOTAL")) };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] pecaMaisLucrativa() throws Exception {
        String sql = """
            SELECT 
                P.TITULO,
                SUM(A.PRECO) AS TOTAL
            FROM VENDAINGRESSO V
            JOIN PECA P ON V.PECA_ID = P.ID_PECA
            JOIN AREA A ON V.AREA_ID = A.ID_AREA
            GROUP BY P.TITULO
            ORDER BY TOTAL DESC
            LIMIT 1;
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new String[] { rs.getString("TITULO"), String.valueOf(rs.getDouble("TOTAL")) };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] pecaMenosLucrativa() throws Exception {
        String sql = """
            SELECT 
                P.TITULO,
                SUM(A.PRECO) AS TOTAL
            FROM VENDAINGRESSO V
            JOIN PECA P ON V.PECA_ID = P.ID_PECA
            JOIN AREA A ON V.AREA_ID = A.ID_AREA
            GROUP BY P.TITULO
            ORDER BY TOTAL ASC
            LIMIT 1;
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new String[] { rs.getString("TITULO"), String.valueOf(rs.getDouble("TOTAL")) };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] faturamentoMedio() throws Exception {
        String sql = """
            SELECT 
                COUNT(*) AS TOTAL_VENDAS,
                AVG(A.PRECO) AS FATURAMENTO_MEDIO
            FROM VENDAINGRESSO V
            JOIN AREA A ON V.AREA_ID = A.ID_AREA;
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new String[] { String.valueOf(rs.getInt("TOTAL_VENDAS")), String.format("%.2f", rs.getDouble("FATURAMENTO_MEDIO")) };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
