package views;

import models.Clientes;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClientesForm extends JDialog {
    private JTextField nomeField, cpfField, telefoneField, emailField, enderecoField, dataNascField, dataCadastroField;
    private JRadioButton ativo, desativo;
    private JButton salvarButton, cancelarButton;

    private Clientes cliente;
    private boolean isEditMode;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ClientesForm(Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false;
        initializeComponents();
    }

    public ClientesForm(Frame parent, String title, Clientes cliente) {
        super(parent, title, true);
        this.cliente = cliente;
        this.isEditMode = true;
        initializeComponents();
        preencherCampos();
    }

    private void initializeComponents() {
        nomeField = new JTextField();
        cpfField = new JTextField();
        dataNascField = new JTextField();
        telefoneField = new JTextField();
        emailField = new JTextField();
        enderecoField = new JTextField();
        dataCadastroField = new JTextField();
        dataCadastroField.setEditable(false); // Data de cadastro não editável

        ButtonGroup statusGroup = new ButtonGroup();
        ativo = new JRadioButton("Ativo");
        desativo = new JRadioButton("Inativo");
        statusGroup.add(ativo);
        statusGroup.add(desativo);

        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

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

        salvarButton.setBackground(new Color(0, 128, 0));
        salvarButton.setForeground(Color.WHITE);
        cancelarButton.setBackground(new Color(255, 0, 0));
        cancelarButton.setForeground(Color.WHITE);

        salvarButton.setForeground(Color.darkGray); // cor do texto
        salvarButton.setFocusPainted(false); // remove o efeito de foco
        salvarButton.setContentAreaFilled(false); // remove o preenchimento de fundo
        salvarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelarButton.setForeground(Color.darkGray); // cor do texto
        cancelarButton.setFocusPainted(false); // remove o efeito de foco
        cancelarButton.setContentAreaFilled(false); // remove o preenchimento de fundo
        cancelarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(salvarButton);
        panel.add(cancelarButton);

        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(panel);

        salvarButton.addActionListener(e -> {
            if (validarCampos()) {
                if (isEditMode) {
                    atualizarCliente();
                } else {
                    adicionarCliente();
                }
                dispose();
            }
        });

        cancelarButton.addActionListener(e -> dispose());

        this.add(container);
        this.pack();
        this.setLocationRelativeTo(getParent());
    }

    private void preencherCampos() {
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
        try {
            if (nomeField.getText().trim().isEmpty() || cpfField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome e CPF são obrigatórios.");
            }

            // Verifica o formato das datas
            LocalDate.parse(dataNascField.getText().trim(), dateFormatter);

        } catch (IllegalArgumentException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro: " + e.getMessage(),
                    "Validação de Campos",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void adicionarCliente() {
        cliente = new Clientes(
                nomeField.getText().trim(),
                cpfField.getText().trim(),
                LocalDate.parse(dataNascField.getText().trim(), dateFormatter),
                telefoneField.getText().trim(),
                emailField.getText().trim(),
                enderecoField.getText().trim(),
                LocalDate.now(), // define data atual para data de cadastro
                ativo.isSelected()
        );
    }

    private void atualizarCliente() {
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
        return cliente;
    }
}
