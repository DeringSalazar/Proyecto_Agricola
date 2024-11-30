/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cultivos;

import Model.Trabajador.Trabajadores;
import java.time.LocalDate;

public class Cultivos {
    private int id;
    private Trabajadores cedula;
    private String nombre;
    private String tipo;
    private double area_Sembrada;
    private String estado_Crecimiento;
    private LocalDate fecha_Siembra;
    private LocalDate fecha_cosecha;

    public int getId() {
        return id;
    }  

    public Trabajadores getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getArea_Sembrada() {
        return area_Sembrada;
    }

    public String getEstado_Crecimiento() {
        return estado_Crecimiento;
    }

    public LocalDate getFecha_Siembra() {
        return fecha_Siembra;
    }

    public LocalDate getFecha_cosecha() {
        return fecha_cosecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setArea_Sembrada(double area_Sembrada) {
        this.area_Sembrada = area_Sembrada;
    }

    public void setEstado_Crecimiento(String estado_Crecimiento) {
        this.estado_Crecimiento = estado_Crecimiento;
    }

    public void setFecha_Siembra(LocalDate fecha_Siembra) {
        this.fecha_Siembra = fecha_Siembra;
    }

    public void setFecha_cosecha(LocalDate fecha_cosecha) {
        this.fecha_cosecha = fecha_cosecha;
    }

    public Cultivos(int id, Trabajadores cedula, String nombre, String tipo, double area_Sembrada, String estado_Crecimiento, LocalDate fecha_Siembra, LocalDate fecha_cosecha) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipo = tipo;
        this.area_Sembrada = area_Sembrada;
        this.estado_Crecimiento = estado_Crecimiento;
        this.fecha_Siembra = fecha_Siembra;
        this.fecha_cosecha = fecha_cosecha;
    }

    public Cultivos() {
        this(0, new Trabajadores(), "", "", 0.0, "", LocalDate.now(), LocalDate.now());
    }
    
    @Override
    public String toString() {
        return "ID: " + id +
                "\nNombre: " + nombre +
                "\nTipo: " + tipo +
                "\nÁrea Sembrada: " + area_Sembrada +
                "\nEstado de Crecimiento: " + estado_Crecimiento +
                "\nFecha de Siembra: " + fecha_Siembra +
                "\nFecha de Cosecha: " + fecha_cosecha;
    }
}
