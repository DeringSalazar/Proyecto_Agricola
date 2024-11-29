/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DataBase;
import Model.UsuarioDAO;
import Model.UsuarioDTO;
import Model.UsuarioMapper;
import Model.Usuarios;
import View.View;
import java.sql.Connection;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class UsuariosControllers {
    private UsuarioDAO dao;
    private final View view;
    private UsuarioDTO logueado;
    private UsuarioMapper mapper;

    public UsuariosControllers(View view) {
        this.view = view;
        mapper = new UsuarioMapper();
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

    
    public void registrarUsuario(Usuarios usuario) {
       if (usuario == null || !validateRequired(usuario)) {
           view.showError("Faltan datos requeridos.");
           return;
       }
       try {
           if (!validatePK(usuario.getUser_name())) {
               view.showMessage("El nombre de usuario ingresado ya está registrado.");
               return;
           }
           // Encriptar la contraseña usando SHA-256
           String hashedPassword = convertirSHA256(usuario.getPassword());
           usuario.setPassword(hashedPassword);

           // Crear el usuario en la base de datos
           dao.create(mapper.toDto(usuario));
           view.showMessage("Usuario registrado correctamente.");
       } catch (SQLException e) {
           view.showError("Error al guardar datos: " + e.getMessage());
       }
   }



    // Actualizar la contraseña de un usuario
    public void actualizarPassword(Usuarios user) {
    try {
        UsuarioDTO usuario = dao.read(user.getUser_name());
        if (usuario == null) {
            view.showError("Usuario no encontrado.");
            return;
        }
        // Encriptar la nueva contraseña
        String hashedPassword = convertirSHA256(user.getPassword());
        usuario.setPassword(hashedPassword);

        if (dao.update(usuario)) {
            view.showSuccess("Contraseña actualizada con éxito.");
        } else {
            view.showError("Error al actualizar la contraseña.");
        }
    } catch (SQLException e) {
        view.showError("Error al actualizar la contraseña: " + e.getMessage());
    }
}

    // Iniciar sesión
    public boolean iniciarSesion(Usuarios user) {
    try {
        UsuarioDTO usuario = dao.read(user.getUser_name());
        if (usuario == null) {
            view.showError("Usuario no encontrado.");
            return false;
        }
        if (usuario.getPassword().equals(convertirSHA256(user.getPassword()))) {
            logueado = usuario;
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
        if (logueado != null) {
            logueado = null;
            view.showSuccess("Sesión cerrada con éxito");
        } else {
            view.showError("No hay ninguna sesión activa");
        }
    }

    // Obtener el usuario actualmente logueado
    public UsuarioDTO getUsuarioLogueado() {
        return logueado;
    }

    // Método para convertir la contraseña a SHA-256 es propio de java
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
    public boolean validatePK(String id) {
        return dao.validatePK(id);
    }
    
    public boolean validateRequired(Usuarios usuarios) {
    return usuarios.getUser_name() != null && !usuarios.getUser_name().trim().isEmpty()
            && usuarios.getPassword() != null && !usuarios.getPassword().trim().isEmpty();
}
}
