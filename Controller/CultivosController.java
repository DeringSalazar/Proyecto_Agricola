/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DataBase;
import Factory.FactoryProducer;
import Model.Cultivos.Cultivos;
import Model.Cultivos.CultivosDAO;
import Model.Cultivos.CultivosDTO;
import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import Model.Trabajador.Trabajadores;
import Model.Trabajador.TrabajadoresDTO;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CultivosController {
    private DAO<CultivosDTO> dao;
    protected Mapper<Cultivos, CultivosDTO> mapper;
    private final View vista;
    private TrabajadorController control;

    public CultivosController(View vista, TrabajadorController control) {
        this.vista = vista;
        this.control=control;
        try {
            DAOFactory factory = FactoryProducer.getFactory("Cultivos");
            this.dao = (DAO<CultivosDTO>) factory.createDAO(DataBase.getInstance().getConnection());
            this.mapper = (Mapper<Cultivos, CultivosDTO>) factory.createrMapper();
        } catch (SQLException e) {
            vista.showError("Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public void insertar(Cultivos cultivo) {
        String cedulaTrabajador = null;
        try {
        TrabajadoresDTO trabajadorDTO = control.read(cedulaTrabajador, false);
        if (trabajadorDTO == null) {
            vista.showError("El trabajador con la cédula " + cedulaTrabajador + " no existe. Agrega una cédula existente.");
            return;
        }
        Trabajadores trabajador = control.mapper.toEntity(trabajadorDTO);
        cultivo.setCedula(trabajador);
        if (!validateRequired(cultivo)) {
            vista.showError("Faltan datos requeridos para el cultivo.");
            return;
        }

        // Crear el cultivo en la base de datos
        dao.create(mapper.toDto(cultivo));
        vista.showMessage("Cultivo registrado con éxito asociado al trabajador: " + trabajador.getNombre());
    } catch (SQLException ex) {
        vista.showError("Error al registrar el cultivo: " + ex.getMessage());
    }
}
    
    public CultivosDTO read(Object id, boolean showMessage) {
        try {
            CultivosDTO cultivoDTO = dao.read(id);
            if (cultivoDTO != null) {
                if (showMessage) {
                    vista.showMessage("Cultivo encontrado: " + cultivoDTO);
                }
                return cultivoDTO;
            } else {
                if (showMessage) {
                    vista.showError("Cultivo no encontrado con ID: " + id);
                }
                return null;
            }
        } catch (SQLException e) {
            vista.showError("Error al buscar el cultivo: " + e.getMessage());
            return null;
        }
    }
    
    public void readAll(){
        try {
            List<CultivosDTO> dtoList = dao.readAll();
            List<Cultivos> cultivosList = dtoList.stream()
                    .map(mapper::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            vista.showAll(cultivosList);
        } catch (SQLException ex) {
            vista.showError("Error al cargar los datos: "+ ex.getMessage());
        }
    }
        public void update(Cultivos cultivo) {
        try {
            CultivosDTO cultivoDTO = dao.read(cultivo.getId());
            if (cultivoDTO == null) {
                vista.showError("El cultivo con ID " + cultivo.getId() + " no existe.");
                return;
            }
            TrabajadoresDTO trabajadorDTO = control.read(cultivo.getCedula().getCedula(), false);
            if (trabajadorDTO == null) {
                vista.showError("El trabajador con la cédula " + cultivo.getCedula().getCedula() + " no existe. Agrega una cédula existente.");
                return;
            }
            Trabajadores trabajador = control.mapper.toEntity(trabajadorDTO);
            cultivo.setCedula(trabajador);
            if (!validateRequired(cultivo)) {
                vista.showError("Faltan datos requeridos para el cultivo.");
                return;
            }
            dao.update(mapper.toDto(cultivo));
            vista.showMessage("Cultivo actualizado con éxito.");
        } catch (SQLException ex) {
            vista.showError("Error al actualizar el cultivo: " + ex.getMessage());
        }
    }
        
      public void delete(Cultivos cultivo) {
        try {
            CultivosDTO cultivoDTO = dao.read(cultivo.getId());
            if (cultivoDTO == null) {
                vista.showError("El cultivo con ID " + cultivo.getId() + " no existe.");
                return;
            }
            dao.delete(cultivo.getId()); 
            vista.showMessage("Cultivo eliminado con éxito.");
        } catch (SQLException ex) {
            vista.showError("Error al eliminar el cultivo: " + ex.getMessage());
        }
    }

     
    public boolean validateRequired(Cultivos cultivo) {
        return  !cultivo.getNombre().trim().isEmpty()&&
                !cultivo.getTipo().trim().isEmpty()&&
                !cultivo.getEstado_Crecimiento().trim().isEmpty();
    }

    public boolean validatePK(int id) {
        try {
            return ((CultivosDAO) dao).validatePK(id);
        } catch (SQLException ex) {
            Logger.getLogger(CultivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
