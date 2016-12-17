/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.engineer;

import com.toedter.calendar.JTextFieldDateEditor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.rem.otdel.ListenerCloseForm;
import main.rem.otdel.MainRemOtdel;
import users.storegman.DetailsStore;

/**
 *
 * @author tigler
 */
public class RepairAddUpdate extends javax.swing.JFrame {

    /**
     * Creates new form RepairAddUpdate
     */
    private ListenerCloseForm listenerCloseForm;
    private int addOrUpdate;
    private int PK;
    private int engPk;

    private ArrayList<String> pkDevice;
    private ArrayList<String> valueDevice;

    public RepairAddUpdate(int addOrUpdate, int PK, int engPK) {
        initComponents();
        this.engPk = engPK;
        this.PK = PK;
        pkDevice = new ArrayList<>();
        valueDevice = new ArrayList<>();
        jDateChooserEnd.setDateFormatString("dd.MM.yyyy");
        jDateChooserEnd.setDate(new java.util.Date());
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) jDateChooserEnd.getDateEditor();
        editor2.setEditable(false);

        jDateChooserStart.setDateFormatString("dd.MM.yyyy");
        jDateChooserStart.setDate(new java.util.Date());
        JTextFieldDateEditor editor3 = (JTextFieldDateEditor) jDateChooserStart.getDateEditor();
        editor3.setEditable(false);

        this.addOrUpdate = addOrUpdate;
        ResultSet resSet = null;
        try {
            resSet = MainRemOtdel.st.executeQuery("select pk_device,modelofdevice,typeofdevice.nameoftype,"
                    + "manufacturer.nameofmanufacturer from device"
                    + " inner join myorder on myorder.PK_order=device.PK_order"
                    + " inner join typeofdevice on typeofdevice.PK_typeofdevice=device.PK_typeofdevice"
                    + " inner join manufacturer on manufacturer.PK_manufacturer=device.PK_manufacturer"
                    + " where myorder.PK_status=2");

            while (resSet.next()) {
                jComboBoxDevice.addItem(resSet.getString(3) + " " + resSet.getString(4) + " " + resSet.getString(2));
                pkDevice.add(resSet.getString(1));
                valueDevice.add(resSet.getString(3) + " " + resSet.getString(4) + " " + resSet.getString(2));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: Невозможно изменить");
            Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose();
        }

        if (addOrUpdate == 1) {
            jButtonAddUpdate.setText("Изменить");
            this.setTitle("Изменить ремонт");
            try {
                resSet = MainRemOtdel.st.executeQuery("select startdate, enddate,pk_device"
                        + " from repair where pk_repair=" + PK);
                if (resSet.next()) {
                    jDateChooserStart.setDate(resSet.getDate(1));
                    jDateChooserEnd.setDate(resSet.getDate(2));
                    for (int i = 0; i < pkDevice.size(); i++) {
                        if (pkDevice.get(i).equals(resSet.getString(3))) {
                            jComboBoxDevice.setSelectedIndex(i);
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RepairAddUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jLabel6.setVisible(false);
            jDateChooserEnd.setVisible(false);
            jDateChooserStart.setDate(new java.util.Date());
            jComboBoxDevice.setSelectedIndex(-1);
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
        jComboBoxDevice = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooserStart = new com.toedter.calendar.JDateChooser();
        jDateChooserEnd = new com.toedter.calendar.JDateChooser();
        jButtonCancel = new javax.swing.JButton();
        jButtonAddUpdate = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Добавить ремонт");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel3.setText("Устройство");

        jLabel5.setText("Дата начала");

        jLabel6.setText("Дата окончания");

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
                            .addComponent(jComboBoxDevice, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jDateChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAddUpdate)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonAddUpdate))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUpdateActionPerformed
        // TODO add your handling code here:
        int devicePk = jComboBoxDevice.getSelectedIndex();
        java.util.Date dateChooserStart = jDateChooserStart.getDate();
        java.sql.Date dateStart = new java.sql.Date(dateChooserStart.getTime());

        if (addOrUpdate == 0) {

            if (jComboBoxDevice.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Невозможно добавить пустое поле");
            } else {
                try {
                    MainRemOtdel.st.executeQuery("Insert into SERVERADM.repair (startdate,PK_device,pk_engineer)"
                            + " values ("
                            + " TO_DATE('" + dateStart + "', 'YYYY-MM-DD'),'"
                            + pkDevice.get(jComboBoxDevice.getSelectedIndex()) + "'"
                            + ",'" + engPk + "')");
                    JOptionPane.showMessageDialog(this, "Запись успешно добавлена");
                    listenerCloseForm.event();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка: Невозможно добавить");
                }
                this.dispose();
            }
        } else {
            if (addOrUpdate == 1) {
                java.sql.Date dateEnd = null;
                java.util.Date dateChooserEnd = jDateChooserEnd.getDate();
                if (dateChooserEnd != null) {
                    dateEnd = new java.sql.Date(dateChooserEnd.getTime());
                }

                if (jComboBoxDevice.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(this, "Невозможно изменить на пустое поле");
                } else {
                    try {
                        MainRemOtdel.st.executeQuery("UPDATE SERVERADM.repair"
                                + " SET startdate = TO_DATE('" + dateStart + "', 'YYYY-MM-DD'),"
                                + "enddate = TO_DATE('" + dateEnd + "', 'YYYY-MM-DD'),'"
                                + pkDevice.get(jComboBoxDevice.getSelectedIndex())
                                + " WHERE PK_repair=" + PK
                                + ")");
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

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        listenerCloseForm.event();
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        listenerCloseForm.event();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddUpdate;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JComboBox<String> jComboBoxDevice;
    private com.toedter.calendar.JDateChooser jDateChooserEnd;
    private com.toedter.calendar.JDateChooser jDateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
