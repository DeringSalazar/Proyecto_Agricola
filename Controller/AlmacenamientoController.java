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

    public AlmacenamientoController(View vista) {
        this.vista = vista;
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
    
    public void insertar(Almacenamiento almacenamiento){
        if(almacenamiento==null || !validateRequired(almacenamiento)) {
            vista.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(almacenamiento.getCantidad())){
                vista.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.create(mapper.toDto(almacenamiento));
            vista.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            vista.showError("Ocurrio un error al guardar los datos: "+ ex.getMessage());
        }
    }
    
    public void read(Object cantidad){
        try {
            AlmacenamientoDTO almacenamientoDTO = dao.read(cantidad);
            if (almacenamientoDTO != null) {
                vista.showMessage("Cultivo encontrado: " + almacenamientoDTO);
            } else {
                vista.showError("Cultivo no encontrado con ID: " + cantidad);
            }
        } catch (SQLException e) {
            vista.showError("Error al buscar el cultivo: " + e.getMessage());
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
