/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Sistema;

import Controller.TrabajadorController;
import Model.Trabajador.Trabajadores;
import UtilDate.UtilGui;
import View.View;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Dering
 */
public class FrmTrabajo extends javax.swing.JFrame implements View<Trabajadores>{
    private TrabajadorController controller;
    private Trabajadores trabajadores;
    private DefaultTableModel tablemodel;
    TableRowSorter<TableModel> sorter;

    /**
     * Creates new form FrmTrabajo
     */
    public FrmTrabajo() {
        initComponents();
       controller = new TrabajadorController((View) this);
        this.setLocationRelativeTo(this);
        tablemodel = (DefaultTableModel) TxtDatos.getModel();
        sorter = new TableRowSorter<>(this.TxtDatos.getModel());
        TxtDatos.setRowSorter(sorter);
        
        TxtDatos.getSelectionModel().addListSelectionListener(evt -> {
        if (!evt.getValueIsAdjusting()) {
            int selectedRow = TxtDatos.getSelectedRow();
            if (selectedRow >= 0) {
                // Obtener los valores de la fila seleccionada
                String cedula = tablemodel.getValueAt(selectedRow, 0).toString();
                String nombre = tablemodel.getValueAt(selectedRow, 1).toString();
                String telefono = tablemodel.getValueAt(selectedRow, 2).toString();
                String correo = tablemodel.getValueAt(selectedRow, 3).toString();
                String puesto = tablemodel.getValueAt(selectedRow, 4).toString();
                String horario = tablemodel.getValueAt(selectedRow, 5).toString();
                double salario = Double.parseDouble(tablemodel.getValueAt(selectedRow, 6).toString());

                // Pasar los valores a los JTextFields y JComboBox
                TxtCedula.setText(cedula);
                TxtNombre.setText(nombre);
                TxtTelefono.setText(telefono);
                TxtCorreo.setText(correo);
                TxtPuesto.setSelectedItem(puesto);
                TxtHorario.setText(horario);
                TxtSalario.setText(String.valueOf(salario));
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

        PnTrabajador = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtID = new javax.swing.JTextField();
        btnBuscarID = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtSalario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxtCedula = new javax.swing.JTextField();
        TxtNombre = new javax.swing.JTextField();
        TxtPuesto = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        TxtCorreo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtHorario = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtDatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnTrabajador.setBackground(new java.awt.Color(255, 255, 255));
        PnTrabajador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel1.setText("Trabajador");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        TxtID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TxtIDMouseReleased(evt);
            }
        });
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
        jPanel3.add(TxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 650, -1));

        btnBuscarID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/buscar.png"))); // NOI18N
        btnBuscarID.setBorder(null);
        btnBuscarID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIDActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscarID, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 40, 40));

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/xml (2).png"))); // NOI18N
        jButton22.setBorder(null);
        jPanel3.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 40, 40));

        PnTrabajador.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 60));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel2.setText("Cédula: ");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        TxtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSalarioActionPerformed(evt);
            }
        });
        jPanel4.add(TxtSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 310, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel3.setText("Nombre: ");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel4.setText("Telefono: ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        TxtCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtCedulaFocusLost(evt);
            }
        });
        TxtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCedulaActionPerformed(evt);
            }
        });
        jPanel4.add(TxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 310, -1));

        TxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNombreActionPerformed(evt);
            }
        });
        jPanel4.add(TxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 310, -1));

        TxtPuesto.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        TxtPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puesto", "Granjero", "Cultivador", "Ayudante Agricola", "Gerente Produccion", " " }));
        TxtPuesto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxtPuestoItemStateChanged(evt);
            }
        });
        jPanel4.add(TxtPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 160, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel5.setText("Correo: ");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        jPanel4.add(TxtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 310, -1));

        jLabel6.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel6.setText("Horario: ");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        TxtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTelefonoActionPerformed(evt);
            }
        });
        jPanel4.add(TxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 140, -1));

        jLabel7.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel7.setText("Salario: ");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        TxtHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtHorarioActionPerformed(evt);
            }
        });
        jPanel4.add(TxtHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 310, -1));

        btnDelete.setBackground(new java.awt.Color(204, 204, 204));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/borrar.png"))); // NOI18N
        btnDelete.setBorder(null);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 90, 80));

        btnAgregar.setBackground(new java.awt.Color(204, 204, 204));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/expediente.png"))); // NOI18N
        btnAgregar.setBorder(null);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, 80));

        btnActualizar.setBackground(new java.awt.Color(204, 204, 204));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/actualizar-datos.png"))); // NOI18N
        btnActualizar.setBorder(null);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel4.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 90, 80));

        PnTrabajador.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 410, 350));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cédula", "Nombre", "Telefono", "Correo", "Puesto", "Horario", "Salario"
            }
        ));
        jScrollPane1.setViewportView(TxtDatos);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 640, 330));

        PnTrabajador.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 660, 350));

        getContentPane().add(PnTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtIDMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIDMouseReleased

    private void TxtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIDActionPerformed

    }//GEN-LAST:event_TxtIDActionPerformed

    private void TxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtIDKeyReleased
        String search =TxtID.getText();
        if(search.trim().isEmpty()){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+search));
        }
    }//GEN-LAST:event_TxtIDKeyReleased

    private void btnBuscarIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIDActionPerformed
        controller.readAll();
    }//GEN-LAST:event_btnBuscarIDActionPerformed

    private void TxtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSalarioActionPerformed

    private void TxtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtCedulaFocusLost
        if(!TxtCedula.isEditable()) return;
        String id = TxtCedula.getText();
        if (id.trim().isEmpty()) return;
        if (!controller.validatePK(id)){
             showError("La cedula ingresada ya se encuentra registrada");
             TxtCedula.setText("");
        }
    }//GEN-LAST:event_TxtCedulaFocusLost

    private void TxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCedulaActionPerformed

    private void TxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNombreActionPerformed

    private void TxtPuestoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxtPuestoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPuestoItemStateChanged

    private void TxtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTelefonoActionPerformed

    private void TxtHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtHorarioActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String cedula = TxtCedula.getText().trim();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un trabajador para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(  this,"¿Está seguro de que desea eliminar al trabajador con cédula: " + cedula + "?","Confirmar eliminación",JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return; 
        }
        try {
            Trabajadores trabajadorAEliminar = new Trabajadores();
            trabajadorAEliminar.setCedula(cedula);
            controller.delete(trabajadorAEliminar);
            clear();
            controller.readAll();
            JOptionPane.showMessageDialog(this, "Trabajador eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el trabajador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (!validateRequired()) {
            JOptionPane.showMessageDialog(this, "Faltan datos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
         
        String puesto = TxtPuesto.getSelectedItem().toString();
        if ("Puesto".equals(puesto)) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un puesto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double salario = Double.parseDouble(TxtSalario.getText().trim()); 
            trabajadores = new Trabajadores(
                TxtCedula.getText().trim(), 
                TxtNombre.getText().trim(), 
                TxtTelefono.getText().trim(), 
                TxtCorreo.getText().trim(), 
                puesto, 
                TxtHorario.getText().trim(), 
                salario
            );
            controller.insertTrabajador(trabajadores);
            clear();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El salario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
            if (!validateRequired()) {
        JOptionPane.showMessageDialog(this, "Faltan datos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        double salario = Double.parseDouble(TxtSalario.getText().trim());
        Trabajadores trabajadorActualizado = new Trabajadores(
            TxtCedula.getText().trim(), 
            TxtNombre.getText().trim(), 
            TxtTelefono.getText().trim(),
            TxtCorreo.getText().trim(),
            TxtPuesto.getSelectedItem().toString(),
            TxtHorario.getText().trim(),
            salario
        );
        controller.update(trabajadorActualizado);
        clear();
        controller.readAll();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El salario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTrabajo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnTrabajador;
    private javax.swing.JTextField TxtCedula;
    private javax.swing.JTextField TxtCorreo;
    private javax.swing.JTable TxtDatos;
    private javax.swing.JTextField TxtHorario;
    private javax.swing.JTextField TxtID;
    private javax.swing.JTextField TxtNombre;
    private javax.swing.JComboBox<String> TxtPuesto;
    private javax.swing.JTextField TxtSalario;
    private javax.swing.JTextField TxtTelefono;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarID;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton jButton22;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void show(Trabajadores ent) {
        trabajadores=ent;
        if (ent==null) {
            clear();
            return;
        }
        TxtCedula.setText(ent.getCedula());
        TxtNombre.setText(ent.getNombre());
        TxtTelefono.setText(ent.getTelefono());
        TxtPuesto.setToolTipText(ent.getPuesto());
        TxtCorreo.setText(ent.getCorreo());
        TxtHorario.setText(ent.getHorario());
        TxtSalario.setText(String.valueOf(ent.getSalario()));

    }

    @Override
    public void showAll(List<Trabajadores> ents) {
        if(ents==null || tablemodel == null)return;
        tablemodel.setNumRows(0);
        ents.forEach(trabajadores->tablemodel.addRow(
                new Object[]{
                    trabajadores.getCedula(),
                    trabajadores.getNombre(),
                    trabajadores.getTelefono(),
                    trabajadores.getCorreo(),
                    trabajadores.getPuesto(),
                    trabajadores.getHorario(),
                    trabajadores.getSalario()
                    
                }
        ));

    }

    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showSuccess(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showError(String err) {
        JOptionPane.showMessageDialog(this, err, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public boolean validateRequired() {
        return UtilGui.validateFields(TxtCedula,TxtNombre,TxtTelefono,TxtCorreo,TxtPuesto,TxtHorario,TxtSalario);
    }
    private void clear(){
        UtilGui.clearTxts(
                TxtCedula,
                TxtNombre,
                TxtTelefono,
                TxtCorreo,
                TxtPuesto,
                TxtHorario,
                TxtSalario
        );
    }

}
