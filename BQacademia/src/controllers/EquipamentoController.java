package controllers;
// define o pacote onde a classe está

import models.Equipamento;
import repository.EquipamentoRepository;
import views.EquipamentoTableView;
import views.EquipamentoForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// a classe EquipamentoController é responsável por gerenciar as operações relacionadas aos equipamentos,
// conectando a interface gráfica (views) com a lógica de negócio (repository e models).
public class EquipamentoController {
    private EquipamentoRepository repository; // repositório que lida com os dados dos equipamentos
    private EquipamentoTableView tableView; // interface gráfica para exibir a tabela de equipamentos

    public EquipamentoController() {
        repository = new EquipamentoRepository(); // inicializa o repositório
        tableView = new EquipamentoTableView(); // inicializa a tabela de equipamentos
        inicializar(); // chama o método para configurar o controlador
    }

    private void inicializar() {
        atualizarTabela(); // carrega os dados dos equipamentos e exibe na tabela

        JToolBar toolBar = new JToolBar(); // cria uma barra de ferramentas para adicionar botões
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS)); // organiza os botões horizontalmente

        JButton voltarButton = new JButton("Voltar"); // botão para voltar à tela principal
        JButton adicionarButton = new JButton("Adicionar"); // botão para adicionar equipamentos
        JButton editarButton = new JButton("Editar"); // botão para editar o equipamento selecionado
        JButton deletarButton = new JButton("Deletar"); // botão para deletar o equipamento selecionado

        toolBar.add(voltarButton); // adiciona o botão "Voltar" à barra de ferramentas

        // adiciona um espaço fixo entre os botões para melhorar o layout
        toolBar.add(Box.createHorizontalStrut(140));

        toolBar.add(adicionarButton);
        toolBar.add(Box.createHorizontalStrut(10)); // espaçamento
        toolBar.add(editarButton); // adiciona o botão "Editar"
        toolBar.add(Box.createHorizontalStrut(10)); // espaçamento
        toolBar.add(deletarButton); 

        // adiciona um espaço flexível para centralizar os botões, se necessário
        toolBar.add(Box.createHorizontalGlue());

        tableView.add(toolBar, java.awt.BorderLayout.NORTH); // posiciona a barra acima da tabela

        // define as ações dos botões:
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fecha a janela atual e retorna à tela principal
                ((JFrame) SwingUtilities.getWindowAncestor(voltarButton)).dispose();
                MainController controller = new MainController();
                controller.iniciar();
            }
        });

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarEquipamento(); // chama o método para adicionar um equipamento
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarEquipamento(); // chama o método para editar um equipamento selecionado
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarEquipamento(); // chama o método para deletar um equipamento selecionado
            }
        });

        tableView.setVisible(true); // torna a tabela de equipamentos visível
    }

    private void atualizarTabela() {
        List<Equipamento> equipamentos = repository.obterTodosEquipamentos(); // busca todos os equipamentos no repositório
        tableView.atualizarTabela(equipamentos); // atualiza a tabela com os dados obtidos
    }

    private void adicionarEquipamento() {
        EquipamentoForm form = new EquipamentoForm(tableView, "Adicionar Equipamento"); // abre o formulário de adição
        form.setVisible(true);
        Equipamento novoEquipamento = form.getEquipamento(); // obtém os dados do novo equipamento
        if (novoEquipamento != null) { // verifica se o equipamento foi preenchido
            repository.adicionarEquipamento(novoEquipamento); // salva o equipamento no repositório
            atualizarTabela(); // atualiza a tabela para incluir o novo equipamento
        }
    }

    private void editarEquipamento() {
        int selectedId = tableView.getSelectedEquipamentoId(); // obtém o ID do equipamento selecionado
        if (selectedId != -1) { // verifica se há um equipamento selecionado
            Equipamento equipamento = repository.obterEquipamentoPorId(selectedId); // busca o equipamento pelo ID
            if (equipamento != null) {
                EquipamentoForm form = new EquipamentoForm(tableView, "Editar Equipamento", equipamento); // abre o formulário de edição
                form.setVisible(true);
                Equipamento equipamentoAtualizado = form.getEquipamento(); // pega os dados atualizados
                if (equipamentoAtualizado != null) {
                    equipamentoAtualizado = new Equipamento(
                            selectedId,
                            equipamentoAtualizado.getNome(),
                            equipamentoAtualizado.getDescricao(),
                            equipamentoAtualizado.getQuantidadeDisponivel(),
                            equipamentoAtualizado.isAtivo()
                    );
                    repository.atualizarEquipamento(equipamentoAtualizado); // atualiza o equipamento no repositório
                    atualizarTabela(); // reflete as alterações na tabela
                }
            } else {
                JOptionPane.showMessageDialog(tableView, "Equipamento não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tableView, "Selecione um equipamento para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deletarEquipamento() {
        int selectedId = tableView.getSelectedEquipamentoId(); // obtém o ID do equipamento selecionado
        if (selectedId != -1) { // verifica se há um equipamento selecionado
            int confirm = JOptionPane.showConfirmDialog(
                tableView,
                "Tem certeza que deseja deletar este equipamento?",
                "Confirmar Deleção",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) { // confirma a exclusão
                repository.deletarEquipamento(selectedId); // remove o equipamento do repositório
                atualizarTabela(); // atualiza a tabela para refletir a exclusão
            }
        } else {
            mostrarMensagem("Selecione um equipamento para deletar.", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarMensagem(String mensagem, int tipo) {
        JOptionPane.showMessageDialog(tableView, mensagem, "Aviso", tipo); // exibe uma mensagem genérica para o usuário
    }

    public void iniciar() {
        tableView.setVisible(true); // exibe a tabela de equipamentos
    }
}
