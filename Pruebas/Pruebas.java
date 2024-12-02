/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controller.CultivosController;
import Controller.ProduccionController;
import Controller.TrabajadorController;
import Model.Cultivos.Cultivos;
import Model.Producción.Produccion;
import View.View;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.List;

public class Pruebas implements View<Produccion>{
private CultivosController controller;
    private TrabajadorController trabajo;

    public Pruebas() {
        this.controller = new CultivosController(this, trabajo);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // Crear una instancia de Pruebas
        Pruebas prueba = new Pruebas();
        
        // Crear una instancia del controlador de producción
        ProduccionController produccionController = new ProduccionController(prueba, prueba.controller);

        // Crear un objeto Cultivos (suponiendo que ya tienes un cultivo existente con ID 1)
        Cultivos cultivo = new Cultivos();
        cultivo.setId(1); // Asignar un ID de cultivo existente

        // Crear un objeto Produccion con los datos necesarios
        Produccion produccion = new Produccion();
        produccion.setIdCultivo(cultivo);
        produccion.setFecha(LocalDate.now());
        produccion.setCalidad("Alta");
        produccion.setDestino("Mercado Local");
        produccion.setCantidad_Recolectada("100 kg");

        // Llamar al método insertar
        produccionController.insertar(produccion);
    }

    @Override
    public void show(Produccion ent) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showAll(List<Produccion> ents) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showMessage(String msg) {
        System.out.println(""+msg);
    }

    @Override
    public void showSuccess(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showError(String err) {
       System.out.println(""+err);
    }

    @Override
    public boolean validateRequired() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
