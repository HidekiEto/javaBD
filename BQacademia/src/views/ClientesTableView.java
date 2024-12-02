package views;

import models.Clientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientesTableView extends JFrame{
    private JTable table;
    private DefaultTableModel tableModel;

   public ClientesTableView(){
    super("Gerenciamento de Clientes");
    initializeComponents();
   }
   private void initializeComponents(){
            String[] columnNames = {"ID", "Nome", "CPF", "DataNasc", "Telefone", "Email", "Endereco", "DataCadastro", "Ativo"};
            tableModel = new DefaultTableModel(columnNames, 0);
            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);

            scrollPane.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10));

                this.setLayout(new BorderLayout());
                this.add(scrollPane, BorderLayout.CENTER);

                this.setSize(800,400);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
   }
   public void atualizarTabela(List<Clientes> clientes) {
            tableModel.setRowCount(0);
            for (Clientes cliente: clientes) {
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
                tableModel.addRow(row);
            }
        }
    public int getSelectedClienteId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }
    public void addToolBar(JToolBar toolBar) {
        this.add(toolBar, BorderLayout.NORTH);
    }
}