package com.example.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Usuario;

public class UsuarioDAOArquivo implements IUsuarioDAO {

    private static final String CAMINHO_ARQUIVO = "TeatroAbc2025V1\\usuarios.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void salvar(Usuario usuario) throws IOException {
        try (FileWriter fw = new FileWriter(CAMINHO_ARQUIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(String.join(";",
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getDataNascimento().format(FORMATTER),
                usuario.getTelefone(),
                usuario.getLogradouro(),
                usuario.getBairro(),
                usuario.getCep(),
                usuario.getCidade(),
                usuario.getUf()
            ));
        }
    }

    @Override
    public Usuario buscarPorCPF(String cpf) throws Exception {
        if (cpf == null || cpf.trim().isEmpty()) return null;

        String cpfLimpo = cpf.replaceAll("[^\\d]", "");

        for (Usuario usuario : carregarTodos()) {
            if (usuario.getCpf() != null &&
                usuario.getCpf().replaceAll("[^\\d]", "").equals(cpfLimpo)) {
                return usuario;
            }
        }

        return null; 
    }

    @Override
    public List<Usuario> carregarTodos() {
        List<Usuario> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 9) {
                    LocalDate dataNascimento = LocalDate.parse(dados[2], FORMATTER);
                    Usuario usuario = new Usuario(
                        dados[0],          // nome
                        dados[1],          // cpf
                        dataNascimento,    // dataNascimento
                        dados[3],          // telefone
                        dados[4],          // logradouro
                        dados[5],          // bairro
                        dados[6],          // cep
                        dados[7],          // cidade
                        dados[8]           // uf
                    );
                    lista.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return lista;
    }

    @Override
    public boolean cpfExiste(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) return false;

        String cpfLimpo = cpf.replaceAll("[^\\d]", "");

        return carregarTodos().stream()
                .filter(u -> u.getCpf() != null)
                .anyMatch(u -> u.getCpf().replaceAll("[^\\d]", "").equals(cpfLimpo));
    }

    @Override
    public List<Usuario> buscarTodos() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodos'");
    }
}
