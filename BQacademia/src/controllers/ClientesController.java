package controllers;
// define o pacote onde esta classe está localizada

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import models.Clientes;
import repository.ClientesRepository;
import views.ClientesForm;
import views.ClientesTableView;


public class ClientesController {
    private ClientesRepository repository; // repositório que lida com os dados dos clientes
    private ClientesTableView tableView; // interface para exibir a tabela de clientes

    public ClientesController() {
        repository = new ClientesRepository(); // inicializa o repositório de clientes
        tableView = new ClientesTableView(); // inicializa a interface gráfica da tabela
        inicializar(); // método que configura os elementos e eventos do controlador
    }

    private void inicializar() {
        atualizarTabela(); // carrega e exibe os clientes na tabela

        JToolBar toolBar = new JToolBar(); // barra de ferramentas para os botões
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS)); // organiza os botões horizontalmente

        JButton voltarButton = new JButton("Voltar"); // botão para voltar à tela principal
        JButton adicionarButton = new JButton("Adicionar"); // botão para adicionar um cliente
        JButton editarButton = new JButton("Editar"); // botão para editar um cliente selecionado
        JButton deletarButton = new JButton("Deletar"); // botão para deletar um cliente selecionado

        toolBar.add(voltarButton); // adiciona o botão "Voltar" à barra

        // adiciona espaço entre o botão "Voltar" e o resto
        toolBar.add(Box.createHorizontalStrut(230)); 

        toolBar.add(adicionarButton); // adiciona botão "Adicionar"
        toolBar.add(Box.createHorizontalStrut(10)); // espaço pequeno
        toolBar.add(editarButton); // adiciona botão "Editar"
        toolBar.add(Box.createHorizontalStrut(10)); // espaço pequeno
        toolBar.add(deletarButton); // adiciona botão "Deletar"

        // adiciona um espaço flexível para centralizar melhor os botões
        toolBar.add(Box.createHorizontalGlue());

        tableView.add(toolBar, java.awt.BorderLayout.NORTH); // coloca a barra de ferramentas acima da tabela

        // define o comportamento de cada botão:
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(voltarButton)).dispose(); // fecha a janela atual
                MainController controller = new MainController(); // cria o controlador principal
                controller.iniciar(); // inicia a tela principal
            }
        });

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente(); // chama o método para adicionar um cliente
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente(); // chama o método para editar um cliente selecionado
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarCliente(); // chama o método para deletar um cliente selecionado
            }
        });

        tableView.setVisible(true); // torna a tabela visível
    }

    private void atualizarTabela() {
        List<Clientes> clientes = repository.obterTodosClientes(); // obtém a lista de clientes do repositório
        tableView.atualizarTabela(clientes); // atualiza a tabela com os dados
    }

    private void adicionarCliente() {
        ClientesForm form = new ClientesForm(tableView, "Adicionar Cliente"); // abre o formulário de adição
        form.setVisible(true);
        Clientes novoCliente = form.getClientes(); // obtém os dados do novo cliente
        if (novoCliente != null) { // verifica se o cliente foi preenchido corretamente
            repository.adicionarCliente(novoCliente); // salva o cliente no repositório
            atualizarTabela(); // atualiza a tabela para exibir o cliente recém-adicionado
        }
    }

    private void editarCliente() {
        int selectedId = tableView.getSelectedClienteId(); // obtém o ID do cliente selecionado
        if (selectedId != -1) { // verifica se há um cliente selecionado
            Clientes cliente = repository.obterClientePorId(selectedId); // obtém os dados do cliente pelo ID
            if (cliente != null) {
                ClientesForm form = new ClientesForm(tableView, "Editar Cliente", cliente); // abre o formulário de edição
                form.setVisible(true);
                Clientes clienteAtualizado = form.getClientes(); // pega os novos dados do cliente
                if (clienteAtualizado != null) {
                    clienteAtualizado = new Clientes(
                        selectedId,
                        clienteAtualizado.getNome(),
                        clienteAtualizado.getCpf(),
                        clienteAtualizado.getDataNasc(),
                        clienteAtualizado.getTelefone(),
                        clienteAtualizado.getEmail(),
                        clienteAtualizado.getEndereco(),
                        clienteAtualizado.getDataCadastro(),
                        clienteAtualizado.isAtivo()
                    );
                    repository.atualizarCliente(clienteAtualizado); // atualiza os dados no repositório
                    atualizarTabela(); // atualiza a tabela para refletir as alterações
                }
            } else {
                JOptionPane.showMessageDialog(tableView, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tableView, "Selecione um cliente para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deletarCliente() {
        int selectedId = tableView.getSelectedClienteId(); // obtém o ID do cliente selecionado
        if (selectedId != -1) { // verifica se há um cliente selecionado
            int confirm = JOptionPane.showConfirmDialog(
                tableView,
                "Tem certeza que deseja deletar este cliente?",
                "Confirmar Deleção",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) { // verifica a confirmação do usuário
                repository.deletarCliente(selectedId); // deleta o cliente pelo ID
                atualizarTabela(); // atualiza a tabela para remover o cliente deletado
            }
        } else {
            JOptionPane.showMessageDialog(
                tableView,
                "Selecione um cliente para deletar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public void iniciar() {
        tableView.setVisible(true); // exibe a interface da tabela
    }
}
