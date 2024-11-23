/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Trabajador;

import Database.DataBase;
import Model.DAO.DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TrabajadoresDAO extends DAO<TrabajadoresDTO> {

    public TrabajadoresDAO(Connection connection) {
        super(DataBase.getInstance().getConnection());
    }

    @Override
    public boolean create(TrabajadoresDTO dto) throws SQLException {
        String query = "INSERT INTO Trabajadores (cedula, nombre, telefono, correo, puesto, horario, salario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getCedula());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getTelefono());
            stmt.setString(4, dto.getCorreo());
            stmt.setString(5, dto.getPuesto());
            stmt.setString(6, dto.getHorario());
            stmt.setDouble(7, dto.getSalario());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public TrabajadoresDTO read(Object id) throws SQLException {
        String query = "SELECT * FROM Trabajadores WHERE cedula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, (String) id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TrabajadoresDTO(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("puesto"),
                        rs.getString("horario"),
                        rs.getDouble("salario")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<TrabajadoresDTO> readAll() throws SQLException {
        List<TrabajadoresDTO> trabajadores = new ArrayList<>();
        String sql = "SELECT * FROM Trabajadores";  // Consulta para obtener todos los trabajadores
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                TrabajadoresDTO trabajador = new TrabajadoresDTO(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("puesto"),
                    rs.getString("horario"),
                    rs.getDouble("salario")
                );
                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return trabajadores;
    }

    @Override
    public boolean update(TrabajadoresDTO dto) throws SQLException {
        String sql = "UPDATE Trabajadores SET nombre = ?, telefono = ?, correo = ?, puesto = ?, horario = ?, salario = ? WHERE cedula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dto.getNombre());
            stmt.setString(2, dto.getTelefono());
            stmt.setString(3, dto.getCorreo());
            stmt.setString(4, dto.getPuesto());
            stmt.setString(5, dto.getHorario());
            stmt.setDouble(6, dto.getSalario());
            stmt.setString(7, dto.getCedula());
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Si se actualizó al menos un registro, retornamos true
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        String sql = "DELETE FROM Trabajadores WHERE cedula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(id));
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;  // Si se eliminó al menos un registro, retornamos true
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
}
