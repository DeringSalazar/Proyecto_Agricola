/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import java.sql.Date;

public class AlmacenamientoDTO {
    private int cantidad;
    private Date fecha_Ingreso;
    private Date fecha_Retiro;

    public int getCantidad() {
        return cantidad;
    }

    public Date getFecha_Ingreso() {
        return fecha_Ingreso;
    }

    public Date getFecha_Retiro() {
        return fecha_Retiro;
    }

    public AlmacenamientoDTO(int cantidad, Date fecha_Ingeso, Date fecha_Retiro) {
        this.cantidad = cantidad;
        this.fecha_Ingreso = fecha_Ingeso;
        this.fecha_Retiro = fecha_Retiro;
    }
}
