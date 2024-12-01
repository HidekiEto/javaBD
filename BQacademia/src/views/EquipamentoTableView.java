package views;

import models.Equipamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EquipamentoTableView extends JFrame{
    private JTable table;
    private DefaultTableModel tableModel;

   public EquipamentoTableView(){
    super("Gerenciamento de Equipamentos");
    initializeComponents();
   }
   private void initializeComponents(){
            String[] columnNames = {"ID", "Nome", "Descrição", "Qtd_disp", "Status"};
            tableModel = new DefaultTableModel(columnNames, 0);
            JScrollPane scrollPane = new JScrollPane(table);

            scrollPane.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10));

                this.setLayout(new BorderLayout());
                this.add(scrollPane, BorderLayout.CENTER);

                this.setSize(600,400);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
   }
   public void atualizarTabela(List<Equipamento> equipamentos) {
            tableModel.setRowCount(0);
            for (Equipamento equipamento: equipamentos) {
                Object[] row = {
                    equipamento.getIdEquipamento(),
                    equipamento.getNome(),
                    equipamento.getDescricao(),
                    equipamento.getQuantidadeDisponivel(),
                    equipamento.isAtivo(),
                };
                tableModel.addRow(row);
            }
        }
    public int getSelectedEquipamentoId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }
}
