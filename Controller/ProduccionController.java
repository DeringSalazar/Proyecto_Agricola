/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DataBase;
import Model.Producción.Produccion;
import Model.Producción.ProduccionDTO;
import Model.Producción.ProduccionMapper;
import Model.Producción.ProducciónDAO;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProduccionController {
    private ProducciónDAO dao;
    private View vista;
    private ProduccionMapper mapper;

    public ProduccionController(View vista) {
        this.vista = vista;
        mapper = new ProduccionMapper();
        try {
            dao=new ProducciónDAO(DataBase.getInstance().getConnection());
        } catch (IllegalStateException e) {
            vista.showError("Error: La conexión a la base de datos no está inicializada.");
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            vista.showError("Error al conectar con la Base de Datos");
        }
    }
    
    public void insertar(Produccion produccion){
        if(produccion==null || !validateRequired(produccion)) {
            vista.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(produccion.getId())){
                vista.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.create(mapper.toDto(produccion));
            vista.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            vista.showError("Ocurrio un error al guardar los datos: "+ ex.getMessage());
        }
    }
    
    public void read(Object id){
        try {
            ProduccionDTO produccionDTO = dao.read(id);
            if (produccionDTO != null) {
                vista.showMessage("Cultivo encontrado: " + produccionDTO);
            } else {
                vista.showError("Cultivo no encontrado con ID: " + id);
            }
        } catch (SQLException e) {
            vista.showError("Error al buscar el cultivo: " + e.getMessage());
        }
    }
    
    public void readAll(){
        try {
            List<ProduccionDTO> dtoList = dao.readAll();
            List<Produccion> produccionList = dtoList.stream()
                    .map(mapper::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            vista.showAll(produccionList);
        } catch (SQLException ex) {
            vista.showError("Error al cargar los datos: "+ ex.getMessage());
        }
    }
     
    public boolean validateRequired(Produccion produccion) {
        return produccion.getId() != 0 &&
                produccion.getFecha() != null &&
                !produccion.getCalidad().trim().isEmpty()&&
                !produccion.getDestino().trim().isEmpty() &&
                produccion.getCantidad_Recolectada() != 0;
    }

    public boolean validatePK(int id) {
        try {
            return dao.validatePK(id);
        } catch (SQLException ex) {
            return false;
        }
    }
}
