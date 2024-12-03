/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producción;

import Model.DAO.DAO;
import Model.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducciónDAO extends DAO<ProduccionDTO> {

    public ProducciónDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(ProduccionDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call InsertarProducciones(?,?,?,?,?)");
        stmt.setInt(1, dto.getIdCultivo());
        stmt.setDate(2, dto.getFecha());
        stmt.setString(3, dto.getCalidad());
        stmt.setString(4, dto.getDestino());
        stmt.setString(5, dto.getCantidad_Recolectada());
        
        return stmt.executeUpdate()>0;
    }

    @Override
    public ProduccionDTO read(Object id) throws SQLException {
        stmt = connection.prepareStatement("call ReadProduccion(?)");
        stmt.setString(1,String.valueOf(id));
        rs = stmt.executeQuery();
        if(rs.next()){
            return new ProduccionDTO(
                    rs.getInt(1), 
                    rs.getInt(2),
                    rs.getDate(3), 
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
        }
        return null;
    }

    @Override
    public List<ProduccionDTO> readAll() throws SQLException {
        stmt = connection.prepareStatement("call ReadAllProduccion()");
        rs = stmt.executeQuery();
        List<ProduccionDTO> dtos = new ArrayList();
        while(rs.next()){
            dtos.add(new ProduccionDTO(
                    rs.getInt(1), 
                    rs.getInt(2),
                    rs.getDate(3), 
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));
        }
        return dtos;
    }

    @Override
    public boolean update(ProduccionDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call UpdateProducciones(?,?,?)");
        stmt.setInt(1, dto.getId());
        stmt.setString(2, dto.getCantidad_Recolectada());
        stmt.setString(3, dto.getDestino());
        
        return stmt.executeUpdate()>0;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        stmt = connection.prepareStatement("call DeleteProduccion(?)");
        stmt.setInt(1, (int) id);
        return stmt.executeUpdate()>0;
    }
    
    public boolean validatePK(int id) {
        try {
            return read(id) == null; 
        } catch (SQLException ex) {
            Logger.getLogger(ProducciónDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }
}
