/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producción;

import Model.DAO.DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducciónDAO extends DAO<ProduccionDTO> {

    public ProducciónDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(ProduccionDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call ProduccionCreate(?,?,?,?,?)");
        stmt.setInt(1, dto.getId());
        stmt.setDate(2, dto.getFecha());
        stmt.setString(3, dto.getCalidad());
        stmt.setString(4, dto.getDestino());
        stmt.setInt(5, dto.getCantidad_Recolectada());
        return stmt.executeUpdate()>0;
    }

    @Override
    public ProduccionDTO read(Object id) throws SQLException {
        stmt = connection.prepareStatement("call ProduccionRead(?)");
        stmt.setString(1,String.valueOf(id));
        rs = stmt.executeQuery();
        if(rs.next()){
            return new ProduccionDTO(
                    rs.getInt(1), 
                    rs.getDate(2), 
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
            );
        }
        return null;
    }

    @Override
    public List<ProduccionDTO> readAll() throws SQLException {
        stmt = connection.prepareStatement("call CustomerReadAll()");
        rs = stmt.executeQuery();
        List<ProduccionDTO> dtos = new ArrayList();
        while(rs.next()){
            dtos.add(new ProduccionDTO(
                    rs.getInt(1), 
                    rs.getDate(2), 
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
            ));
        }
        return dtos;
    }

    @Override
    public boolean update(ProduccionDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call CustomerUpdate(?,?,?)");
        stmt.setInt(1, dto.getId());
        stmt.setString(2, dto.getDestino());
        stmt.setInt(3, dto.getCantidad_Recolectada());
        return stmt.executeUpdate()>0;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        stmt = connection.prepareStatement("call CustomerDelete(?)");
        stmt.setString(1, String.valueOf(id));
        return stmt.executeUpdate()>0;
    }
}
