/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.DAO;

import Model.Mapper.Mapper;
import java.sql.Connection;

public interface DAOFactory {
    public DAO<?> createDAO(Connection connection);
    public Mapper<?,?> createrMapper();
}
