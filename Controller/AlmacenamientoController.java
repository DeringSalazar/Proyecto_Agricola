/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DataBase;
import Model.Almacenamiento.Almacenamiento;
import Model.Almacenamiento.AlmacenamientoDAO;
import Model.Almacenamiento.AlmacenamientoDTO;
import Model.Almacenamiento.AlmacenamientoMapper;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlmacenamientoController {
    private AlmacenamientoDAO dao;
    private View vista;
    private AlmacenamientoMapper mapper;

    public AlmacenamientoController(View vista) {
        this.vista = vista;
        mapper = new AlmacenamientoMapper();
        try {
            dao=new AlmacenamientoDAO(DataBase.getInstance().getConnection());
        } catch (IllegalStateException e) {
            vista.showError("Error: La conexión a la base de datos no está inicializada.");
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            vista.showError("Error al conectar con la Base de Datos");
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
            return dao.validatePK(id);
        } catch (SQLException ex) {
            return false;
        }
    }
}
