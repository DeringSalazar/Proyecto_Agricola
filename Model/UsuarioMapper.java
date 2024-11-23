/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Mapper.Mapper;

public class UsuarioMapper implements Mapper<Usuarios, UsuarioDTO>{

    @Override
    public UsuarioDTO toDto(Usuarios ent) {
        if(ent==null){
            return null;
        }
        return new UsuarioDTO(
                ent.getId(), 
                ent.getUser_name(),
                ent.getPassword(), 
                ent.getRol()
        );
    }

    @Override
    public Usuarios toEntity(UsuarioDTO dto) {
        if(dto==null){
            return null;
        }
        return new Usuarios(
                dto.getId(),
                dto.getUser_name(), 
                dto.getPassword(),
                dto.getRol()
        );
    }
}
