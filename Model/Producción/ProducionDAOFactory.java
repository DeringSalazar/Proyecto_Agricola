/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producción;

import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import java.sql.Connection;

/**
 *
 * @author 9567
 */
public class ProducionDAOFactory implements DAOFactory{

    @Override
    public DAO<ProduccionDTO> createDAO(Connection connection) {
        return new ProducciónDAO(connection);
    }

    @Override
    public Mapper<Produccion, ProduccionDTO> createrMapper() {
        return new ProduccionMapper();
    }
    
}
