package views;

import models.Equipamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EquipamentoForm extends JDialog {
    private JTextField nomeField, descricaoField, qtdDisponivelField; // campos de texto para entrada dos dados
    private JRadioButton ativo, desativo; // botões para selecionar o status (Ativo/Inativo)
    private JButton salvarButton, cancelarButton; // botões de ação (salvar e cancelar)

    private Equipamento equipamento; // objeto Equipamento que será manipulado no formulário
    private boolean isEditMode; // variável que define se o formulário está no modo de edição

    // construtor para um novo Equipamento
    public EquipamentoForm(Frame parent, String title) {
        super(parent, title, true); // define a janela como modal
        this.isEditMode = false; // modo de criação (não edição)
        initializeComponents(); // chama o método para inicializar os componentes
    }

    // construtor para editar um Equipamento existente
    public EquipamentoForm(Frame parent, String title, Equipamento equipamento) {
        super(parent, title, true); // define a janela como modal
        this.equipamento = equipamento;
        this.isEditMode = true; // modo de edição
        initializeComponents(); // chama o método para inicializar os componentes
        preencherCampos(); // preenche os campos com os dados do Equipamento existente
    }

    // método que inicializa e configura todos os componentes gráficos da janela
    private void initializeComponents() {
        // inicializa os campos de texto
        nomeField = new JTextField();
        descricaoField = new JTextField();
        qtdDisponivelField = new JTextField();

        // cria um grupo de botões para o status (ativo ou inativo)
        ButtonGroup statusGroup = new ButtonGroup();
        ativo = new JRadioButton("Ativo");
        desativo = new JRadioButton("Inativo");
        statusGroup.add(ativo);
        statusGroup.add(desativo);

        // inicializa os botões de salvar e cancelar
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        // cria um painel contêiner com layout de borda
        JPanel container = new JPanel(new BorderLayout());

        // painel de título com um texto
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(new JLabel("Registro de Equipamento"));

        // painel principal com campos de entrada organizados em uma grid
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Nome: "));
        panel.add(nomeField);
        panel.add(new JLabel("Descrição: "));
        panel.add(descricaoField);
        panel.add(new JLabel("Quantidade Disponível: "));
        panel.add(qtdDisponivelField);
        panel.add(new JLabel("Status:"));
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(ativo);
        statusPanel.add(desativo);
        panel.add(statusPanel);

        // define a aparência dos botões de salvar e cancelar
        salvarButton.setBackground(Color.GREEN);
        cancelarButton.setBackground(Color.RED);
        cancelarButton.setForeground(Color.WHITE);

        // remove efeitos de foco e muda a aparência dos botões
        salvarButton.setForeground(Color.darkGray);
        salvarButton.setFocusPainted(false); // tira o foco
        salvarButton.setContentAreaFilled(false); // tira o background 
        salvarButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //muda po cursor

        cancelarButton.setForeground(Color.darkGray);
        cancelarButton.setFocusPainted(false);
        cancelarButton.setContentAreaFilled(false);
        cancelarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // adiciona os botões no painel
        panel.add(salvarButton);
        panel.add(cancelarButton);

        // adiciona uma margem de 15 pixels ao painel
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // adiciona o painel de título e o painel principal no container
        container.add(titlePanel, BorderLayout.NORTH);
        container.add(panel);

        // define as ações dos botões
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    if (isEditMode) {
                        atualizarEquipamento(); // atualiza o equipamento existente
                    } else {
                        adicionarEquipamento(); // adiciona um novo equipamento
                    }
                    dispose(); // fecha o formulário
                }
            }
        });

        // ação do botão cancelar: simplesmente fecha o formulário sem salvar
        cancelarButton.addActionListener(e -> dispose());

        // adiciona o painel container à janela e configura a posição
        this.add(container);
        this.pack();
        this.setLocationRelativeTo(getParent()); // centraliza a janela
    }

    // preenche os campos com os dados do Equipamento quando o formulário está em modo de edição
    private void preencherCampos() {
        if (equipamento != null) {
            nomeField.setText(equipamento.getNome());
            descricaoField.setText(equipamento.getDescricao());
            qtdDisponivelField.setText(String.valueOf(equipamento.getQuantidadeDisponivel()));
            ativo.setSelected(equipamento.isAtivo());
            desativo.setSelected(!equipamento.isAtivo());
        }
    }

    // método de validação dos campos de entrada
    private boolean validarCampos() {
        // verifica se os campos obrigatórios (Nome e Descrição) não estão vazios
        if (nomeField.getText().trim().isEmpty() ||
                descricaoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nome e Descrição são obrigatórios.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // método para adicionar um novo Equipamento
    private void adicionarEquipamento() {
        int quantidadeDisponivel;
        try {
            // tenta converter o campo de quantidade disponível para número inteiro
            quantidadeDisponivel = Integer.parseInt(qtdDisponivelField.getText().trim());
        } catch (NumberFormatException e) {
            // se falhar, exibe um erro
            JOptionPane.showMessageDialog(this,
                    "Quantidade Disponível deve ser um número válido.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // cria o novo objeto Equipamento com os dados do formulário
        equipamento = new Equipamento(
                nomeField.getText().trim(),
                descricaoField.getText().trim(),
                quantidadeDisponivel,
                ativo.isSelected());
    }

    // método para atualizar um Equipamento existente
    private void atualizarEquipamento() {
        int quantidadeDisponivel;
        try {
            // tenta converter o campo de quantidade disponível para número inteiro
            quantidadeDisponivel = Integer.parseInt(qtdDisponivelField.getText().trim());
        } catch (NumberFormatException e) {
            // se falhar, exibe um erro
            JOptionPane.showMessageDialog(this,
                    "Quantidade Disponível deve ser um número válido.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // atualiza os dados do Equipamento
        if (equipamento != null) {
            equipamento.setNome(nomeField.getText().trim());
            equipamento.setDescricao(descricaoField.getText().trim());
            equipamento.setQuantidadeDisponivel(quantidadeDisponivel);
            equipamento.setAtivo(ativo.isSelected());
        }
    }

    // método para obter o Equipamento preenchido no formulário
    public Equipamento getEquipamento() {
        return equipamento;
    }
}
