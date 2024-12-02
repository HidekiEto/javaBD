package controllers;

import views.MainView;
import controllers.EquipamentoController; // Importação hipotética para o EquipamentoController
import javax.swing.*;

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
            JOptionPane.showMessageDialog(mainView, "Funcionalidade de Clientes ainda não implementada.");
        });
    }

    public void iniciar() {
        mainView.setVisible(true); // Exibe a tela principal
    }
}
