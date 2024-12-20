/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cultivos;

import java.sql.Date;

public class CultivosDTO {
    private int id;
    private String cedula_trabajador;
    private String nombre;
    private String tipo;
    private double area_Sembrada;
    private String estado_Crecimiento;
    private Date fecha_Siembra;
    private Date fecha_cosecha;

    public int getId() {
        return id;
    }

    public String getCedula_trabajador() {
        return cedula_trabajador;
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

    public Date getFecha_Siembra() {
        return fecha_Siembra;
    }

    public Date getFecha_cosecha() {
        return fecha_cosecha;
    }

    public CultivosDTO(int id, String cedula, String nombre, String tipo, double area_Sembrada, String estado_Crecimiento, Date fecha_Siembra, Date fecha_cosecha) {
        this.id = id;
        this.cedula_trabajador = cedula;
        this.nombre = nombre;
        this.tipo = tipo;
        this.area_Sembrada = area_Sembrada;
        this.estado_Crecimiento = estado_Crecimiento;
        this.fecha_Siembra = fecha_Siembra;
        this.fecha_cosecha = fecha_cosecha;
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
