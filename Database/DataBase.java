/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    private static DataBase instance;
    private Connection connection;
    private final String URL = "jdbc:mysql://127.0.0.1:3307/produccionagricola";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    private DataBase(){
        try 
        {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static DataBase getInstance(){
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
   public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            //prueba de conexion
            System.out.println("Reconectando a la base de datos...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
