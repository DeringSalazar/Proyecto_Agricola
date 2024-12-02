/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controller.CultivosController;
import Controller.ProduccionController;
import Controller.TrabajadorController;
import Controller.UsuariosControllers;
import Model.Trabajador.TrabajadoresDAO;
import Model.Trabajador.TrabajadoresDTO;
import Model.UsuarioDAO;
import Model.UsuarioDTO;
import java.sql.SQLException;
import Database.DataBase;
import Model.Cultivos.Cultivos;
import Model.Cultivos.CultivosDTO;
import Model.Producción.ProduccionDTO;
import Model.Trabajador.Trabajadores;
import Model.Usuarios;
import UtilDate.UtilDate;
import View.View;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 9567
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
         // Crear la vista de la consola
//        ConsoleView view = new ConsoleView();
//
//        // Crear el controlador de trabajadores
//        TrabajadorController trabajadorController = new TrabajadorController(view);
//
//        // Crear el controlador de cultivos, pasando el controlador de trabajadores
//        CultivosController cultivosController = new CultivosController(view, trabajadorController);
//
//        // Datos del trabajador que no existe
//        String cedulaTrabajador = "123456789"; // Cédula del trabajador que no existe
//        Cultivos cultivo = new Cultivos();
//        cultivo.setId(1); // ID del cultivo
//        cultivo.setNombre("Maíz");
//        cultivo.setTipo("Cereal");
//        cultivo.setArea_Sembrada(100.0);
//        cultivo.setEstado_Crecimiento("Creciendo");
//        // Ejemplo de cómo usar los métodos correctamente
//        cultivo.setFecha_Siembra(UtilDate.toLocalDate("11/11/2000"));
//        cultivo.setFecha_Siembra(UtilDate.toLocalDate("15/11/2000"));
//
//
//        // Intentar insertar el cultivo a un trabajador que no existe
//        cultivosController.insertar(cultivo, cedulaTrabajador);
    }
}
