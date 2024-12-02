package views; // define o pacote da classe

import javax.swing.*; // importa as classes Swing para criar a interface gráfica
import java.awt.*; // importa as classes AWT para manipulação de layouts e componentes gráficos

public class MainView extends JFrame { // define a classe principal que herda de JFrame
    private JButton equipamentoButton, clienteButton; // declara os botões de gerenciamento

    public MainView() { // construtor da classe
        super("Tela Principal"); // define o título da janela
        initializeComponents(); // inicializa os componentes da interface
    }

    private void initializeComponents() { // método para inicializar os componentes da interface
        // configuração básica da janela
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // define a operação padrão ao fechar a janela
        this.setSize(400, 200); // define o tamanho da janela
        this.setLocationRelativeTo(null); // centraliza a janela na tela
        this.setLayout(new BorderLayout()); // define o layout da janela como BorderLayout

        JLabel titleLabel = new JLabel("BQacademia", SwingConstants.CENTER); // deixa centralizado
        titleLabel.add(Box.createVerticalStrut(10)); // adiciona espaçamento vertical
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // define a fonte do rótulo
        this.add(titleLabel, BorderLayout.NORTH); // adiciona o rótulo na parte superior da janela

        // painel central com botões
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // cria um painel com grade de 2 linhas e 1 coluna
        equipamentoButton = new JButton("Gerenciar Equipamentos"); // cria o botão para gerenciar equipamentos
        clienteButton = new JButton("Gerenciar Clientes"); // cria o botão para gerenciar clientes

        // configurações visuais do botão de equipamentos
        equipamentoButton.setForeground(Color.darkGray); // define a cor do texto
        equipamentoButton.setFocusPainted(false); // remove o efeito de foco ao clicar
        equipamentoButton.setContentAreaFilled(false); // remove o preenchimento de fundo
        equipamentoButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // altera o cursor para o ícone de mão

        // criação e redimensionamento do ícone do botão de equipamentos
        ImageIcon icon = new ImageIcon("assets/dumbell.png"); // carrega o ícone
        Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // redimensiona o ícone
        ImageIcon tamanhoIcon = new ImageIcon(image); // cria o ícone redimensionado
        equipamentoButton.setIcon(tamanhoIcon); // define o ícone no botão
        equipamentoButton.setHorizontalTextPosition(SwingConstants.LEFT); // ajusta a posição do texto

        // configurações visuais do botão de clientes
        clienteButton.setForeground(Color.darkGray); // define a cor do texto
        clienteButton.setFocusPainted(false); // remove o efeito de foco ao clicar
        clienteButton.setContentAreaFilled(false); // remove o preenchimento de fundo
        clienteButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // altera o cursor para o ícone de mão

        // criação e redimensionamento do ícone do botão de clientes
        ImageIcon iconCliente = new ImageIcon("assets/cliente.png"); // carrega o ícone
        Image imagemCliente = iconCliente.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // redimensiona o ícone
        ImageIcon tamanhoIconeCliente = new ImageIcon(imagemCliente); // cria o ícone redimensionado
        clienteButton.setIcon(tamanhoIconeCliente); // define o ícone no botão
        clienteButton.setHorizontalTextPosition(SwingConstants.LEFT); // ajusta a posição do texto

        // configuração do painel de botões
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // define margens internas
        buttonPanel.add(equipamentoButton); // adiciona o botão de equipamentos ao painel
        buttonPanel.add(clienteButton); // adiciona o botão de clientes ao painel

        JPanel tables = new JPanel(); // cria um painel para organizar os botões
        tables.setLayout(new FlowLayout()); // define o layout como FlowLayout
        tables.add(buttonPanel); // adiciona o painel de botões

        this.add(tables, BorderLayout.CENTER); // adiciona o painel ao centro da janela

        // footer
        JLabel footerLabel = new JLabel("© 2024 Gerenciamento de Sistemas", SwingConstants.CENTER); // cria um rótulo de rodapé
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // define a fonte do rodapé
        this.add(footerLabel, BorderLayout.SOUTH); // adiciona o rodapé na parte inferior da janela
    }

    public JButton getEquipamentoButton() { // método para obter o botão de equipamentos
        return equipamentoButton;
    }

    public JButton getClienteButton() { // método para obter o botão de clientes
        return clienteButton;
    }
}
