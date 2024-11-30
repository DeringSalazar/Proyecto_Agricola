/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cultivos;

import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import java.sql.Connection;

/**
 *
 * @author 9567
 */
public class CultivosDAOFactory implements DAOFactory{

    @Override
    public DAO<CultivosDTO> createDAO(Connection connection) {
        return new CultivosDAO(connection);
    }

    @Override
    public Mapper<Cultivos, CultivosDTO> createrMapper() {
        return new CultivosMapper();
    }
    
}
