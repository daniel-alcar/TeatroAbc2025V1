// DAO: ConsultaIngressoBanco.java
package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultaIngressoBanco implements IConsultaDAO{

    private final Connection connection;

    public ConsultaIngressoBanco(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public List<String> buscarIngressoCPF(String cpf) throws Exception {
        String sql = """
                SELECT 
                    U.NOME,
                    U.CPF,
                    P.TITULO AS PECA,
                    A.TITULO AS AREA,
                    V.POLTRONA
                FROM VENDAINGRESSO V
                JOIN USUARIOS U ON V.CPF_USUARIO = U.CPF
                JOIN PECA P ON V.PECA_ID = P.ID_PECA
                JOIN AREA A ON V.AREA_ID = A.ID_AREA
                WHERE U.CPF = ?;
                """;

        List<String> resultados = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String linha = String.format("Nome: %s | Peça: %s | Área: %s | Poltrona: %s",
                            rs.getString("NOME"),
                            rs.getString("PECA"),
                            rs.getString("AREA"),
                            rs.getInt("POLTRONA"));
                    resultados.add(linha);
                }
            }
        }
        return resultados;
    }
}
