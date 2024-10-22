package com.academia_bq;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cadastro extends JFrame {
    private JLabel nome, desc, qntDisp, estado;
    private JButton salvar,cancelar, consultar;
    private JTextField tf1, tf2, tf3;
    private JTextArea textDesc;
    private JComboBox cbEstado;
    private JPanel panel1, panel2, panel3, panel4;
    
    public Cadastro(){
        super("Cadastrar");

            panel1 = new JPanel();
            panel1.setLayout(new GridLayout(4,1));
            panel1.add(nome = new JLabel("Nome:"));
            panel1.add(tf1 = new JTextField());
            panel1.add(desc = new JLabel("Descrição"));
            panel1.add(textDesc = new JTextArea());

            panel2 = new JPanel();
            panel2.setLayout(new GridLayout(3,1));
            panel2.add(qntDisp = new JLabel("Quantidade Disponível"));
            panel2.add(tf2 = new JTextField());
            panel2.add(cbEstado = new JComboBox());
            cbEstado.addItem("Diponível");
            cbEstado.addItem("Em manutenção");
        
            panel3 = new JPanel();
            panel3.setLayout(new GridLayout(2,1));
            panel3.add(salvar = new JButton("Salvar"));
            panel3.add(cancelar = new JButton("Cancelar"));
            panel3.add(consultar = new JButton("Consultar"));
            
            panel4 = new JPanel();
            panel4.add(panel2);
            panel4.add(panel3);

            Container janela;
            janela = getContentPane();
            janela.setLayout(new FlowLayout());
            janela.add(panel1);
            janela.add(panel4);

   
            setSize(300,200);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

        
    }
}
