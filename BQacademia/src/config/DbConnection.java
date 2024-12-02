package config;
// define o pacote onde esta classe está localizada.

 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;
 // importa as classes necessárias para manipulação de conexões com o banco de dados SQL

 public class DbConnection {
    // declaração da classe `DbConnection`, que será usada para gerenciar a conexão com o banco de dados
    private static final String URL = 
        "jdbc:mysql://localhost:3306/bd_academia?useSSL=false&serverTimezone=UTC";
        // define a URL de conexão com o banco de dados MySQL:
    private static final String USER = "root";
      // o nome de usuário para conectar ao banco de dados
    private static final String PASSWORD = "";
    // a senha para conectar ao banco de dados

    public static Connection connection = null;
// declara uma variável estática que armazenará a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        // método público e estático para obter a conexão com o banco de dados
        // lança uma exceção `SQLException` caso ocorra algum erro ao tentar conectar
        if (connection == null || connection.isClosed()){
            // verifica se a conexão é nula (não foi criada) ou está fechada.
            try {
           
                Class.forName("com.mysql.cj.jdbc.Driver");
                // carrega a classe do driver MySQL
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                  // estabelece a conexão com o banco de dados usando a URL, o usuário e a senha definidos.
            } catch (Exception e) {
                // captura qualquer exceção que ocorra durante o processo de conexão
                System.out.println("Driver do MySql não encontrado.");
                System.out.println(e.getMessage());
                e.printStackTrace();
                // imprime o rastreamento da pilha para ajudar a diagnosticar o erro.
            }
        } 
        return connection;
    }
    public void disconnect(Connection connection){
        // método para desconectar do banco de dados
        try {
            connection.close();
            // tenta fechar a conexão
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao desconectar o banco de dados", e);
            // lança uma nova exceção `RuntimeException` com uma mensagem e a exceção original.
        }
    }
 }