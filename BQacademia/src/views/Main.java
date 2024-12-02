package views;

import javax.swing.*;

public class Main extends JFrame {
    private JLabel title;
    private JButton equipamentos;

    public Main() {
        super("Tela de ínicio");
        initializeComponents();
    }

    private void initializeComponents(){
        title = new JLabel("Qual tabela deseja acessar?");
        
    }
}
