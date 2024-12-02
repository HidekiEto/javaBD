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
        // configuração básica da janela
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("BQacademia", SwingConstants.CENTER);
        titleLabel.add(Box.createVerticalStrut(10));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel, BorderLayout.NORTH);

        // painel central com botões
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // 2 linhas, 1 coluna, espaçamento de 10px
        equipamentoButton = new JButton("Gerenciar Equipamentos");
        clienteButton = new JButton("Gerenciar Clientes");

        // configurações visuais do botão
        equipamentoButton.setForeground(Color.darkGray); // cor do texto
        equipamentoButton.setFocusPainted(false); // remove o efeito de foco
        equipamentoButton.setContentAreaFilled(false); // remove o preenchimento de fundo
        equipamentoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // criação e redimensionamento do ícone
        ImageIcon icon = new ImageIcon("assets/dumbell.png"); // criação do ícone original
        Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);

        // define o ícone redimensionado no botão
        equipamentoButton.setIcon(resizedIcon);

        // ajusta a posição do texto para a esquerda do ícone
        equipamentoButton.setHorizontalTextPosition(SwingConstants.LEFT);

        clienteButton.setForeground(Color.darkGray); // cor do texto
        clienteButton.setFocusPainted(false); // remove o efeito de foco
        clienteButton.setContentAreaFilled(false); // remove o preenchimento de fundo
        clienteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // criação e redimensionamento do ícone
        ImageIcon iconCliente = new ImageIcon("assets/cliente.png"); 
        Image imagemCliente = iconCliente.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIconCliente = new ImageIcon(imagemCliente);

        // define o ícone redimensionado no botão
        clienteButton.setIcon(resizedIconCliente);

        // ajusta a posição do texto para a esquerda do ícone
        clienteButton.setHorizontalTextPosition(SwingConstants.LEFT);

        // configuração do painel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margem interna
        buttonPanel.add(equipamentoButton);
        buttonPanel.add(clienteButton);

        JPanel tables = new JPanel();
        tables.setLayout(new FlowLayout());
        tables.add(buttonPanel);

        this.add(tables, BorderLayout.CENTER);

        // footer
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
