/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spravochn.storegman;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import main.rem.otdel.ListenerCloseForm;
import main.rem.otdel.MainRemOtdel;

/**
 *
 * @author tigler
 */
public class StoragemanAddUpdate extends javax.swing.JFrame {

    /**
     * Creates new form StaragemanAddUpdate
     */
    private int addOrUpdate;
    private int PK;
    private ListenerCloseForm listenerCloseForm;

    public StoragemanAddUpdate(int addOrUpdate, int PK) {
        initComponents();
        this.addOrUpdate = addOrUpdate;
        this.PK = PK;
        if (addOrUpdate == 1) {
            jButtonAddUpdate.setText("Изменить");
            this.setTitle("Изменить кладовщика");

            ResultSet resSet = null;
            try {
                resSet = MainRemOtdel.st.executeQuery("select nameofstorekeeper,otcofstorekeeper,famofstorekeeper,login,password"
                        + " from storekeeper where storekeeper.PK_storekeeper=" + PK);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: Невозможно изменить");

                this.dispose();
            }
            try {
                if (resSet.next()) {
                    jTextFieldName.setText(resSet.getString(1));
                    jTextFieldOtch.setText(resSet.getString(2));
                    jTextFieldFam.setText(resSet.getString(3));
                    jTextFieldLogin.setText(resSet.getString(4));
                    jTextFieldPassword.setText(resSet.getString(5));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ошибка: Невозможно изменить");
                this.dispose();
            }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldFam = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldOtch = new javax.swing.JTextField();
        jButtonCancel = new javax.swing.JButton();
        jButtonAddUpdate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jTextFieldPassword = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Добавление кладовщика");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Фамилия");

        jLabel2.setText("Имя");

        jLabel3.setText("Отчество");

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

        jLabel4.setText("Логин");

        jLabel5.setText("Пароль");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(jButtonAddUpdate))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldFam)
                            .addComponent(jTextFieldName)
                            .addComponent(jTextFieldOtch, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(jTextFieldLogin)
                            .addComponent(jTextFieldPassword))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldFam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldOtch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonAddUpdate))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        listenerCloseForm.event();
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonAddUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUpdateActionPerformed
        // TODO add your handling code here:
        if (addOrUpdate == 0) {
            String textLogin = jTextFieldLogin.getText();
            String textPassword = jTextFieldPassword.getText();
            String textFam = jTextFieldFam.getText();
            String textName = jTextFieldName.getText();
            String textOcth = jTextFieldOtch.getText();
            if (textLogin.equals("") || textPassword.equals("") || textFam.equals("")
                    || textName.equals("") || textOcth.equals("")) {
                JOptionPane.showMessageDialog(this, "Невозможно добавить пустое поле");
            } else {
                try {
                    MainRemOtdel.st.executeQuery("Insert into SERVERADM.storekeeper (famofstorekeeper,nameofstorekeeper,otcofstorekeeper,login,password) values "
                            + "('" + textFam + "','" + textName + "','" + textOcth + "','" + textLogin + "','" + textPassword + "')");
                    JOptionPane.showMessageDialog(this, "Запись успешно добавлена");
                    listenerCloseForm.event();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка: Невозможно добавить");

                }
                this.dispose();
            }
        } else {
            if (addOrUpdate == 1) {

                String textLogin = jTextFieldLogin.getText();
                String textPassword = jTextFieldPassword.getText();
                String textFam = jTextFieldFam.getText();
                String textName = jTextFieldName.getText();
                String textOcth = jTextFieldOtch.getText();
                if (textLogin.equals("") || textPassword.equals("") || textFam.equals("")
                        || textName.equals("") || textOcth.equals("")) {
                    JOptionPane.showMessageDialog(this, "Невозможно изменить на пустое поле");
                } else {
                    try {
                        MainRemOtdel.st.executeQuery("UPDATE SERVERADM.storekeeper SET famofstorekeeper = '" + textFam + "', "
                                + "nameofstorekeeper = '" + textName + "', otcofstorekeeper = '" + textOcth + "',"
                                + " login='" + textLogin + "',password='" + textPassword + "' WHERE PK_storekeeper=" + PK
                        );
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextFieldFam;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldOtch;
    private javax.swing.JTextField jTextFieldPassword;
    // End of variables declaration//GEN-END:variables
}