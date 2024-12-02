package controllers;
// define o pacote onde a classe está localizada

import javax.swing.*;
import java.awt.*; // importa classes relacionadas a componentes gráficos do Java Swing
import views.MainView; // importa a classe da interface gráfica principal

// a classe MainController é responsável por gerenciar as interações na tela principal (MainView)
// ela conecta os botões da interface principal com os controladores de clientes e equipamentos
public class MainController {
    private MainView mainView; // referência à interface gráfica principal

    // construtor da classe que inicializa a interface e configura os comportamentos
    public MainController() {
        mainView = new MainView(); // cria a tela principal
        inicializar(); // configura as ações dos botões
    }

    // método privado que configura as interações dos botões na interface principal
    private void inicializar() {

        // configura o botão de "Equipamentos" para abrir o controlador de equipamentos
        mainView.getEquipamentoButton().addActionListener(e -> {
            EquipamentoController controller = new EquipamentoController(); // cria o controlador de equipamentos
            controller.iniciar(); // inicia o controlador (exibe a tela de equipamentos)

            // fecha a janela principal após abrir a tela de equipamentos
            Window window = SwingUtilities.getWindowAncestor(mainView.getEquipamentoButton());
            if (window instanceof JFrame) { // verifica se a janela principal é um JFrame
                ((JFrame) window).dispose(); // fecha a janela principal
            }
        });

        // configura o botão de "Clientes" para abrir o controlador de clientes
        mainView.getClienteButton().addActionListener(e -> {
            ClientesController controller = new ClientesController(); // cria o controlador de clientes
            controller.iniciar(); // inicia o controlador (exibe a tela de clientes)

            // fecha a janela principal após abrir a tela de clientes
            Window window = SwingUtilities.getWindowAncestor(mainView.getClienteButton());
            if (window instanceof JFrame) { // verifica se a janela principal é um JFrame
                ((JFrame) window).dispose(); // fecha a janela principal
            }
        });
    }

    // método público que exibe a tela principal
    public void iniciar() {
        mainView.setVisible(true); // torna a interface principal visível para o usuário
    }
}
