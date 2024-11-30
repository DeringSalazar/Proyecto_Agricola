/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producción;

import java.sql.Date;

public class ProduccionDTO {
    private int id; 
    private int idCultivo;
    private Date fecha;
    private String calidad;
    private String destino;
    private String cantidad_Recolectada;

    public int getId() {
        return id;
    }

    public int getIdCultivo() {
        return idCultivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCalidad() {
        return calidad;
    }

    public String getDestino() {
        return destino;
    }

    public String getCantidad_Recolectada() {
        return cantidad_Recolectada;
    }

    public ProduccionDTO(int id, int idCultivo, Date fecha, String calidad, String destino, String cantidad_Recolectada) {
        this.id = id;
        this.idCultivo = idCultivo;
        this.fecha = fecha;
        this.calidad = calidad;
        this.destino = destino;
        this.cantidad_Recolectada = cantidad_Recolectada;
    }
}
