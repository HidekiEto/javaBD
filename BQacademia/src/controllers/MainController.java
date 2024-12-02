package controllers;

import views.Main;

import javax.swing.*;
import java.awt.*;

public class MainController {
    private JLabel teste;
    private Main panel;

    public MainController(){
        panel = new Main();
        inicializar();
    }

    public void inicializar(){
        teste = new JLabel("teste");
        panel.add(teste);
        panel.pack();

        panel.setVisible(true);
    }

    

    public void iniciar(){     
    }
}
