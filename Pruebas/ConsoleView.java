/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import View.View;
import View.ViewList;
import java.util.List;

/**
 *
 * @author 9567
 */
public class ConsoleView implements View {

    @Override
    public void show(Object ent) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showAll(List ents) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showMessage(String msg) {
        System.out.println("MENSAJE: " + msg);
    }

    @Override
    public void showSuccess(String msg) {
        System.out.println("ÉXITO: " + msg);
    }

    @Override
    public void showError(String err) {
        System.err.println("ERROR: " + err);
    }

    @Override
    public boolean validateRequired() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}

