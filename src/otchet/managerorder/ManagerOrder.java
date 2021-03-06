package otchet.managerorder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import main.rem.otdel.ListenerCloseForm;
import main.rem.otdel.MainRemOtdel;
import main.rem.otdel.UpdatesDataInForms;
import net.proteanit.sql.DbUtils;
import otchet.alldetail.AllDetail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Максим
 */
public class ManagerOrder extends javax.swing.JFrame implements UpdatesDataInForms {

    private int pkMeneger = -1;
    private ListenerCloseForm listenerCloseForm;

    /**
     * Creates new form ManagerOrder
     */
    public ManagerOrder() {
        initComponents();
    }

    public void setPkMeneger(int pkMeneger) {
        this.pkMeneger = pkMeneger;
    }

    @Override
    public void addDataInTable() {
        this.setEnabled(true);
        ResultSet resSet = null;
        try {

            resSet = MainRemOtdel.st.executeQuery("select myorder.NUMOFORDER,"
                    + "TO_CHAR(myorder.TIMETOACCEPT, 'DD.MM.YYYY'),"
                    + "TO_CHAR(myorder.TIMETODELIVERY, 'DD.MM.YYYY'),"
                    + "myorder.COSTOFORDER,"
                    + "myorder.TYPEOFORDER,"
                    + " status.NAMEOFSTATUS,"
                    + " client.FAMOFCLIENT || ' ' || client.NAMEOFCLIENT  || ' ' || client.OTCOFCLIENT"
                    // + " manager.FAMOFMANAGER || ' ' || manager.NAMEOFMANAGER  || ' ' || manager.OTCOFMANAGER"
                    + " from myorder "
                    + " inner join status on status.PK_status=myorder.PK_status"
                    + " inner join manager on manager.PK_manager=myorder.PK_manager"
                    + " inner join client on client.PK_client=myorder.PK_client"
                    + " where myorder.PK_manager= " + pkMeneger);
            jTable1.setModel(DbUtils.resultSetToTableModel(resSet));
        } catch (SQLException ex) {
            Logger.getLogger(ManagerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        correctSizeTable(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue("Номер");
        jTable1.getColumnModel().getColumn(1).setHeaderValue("Дата создания");
        jTable1.getColumnModel().getColumn(2).setHeaderValue("Дата завершения");
        jTable1.getColumnModel().getColumn(3).setHeaderValue("Стоимость");
        jTable1.getColumnModel().getColumn(4).setHeaderValue("Тип");
        jTable1.getColumnModel().getColumn(5).setHeaderValue("Статус");
        jTable1.getColumnModel().getColumn(6).setHeaderValue("Клиент");

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (jTable1.getValueAt(i, 4).toString().equals("0")) {
                jTable1.setValueAt("Гарантийный", i, 4);
            } else {
                if (jTable1.getValueAt(i, 4).toString().equals("1")) {
                    jTable1.setValueAt("Не гарантийный", i, 4);
                } else {
                    jTable1.setValueAt("Неизвестно", i, 4);
                }
            }
        }

    }

    public void correctSizeTable(javax.swing.JTable jTable) {
        jTable.getColumnModel().getColumn(0).setMaxWidth(60);
        jTable.getColumnModel().getColumn(0).setMinWidth(60);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        //
        jTable.getColumnModel().getColumn(1).setMaxWidth(100);
        jTable.getColumnModel().getColumn(1).setMinWidth(100);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        //
        jTable.getColumnModel().getColumn(2).setMaxWidth(100);
        jTable.getColumnModel().getColumn(2).setMinWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        //
        jTable.getColumnModel().getColumn(3).setMaxWidth(70);
        jTable.getColumnModel().getColumn(3).setMinWidth(70);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        //
        jTable.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable.getColumnModel().getColumn(4).setMinWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        //
        jTable.getColumnModel().getColumn(5).setMaxWidth(140);
        jTable.getColumnModel().getColumn(5).setMinWidth(140);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(140);
        //
       /* jTable.getColumnModel().getColumn(6).setMaxWidth(170);
         jTable.getColumnModel().getColumn(6).setMinWidth(170);
         jTable.getColumnModel().getColumn(6).setPreferredWidth(170);*/
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable( )         {             @Override             public boolean isCellEditable(int row, int column)             {                 return false;             }         };
        jButtonCancel = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButtonChooseMeneger = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Заказы конкректного менеджера");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonCancel.setText("Отмена");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonPrint.setText("Распечатать");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        jButtonChooseMeneger.setText("Выбрать менеджера");
        jButtonChooseMeneger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseMenegerActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Обновить");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
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
                        .addComponent(jButtonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonChooseMeneger, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonChooseMeneger)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonPrint)
                    .addComponent(jButtonUpdate))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        listenerCloseForm.event();
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        listenerCloseForm.event();
    }//GEN-LAST:event_formWindowClosing

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Нечего печатать");
            return;
        }
        ResultSet resSet = null;
        try {
            resSet = MainRemOtdel.st.executeQuery("select PK_MANAGER, FAMOFMANAGER || ' ' || NAMEOFMANAGER || ' ' ||  OTCOFMANAGER from manager"
                    + " where PK_MANAGER =" + pkMeneger);
            resSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        try {
            boolean complete = jTable1.print(JTable.PrintMode.FIT_WIDTH,
                    new MessageFormat("Заказы менеджера: "+ resSet.getString(2)),
                    new MessageFormat("Страница {0,number,integer}"));
        } catch (Exception p) {
            /* Printing failed, report to the user */
            JOptionPane.showMessageDialog(this, "Ошибка: Невозможно распечатать");
        }
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jButtonChooseMenegerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChooseMenegerActionPerformed
        ChooseMeneger сhooseMeneger = new ChooseMeneger(this);
        сhooseMeneger.setVisible(true);
        сhooseMeneger.setListenerCloseForm(new ListenerCloseForm(this));
        this.setEnabled(false);
    }//GEN-LAST:event_jButtonChooseMenegerActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (this.pkMeneger == -1) {
            JOptionPane.showMessageDialog(this, "Сначала выберите менеджера");
        }
        addDataInTable();
    }//GEN-LAST:event_jButtonUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonChooseMeneger;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
