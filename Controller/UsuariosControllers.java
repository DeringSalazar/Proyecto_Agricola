/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Database.DataBase;
import Factory.FactoryProducer;
import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import Model.UsuarioDAO;
import Model.UsuarioDTO;
import Model.Usuarios;
import View.View;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UsuariosControllers {
    
    private DAO<UsuarioDTO> dao;
    private Mapper<Usuarios, UsuarioDTO> mapper;
    private View view;
    private UsuarioDTO logueado;

    public UsuariosControllers(View view) {
        this.view = view;
        try {
            // Usamos la FactoryProducer para obtener la fábrica correcta para "Usuario"
            DAOFactory factory = FactoryProducer.getFactory("Usuario"); // Aquí pasas el tipo de entidad, en este caso "Usuario"
            dao = (DAO<UsuarioDTO>) factory.createDAO(DataBase.getInstance().getConnection()); // Usamos la fábrica para crear el DAO
            mapper = (Mapper<Usuarios, UsuarioDTO>) factory.createrMapper(); 
        } catch (SQLException e) {
            view.showError("Error al conectar con la base de datos: " + e.getMessage());
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
    public void actualizarUsuario(Usuarios user) {
        if (user == null || !validateRequired(user)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (validatePK(user.getUser_name())) {
                view.showError("El usuario ingresado no se encuentra registrado");
                return;
            }
            // Encriptar la contraseña antes de actualizar
            String hashedPassword = convertirSHA256(user.getPassword());
            user.setPassword(hashedPassword);

            // Convertir a DTO y actualizar
            dao.update(mapper.toDto(user));
            view.showSuccess("Usuario actualizado con éxito.");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
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
        return ((UsuarioDAO) dao).validatePK(id);
}

    
    public boolean validateRequired(Usuarios usuarios) {
    return usuarios.getUser_name() != null && !usuarios.getUser_name().trim().isEmpty()
            && usuarios.getPassword() != null && !usuarios.getPassword().trim().isEmpty();
    }
}
