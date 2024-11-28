/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controller.UsuariosControllers;
import Model.Trabajador.TrabajadoresDAO;
import Model.Trabajador.TrabajadoresDTO;
import Model.UsuarioDAO;
import Model.UsuarioDTO;
import java.sql.SQLException;
import Database.DataBase;
import View.View;
import java.sql.Connection;

/**
 *
 * @author 9567
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
    DataBase connection = DataBase.getInstance();
    UsuariosControllers usuariosController = new UsuariosControllers(new ConsoleView()); //Para hacer pruebas

    try {
        // Variables de entrada
        String userName = "50411193"; // Nombre de usuario
        String oldPassword = "123";   // Contraseña actual
        String newPassword = "456";   // Nueva contraseña que el usuario quiere establecer

        // Intentar iniciar sesión
        if (usuariosController.iniciarSesion(userName, oldPassword)) {
            System.out.println("Inicio de sesión exitoso.");

            // Mostrar información del usuario logueado
            UsuarioDTO usuarioLogueado = usuariosController.getUsuarioLogueado();
            if (usuarioLogueado != null) {
                System.out.println("Usuario logueado: " + usuarioLogueado.getUser_name());
                System.out.println("Rol: " + (usuarioLogueado.getRol() == 0 ? "Administrador" : "Trabajador"));

                // Intentar actualizar la contraseña
                if (usuariosController.actualizarPassword(userName, newPassword)) {
                    System.out.println("Contraseña actualizada correctamente.");
                } else {
                    System.err.println("Error al actualizar la contraseña.");
                }
            }
        } else {
            System.err.println("Error: Inicio de sesión fallido. Verifica tus credenciales.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
}
