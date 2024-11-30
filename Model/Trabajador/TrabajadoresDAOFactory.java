/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Trabajador;

import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import java.sql.Connection;

/**
 *
 * @author 9567
 */
public class TrabajadoresDAOFactory implements DAOFactory{

    @Override
    public DAO<TrabajadoresDTO> createDAO(Connection connection) {
        return new TrabajadoresDAO(connection);
    }

    @Override
    public Mapper<Trabajadores, TrabajadoresDTO> createrMapper() {
        return new TrabajadoresMapper();
    }
    
}
