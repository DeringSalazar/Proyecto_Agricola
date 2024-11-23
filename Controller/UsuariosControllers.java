/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DataBase;
import Model.UsuarioDAO;
import Model.UsuarioDTO;
import View.View;
import java.sql.Connection;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuariosControllers {
    private UsuarioDAO dao;
    private final View view;
    private UsuarioDTO usuarioLogueado; // Usuario actualmente logueado

    public UsuariosControllers(View view) {
        this.view = view;
        try {
            Connection connection = DataBase.getInstance().getConnection();
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Conexión a la base de datos no válida.");
            }
            this.dao = new UsuarioDAO(connection);
        } catch (IllegalStateException e) {
            view.showError("Error: La conexión a la base de datos no está inicializada.");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            view.showError("Error al verificar la conexión: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Crear un nuevo usuario
    public boolean registrarUsuario(String userName, String password, int rol) {
        if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
            view.showError("El nombre de usuario y la contraseña son obligatorios");
            return false;
        }
        if (usuarioExiste(userName)) {
            view.showError("El usuario ya existe.");
            return false;
        }
        try {
            // Encriptar la contraseña usando SHA-256
            String hashedPassword = convertirSHA256(password);
            UsuarioDTO nuevoUsuario = new UsuarioDTO(0, userName, hashedPassword, rol);
            boolean creado = dao.create(nuevoUsuario);
            if (creado) {
                view.showMessage("Usuario registrado con éxito");
            } else {
                view.showError("Error al registrar el usuario");
            }
            return creado;
        } catch (SQLException e) {
            view.showError("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    // Actualizar la contraseña de un usuario
    public boolean actualizarPassword(String userName, String newPassword) {
        try {
            UsuarioDTO usuario = dao.read(userName);
            if (usuario == null) {
                view.showError("Usuario no encontrado.");
                return false;
            }
            // Encriptar la nueva contraseña
            String hashedPassword = convertirSHA256(newPassword);
            usuario.setPassword(hashedPassword);
            
            boolean actualizado = dao.update(usuario);
            if (actualizado) {
                view.showSuccess("Contraseña actualizada con éxito.");
            } else {
                view.showError("Error al actualizar la contraseña.");
            }
            return actualizado;
        } catch (SQLException e) {
            view.showError("Error al actualizar la contraseña: " + e.getMessage());
            return false;
        }
    }

    // Iniciar sesión
    public boolean iniciarSesion(String userName, String password) {
        try {
            UsuarioDTO usuario = dao.read(userName);
            if (usuario == null) {
                view.showError("Usuario no encontrado.");
                return false;
            }
            // Comparar la contraseña encriptada
            if (usuario.getPassword().equals(convertirSHA256(password))) {
                usuarioLogueado = usuario;
                view.showSuccess("Inicio de sesión exitoso.");
                return true;
            } else {
                view.showError("Contraseña incorrecta.");
                return false;
            }
        } catch (SQLException e) {
            view.showError("Error al iniciar sesión: " + e.getMessage());
            return false;
        }
    }

    // Cerrar sesión
    public void cerrarSesion() {
        if (usuarioLogueado != null) {
            usuarioLogueado = null;
            view.showSuccess("Sesión cerrada con éxito");
        } else {
            view.showError("No hay ninguna sesión activa");
        }
    }

    // Obtener el usuario actualmente logueado
    public UsuarioDTO getUsuarioLogueado() {
        return usuarioLogueado;
    }

    // Verificar si el usuario ya existe
    public boolean usuarioExiste(String userName) {
        try {
            return dao.read(userName) != null;
        } catch (SQLException e) {
            view.showError("Error al verificar usuario: " + e.getMessage());
            return false;
        }
    }

    // Método para convertir la contraseña a SHA-256 propio de java
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {        
            e.printStackTrace();
            return null;
        }
            
        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
            
        for (byte b : hash) {        
            sb.append(String.format("%02x", b));
        }
            
        return sb.toString();
    }
}
