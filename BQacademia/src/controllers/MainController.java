package controllers;

import views.MainView;


public class MainController {
    private MainView mainView;

    public MainController() {
        mainView = new MainView();
        inicializar();
    }

    private void inicializar() {
        // Adiciona os ActionListeners aos botões da MainView
        mainView.getEquipamentoButton().addActionListener(e -> {
            EquipamentoController controller = new EquipamentoController();
            controller.iniciar(); 
        });

        mainView.getClienteButton().addActionListener(e -> {
            ClientesController controller = new ClientesController();
            controller.iniciar();  // Inicia a tela de clientes
        });
    }

    public void iniciar() {
        mainView.setVisible(true); // Exibe a tela principal
    }
}
