/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DataBase;
import Factory.FactoryProducer;
import Model.Almacenamiento.Almacenamiento;
import Model.Almacenamiento.AlmacenamientoDAO;
import Model.Almacenamiento.AlmacenamientoDTO;
import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import Model.Producción.ProduccionDTO;
import Model.Producción.ProducciónDAO;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AlmacenamientoController {
    private DAO<AlmacenamientoDTO> dao;
    private View vista;
    private Mapper<Almacenamiento, AlmacenamientoDTO> mapper;
    private ProduccionController produccionController;

    public AlmacenamientoController(View vista,ProduccionController produccionController) {
        this.vista = vista;
        this.produccionController=produccionController;
        try {
            // Usamos FactoryProducer para obtener la fábrica correcta para "Cultivos"
            DAOFactory factory = FactoryProducer.getFactory("Produccion"); // Indicamos el tipo de entidad
            this.dao = (DAO<AlmacenamientoDTO>) factory.createDAO(DataBase.getInstance().getConnection());
            this.mapper = (Mapper<Almacenamiento, AlmacenamientoDTO>) factory.createrMapper();
        } catch (SQLException e) {
            vista.showError("Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void insertar(Almacenamiento almacenamiento) {
        if (almacenamiento == null || !validateRequired(almacenamiento)) {
            vista.showError("Faltan datos requeridos.");
            return;
        }
        try {
            ProduccionDTO produccion = produccionController.read(almacenamiento.getIdProducción(),false);
            if (produccion == null) {
                vista.showError("La producción asociada con ID " + almacenamiento.getIdProducción() + " no existe.");
                return;
            }
            if (!validatePK(almacenamiento.getCantidad())) {
                vista.showError("El almacenamiento con la cantidad " + almacenamiento.getCantidad() + " ya existe.");
                return;
            }
            dao.create(mapper.toDto(almacenamiento));
            vista.showMessage("Almacenamiento registrado correctamente.");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al registrar el almacenamiento: " + ex.getMessage());
        }
    }
    
    public void read(Object cantidad){
        try {
            AlmacenamientoDTO almacenamientoDTO = dao.read(cantidad);
            if (almacenamientoDTO != null) {
                vista.showMessage("Almacenamiento encontrado: " + almacenamientoDTO);
            } else {
                vista.showError("Almacenamiento no encontrado con ID: " + cantidad);
            }
        } catch (SQLException e) {
            vista.showError("Error al buscar el Almacenamiento: " + e.getMessage());
        }
    }
    
    public void readAll(){
        try {
            List<AlmacenamientoDTO> dtoList = dao.readAll();
            List<Almacenamiento> almacenamientoList = dtoList.stream()
                    .map(mapper::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            vista.showAll(almacenamientoList);
        } catch (SQLException ex) {
            vista.showError("Error al cargar los datos: "+ ex.getMessage());
        }
    }
    public void update(Almacenamiento almacenamiento) {
        if (almacenamiento == null || !validateRequired(almacenamiento)) {
            vista.showError("Faltan datos requeridos.");
            return;
        }
        try {
            ProduccionDTO produccion = produccionController.read(almacenamiento.getIdProducción(),false);
            if (produccion == null) {
                vista.showError("La producción asociada con ID " + almacenamiento.getIdProducción() + " no existe.");
                return;
            }
            if (!validatePK(almacenamiento.getCantidad())) {
                vista.showError("El almacenamiento con la cantidad " + almacenamiento.getCantidad() + " no existe.");
                return;
            }
            dao.update(mapper.toDto(almacenamiento));
            vista.showMessage("Almacenamiento actualizado correctamente.");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al actualizar el almacenamiento: " + ex.getMessage());
        }
    }
    
    public void delete(int cantidad) {
        try {
            // Verificar si el almacenamiento existe antes de eliminarlo
            if (!validatePK(cantidad)) {
                vista.showError("El almacenamiento con la cantidad " + cantidad + " no existe.");
                return;
            }

            dao.delete(cantidad);
            vista.showMessage("Almacenamiento eliminado correctamente.");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al eliminar el almacenamiento: " + ex.getMessage());
        }
    }
     
    public boolean validateRequired(Almacenamiento almacenamiento) {
        return almacenamiento.getCantidad() != 0 &&
                almacenamiento.getFecha_Ingreso() != null;
    }

    public boolean validatePK(int id) {
        try {
            return ((AlmacenamientoDAO) dao).validatePK(id);
        } catch (SQLException ex) {
            Logger.getLogger(CultivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
