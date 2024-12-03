/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.DAO.DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import Database.DataBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class UsuarioDAO extends DAO<UsuarioDTO>{

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(UsuarioDTO dto) throws SQLException {
        String sql = "call InsertarUsuarios(?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dto.getUser_name());
            ps.setString(2, dto.getPassword());
            ps.setInt(3, dto.getRol());
            return ps.executeUpdate()>0;
        }
    }

    @Override
    public UsuarioDTO read(Object id) throws SQLException {
        String query = "call ReadUsuarios(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, (String) id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioDTO(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getInt("rol")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> readAll() throws SQLException {
         List<UsuarioDTO> usuarios = new ArrayList<>();
        String query = "call ReadAllUsuarios()";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new UsuarioDTO(
                    rs.getInt("id"),
                    rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getInt("rol")
                ));
            }
        }
        return usuarios;
    }

    @Override
    public boolean update(UsuarioDTO dto) throws SQLException {
        String query = "call UpdateUsuarios(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getUser_name());
            stmt.setString(2, dto.getPassword());
            stmt.setInt(3, dto.getRol());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        String query = "call DeleteUsuarios(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, (String) id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean validatePK(String userName) {
        try {
            // Usa read para verificar si el usuario existe
            return read(userName) == null; // Si no existe, es válido para registrar
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Si hay error, asumimos que no es válido
    }
}
