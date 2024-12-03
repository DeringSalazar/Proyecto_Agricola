/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import Model.DAO.DAO;
import Model.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlmacenamientoDAO extends DAO<AlmacenamientoDTO>{

    public AlmacenamientoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(AlmacenamientoDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call InsertarAlmacenamientos(?,?,?)");
        stmt.setInt(1, dto.getIdProducción());
        stmt.setInt(2, dto.getCantidad());
        stmt.setDate(3, dto.getFecha_Ingreso());
        return stmt.executeUpdate()>0;
    }

    @Override
        public AlmacenamientoDTO read(Object id) throws SQLException {
        stmt = connection.prepareStatement("call ReadAlmacenamiento(?)");
        stmt.setInt(1, (int) id);  // Asegúrate de que el id sea entero
        rs = stmt.executeQuery();
        if (rs.next()) {
            return new AlmacenamientoDTO(
                    rs.getInt(1),   
                    rs.getInt(2),  
                    rs.getInt(3),   
                    rs.getDate(4),  
                    rs.getDate(5) 
            );
        }
        return null;
        }

    @Override
    public List<AlmacenamientoDTO> readAll() throws SQLException {
        stmt = connection.prepareStatement("call AlmacenamientoReadAll()");
        rs = stmt.executeQuery();
        List<AlmacenamientoDTO> dtos = new ArrayList();
        while(rs.next()){
            dtos.add(new AlmacenamientoDTO(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3), 
                    rs.getDate(4),
                    rs.getDate(5)
            ));
        }
        return dtos;
    }

    @Override
    public boolean update(AlmacenamientoDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call UpdateAlmacenamiento(?,?)");
        stmt.setInt(1, dto.getId());
        stmt.setDate(2, dto.getFecha_Retiro());
        return stmt.executeUpdate()>0;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        stmt = connection.prepareStatement("call DeleteAlmacenamiento(?)");
        stmt.setInt(1, (int) (id));
        return stmt.executeUpdate()>0;
    }
    

       public boolean validatePK(int id) throws SQLException {
        try {
            return read(id) == null;  
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenamientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
        }
}
