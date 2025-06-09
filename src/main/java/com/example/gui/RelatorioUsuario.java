package com.example.gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.example.dao.EstatisticaDAO;
import com.example.dao.IEstatisticaDAO;
import com.example.util.ConexaoBanco;

public class RelatorioUsuario extends JFrame {

    private JTable tabelaEstatisticas;

    public RelatorioUsuario() {
        super("Relatório de Estatísticas do Teatro");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal com imagem de fundo
        JPanel painelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon icon = new ImageIcon(getClass().getResource("/Icons/theater-bg.jpg"));
                    if (icon.getImage() != null) {
                        g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                    }
                } catch (Exception e) {
                    g.setColor(new Color(240, 240, 240));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Painel de conteúdo com efeito de vidro fosco
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        
        JPanel glassPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
                g2d.setColor(new Color(255, 255, 255, 220));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2d.dispose();
            }
        };
        glassPanel.setOpaque(false);
        glassPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        glassPanel.add(criarCabecalho("Estatísticas do Teatro", "/icons/stats.png"), BorderLayout.NORTH);
        glassPanel.add(new JScrollPane(criarTabelaEstatisticas()), BorderLayout.CENTER);
        
        contentPanel.add(glassPanel, BorderLayout.CENTER);
        painelPrincipal.add(contentPanel, BorderLayout.CENTER);
        
        add(painelPrincipal);
    }

    private JTable criarTabelaEstatisticas() {
        String[] colunas = {"Indicador", "Valor"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try (Connection conexao = ConexaoBanco.getConnection()) {
            IEstatisticaDAO dao = new EstatisticaDAO(conexao);

            // Adiciona os dados ao modelo
            String[] maisVendida = dao.pecaMaisV();
            modelo.addRow(new String[]{"Peça mais vendida", maisVendida[0] + " (" + maisVendida[1] + " ingressos)"});

            String[] menosVendida = dao.pecaMenosV();
            modelo.addRow(new String[]{"Peça menos vendida", menosVendida[0] + " (" + menosVendida[1] + " ingressos)"});

            String[] sessaoMaisOcupada = dao.sessaoMaisOcupada();
            modelo.addRow(new String[]{"Sessão mais ocupada", sessaoMaisOcupada[0] + " (" + sessaoMaisOcupada[1] + " sessões)"});

            String[] sessaoMenosOcupada = dao.sessaoMenosOcupada();
            modelo.addRow(new String[]{"Sessão menos ocupada", sessaoMenosOcupada[0] + " (" + sessaoMenosOcupada[1] + " sessões)"});

            String[] pecaMaisLucrativa = dao.pecaMaisLucrativa();
            modelo.addRow(new String[]{"Peça mais lucrativa", pecaMaisLucrativa[0] + " (R$ " + pecaMaisLucrativa[1] + ")"});

            String[] pecaMenosLucrativa = dao.pecaMenosLucrativa();
            modelo.addRow(new String[]{"Peça menos lucrativa", pecaMenosLucrativa[0] + " (R$ " + pecaMenosLucrativa[1] + ")"});

            String[] faturamentoMedio = dao.faturamentoMedio();
            modelo.addRow(new String[]{"Faturamento médio (R$)", "R$ " + faturamentoMedio[1]});

        } catch (Exception e) {
            mostrarErro("Erro ao carregar estatísticas: " + e.getMessage());
        }

        tabelaEstatisticas = new JTable(modelo);
        
        // Estilização avançada da tabela
        tabelaEstatisticas.setRowHeight(35);
        tabelaEstatisticas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tabelaEstatisticas.setShowGrid(false);
        tabelaEstatisticas.setIntercellSpacing(new Dimension(0, 5));
        
        // Estilo do cabeçalho
        JTableHeader header = tabelaEstatisticas.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Renderizador personalizado
        tabelaEstatisticas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, 
                    isSelected, hasFocus, row, column);
                
                c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : Color.WHITE);
                if (column == 1) {
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                    if (value.toString().contains("R$")) {
                        c.setForeground(new Color(0, 100, 0)); // Verde para valores monetários
                    }
                }
                setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
                return c;
            }
        });
        
        return tabelaEstatisticas;
    }

    private JPanel criarCabecalho(String titulo, String iconePath) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setOpaque(false);
        
        // Painel com gradiente
        JPanel gradientePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(255, 102, 0), 
                    getWidth(), 0, new Color(220, 60, 0));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        gradientePanel.setLayout(new BorderLayout());
        gradientePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        
        JLabel icone = new JLabel();
        try {
            ImageIcon imagem = new ImageIcon(getClass().getResource(iconePath));
            if (imagem.getImage() != null) {
                Image imgRedimensionada = imagem.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
                icone.setIcon(new ImageIcon(imgRedimensionada));
            }
        } catch (Exception e) {
            // Ignora se o ícone não carregar
        }
        
        gradientePanel.add(label, BorderLayout.CENTER);
        gradientePanel.add(icone, BorderLayout.EAST);
        
        painel.add(gradientePanel, BorderLayout.CENTER);
        return painel;
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RelatorioUsuario frame = new RelatorioUsuario();
            frame.setVisible(true);
            
        });
    }
}