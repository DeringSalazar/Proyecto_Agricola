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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        if(trabajador == null || trabajador.getCedula() == null || trabajador.getCedula().isEmpty()){
            view.showError("Debes insertar la cedula del trabajador");
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
             view.showError("Error al registrar trabajador: " + ex.getMessage());
        }
    }
    public TrabajadoresDTO obtenerTrabajador(String cedula){
        if(cedula==null || cedula.isEmpty()){
            view.showError("Ingresa la cedula para buscar un trabajador");
            return null;
        }
        try {
            TrabajadoresDTO trabajador = dao.read(cedula);
            if(trabajador == null){
                view.showError("Trabajador no encontrado");
            }
            return trabajador;
        } catch (SQLException ex) {
             view.showError("Error al buscar trabajador: " + ex.getMessage());
            return null;
        }
    }
    public List<TrabajadoresDTO> ObtenerTodosTrabajadores(){
        try {
            List<TrabajadoresDTO> list = dao.readAll();
            if(list.isEmpty()){
                view.showError("No hay trabajadores registrados en la base de datos");
            }
            return list;
        } catch (SQLException ex) {
            view.showError("Error al obtener trabajadores: " + ex.getMessage());
            return null;
        }
    }
    public boolean actualizarTrabajador(TrabajadoresDTO trabajador){
        if(trabajador == null || trabajador.getCedula() == null || trabajador.getCedula().isEmpty()){
            view.showError("La cedula del trabajador es obligatoria para poder actualizar los datos de un trabajador");
            return false;
        }
        try {
            boolean trabajadorActualizado = dao.update(trabajador);
            if(trabajadorActualizado){
                view.showMessage("Trabajador actualizado con exito");
            }else{
                view.showError("Error al actualizar trabajador");
            }
            return trabajadorActualizado;
        } catch (SQLException ex) {
             view.showError("Error al actualizar trabajador: " + ex.getMessage());
            return false;
        }
    }
    public boolean eliminarTrabajador(String cedula){
        if(cedula == null || cedula.isEmpty()){
            view.showError("La cedula es obligatoria para eliminar un trabajador");
            return false;
        } 
        try {
            boolean eliminadoTrabajador = dao.delete(cedula);
            if(eliminadoTrabajador){
                view.showMessage("Se elimino con exito al trabajador");
            }else{
                view.showError("Error al eliminar un trabajador");
            }
            return eliminadoTrabajador;
        } catch (SQLException ex) {
            view.showError("Error al eliminar trabajador: " + ex.getMessage());
            return false;
        }
    }
        public boolean validatePK(String id) {
        return dao.validatePK(id);
    }
}
