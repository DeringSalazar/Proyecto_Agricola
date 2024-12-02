/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import java.sql.Connection;

/**
 *
 * @author 9567
 */
public class AlmacenamientoDAOFactory implements DAOFactory{

    @Override
    public DAO<AlmacenamientoDTO> createDAO(Connection connection) {
        return new AlmacenamientoDAO(connection);
    }

    @Override
    public Mapper<Almacenamiento, AlmacenamientoDTO> createrMapper() {
        return new AlmacenamientoMapper();
    }
    
}
