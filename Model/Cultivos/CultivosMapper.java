/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cultivos;

import Database.DataBase;
import Model.Mapper.Mapper;
import Model.Trabajador.TrabajadoresDAO;
import Model.Trabajador.TrabajadoresMapper;
import UtilDate.UtilDate;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CultivosMapper implements Mapper<Cultivos, CultivosDTO>{

    @Override
    public CultivosDTO toDto(Cultivos ent) {
        if(ent == null) return null;
        return new CultivosDTO(
                ent.getId(),
                ent.getCedula_trabajador().getCedula(),
                ent.getNombre(),
                ent.getTipo(),
                ent.getArea_Sembrada(),
                ent.getEstado_Crecimiento(),
                UtilDate.toSqlDate(ent.getFecha_Siembra()),
                UtilDate.toSqlDate(ent.getFecha_cosecha())
        );
    }

    @Override
    public Cultivos toEntity(CultivosDTO dto) {
        try {
            if(dto == null) return null;
            return new Cultivos(
                    dto.getId(),
                    new TrabajadoresMapper().toEntity(new TrabajadoresDAO(DataBase.getInstance().getConnection()).read(dto.getCedula_trabajador())),
                    dto.getNombre(),
                    dto.getTipo(),
                    dto.getArea_Sembrada(),
                    dto.getEstado_Crecimiento(),
                    UtilDate.toLocalDate(dto.getFecha_Siembra()),
                    UtilDate.toLocalDate(dto.getFecha_cosecha())
            );
        } catch (SQLException ex) {
            Logger.getLogger(CultivosMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
