/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Mapper;

public interface Mapper<Entity,Dto> {
    public Dto toDto(Entity ent);
    public Entity toEntity(Dto dto);
}