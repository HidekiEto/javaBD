package views;

import models.Equipamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EquipamentoForm extends JDialog{
        private JTextField nomeField, descricaoField, qdtDisponivelField;
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
            qdtDisponivelField = new JTextField();
            ativo = new JRadioButton();
            desativo = new JRadioButton();
            salvarButton = new JButton("Salvar");
            cancelarButton = new JButton("Cancelar");

            JPanel panel = new JPanel(new GridLayout(4,2, 10,10));
            panel.add(new JLabel("Nome: "));
            panel.add(nomeField);
            panel.add(new JLabel("Descrição: "));
            panel.add(descricaoField);
            panel.add(new JLabel("Quantidade Disponível: "));
            panel.add(qdtDisponivelField);
            panel.add(new JLabel("Em Uso"));
            panel.add(ativo);
            panel.add(new JLabel("Em Manutenção"));
            panel.add(desativo);
            panel.add(salvarButton);
            panel.add(cancelarButton);

            // margin de 10 pixels
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

            this.add(panel);
            this.pack();
            this.setLocationRelativeTo(getParent());
        }

        private void preencherCampos() {
            if (equipamento != null) {
                nomeField.setText(equipamento.getNome());
                descricaoField.setText(equipamento.getDescricao());
                qdtDisponivelField.setText(String.valueOf(equipamento.getQuantidadeDisponivel()));
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

            private void adicionarEquipamento(){
                int quantidadeDisponivel;
                try {
                    quantidadeDisponivel = Integer.parseInt(qdtDisponivelField.getText().trim());
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
                    ativo.isSelected()
                );
            }
            private void atualizarEquipamento(){
                int quantidadeDisponivel;
                try {
                   quantidadeDisponivel = Integer.parseInt(qdtDisponivelField.getText().trim());
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

            public Equipamento getEquipamento(){
                return equipamento;
            }
}