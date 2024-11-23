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
    
    private DataBase(){
        try 
        {
            String URL = "jdbc:mysql://127.0.0.1:3307/produccion_agricola";
            String USER = "root";
            String PASSWORD = "";
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
    public Connection getConnection(){
        return connection;
    }
}
