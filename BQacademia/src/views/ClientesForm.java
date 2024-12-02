package views;

// importa as classes necessárias para criar o formulário e manipular dados de clientes
import models.Clientes;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClientesForm extends JDialog {
    // define os campos de entrada e botões do formulário
    private JTextField nomeField, cpfField, telefoneField, emailField, enderecoField, dataNascField, dataCadastroField;
    private JRadioButton ativo, desativo;
    private JButton salvarButton, cancelarButton;

    private Clientes cliente; // armazena o cliente que está sendo editado ou criado
    private boolean isEditMode; // indica se o formulário está no modo de edição

    // formata as datas no padrão dd/MM/yyyy
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // construtor para criar o formulário no modo de criação
    public ClientesForm(Frame parent, String title) {
        super(parent, title, true); // configura o formulário como modal
        this.isEditMode = false; // define o modo de criação
        initializeComponents(); // inicializa os componentes da interface
    }

    // construtor para criar o formulário no modo de edição
    public ClientesForm(Frame parent, String title, Clientes cliente) {
        super(parent, title, true);
        this.cliente = cliente; // atribui o cliente que será editado
        this.isEditMode = true; // define o modo de edição
        initializeComponents(); // inicializa os componentes da interface
        preencherCampos(); // preenche os campos com os dados do cliente
    }

    private void initializeComponents() {
        // inicializa os campos de texto e define comportamentos
        nomeField = new JTextField();
        cpfField = new JTextField();
        dataNascField = new JTextField();
        telefoneField = new JTextField();
        emailField = new JTextField();
        enderecoField = new JTextField();
        dataCadastroField = new JTextField();
        dataCadastroField.setEditable(false); // impede a edição da data de cadastro

        // cria os botões de status (ativo e inativo)
        ButtonGroup statusGroup = new ButtonGroup();
        ativo = new JRadioButton("Ativo");
        desativo = new JRadioButton("Inativo");
        statusGroup.add(ativo);
        statusGroup.add(desativo);

        // inicializa os botões de ação
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        // define o layout e organiza os componentes
        JPanel container = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(new JLabel("Registro de Cliente"));

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.add(new JLabel("Nome: "));
        panel.add(nomeField);
        panel.add(new JLabel("CPF: "));
        panel.add(cpfField);
        panel.add(new JLabel("Data de Nascimento (dd/MM/yyyy): "));
        panel.add(dataNascField);
        panel.add(new JLabel("Telefone: "));
        panel.add(telefoneField);
        panel.add(new JLabel("Email: "));
        panel.add(emailField);
        panel.add(new JLabel("Endereço: "));
        panel.add(enderecoField);
        panel.add(new JLabel("Data de Cadastro: "));
        panel.add(dataCadastroField);
        panel.add(new JLabel("Status:"));
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(ativo);
        statusPanel.add(desativo);
        panel.add(statusPanel);

        // configurações visuais dos botões
        salvarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(salvarButton);
        panel.add(cancelarButton);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(panel);

        // adiciona os listeners para os botões
        salvarButton.addActionListener(e -> {
            if (validarCampos()) { // valida os campos antes de salvar
                if (isEditMode) {
                    atualizarCliente(); // atualiza o cliente existente
                } else {
                    adicionarCliente(); // adiciona um novo cliente
                }
                dispose(); // fecha o formulário
            }
        });

        cancelarButton.addActionListener(e -> dispose()); // fecha o formulário sem salvar

        this.add(container); // adiciona o container ao formulário
        this.pack(); // ajusta o tamanho do formulário
        this.setLocationRelativeTo(getParent()); // centraliza o formulário na tela
    }

    private void preencherCampos() {
        // preenche os campos com os dados do cliente no modo de edição
        if (cliente != null) {
            nomeField.setText(cliente.getNome());
            cpfField.setText(cliente.getCpf());
            dataNascField.setText(cliente.getDataNasc().format(dateFormatter));
            telefoneField.setText(cliente.getTelefone());
            emailField.setText(cliente.getEmail());
            enderecoField.setText(cliente.getEndereco());
            dataCadastroField.setText(cliente.getDataCadastro().format(dateFormatter));
            ativo.setSelected(cliente.isAtivo());
            desativo.setSelected(!cliente.isAtivo());
        }
    }

    private boolean validarCampos() {
        // valida os campos obrigatórios e o formato das datas
        try {
            if (nomeField.getText().trim().isEmpty() || cpfField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome e CPF são obrigatórios");
            }
            LocalDate.parse(dataNascField.getText().trim(), dateFormatter); // verifica o formato da data
        } catch (IllegalArgumentException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Validação de Campos", JOptionPane.ERROR_MESSAGE);
            return false; // retorna falso se houver erro
        }
        return true; // retorna verdadeiro se todos os campos forem válidos
    }

    private void adicionarCliente() {
        // cria um novo cliente com os dados do formulário
        cliente = new Clientes(
                nomeField.getText().trim(),
                cpfField.getText().trim(),
                LocalDate.parse(dataNascField.getText().trim(), dateFormatter),
                telefoneField.getText().trim(),
                emailField.getText().trim(),
                enderecoField.getText().trim(),
                LocalDate.now(), // define a data atual como data de cadastro
                ativo.isSelected()
        );
    }

    private void atualizarCliente() {
        // atualiza os dados do cliente existente
        if (cliente != null) {
            cliente.setNome(nomeField.getText().trim());
            cliente.setCpf(cpfField.getText().trim());
            cliente.setDataNasc(LocalDate.parse(dataNascField.getText().trim(), dateFormatter));
            cliente.setTelefone(telefoneField.getText().trim());
            cliente.setEmail(emailField.getText().trim());
            cliente.setEndereco(enderecoField.getText().trim());
            cliente.setAtivo(ativo.isSelected());
        }
    }

    public Clientes getClientes() {
        // retorna o cliente criado ou atualizado
        return cliente;
    }
}
