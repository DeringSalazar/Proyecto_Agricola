/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producción;

import Database.DataBase;
import Model.Cultivos.CultivosDAO;
import Model.Cultivos.CultivosMapper;
import Model.Mapper.Mapper;
import Model.Trabajador.TrabajadoresDAO;
import Model.Trabajador.TrabajadoresMapper;
import UtilDate.UtilDate;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProduccionMapper implements Mapper<Produccion, ProduccionDTO>{

    @Override
    public ProduccionDTO toDto(Produccion ent) {
       if(ent == null) return null;
        return new ProduccionDTO(
                ent.getId(),
                ent.getIdCultivo().getId(),
                UtilDate.toSqlDate(ent.getFecha()),
                ent.getCalidad(),
                ent.getDestino(),
                ent.getCantidad_Recolectada()
        );
    }

    @Override
    public Produccion toEntity(ProduccionDTO dto) {
        try {
            if(dto == null) return null;
            return new Produccion(
                    dto.getId(),
                    new CultivosMapper().toEntity(new CultivosDAO(DataBase.getInstance().getConnection()).read(dto.getIdCultivo())),
                    UtilDate.toLocalDate(dto.getFecha()),
                    dto.getCalidad(),
                    dto.getDestino(),
                    dto.getCantidad_Recolectada()
            );
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
