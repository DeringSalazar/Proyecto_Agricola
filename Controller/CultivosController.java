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
    public Mapper<Cultivos, CultivosDTO> mapper;
    private final View viewerror;
    private TrabajadorController control;

    public CultivosController(View vista, TrabajadorController control) {
        this.viewerror = vista;
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
    
    public void insertar(Cultivos cultivo) throws SQLException {
        if (!validateRequired(cultivo)) {
            viewerror.showError("Faltan datos requeridos para el cultivo.");
            return;
        }
        try{
            // Crear el cultivo en la base de datos
            dao.create(mapper.toDto(cultivo));
            viewerror.showMessage("Cultivo registrado con éxito asociado al trabajador: " + cultivo.getId());
        } catch (SQLException ex) {
            viewerror.showError("Error al registrar el cultivo: " + ex.getMessage());
        }
    }
    
    public CultivosDTO read(Object id, boolean showMessage) {
        try {
            CultivosDTO cultivoDTO = dao.read(id);
            if (cultivoDTO != null) {
                if (showMessage) {
                    viewerror.showMessage("Cultivo encontrado: " + cultivoDTO);
                }
                return cultivoDTO;
            } else {
                if (showMessage) {
                    viewerror.showError("Cultivo no encontrado con ID: " + id);
                }
                return null;
            }
        } catch (SQLException e) {
            viewerror.showError("Error al buscar el cultivo: " + e.getMessage());
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
            viewerror.showAll(cultivosList);
        } catch (SQLException ex) {
            viewerror.showError("Error al cargar los datos: "+ ex.getMessage());
        }
    }
        public void update(Cultivos cultivo) {
        try {
            CultivosDTO cultivoDTO = dao.read(cultivo.getId());
            if (cultivoDTO == null) {
                viewerror.showError("El cultivo con ID " + cultivo.getId() + " no existe.");
                return;
            }
            TrabajadoresDTO trabajadorDTO = control.read(cultivo.getCedula_trabajador().getCedula(), false);
            if (trabajadorDTO == null) {
                viewerror.showError("El trabajador con la cédula " + cultivo.getCedula_trabajador().getCedula() + " no existe. Agrega una cédula existente.");
                return;
            }
            Trabajadores trabajador = control.mapper.toEntity(trabajadorDTO);
            cultivo.setCedula_trabajador(trabajador);
            if (!validateRequired(cultivo)) {
                viewerror.showError("Faltan datos requeridos para el cultivo.");
                return;
            }
            dao.update(mapper.toDto(cultivo));
            viewerror.showMessage("Cultivo actualizado con éxito.");
        } catch (SQLException ex) {
            viewerror.showError("Error al actualizar el cultivo: " + ex.getMessage());
        }
    }
        
      public void delete(Cultivos cultivo) {
        try {
            CultivosDTO cultivoDTO = dao.read(cultivo.getId());
            if (cultivoDTO == null) {
                viewerror.showError("El cultivo con ID " + cultivo.getId() + " no existe.");
                return;
            }
            dao.delete(cultivo.getId()); 
            viewerror.showMessage("Cultivo eliminado con éxito.");
        } catch (SQLException ex) {
            viewerror.showError("Error al eliminar el cultivo: " + ex.getMessage());
        }
    }

     
    public boolean validateRequired(Cultivos cultivo) {
        if (cultivo == null) {
        return false;
    }
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
