/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;

import Model.Cultivos.CultivosDAOFactory;
import Model.DAO.DAOFactory;
import Model.Trabajador.TrabajadoresDAOFactory;
import Model.UsuarioDAOFactory;

/**
 *
 * @author 9567
 */
public class FactoryProducer {
    public static DAOFactory getFactory(String ent){
        switch (ent) {
           case "Usuario":
                return new UsuarioDAOFactory(); // Fábrica para Usuario
            case "Trabajadores":
                return new TrabajadoresDAOFactory(); // Fábrica para Trabajadores
            case "Cultivos":
                return new CultivosDAOFactory(); // Fábrica para Cultivos
            default:
                throw new IllegalArgumentException("No se reconoce la entidad: " + ent);
        }
    }
}
