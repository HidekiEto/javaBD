package views;

import models.Equipamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EquipamentoForm extends JDialog {
    private JTextField nomeField, descricaoField, qtdDisponivelField;
    private JRadioButton ativo, desativo;
    private JButton salvarButton, cancelarButton;

    private Equipamento equipamento;
    private boolean isEditMode;

    public EquipamentoForm(Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false;
        initializeComponents();
    }

    public EquipamentoForm(Frame parent, String title, Equipamento equipamento) {
        super(parent, title, true);
        this.equipamento = equipamento;
        this.isEditMode = true;
        initializeComponents();
        preencherCampos();
    }

    private void initializeComponents() {
        nomeField = new JTextField();
        descricaoField = new JTextField();
        qtdDisponivelField = new JTextField();
        ButtonGroup statusGroup = new ButtonGroup();
        ativo = new JRadioButton("Ativo");
        desativo = new JRadioButton("Inativo");
        statusGroup.add(ativo);
        statusGroup.add(desativo);
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        JPanel container = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout()); 
        titlePanel.add(new JLabel("Registro de Equipamento"));

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

        salvarButton.setBackground(Color.GREEN);
        salvarButton.setBackground(Color.GREEN);
        cancelarButton.setBackground(Color.RED);
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

        // margin de 15 pixels
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        container.add(titlePanel, BorderLayout.NORTH);
        container.add(panel);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    if (isEditMode) {
                        atualizarEquipamento();
                    } else {
                        adicionarEquipamento();
                    }
                    dispose();
                }
            }
        });

        cancelarButton.addActionListener(e -> dispose());

     
        this.add(container);
        this.pack();
        this.setLocationRelativeTo(getParent());
    }

    private void preencherCampos() {
        if (equipamento != null) {
            nomeField.setText(equipamento.getNome());
            descricaoField.setText(equipamento.getDescricao());
            qtdDisponivelField.setText(String.valueOf(equipamento.getQuantidadeDisponivel()));
            ativo.setSelected(equipamento.isAtivo());
            desativo.setSelected(!equipamento.isAtivo());
        }
    }

    private boolean validarCampos() {
        if (nomeField.getText().trim().isEmpty() ||
                descricaoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nome e Descrição são obrigatórios.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void adicionarEquipamento() {
        int quantidadeDisponivel;
        try {
            quantidadeDisponivel = Integer.parseInt(qtdDisponivelField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Quantidade Disponível deve ser um número válido.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        equipamento = new Equipamento(
                nomeField.getText().trim(),
                descricaoField.getText().trim(),
                quantidadeDisponivel,
                ativo.isSelected());
    }

    private void atualizarEquipamento() {
        int quantidadeDisponivel;
        try {
            quantidadeDisponivel = Integer.parseInt(qtdDisponivelField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Quantidade Disponível deve ser um número válido.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (equipamento != null) {
            equipamento.setNome(nomeField.getText().trim());
            equipamento.setDescricao(descricaoField.getText().trim());
            equipamento.setQuantidadeDisponivel(quantidadeDisponivel);
            equipamento.setAtivo(ativo.isSelected());
        }
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }
}