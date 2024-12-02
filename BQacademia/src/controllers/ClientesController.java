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

    public ClientesController(){
        repository = new ClientesRepository();
        tableView = new ClientesTableView();
        inicializar();
    }
    private void inicializar(){
        atualizarTabela();

        JToolBar toolBar = new JToolBar();
toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS)); // configura layout horizontal

JButton voltarButton = new JButton("Voltar");
JButton adicionarButton = new JButton("Adicionar");
JButton editarButton = new JButton("Editar");
JButton deletarButton = new JButton("Deletar");

toolBar.add(voltarButton);

// adiciona um espaço fixo pequeno entre os botões "Voltar" e "Adicionar"
toolBar.add(Box.createHorizontalStrut(230)); 

toolBar.add(adicionarButton);
toolBar.add(Box.createHorizontalStrut(10)); 
toolBar.add(editarButton);
toolBar.add(Box.createHorizontalStrut(10)); 
toolBar.add(deletarButton);

// centralização final se necessário
toolBar.add(Box.createHorizontalGlue());

tableView.add(toolBar, java.awt.BorderLayout.NORTH);


        // as ações dos botões declarados acima
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor(voltarButton)).dispose();
                MainController controller = new MainController();
                controller.iniciar();
            }
        });

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
            Clientes novoCliente = form.getClientes();
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
                Clientes clienteAtualizado = form.getClientes();
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
    public void iniciar() {
        tableView.setVisible(true);
     }
}
