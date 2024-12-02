package controllers;

import models.Equipamento;
import repository.EquipamentoRepository;
import views.EquipamentoTableView;
import views.EquipamentoForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EquipamentoController {
        private EquipamentoRepository repository;
        private EquipamentoTableView tableView;

        public EquipamentoController() {
            repository = new EquipamentoRepository();
            tableView = new EquipamentoTableView();
            inicializar();
        }

        private void inicializar() {
            atualizarTabela(); // vai atualizar a tabela com os equipamentos existentes

            JToolBar toolBar = new JToolBar();
            toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS)); // Configura layout horizontal
            
            JButton adicionarButton = new JButton("Adicionar");
            JButton editarButton = new JButton("Editar");
            JButton deletarButton = new JButton("Deletar");
            
            // adiciona um espaço flexível antes dos botões (ajuda na centralização)
            toolBar.add(Box.createHorizontalGlue());
            
            // adiciona os botões com espaços fixos entre eles
            toolBar.add(adicionarButton);
            toolBar.add(Box.createHorizontalStrut(10)); // Espaço de 10px
            toolBar.add(editarButton);
            toolBar.add(Box.createHorizontalStrut(10)); // Espaço de 10px
            toolBar.add(deletarButton);
            
            // adiciona um espaço flexível depois dos botões (ajuda na centralização)
            toolBar.add(Box.createHorizontalGlue());
            

            tableView.add(toolBar, java.awt.BorderLayout.NORTH);

            // as ações dos botões declarados acima
            adicionarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarEquipamento();
                }
            });

            editarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editarEquipamento();
                }
            });

            deletarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deletarEquipamento();
                }
            });

            tableView.setVisible(true);
        }

        private void atualizarTabela() {
            List<Equipamento> equipamentos = repository.obterTodosEquipamentos();
            tableView.atualizarTabela(equipamentos);
        }

        private void adicionarEquipamento() {
            EquipamentoForm form = new EquipamentoForm(tableView, "Adicionar Equipamento");
            form.setVisible(true);
            Equipamento novoEquipamento = form.getEquipamento();
            if (novoEquipamento != null) {
                repository.adicionarEquipamento(novoEquipamento);
                atualizarTabela();
            }
        }

        private void editarEquipamento(){
            int selectedId = tableView.getSelectedEquipamentoId();
            if (selectedId != -1) {
                Equipamento equipamento = repository.obterEquipamentoPorId(selectedId);
                if (equipamento != null) {
                    EquipamentoForm form = new EquipamentoForm(tableView, 
                    "Editar Equipamento", equipamento); 
                form.setVisible(true);
                Equipamento equipamentoAtualizado = form.getEquipamento();
                if (equipamentoAtualizado != null) {
                    equipamentoAtualizado = new Equipamento(
                        selectedId,
                        equipamentoAtualizado.getNome(),
                        equipamentoAtualizado.getDescricao(),
                        equipamentoAtualizado.getQuantidadeDisponivel(),
                        equipamentoAtualizado.isAtivo()
                    );
                    repository.atualizarEquipamento(equipamentoAtualizado);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(tableView, 
                "Equipamento não encontrado.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tableView, 
                "Selecione um equipamento para editar",
                "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
        private void deletarEquipamento(){
            int selectedId = tableView.getSelectedEquipamentoId();
            if (selectedId != -1) {
                int confirm = JOptionPane.showConfirmDialog(tableView,
                "Tem certeza que deseja deletar este equipamento?",
                "Confirmar Deleção",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                repository.deletarEquipamento(selectedId);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(
                tableView,
                "Selecione um equipamento para deletar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
}
