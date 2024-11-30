/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Almacenamiento;

import Model.Producción.Produccion;
import java.sql.Date;

public class AlmacenamientoDTO {
    private int id;
    private int idProducción;
    private int cantidad;
    private Date fecha_Ingreso;
    private Date fecha_Retiro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducción() {
        return idProducción;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Date getFecha_Ingreso() {
        return fecha_Ingreso;
    }

    public Date getFecha_Retiro() {
        return fecha_Retiro;
    }

    public void setFecha_Retiro(Date fecha_Retiro) {
        this.fecha_Retiro = fecha_Retiro;
    }
    

    public AlmacenamientoDTO(int id, int idProducción, int cantidad, Date fecha_Ingreso, Date fecha_Retiro) {
        this.id = id;
        this.idProducción = idProducción;
        this.cantidad = cantidad;
        this.fecha_Ingreso = fecha_Ingreso;
        this.fecha_Retiro = fecha_Retiro;
    }
}
