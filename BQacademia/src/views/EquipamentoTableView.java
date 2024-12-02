package views; // define o pacote da classe

import models.Equipamento; // importa a classe Equipamento do pacote models

import javax.swing.*; // importa as classes do pacote javax.swing para GUI
import javax.swing.table.DefaultTableModel; // importa a classe DefaultTableModel para tabelas
import java.awt.*; // importa as classes de layout e componentes gráficos
import java.util.List; // importa a classe List para trabalhar com listas

public class EquipamentoTableView extends JFrame { // define a classe que herda de JFrame
    private JTable table; // tabela para exibição de dados
    private DefaultTableModel tableModel; // modelo da tabela para manipulação de dados

    public EquipamentoTableView() { // construtor da classe
        super("Gerenciamento de Equipamentos"); // define o título da janela
        initializeComponents(); // inicializa os componentes da interface
    }

    private void initializeComponents() { // método para inicializar os componentes
        String[] columnNames = {"ID", "Nome", "Descrição", "Qtd_disp", "Ativo"}; // define os nomes das colunas da tabela
        tableModel = new DefaultTableModel(columnNames, 0); // cria o modelo da tabela com as colunas definidas
        table = new JTable(tableModel); // cria a tabela com o modelo definido
        JScrollPane scrollPane = new JScrollPane(table); // adiciona a tabela em um painel com barra de rolagem

        scrollPane.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)); // define uma borda vazia ao redor da tabela

        this.setLayout(new BorderLayout()); // define o layout da janela como BorderLayout
        this.add(scrollPane, BorderLayout.CENTER); // adiciona o painel com a tabela ao centro da janela

        this.setSize(600, 400); // define o tamanho da janela
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // define a operação padrão de fechamento
        this.setLocationRelativeTo(null); // centraliza a janela na tela
    }

    public void atualizarTabela(List<Equipamento> equipamentos) { // método para atualizar os dados da tabela
        tableModel.setRowCount(0); // limpa todas as linhas da tabela
        for (Equipamento equipamento : equipamentos) { // itera sobre a lista de equipamentos
            Object[] row = { 
                equipamento.getIdEquipamento(), // obtém o ID do equipamento
                equipamento.getNome(), // obtém o nome do equipamento
                equipamento.getDescricao(), // obtém a descrição do equipamento
                equipamento.getQuantidadeDisponivel(), // obtém a quantidade disponível
                equipamento.isAtivo(), // obtém o status ativo/inativo
            };
            tableModel.addRow(row); // adiciona uma nova linha com os dados do equipamento
        }
    }

    public int getSelectedEquipamentoId() { // método para obter o ID do equipamento selecionado
        int selectedRow = table.getSelectedRow(); // obtém a linha selecionada na tabela
        if (selectedRow != -1) { // verifica se uma linha está selecionada
            return (int) tableModel.getValueAt(selectedRow, 0); // retorna o valor da primeira coluna (ID)
        }
        return -1; // retorna -1 se nenhuma linha estiver selecionada
    }

    public void addToolBar(JToolBar toolBar) { // método para adicionar uma barra de ferramentas à interface
        this.add(toolBar, BorderLayout.NORTH); // adiciona a barra de ferramentas na parte superior da janela
    }
}
