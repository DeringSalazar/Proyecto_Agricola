/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.View;

import Database.DataBase;
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
    private TrabajadoresDAO dao;
    private final View view;
    private final TrabajadoresMapper mapper;
    
    
    public TrabajadorController(View view) throws SQLException{
        this.view=view;
        mapper=new TrabajadoresMapper();
        try{
            Connection connection = DataBase.getInstance().getConnection();
            if(connection == null || connection.isClosed()){
                throw new IllegalStateException("Conexión a la base de datos no válida.");
            }
            this.dao = new TrabajadoresDAO(connection);
        }catch(IllegalStateException e) {
            view.showError("Error: La conexion a la base de datos: "+e.getMessage());
            throw new RuntimeException(e);
        }catch(SQLException e){
            view.showError("Error al verificar la conexion");
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
                view.showMessage("La cedula ingresa ya esta registrada");
                return;
            }
            dao.create(mapper.toDto(trabajador));
            view.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
             view.showError("Error al guardar datos " + ex.getMessage());
        }
    }
    public void read(Object id){
        try {
            TrabajadoresDTO trabajadoresDTO = dao.read(id);
            if (trabajadoresDTO != null) {
                view.showMessage("Cultivo encontrado: " + trabajadoresDTO);
            } else {
                view.showError("Cultivo no encontrado con ID: " + id);
            }
        } catch (SQLException e) {
            view.showError("Error al buscar el cultivo: " + e.getMessage());
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
                view.showError("La cedula ingresada no se encuentra registrada");
                return;
            }
            dao.update(mapper.toDto(trabajadores));
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
                view.showError("La cedula ingresada no ya se encuentra registrada");
                return;
            }
            dao.delete(trabajadores.getCedula());
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al eliminar los datos: "+ ex.getMessage());
        }
    }
        public boolean validatePK(String id) {
        return dao.validatePK(id);
    }
        public boolean validateRequired(Trabajadores trabajadores) {
        return trabajadores.getCedula() != null &&
                trabajadores.getNombre() != null;
    }
}