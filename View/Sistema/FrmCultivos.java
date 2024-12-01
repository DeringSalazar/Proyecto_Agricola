/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Sistema;

import Controller.CultivosController;
import Controller.TrabajadorController;
import Model.Cultivos.Cultivos;
import Model.Trabajador.Trabajadores;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FrmCultivos extends javax.swing.JFrame {
    private CultivosController controller;
    private TrabajadorController trabajador;
    private Trabajadores trabajadores;
    private Cultivos cultivos;
    private DefaultTableModel tablemodel;
    TableRowSorter<TableModel> sorter;
    
    /**
     * Creates new form FrmCultivos
     */
    public FrmCultivos() {
        initComponents();
        controller = new CultivosController(this, trabajador);
        this.setLocationRelativeTo(this);
        tablemodel = (DefaultTableModel) TxtDatos.getModel();
        sorter = new TableRowSorter<>(this.TxtDatos.getModel());
        TxtDatos.setRowSorter(sorter);
        
        TxtDatos.getSelectionModel().addListSelectionListener(evt -> {
        if (!evt.getValueIsAdjusting()) {
            int selectedRow = TxtDatos.getSelectedRow();
            if (selectedRow >= 0) {
                // Obtener los valores de la fila seleccionada
                int codigo = Integer.parseInt(tablemodel.getValueAt(selectedRow, 0).toString());
                String cedula = tablemodel.getValueAt(selectedRow, 1).toString();
                String nombre = tablemodel.getValueAt(selectedRow, 2).toString();
                String tipo = tablemodel.getValueAt(selectedRow, 3).toString();
                double area = Double.parseDouble(tablemodel.getValueAt(selectedRow, 4).toString());
                String estado = tablemodel.getValueAt(selectedRow, 5).toString();
                String siembra = tablemodel.getValueAt(selectedRow, 6).toString();
                
                // Pasar los valores a los JTextFields y JComboBox
                TxtCodigo.setText(String.valueOf(codigo));
                TxtCed.setText(cedula);
                TxtNom.setText(nombre);
                Txtip.setText(tipo);
                TxtAre.setText(String.valueOf(area));
                TxtEst.setText(estado);
                TxtSiem.setText(String.valueOf(siembra));
            }
        }
    });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnCultivo = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        TxtCed = new javax.swing.JTextField();
        TxtNom = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        TxtSiem = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        Txtip = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        Eliminar = new javax.swing.JButton();
        Agregar = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        TxtAre = new javax.swing.JTextField();
        TxtEst = new javax.swing.JTextField();
        TxtCodigo = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TxtDatos = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        TxtID = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnCultivo.setBackground(new java.awt.Color(255, 255, 255));
        PnCultivo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel17.setText("Codigo: ");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel18.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel18.setText("Cédula: ");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel19.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel19.setText("Tipo:");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        TxtCed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCedActionPerformed(evt);
            }
        });
        jPanel10.add(TxtCed, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 310, -1));

        TxtNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNomActionPerformed(evt);
            }
        });
        jPanel10.add(TxtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 310, -1));

        jLabel20.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel20.setText("Estado:");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        TxtSiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSiemActionPerformed(evt);
            }
        });
        jPanel10.add(TxtSiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 310, -1));

        jLabel21.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel21.setText("Siembra:");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        Txtip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtipActionPerformed(evt);
            }
        });
        jPanel10.add(Txtip, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 310, -1));

        jLabel22.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jPanel10.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        Eliminar.setBackground(new java.awt.Color(204, 204, 204));
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/borrar.png"))); // NOI18N
        Eliminar.setBorder(null);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPanel10.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 90, 80));

        Agregar.setBackground(new java.awt.Color(204, 204, 204));
        Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/expediente.png"))); // NOI18N
        Agregar.setBorder(null);
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });
        jPanel10.add(Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, 80));

        Actualizar.setBackground(new java.awt.Color(204, 204, 204));
        Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/actualizar-datos.png"))); // NOI18N
        Actualizar.setBorder(null);
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPanel10.add(Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 90, 80));

        jLabel23.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel23.setText("Area: ");
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));
        jPanel10.add(TxtAre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 310, -1));

        TxtEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtEstActionPerformed(evt);
            }
        });
        jPanel10.add(TxtEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 310, -1));

        TxtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCodigoActionPerformed(evt);
            }
        });
        jPanel10.add(TxtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 310, -1));

        jLabel29.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel29.setText("Nombre: ");
        jPanel10.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        PnCultivo.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 410, 350));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Cédula", "Nombre", "Tipo", "Area", "Estado", "Siembra", "Cosecha"
            }
        ));
        jScrollPane3.setViewportView(TxtDatos);

        jPanel11.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 640, 330));

        PnCultivo.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 660, 350));

        getContentPane().add(PnCultivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1080, 430));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel16.setText("Cultivos");
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        TxtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIDActionPerformed(evt);
            }
        });
        TxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtIDKeyReleased(evt);
            }
        });
        jPanel9.add(TxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 650, -1));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/buscar.png"))); // NOI18N
        jButton12.setBorder(null);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 40, 40));

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/xml (2).png"))); // NOI18N
        jButton23.setBorder(null);
        jPanel9.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 40, 40));

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIDActionPerformed

    private void TxtCedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCedActionPerformed

    private void TxtNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNomActionPerformed

    private void TxtSiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSiemActionPerformed

    private void TxtipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtipActionPerformed

    private void TxtEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtEstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtEstActionPerformed

    private void TxtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCodigoActionPerformed
        if(!TxtCodigo.isEditable()) return;
        int id = Integer.parseInt(TxtCodigo.getText());
        if (!controller.validatePK(id)){
            showError("La cedula ingresada ya se encuentra registrada");
            TxtCodigo.setText("");
        }
    }//GEN-LAST:event_TxtCodigoActionPerformed

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
         if (!validateRequired()) {
        JOptionPane.showMessageDialog(this, "Faltan datos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    try {
        int codigo = Integer.parseInt(TxtCodigo.getText().trim());
        double area = Double.parseDouble(TxtAre.getText().trim());
        LocalDate siembra = LocalDate.parse(TxtSiem.getText().trim());
        
        // Asegúrate de inicializar correctamente el objeto Trabajadores
        Trabajadores cedula = new Trabajadores();
        cedula.setCedula(TxtCedula.getText().trim()); // Asegúrate de que este método exista y funcione
        
        cultivos = new Cultivos(
            codigo,
            cedula, 
            TxtNom.getText().trim(), 
            Txtip.getText().trim(), 
            area, 
            TxtEst.getText().trim(), 
            siembra
        );
        
        controller.insertar(cultivos);
        clear();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al insertar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_AgregarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        if (!validateRequired()) {
        JOptionPane.showMessageDialog(this, "Faltan datos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        LocalDate siembra = LocalDate.parse(TxtSiem.getText().trim());
        double area = Double.parseDouble(TxtAre.getText().trim());
        int codigo = Integer.parseInt(TxtCodigo.getText().trim());
        Trabajadores cedula = new Trabajadores();
        Cultivos cultivosActualizado = new Cultivos(
            codigo,
            cedula, 
            TxtNom.getText().trim(), 
            Txtip.getText().trim(),
            area,   
            TxtEst.getText().trim(),
            siembra
        );
        controller.update(cultivosActualizado);
        clear();
        // Actualizar la tabla con los nuevos datos
        controller.readAll();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El salario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_ActualizarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int codigo = Integer.parseInt(TxtCodigo.getText().trim());
    if (codigo < 0) {
        JOptionPane.showMessageDialog(this, "Seleccione un trabajador para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    int confirm = JOptionPane.showConfirmDialog(  this,"¿Está seguro de que desea eliminar al trabajador con cédula: " + codigo + "?","Confirmar eliminación",JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return; 
    }
    try {
        Cultivos cultivosAEliminar = new Cultivos();
        cultivosAEliminar.setId(codigo);
        controller.delete(cultivosAEliminar);
        clear();
        controller.readAll();
        JOptionPane.showMessageDialog(this, "Trabajador eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al eliminar el trabajador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_EliminarActionPerformed

    private void TxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtIDKeyReleased
        String search =TxtID.getText();
        if(search.trim().isEmpty()){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+search));
        }
    }//GEN-LAST:event_TxtIDKeyReleased

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        controller.readAll();
    }//GEN-LAST:event_jButton12ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCultivos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Agregar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JPanel PnCultivo;
    private javax.swing.JTextField TxtAre;
    private javax.swing.JTextField TxtCed;
    private javax.swing.JTextField TxtCodigo;
    private javax.swing.JTable TxtDatos;
    private javax.swing.JTextField TxtEst;
    private javax.swing.JTextField TxtID;
    private javax.swing.JTextField TxtNom;
    private javax.swing.JTextField TxtSiem;
    private javax.swing.JTextField Txtip;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton23;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
