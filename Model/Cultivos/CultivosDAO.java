/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cultivos;

import Model.DAO.DAO;
import Model.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CultivosDAO extends DAO<CultivosDTO>{

    public CultivosDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(CultivosDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call InsertarCultivos(?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getTipo());
            stmt.setDouble(4, dto.getArea_Sembrada());
            stmt.setString(5, dto.getEstado_Crecimiento());
            stmt.setDate(6, dto.getFecha_Siembra());
            stmt.setDate(7, dto.getFecha_cosecha());
            return stmt.executeUpdate()>0;
    }

    @Override
    public CultivosDTO read(Object id) throws SQLException {
        stmt = connection.prepareStatement("call CultivoRead(?)");
        stmt.setString(1,String.valueOf(id));
        rs = stmt.executeQuery();
        if (rs.next()) {
            return new CultivosDTO(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getDouble(4),
            rs.getString(5),
            rs.getDate(6),
            rs.getDate(7)
                    );
                }
        return null;
    }

    @Override
    public List<CultivosDTO> readAll() throws SQLException {
        stmt = connection.prepareStatement("call Cultivo ReadAll()");
        rs = stmt.executeQuery();
        List<CultivosDTO> dtos = new ArrayList();
        while(rs.next()){
            dtos.add(new CultivosDTO(
                    rs.getInt(1),
                 rs.getString(2),
                   rs.getString(3),
            rs.getDouble(4),
            rs.getString(5),
            rs.getDate(6),
            rs.getDate(7)
            ));
        }
        return dtos;
    }

    @Override
    public boolean update(CultivosDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call CultivoUpdate(?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, dto.getId());
        stmt.setString(2, dto.getTipo());
        stmt.setDouble(3, dto.getArea_Sembrada());
        stmt.setString(4, dto.getEstado_Crecimiento());
        stmt.setDate(5, dto.getFecha_Siembra());
        stmt.setDate(6, dto.getFecha_cosecha());
        return stmt.executeUpdate()>0;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        stmt = connection.prepareStatement("call CultivoDelete(?)");
        stmt.setString(1, String.valueOf(id));
        return stmt.executeUpdate()>0;
    }
    
}
