/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import Database.DataBase;
import Model.Mapper.Mapper;
import Model.Producción.ProduccionMapper;
import Model.Producción.ProducciónDAO;
import UtilDate.UtilDate;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlmacenamientoMapper implements Mapper<Almacenamiento, AlmacenamientoDTO>{

    @Override
    public AlmacenamientoDTO toDto(Almacenamiento ent) {
        return new AlmacenamientoDTO(
                ent.getId(),
                ent.getIdProducción().getId(),
                ent.getCantidad(),
                UtilDate.toSqlDate(ent.getFecha_Ingreso()),
                UtilDate.toSqlDate(ent.getFecha_Retiro())
                
        );
    }

    @Override
    public Almacenamiento toEntity(AlmacenamientoDTO dto) {
        try {
            if(dto == null) return null;
            return new Almacenamiento(
                    dto.getId(),
                    new ProduccionMapper().toEntity(new ProducciónDAO(DataBase.getInstance().getConnection()).read(dto.getIdProducción())),
                    dto.getCantidad(),
                    UtilDate.toLocalDate(dto.getFecha_Ingreso()),
                    UtilDate.toLocalDate(dto.getFecha_Retiro())
            );
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenamientoMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}

