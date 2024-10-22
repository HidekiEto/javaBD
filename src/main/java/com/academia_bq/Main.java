package com.academia_bq;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    private JPanel panel;
    private JButton btn1, btn2;

        public Main(){
            super("Tela inicial");

            panel = new JPanel();
            panel.setLayout(new GridLayout(2,1, 1 ,20));
            panel.add(btn1 = new JButton("Tela de Cadastro"));
            btn1.addActionListener((actionEvent) -> cadastrar(actionEvent));
            panel.add(btn2 = new JButton("Tela de Registro"));

            Container janela;
            janela = getContentPane();
            janela.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));
            janela.add(panel);
        
        }



    public static void main(String[] args) {
        Main teste = new Main();
        teste.setVisible(true);
        teste.setDefaultCloseOperation(EXIT_ON_CLOSE);
        teste.setSize(300,200);
    }

        private void cadastrar(ActionEvent e){
        if (e.getSource() == btn1){
          //  Cadastro cadastro = new Cadastro();
           // cadastro.setVisible(true);
            
        }
    }
}