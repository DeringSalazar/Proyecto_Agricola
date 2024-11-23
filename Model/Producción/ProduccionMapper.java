/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producción;

import Model.Mapper.Mapper;
import UtilDate.UtilDate;

public class ProduccionMapper implements Mapper<Produccion, ProduccionDTO>{

    @Override
    public ProduccionDTO toDto(Produccion ent) {
       if(ent == null) return null;
        return new ProduccionDTO(
                ent.getId(),
                UtilDate.toSqlDate(ent.getFecha()),
                ent.getCalidad(),
                ent.getDestino(),
                ent.getCantidad_Recolectada()
        );
    }

    @Override
    public Produccion toEntity(ProduccionDTO dto) {
        if(dto == null) return null;
       return new Produccion(
               dto.getId(),
               UtilDate.toLocalDate(dto.getFecha()),
               dto.getCalidad(),
               dto.getDestino(),
               dto.getCantidad_Recolectada()
       );
    }
    
}
