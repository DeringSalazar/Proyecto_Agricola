/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import java.sql.Connection;

/**
 *
 * @author 9567
 */
public class UsuarioDAOFactory implements DAOFactory{

    @Override
    public DAO<UsuarioDTO> createDAO(Connection connection) {
        return new UsuarioDAO(connection);
    }

    @Override
    public Mapper<Usuarios, UsuarioDTO> createrMapper() {
        return new UsuarioMapper();
    }
    
}
