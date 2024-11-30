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
import Model.Cultivos.CultivosMapper;
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

public class CultivosController {
    private DAO<CultivosDTO> dao;
    private Mapper<Cultivos, CultivosDTO> mapper;
    private final View vista;

    public CultivosController(View vista) {
        this.vista = vista;

        try {
            // Usamos FactoryProducer para obtener la fábrica correcta para "Cultivos"
            DAOFactory factory = FactoryProducer.getFactory("Cultivos"); // Indicamos el tipo de entidad
            this.dao = (DAO<CultivosDTO>) factory.createDAO(DataBase.getInstance().getConnection());
            this.mapper = (Mapper<Cultivos, CultivosDTO>) factory.createrMapper();
        } catch (SQLException e) {
            vista.showError("Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    public void insertar(Cultivos cultivo){
        if(cultivo==null || !validateRequired(cultivo)) {
            vista.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(cultivo.getId())){
                vista.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.create(mapper.toDto(cultivo));
            vista.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            vista.showError("Ocurrio un error al guardar los datos: "+ ex.getMessage());
        }
    }
    
    public void read(Object id){
        try {
            CultivosDTO cultivoDTO = dao.read(id);
            if (cultivoDTO != null) {
                vista.showMessage("Cultivo encontrado: " + cultivoDTO);
            } else {
                vista.showError("Cultivo no encontrado con ID: " + id);
            }
        } catch (SQLException e) {
            vista.showError("Error al buscar el cultivo: " + e.getMessage());
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
     
    public boolean validateRequired(Cultivos cultivo) {
        return cultivo.getId() != 0 &&
                !cultivo.getNombre().trim().isEmpty()&&
                !cultivo.getTipo().trim().isEmpty()&&
                cultivo.getArea_Sembrada() != 0.0 &&
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
