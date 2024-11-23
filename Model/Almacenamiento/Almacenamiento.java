/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import java.time.LocalDate;

public class Almacenamiento {
    private int cantidad;
    private LocalDate fecha_Ingreso;
    private LocalDate fecha_Retiro;

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFecha_Ingreso() {
        return fecha_Ingreso;
    }

    public LocalDate getFecha_Retiro() {
        return fecha_Retiro;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha_Ingreso(LocalDate fecha_Ingeso) {
        this.fecha_Ingreso = fecha_Ingeso;
    }

    public Almacenamiento(int cantidad, LocalDate fecha_Ingeso, LocalDate fecha_Retiro) {
        this.cantidad = cantidad;
        this.fecha_Ingreso = fecha_Ingeso;
        this.fecha_Retiro = fecha_Retiro;
    }

    public Almacenamiento() {
        this(0, LocalDate.now(), LocalDate.now());
    }
}
