package views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JButton equipamentoButton, clienteButton;

    public MainView() {
        super("Tela Principal");
        initializeComponents();
    }

    private void initializeComponents() {
        // Configuração básica da janela
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Título na parte superior
        JLabel titleLabel = new JLabel("Sistema de Gerenciamento", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel, BorderLayout.NORTH);

        // Painel central com botões
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // 2 linhas, 1 coluna, espaçamento de 10px
        equipamentoButton = new JButton("Gerenciar Equipamentos");
        clienteButton = new JButton("Gerenciar Clientes");

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem interna
        buttonPanel.add(equipamentoButton);
        buttonPanel.add(clienteButton);

        this.add(buttonPanel, BorderLayout.CENTER);

        // Rodapé (opcional)
        JLabel footerLabel = new JLabel("© 2024 Gerenciamento de Sistemas", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        this.add(footerLabel, BorderLayout.SOUTH);
    }
    public JButton getEquipamentoButton() {
        return equipamentoButton;
    }
    
    public JButton getClienteButton() {
        return clienteButton;
    }

    
}
