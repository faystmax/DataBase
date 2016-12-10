/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spravochn.manufacture;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import main.rem.otdel.ListenerCloseForm;
import main.rem.otdel.MainRemOtdel;

/**
 *
 * @author tigler
 */
public class ManufacturerAddUpdate extends javax.swing.JFrame {

    /**
     * Creates new form ManufacturerAddUpdate
     */
    private ListenerCloseForm listenerCloseForm;
    private int addOrUpdate;
    private int PK;

    ManufacturerAddUpdate(int addOrUpdate, int PK) {
        initComponents();
        this.addOrUpdate = addOrUpdate;
        if (addOrUpdate == 1) {
            jButtonAddUpdate.setText("Изменить");
            this.setTitle("Изменить производителя");
        }
        this.PK = PK;

        ResultSet resSet = null;
        try {
            resSet = MainRemOtdel.st.executeQuery("select nameofmanufacturer from manufacturer where PK_manufacturer=" + PK);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: Невозможно изменить");

            this.dispose();
        }
        try {
            if (resSet.next()) {
                jTextField1.setText(resSet.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка: Невозможно изменить");
            this.dispose();
        }
    }

    public void setListenerCloseForm(ListenerCloseForm listenerCloseForm) {
        this.listenerCloseForm = listenerCloseForm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButtonCancel = new javax.swing.JButton();
        jButtonAddUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Добавить производителя");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Производитель");

        jButtonCancel.setText("Отмена");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonAddUpdate.setText("Добавить");
        jButtonAddUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAddUpdate)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonAddUpdate))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        listenerCloseForm.event();
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonAddUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUpdateActionPerformed
        // TODO add your handling code here:
        if (addOrUpdate == 0) {
            String text = jTextField1.getText();
            if (text.equals("")) {
                JOptionPane.showMessageDialog(this, "Невозможно добавить пустое поле");
            } else {
                try {
                    MainRemOtdel.st.executeQuery("Insert into SERVERADM.manufacturer (NAMEOFmanufacturer) values ('" + text + "')");
                    JOptionPane.showMessageDialog(this, "Запись успешно добавлена");
                    listenerCloseForm.event();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка: Невозможно добавить");

                }
                this.dispose();
            }
        } else {
            if (addOrUpdate == 1) {

                String text = jTextField1.getText();

                if (text.equals("")) {
                    JOptionPane.showMessageDialog(this, "Невозможно изменить на пустое поле");
                } else {
                    try {
                        MainRemOtdel.st.executeQuery("UPDATE SERVERADM.manufacturer SET NAMEOFmanufacturer = '" + text + "' WHERE PK_manufacturer=" + PK);
                        JOptionPane.showMessageDialog(this, "Запись успешно изменена");
                        listenerCloseForm.event();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Ошибка: Невозможно изменить");
                    }
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_jButtonAddUpdateActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        listenerCloseForm.event();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddUpdate;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
