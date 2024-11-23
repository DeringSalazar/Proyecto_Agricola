/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import Model.Mapper.Mapper;
import UtilDate.UtilDate;

public class AlmacenamientoMapper implements Mapper<Almacenamiento, AlmacenamientoDTO>{

    @Override
    public AlmacenamientoDTO toDto(Almacenamiento ent) {
        return new AlmacenamientoDTO(
                ent.getCantidad(),
                UtilDate.toSqlDate(ent.getFecha_Ingreso()),
                UtilDate.toSqlDate(ent.getFecha_Retiro())
                
        );
    }

    @Override
    public Almacenamiento toEntity(AlmacenamientoDTO dto) {
       if(dto == null) return null;
       return new Almacenamiento(
              dto.getCantidad(),
                UtilDate.toLocalDate(dto.getFecha_Ingreso()),
                UtilDate.toLocalDate(dto.getFecha_Retiro())
       );
    }
    
}

