/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;

import Model.Almacenamiento.Almacenamiento;
import Model.Almacenamiento.AlmacenamientoDAOFactory;
import Model.Cultivos.CultivosDAOFactory;
import Model.DAO.DAOFactory;
import Model.Producción.ProducionDAOFactory;
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
                return new UsuarioDAOFactory(); 
            case "Trabajadores":
                return new TrabajadoresDAOFactory(); 
            case "Cultivos":
                return new CultivosDAOFactory(); 
            case "Produccion":
                return new ProducionDAOFactory();
            case "Almacenamiento":
                return new AlmacenamientoDAOFactory();
            default:
                throw new IllegalArgumentException("No se reconoce la entidad: " + ent);
        }
    }
}
