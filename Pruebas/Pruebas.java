/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controller.ProduccionController;
import Controller.UsuariosControllers;
import Model.Trabajador.TrabajadoresDAO;
import Model.Trabajador.TrabajadoresDTO;
import Model.UsuarioDAO;
import Model.UsuarioDTO;
import java.sql.SQLException;
import Database.DataBase;
import Model.Producción.ProduccionDTO;
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
        ConsoleView view = new ConsoleView();

        // Crear el controlador para manejar la lógica de la producción
        ProduccionController produccionController = new ProduccionController(view);

        // Obtener las producciones desde la base de datos
        List<ProduccionDTO> producciones = produccionController.getProduccionesFromDB();

        // Si se obtienen producciones de la base de datos, generar el reporte XML
        if (producciones != null && !producciones.isEmpty()) {
            // Llamar al método para generar el reporte XML
            produccionController.generarReporteXML(producciones);
        } else {
            view.showError("No se encontraron producciones para generar el reporte.");
        }
    }
}
