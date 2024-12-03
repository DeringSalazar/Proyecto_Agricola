/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import Model.DAO.DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoDAO extends DAO<AlmacenamientoDTO>{

    public AlmacenamientoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(AlmacenamientoDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call InsertarAlmacenamiento(?,?,?)");
        stmt.setInt(1, dto.getIdProducción());
        stmt.setInt(2, dto.getCantidad());
        stmt.setDate(3, dto.getFecha_Ingreso());
        return stmt.executeUpdate()>0;
    }

    @Override
    public AlmacenamientoDTO read(Object id) throws SQLException {
        stmt = connection.prepareStatement("call ReadAlmacenamiento(?)");
        stmt.setString(1,String.valueOf(id));
        rs = stmt.executeQuery();
        if(rs.next()){
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
        stmt.setString(1, String.valueOf(id));
        return stmt.executeUpdate()>0;
    }
    
    public boolean validatePK (Object id) throws SQLException{
        return read(id) == null;
    }
}
