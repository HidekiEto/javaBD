package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import models.Clientes;
import repository.ClientesRepository;
import views.ClientesForm;
import views.ClientesTableView;

public class ClientesController {
    private ClientesRepository repository;
    private ClientesTableView tableView;

    public ClientesController(ClientesRepository repository){
        this.repository = repository;
        tableView = new ClientesTableView();
        inicializar();
    }
    private void inicializar(){
        atualizarTabela();

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
                adicionarCliente();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarCliente();
            }
        });

        tableView.setVisible(true);
    }
    private void atualizarTabela() {
            List<Clientes> clientes = repository.obterTodosClientes();
            tableView.atualizarTabela(clientes);
        }

        private void adicionarCliente() {
            ClientesForm form = new ClientesForm(tableView, "Adicionar Cliente");
            form.setVisible(true);
            Clientes novoCliente = form.getCliente();
            if (novoCliente != null) {
                repository.adicionarCliente(novoCliente);
                atualizarTabela();
            }
        }

        private void editarCliente(){
            int selectedId = tableView.getSelectedClienteId();
            if (selectedId != -1) {
                Clientes cliente = repository.obterClientePorId(selectedId);
                if (cliente != null) {
                    ClientesForm form = new ClientesForm(tableView, 
                    "Editar Cliente", cliente); 
                form.setVisible(true);
                Clientes clienteAtualizado = form.getCliente();
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
                    repository.atualizarCliente(clienteAtualizado);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(tableView, 
                "Cliente não encontrado.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tableView, 
                "Selecione um cliente para editar",
                "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

        private void deletarCliente(){
            int selectedId = tableView.getSelectedClienteId();
            if (selectedId != -1) {
                int confirm = JOptionPane.showConfirmDialog(tableView,
                "Tem certeza que deseja deletar este cliente?",
                "Confirmar Deleção",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                repository.deletarCliente(selectedId);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(
                tableView,
                "Selecione um cliente para deletar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
