/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cultivos;

import Model.Mapper.Mapper;
import UtilDate.UtilDate;

public class CultivosMapper implements Mapper<Cultivos, CultivosDTO>{

    @Override
    public CultivosDTO toDto(Cultivos ent) {
        if(ent == null) return null;
        return new CultivosDTO(
                ent.getId(),
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
        if(dto == null) return null;
       return new Cultivos(
               dto.getId(),
               dto.getNombre(),
               dto.getTipo(),
               dto.getArea_Sembrada(),
               dto.getEstado_Crecimiento(),
               UtilDate.toLocalDate(dto.getFecha_Siembra()),
               UtilDate.toLocalDate(dto.getFecha_cosecha())
       );
    }
    
}
