/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producción;

import java.time.LocalDate;

public class Produccion {
    private int id; 
    private LocalDate fecha;
    private String calidad;
    private String destino;
    private int cantidad_Recolectada;

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getCalidad() {
        return calidad;
    }

    public String getDestino() {
        return destino;
    }

    public int getCantidad_Recolectada() {
        return cantidad_Recolectada;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setCantidad_Recolectada(int cantidad_Recolectada) {
        this.cantidad_Recolectada = cantidad_Recolectada;
    }

    public Produccion(int id, LocalDate fecha, String calidad, String destino, int cantidad_Recolectada) {
        this.id = id;
        this.fecha = fecha;
        this.calidad = calidad;
        this.destino = destino;
        this.cantidad_Recolectada = cantidad_Recolectada;
    }

    public Produccion() {
        this(0, LocalDate.now(), "", "", 0);
    }
}
