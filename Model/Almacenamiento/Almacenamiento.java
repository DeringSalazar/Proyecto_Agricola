/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import Model.Producción.Produccion;
import java.time.LocalDate;

public class Almacenamiento {
    private int id;
    private Produccion idProducción;
    private int cantidad;
    private LocalDate fecha_Ingreso;
    private LocalDate fecha_Retiro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProducción(Produccion idProducción) {
        this.idProducción = idProducción;
    }

    public void setFecha_Retiro(LocalDate fecha_Retiro) {
        this.fecha_Retiro = fecha_Retiro;
    }

    public Produccion getIdProducción() {
        return idProducción;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFecha_Ingreso() {
        return fecha_Ingreso;
    }

    public LocalDate getFecha_Retiro() {
        return fecha_Retiro;
    }

    public Almacenamiento(int id, Produccion idProducción, int cantidad, LocalDate fecha_Ingreso, LocalDate fecha_Retiro) {
        this.id = id;
        this.idProducción = idProducción;
        this.cantidad = cantidad;
        this.fecha_Ingreso = fecha_Ingreso;
        this.fecha_Retiro = fecha_Retiro;
    }

    public Almacenamiento(int id, Produccion idProducción, int cantidad, LocalDate fecha_Ingreso) {
        this.id = id;
        this.idProducción = idProducción;
        this.cantidad = cantidad;
        this.fecha_Ingreso = fecha_Ingreso;
    }
    
    

    public Almacenamiento() {
        this(0, new Produccion(), 0, LocalDate.now(), LocalDate.now());
    }
}
