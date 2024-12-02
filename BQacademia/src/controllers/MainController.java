package controllers;

import javax.swing.*;
import java.awt.*;

import views.MainView;

public class MainController {
    private MainView mainView;

    public MainController() {
        mainView = new MainView();
        inicializar();
    }

    private void inicializar() {
  
        mainView.getEquipamentoButton().addActionListener(e -> {
            EquipamentoController controller = new EquipamentoController();
            controller.iniciar();

            Window window = SwingUtilities.getWindowAncestor(mainView.getEquipamentoButton());
            if (window instanceof JFrame) {
                ((JFrame) window).dispose();
            }
        });

        mainView.getClienteButton().addActionListener(e -> {
            ClientesController controller = new ClientesController();
            controller.iniciar();

            Window window = SwingUtilities.getWindowAncestor(mainView.getClienteButton());
            if (window instanceof JFrame) {
                ((JFrame) window).dispose();
            }
        });
    }

    public void iniciar() {
        mainView.setVisible(true); // exibe a tela principal
    }
}
