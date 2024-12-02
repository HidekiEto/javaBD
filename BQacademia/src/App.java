import controllers.MainController; // importa a classe MainController do pacote controllers

public class App { // define a classe principal do aplicativo
    public static void main(String[] args) throws Exception { // método principal que lança exceções
        MainController controller = new MainController(); // cria uma instância de MainController
        controller.iniciar(); // chama o método iniciar para iniciar o fluxo do aplicativo
    }
}
