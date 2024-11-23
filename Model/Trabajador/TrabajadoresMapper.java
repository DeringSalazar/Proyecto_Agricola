/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Trabajador;

import Model.Mapper.Mapper;

public class TrabajadoresMapper implements Mapper<Trabajadores, TrabajadoresDTO>{

    @Override
    public TrabajadoresDTO toDto(Trabajadores ent) {
        if(ent==null){
            return null;
        }
        return new TrabajadoresDTO(
                ent.getCedula(), 
                ent.getNombre(), 
                ent.getTelefono(), 
                ent.getCorreo(),
                ent.getPuesto(),
                ent.getHorario(), 
                ent.getSalario()
        );
    }

    @Override
    public Trabajadores toEntity(TrabajadoresDTO dto) {
        if(dto==null){
            return null;
        }
        return new Trabajadores(
                dto.getCedula(),
                dto.getNombre(), 
                dto.getTelefono(), 
                dto.getCorreo(), 
                dto.getPuesto(), 
                dto.getHorario(), 
                dto.getSalario()
        );
    }
    
}