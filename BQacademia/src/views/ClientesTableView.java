package views;

import models.Clientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientesTableView extends JFrame {
    private JTable table; // a tabela que exibirá os dados dos clientes
    private DefaultTableModel tableModel; // o modelo de dados da tabela

    // construtor que inicializa a janela com o título
    public ClientesTableView() {
        super("Gerenciamento de Clientes");
        initializeComponents(); // chama o método para configurar os componentes da interface
    }

    // método que inicializa e configura todos os componentes gráficos
    private void initializeComponents() {
        // define os nomes das colunas da tabela
        String[] columnNames = {"ID", "Nome", "CPF", "DataNasc", "Telefone", "Email", "Endereco", "DataCadastro", "Ativo"};
        // cria o modelo da tabela com os nomes das colunas
        tableModel = new DefaultTableModel(columnNames, 0);
        // cria a tabela com o modelo
        table = new JTable(tableModel);
        // adiciona a tabela a um painel de rolagem
        JScrollPane scrollPane = new JScrollPane(table);

        // define uma borda ao redor da área de rolagem
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // define o layout do JFrame e adiciona o painel de rolagem no centro
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        // define o tamanho da janela, comportamento de fechamento e posição centralizada
        this.setSize(800, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // janela centralizada
    }

    // método que atualiza a tabela com uma lista de clientes
    public void atualizarTabela(List<Clientes> clientes) {
        // limpa todas as linhas da tabela antes de adicionar novas
        tableModel.setRowCount(0);
        // percorre a lista de clientes e adiciona cada um à tabela
        for (Clientes cliente : clientes) {
            Object[] row = {
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getDataNasc(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getEndereco(),
                cliente.getDataCadastro(),
                cliente.isAtivo(),
            };
            // adiciona a linha com os dados do cliente
            tableModel.addRow(row);
        }
    }

    // método que retorna o ID do cliente selecionado na tabela
    public int getSelectedClienteId() {
        int selectedRow = table.getSelectedRow(); // pega a linha selecionada
        if (selectedRow != -1) { // se houver uma linha selecionada
            return (int) tableModel.getValueAt(selectedRow, 0); // retorna o ID do cliente na primeira coluna
        }
        return -1; // retorna -1 se nenhuma linha foi selecionada
    }

    // método que adiciona uma barra de ferramentas (JToolBar) ao topo da janela
    public void addToolBar(JToolBar toolBar) {
        this.add(toolBar, BorderLayout.NORTH); // adiciona a barra de ferramentas no topo (NORTH)
    }
}
