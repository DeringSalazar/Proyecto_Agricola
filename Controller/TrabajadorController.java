/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.View;

import Database.DataBase;
import Factory.FactoryProducer;
import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import Model.Trabajador.Trabajadores;
import Model.Trabajador.TrabajadoresDAO;
import Model.Trabajador.TrabajadoresDTO;
import Model.Trabajador.TrabajadoresMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author 9567
 */
public class TrabajadorController {
    private DAO<TrabajadoresDTO> dao;
    private final View view;
    protected final Mapper<Trabajadores, TrabajadoresDTO> mapper;

    public TrabajadorController(View view) {
        this.view = view;

        try {
            // Usamos la FactoryProducer para obtener la fábrica correcta para "Trabajadores"
            DAOFactory factory = FactoryProducer.getFactory("Trabajadores"); // Indicamos el tipo de entidad
            this.dao = (DAO<TrabajadoresDTO>) factory.createDAO(DataBase.getInstance().getConnection());
            this.mapper = (Mapper<Trabajadores, TrabajadoresDTO>) factory.createrMapper();
        } catch (SQLException e) {
            view.showError("Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void insertTrabajador(Trabajadores trabajador){
        if(trabajador == null || !validateRequired(trabajador)){
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if(!validatePK(trabajador.getCedula())){
                view.showError("La cedula ingresa ya esta registrada");
                return;
            }
            dao.create(mapper.toDto(trabajador));
            view.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
             view.showError("Error al guardar datos " + ex.getMessage());
        }
    }
    public TrabajadoresDTO read(Object id, boolean showMessage){
        try {
            TrabajadoresDTO trabajadoresDTO = dao.read(id);
            if (trabajadoresDTO != null) {
                if(showMessage){
                    view.showMessage("Trabajador no encontrado: " + trabajadoresDTO);
                }
                return trabajadoresDTO;
            } else {
                if(showMessage){
                    view.showError("Trabajador no encontrado con ID: " + id); 
                }
                return null;
            }
        } catch (SQLException e) {
            view.showError("Error al buscar el cultivo: " + e.getMessage());
            return null;
        }
    }
    public void readAll(){
        try {
            List<TrabajadoresDTO> dtoList = dao.readAll();
            List<Trabajadores> cultivosList = dtoList.stream()
                    .map(mapper::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            view.showAll(cultivosList);
        } catch (SQLException ex) {
            view.showError("Error al cargar los datos: "+ ex.getMessage());
        }
    }
    public void update(Trabajadores trabajadores){
        if(trabajadores==null || !validateRequired(trabajadores)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (validatePK(trabajadores.getCedula())){
                view.showError("La cedula ingresada no se encuentra");
                return;
            }
            dao.update(mapper.toDto(trabajadores));
            view.showMessage("Se actualizao correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al actualizar los datos: "+ ex.getMessage());
        }
    }
    
    public void delete(Trabajadores trabajadores){
        if(trabajadores==null || !validateRequired(trabajadores)) {
            view.showError("No hay ningun cliente cargado actualmente");
            return;
        }
        try {
            if (validatePK(trabajadores.getCedula())){
                view.showError("La cedula ingresada no se encuentra");
                return;
            }
            dao.delete(trabajadores.getCedula());
            view.showMessage("Se elimino correctamente.");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al eliminar los datos: "+ ex.getMessage());
        }
    }
        public boolean validatePK(String id) {
        return ((TrabajadoresDAO) dao).validatePK(id);
    }
        public boolean validateRequired(Trabajadores trabajadores) {
        return trabajadores.getCedula() != null &&
                trabajadores.getNombre() != null;
    }
}
