/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Sistema;

import Controller.AlmacenamientoController;
import Controller.ProduccionController;
import Model.Almacenamiento.Almacenamiento;
import Model.Producción.Produccion;
import UtilDate.UtilDate;
import View.FrmMenuCPA;
import View.FrmMenuTCPA;
import View.View;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FrmAlmacenamiento extends javax.swing.JFrame implements View<Almacenamiento>{
    private AlmacenamientoController controller;
    List<Almacenamiento> ents;
    private ProduccionController trabajador;
    private Produccion produccion;
    private Almacenamiento almacenamiento;
    private DefaultTableModel tablemodel;
    TableRowSorter<TableModel> sorter;
    
    public FrmAlmacenamiento() {
        initComponents();
        controller = new AlmacenamientoController(this, trabajador);
        this.setLocationRelativeTo(this);
        tablemodel = (DefaultTableModel) TxtDatos1.getModel();
        sorter = new TableRowSorter<>(this.TxtDatos1.getModel());
        TxtDatos1.setRowSorter(sorter);
        
        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.readAll(); // Llama al método readAll del controlador
            }
        });
    }

    public void setEnts(List<Almacenamiento> ents) {
        this.ents = ents;
        if (ents == null || tablemodel == null) return;
        tablemodel.setNumRows(0);
        
        ents.forEach(Almacenamiento -> tablemodel.addRow(
                new Object[]{
                    Almacenamiento.getId(),
                    Almacenamiento.getIdProducción().getId(),
                    Almacenamiento.getCantidad(),
                    UtilDate.toString(Almacenamiento.getFecha_Ingreso()),
                    UtilDate.toString(Almacenamiento.getFecha_Retiro())
                }
        ));
    }
    
    @Override
    public void show(Almacenamiento ent) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showAll(List<Almacenamiento> ents) {
        if (ents == null || ents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay almacenamientos disponibles", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        setEnts(ents);
    }

    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showSuccess(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showError(String err) {
        JOptionPane.showMessageDialog(this, err, "Error", JOptionPane.ERROR_MESSAGE); 
    }

    @Override
    public boolean validateRequired() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   private void clear() {
        TxtRetiro.setText("");
        jTextField10.setText("");
        jTextField9.setText("");
        jTextField22.setText("");
        TxtRetiro.setText("");
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnAlmacenamiento = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TxtID = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtDatos1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        TxtRetiro = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Eliminar = new javax.swing.JButton();
        Agregar = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnAlmacenamiento.setBackground(new java.awt.Color(255, 255, 255));
        PnAlmacenamiento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel8.setText("Almacenamiento");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        TxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtIDKeyReleased(evt);
            }
        });
        jPanel6.add(TxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 580, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/buscar.png"))); // NOI18N
        jButton7.setBorder(null);
        jPanel6.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 40, 40));

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/left-chevron_10117734.png"))); // NOI18N
        jButton25.setBorder(null);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 40, 40));

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/arrow-left_10027259 (1).png"))); // NOI18N
        jButton26.setBorder(null);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 40, 40));

        PnAlmacenamiento.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 60));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtDatos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Producción", "Cantidad", "Ingreso", "Retiro"
            }
        ));
        jScrollPane2.setViewportView(TxtDatos1);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 640, 330));

        PnAlmacenamiento.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 660, 350));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel10.setText("Cantidad:");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        TxtRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtRetiroActionPerformed(evt);
            }
        });
        jPanel8.add(TxtRetiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 260, -1));

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 300, -1));

        jLabel14.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        Eliminar.setBackground(new java.awt.Color(204, 204, 204));
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/borrar.png"))); // NOI18N
        Eliminar.setBorder(null);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPanel8.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 90, 80));

        Agregar.setBackground(new java.awt.Color(204, 204, 204));
        Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/expediente.png"))); // NOI18N
        Agregar.setBorder(null);
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });
        jPanel8.add(Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, 80));

        Actualizar.setBackground(new java.awt.Color(204, 204, 204));
        Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/actualizar-datos.png"))); // NOI18N
        Actualizar.setBorder(null);
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPanel8.add(Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 90, 80));

        jLabel24.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel24.setText("Fecha de retiro:  ");
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 260, -1));

        jLabel11.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel11.setText("Producción:");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });
        jPanel8.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 300, -1));

        jLabel25.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel25.setText("Fecha de ingreso:  ");
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        PnAlmacenamiento.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 410, 350));

        getContentPane().add(PnAlmacenamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtRetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtRetiroActionPerformed
        
    }//GEN-LAST:event_TxtRetiroActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void TxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtIDKeyReleased
        String search = TxtID.getText();
        if (search.trim().isEmpty()) {
             sorter.setRowFilter(null);
        } else {
             sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search, 0));
        }
    }//GEN-LAST:event_TxtIDKeyReleased

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
        // Inicializa el objeto cultivos antes de usarlo
        int codigo = Integer.parseInt(TxtRetiro.getText().trim());
        int cantidad = Integer.parseInt(jTextField9.getText().trim());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(jTextField22.getText().trim(), formato);
        LocalDate retiro = LocalDate.parse(TxtRetiro.getText().trim(), formato);
        // Asegúrate de inicializar correctamente el objeto Trabajadores
        Produccion produccion = new Produccion();
        produccion.setId(Integer.parseInt(jTextField10.getText().trim()));
        // Inicializa el objeto cultivos
        almacenamiento = new Almacenamiento(
                codigo,
                produccion,
                cantidad,
                fecha,
                retiro
        );
        // Asegúrate de que cultivos no sea null antes de validar
        if (!controller.validateRequired(almacenamiento)) {
            JOptionPane.showMessageDialog(this, "Faltan datos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        controller.insertar(almacenamiento);
        clear();
    }//GEN-LAST:event_AgregarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        if (!controller.validateRequired(almacenamiento)) {
        JOptionPane.showMessageDialog(this, "Faltan datos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try {
        int codigo = Integer.parseInt(TxtRetiro.getText().trim());
        int cantidad = Integer.parseInt(jTextField9.getText().trim());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(jTextField22.getText().trim(), formato);
        LocalDate retiro = LocalDate.parse(TxtRetiro.getText().trim(), formato);
        Produccion produccion = new Produccion();
        Almacenamiento AlmacenamientoActualizado = new Almacenamiento(
            codigo,
            produccion, 
            cantidad,
            fecha,
                retiro
        );
        controller.update(AlmacenamientoActualizado);
        clear();
        // Actualizar la tabla con los nuevos datos
        controller.readAll();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_ActualizarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int codigo = Integer.parseInt(TxtRetiro.getText().trim());
    if (codigo < 0) {
        JOptionPane.showMessageDialog(this, "Seleccione un trabajador para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    int confirm = JOptionPane.showConfirmDialog(  this,"¿Está seguro de que desea eliminar al trabajador con cédula: " + codigo + "?","Confirmar eliminación",JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return; 
    }
    try {
        Almacenamiento AlmacenamientoAEliminar = new Almacenamiento();
        AlmacenamientoAEliminar.setId(codigo);
        controller.delete(AlmacenamientoAEliminar);
        clear();
        controller.readAll();
        JOptionPane.showMessageDialog(this, "Trabajador eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (HeadlessException e) {
        JOptionPane.showMessageDialog(this, "Error al eliminar el trabajador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_EliminarActionPerformed

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        if(!jTextField10.isEditable()) return;
        int id = Integer.parseInt(jTextField10.getText());
        if (!controller.validatePK(id)){
            JOptionPane.showMessageDialog(this, "La codigo ingresado ya esta se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE); 
            jTextField10.setText("");
        }
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        this.setVisible(false);
        FrmMenuTCPA frm = new FrmMenuTCPA();
        frm.setVisible(true);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        this.setVisible(false);
        FrmMenuCPA frm = new FrmMenuCPA();
        frm.setVisible(true);
    }//GEN-LAST:event_jButton25ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAlmacenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAlmacenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAlmacenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAlmacenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAlmacenamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Agregar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JPanel PnAlmacenamiento;
    private javax.swing.JTable TxtDatos1;
    private javax.swing.JTextField TxtID;
    private javax.swing.JTextField TxtRetiro;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
