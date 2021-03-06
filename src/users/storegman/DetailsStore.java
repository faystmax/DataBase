/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.storegman;

import com.toedter.calendar.JTextFieldDateEditor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import main.rem.otdel.ListenerCloseForm;
import main.rem.otdel.LoginFrame;
import main.rem.otdel.MainRemOtdel;
import main.rem.otdel.UpdatesDataInForms;
import net.proteanit.sql.DbUtils;
import spravochn.detail.Detail;
import spravochn.manager.ManagersAddUpdate;
import users.engineer.AddUpdateDetailFromStore;
import users.engineer.SctructForTables;

/**
 *
 * @author tigler
 */
public class DetailsStore extends javax.swing.JFrame implements UpdatesDataInForms {

    /**
     * Creates new form DetailsStore
     */
    private int PK;
    private DefaultTableModel dtm4;
    private ArrayList<DefaultTableModel> sctructForTableses;
    private ArrayList<Integer> indsParTable;

    public DetailsStore(int PK) throws SQLException {
        initComponents();
        this.PK = PK;
        addDataInTable();
        sctructForTableses = new ArrayList<>();
        indsParTable = new ArrayList<>();
        jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable3.getColumnModel().getColumn(0).setMinWidth(0);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(0);

        dtm4 = (DefaultTableModel) jTable4.getModel();
        Object[] objects = {};
        dtm4.addColumn("Взять деталей(шт)");
        jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable4.getColumnModel().getColumn(0).setMinWidth(0);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable4.getColumnModel().getColumn(1).setHeaderValue("Деталь");
        jTable4.getColumnModel().getColumn(2).setHeaderValue("Расположение");
        jTable4.getColumnModel().getColumn(3).setHeaderValue("Колличество на складе");

        jDateChooser1.setDate(new java.util.Date());
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor2.setEditable(false);
    }

    @Override
    public void addDataInTable() {
        ResultSet resSet = null;
        try {
            resSet = MainRemOtdel.st.executeQuery("select storekeeper.FAMOFSTOREKEEPER,storekeeper.NAMEOFSTOREKEEPER,storekeeper.OTCOFSTOREKEEPER from storekeeper"
                    + " where PK_STOREKEEPER=" + PK);
            if (resSet.next()) {
                jLabelFIO.setText("Кладовщик: " + resSet.getString(1) + " "
                        + resSet.getString(2) + " "
                        + resSet.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            resSet = MainRemOtdel.st.executeQuery("select PK_detailfromwh, detail.nameofdetail,amount,location"
                    + " from detailfromwarehouse"
                    + " inner join detail on detail.PK_Detail=detailfromwarehouse.PK_Detail"
                    + " inner join storekeeper on storekeeper.PK_storekeeper=detailfromwarehouse.PK_storekeeper"
                    + " where detailfromwarehouse.pk_storekeeper=" + PK);
        } catch (SQLException ex) {
            Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.setModel(DbUtils.resultSetToTableModel(resSet));
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(1).setHeaderValue("Деталь");
        jTable1.getColumnModel().getColumn(2).setHeaderValue("Колличество");
        jTable1.getColumnModel().getColumn(3).setHeaderValue("Расположение");

        try {
            resSet = MainRemOtdel.st.executeQuery("select PK_zapros,"
                    + " TO_CHAR(zapros.TIMETOGet, 'DD.MM.YYYY'),"
                    + " TO_CHAR(zapros.TIMETOComplete, 'DD.MM.YYYY'),"
                    + " engineer.FAMOFengineer || ' ' || engineer.NAMEOFengineer || ' ' || engineer.OTChOFengineer,"
                    + " flagofcomplete"
                    + " from zapros"
                    + " inner join repair on repair.PK_repair=zapros.PK_repair"
                    + " inner join engineer on engineer.PK_engineer=repair.PK_engineer"
            );
        } catch (SQLException ex) {
            Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable2.setModel(DbUtils.resultSetToTableModel(resSet));
        jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(0).setMinWidth(0);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);

        jTable2.getColumnModel().getColumn(1).setHeaderValue("Дата создания");
        jTable2.getColumnModel().getColumn(2).setHeaderValue("Дата выполнения");
        jTable2.getColumnModel().getColumn(3).setHeaderValue("Инженер");
        jTable2.getColumnModel().getColumn(4).setHeaderValue("Состояние");
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            if (jTable2.getValueAt(i, 4).toString().equals("0")) {
                jTable2.setValueAt("Ожидает выполнения", i, 4);
            } else {
                if (jTable2.getValueAt(i, 4).toString().equals("1")) {
                    jTable2.setValueAt("Выполнено", i, 4);
                } else {
                    jTable2.setValueAt("Неизвестно", i, 4);
                }
            }
        }
        jDateChooser1.setDateFormatString("dd.MM.yyyy");
        jDateChooser1.setDate(new java.util.Date());
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor2.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable( )
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable( )
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        jButtonExecute = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable( )         {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if(column==5){
                    return true;
                }
                return false;
            }
        };
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable( )         {             @Override             public boolean isCellEditable(int row, int column)             {                 return false;             }         };
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabelFIO = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItemClose = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Работа на складе");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "pk", "Деталь", "Колличество"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButtonExecute.setText("Выполнить");
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jLabel1.setText("Дата выполнения");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jButtonExecute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(71, 71, 71))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExecute)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Выполнение запросов", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonAdd.setText("Добавить");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Изменить");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Удалить");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jButtonAdd)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdate)
                .addGap(18, 18, 18)
                .addComponent(jButtonDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Детали", jPanel1);

        jLabelFIO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFIO.setText("Фио");

        jMenu1.setText("Файл");

        jMenuItem2.setText("Смена пользователя");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItemClose.setText("Закрыть");
        jMenuItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCloseActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemClose);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Справочники");

        jMenuItem1.setText("Детали");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabelFIO, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelFIO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        // TODO add your handling code here:

        sctructForTableses = new ArrayList<>();
        indsParTable = new ArrayList<>();
        jTable4.setModel(dtm4);
        jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable4.getColumnModel().getColumn(0).setMinWidth(0);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable4.getColumnModel().getColumn(1).setHeaderValue("Деталь");
        jTable4.getColumnModel().getColumn(2).setHeaderValue("Расположение");
        jTable4.getColumnModel().getColumn(3).setHeaderValue("Колличество на складе");

        int idx = jTable2.getSelectedRow();
        String pkReq = jTable2.getValueAt(idx, 0).toString();
        ResultSet resSet = null;
        try {
            resSet = MainRemOtdel.st.executeQuery("select PK_detailofrequest, detailofrequest.PK_detail, detail.nameofdetail,amount from detailofrequest"
                    + " inner join detail on detail.PK_detail=detailofrequest.PK_detail"
                    + " where PK_Zapros=" + pkReq);
        } catch (SQLException ex) {
            Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable3.setModel(DbUtils.resultSetToTableModel(resSet));
        jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable3.getColumnModel().getColumn(0).setMinWidth(0);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable3.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable3.getColumnModel().getColumn(1).setMinWidth(0);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(0);
        jTable3.getColumnModel().getColumn(2).setHeaderValue("Деталь");
        jTable3.getColumnModel().getColumn(3).setHeaderValue("Колличество");
        if (jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString().equals("Ожидает выполнения")) {
            jButtonExecute.setEnabled(true);
        } else {
            jButtonExecute.setEnabled(false);
        }


    }//GEN-LAST:event_jTable2MousePressed

    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecuteActionPerformed
        // TODO add your handling code here:
        ResultSet resSet = null;
        if (jTable3.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(this, "Пустой запрос.Нечего выполнять");
        } else {

            jTable4.editCellAt(0, 0); //сбить фокус с редактируемой ячейки
            boolean flagCorrectData = true;
            ArrayList<ArrayList<String>> pkDetailsStor = new ArrayList<>();
            ArrayList<ArrayList<String>> pkDetails = new ArrayList<>();
            ArrayList<ArrayList<Integer>> amountDetails = new ArrayList<>();
            ArrayList<ArrayList<Integer>> amountDetailsRaznic = new ArrayList<>();
            for (int i = 0; i < jTable3.getRowCount(); i++) {
                int amountInReq = Integer.parseInt(jTable3.getValueAt(i, 3).toString());
                ArrayList<String> pkDetStor = new ArrayList<>();
                ArrayList<String> pkDet = new ArrayList<>();
                ArrayList<Integer> amountDet = new ArrayList<>();
                ArrayList<Integer> amountDetRaznic = new ArrayList<>();
                for (int j = 0; j < sctructForTableses.size(); j++) {
                    for (int k = 0; k < sctructForTableses.get(j).getRowCount(); k++) {
                        if (sctructForTableses.get(j).getValueAt(k, 1).toString().equals(jTable3.getValueAt(i, 1).toString())) {
                            if (sctructForTableses.get(j).getValueAt(k, 5) != null) {
                                int countInputUser = 0;
                                int countInStore = 0;
                                try {
                                    countInputUser = Integer.parseInt(sctructForTableses.get(j).getValueAt(k, 5).toString());
                                    countInStore = Integer.parseInt(sctructForTableses.get(j).getValueAt(k, 4).toString());
                                } catch (Exception ex) {
                                    flagCorrectData = false;
                                    JOptionPane.showMessageDialog(this, "Данные в столбце 'Взять деталей' не корректные. Введите целые числа");
                                    break;
                                }

                                if (countInStore < countInputUser) {
                                    flagCorrectData = false;
                                    JOptionPane.showMessageDialog(this, "Деталей на локации меньше, чем предполагается взять\n"
                                            + sctructForTableses.get(j).getValueAt(k, 2).toString());
                                } else {
                                    if (countInStore >= countInputUser) {
                                        int countTmp = countInStore - countInputUser;
                                        pkDetStor.add(sctructForTableses.get(j).getValueAt(k, 0).toString());
                                        pkDet.add(sctructForTableses.get(j).getValueAt(k, 1).toString());
                                        amountDet.add(countInputUser);
                                        amountDetRaznic.add(countTmp);
                                    }
                                }
                            }

                        }

                    }
                }
                pkDetailsStor.add(pkDetStor);
                pkDetails.add(pkDet);
                amountDetails.add(amountDet);
                amountDetailsRaznic.add(amountDetRaznic);
            }
            if (flagCorrectData) {
                boolean flagAmCorrect = true;

                for (int i = 0; i < pkDetails.size(); i++) {
                    int amCount = 0;
                    for (int j = 0; j < pkDetails.get(i).size(); j++) {
                        amCount += amountDetails.get(i).get(j);
                    }
                    if (amCount != Integer.parseInt(jTable3.getValueAt(i, 3).toString())) {
                        flagAmCorrect = false;
                        JOptionPane.showMessageDialog(this, "Колличество взятых деталей не "
                                + "\nсоответствует колличеству деталей в запросе\n"
                                + jTable3.getValueAt(i, 2).toString());
                        break;
                    }
                }
                if (!flagAmCorrect) {
                    return;
                }
                for (int i = 0; i < pkDetails.size(); i++) {
                    for (int j = 0; j < pkDetails.get(i).size(); j++) {
                        try {
                            MainRemOtdel.st.executeQuery("update detailfromwarehouse set"
                                    + " amount=" + amountDetailsRaznic.get(i).get(j)
                                    + " where PK_detailfromwh=" + pkDetailsStor.get(i).get(j));
                        } catch (SQLException ex) {
                            Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                try {
                    java.sql.Date date = new java.sql.Date(jDateChooser1.getDateEditor().getDate().getTime());
                    MainRemOtdel.st.executeQuery("update zapros set"
                            + " flagofcomplete=1,"
                            + " timetocomplete = TO_DATE('" + date + "', 'YYYY-MM-DD')"
                            + " where PK_zapros=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                    JOptionPane.showMessageDialog(this, "Запрос успешно выполнен");
                    addDataInTable();
                    jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
                    jTable4.getColumnModel().getColumn(0).setMinWidth(0);
                    jTable4.getColumnModel().getColumn(0).setPreferredWidth(0);
                    jTable4.getColumnModel().getColumn(1).setHeaderValue("Деталь");
                    jTable4.getColumnModel().getColumn(2).setHeaderValue("Расположение");
                    jTable4.getColumnModel().getColumn(3).setHeaderValue("Колличество на складе");
                } catch (SQLException ex) {
                    Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }//GEN-LAST:event_jButtonExecuteActionPerformed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        // TODO add your handling code here:
        ResultSet resSet = null;
        jTable4.editCellAt(0, 0); //уводим фокус с редактируемой ячейки
        boolean flagUseModel = false;
        int i = 0;
        for (; i < indsParTable.size(); i++) {
            if (indsParTable.get(i) == jTable3.getSelectedRow()) {
                flagUseModel = true;
                break;
            }
        }
        if (flagUseModel) {
            jTable4.setModel(sctructForTableses.get(i));
        } else {
            try {
                resSet = MainRemOtdel.st.executeQuery("select PK_detailfromwh, detailfromwarehouse.pk_detail, detail.nameofdetail,location, amount from detailfromwarehouse"
                        + " inner join detail on detail.PK_detail=detailfromwarehouse.PK_detail"
                        + " where detailfromwarehouse.PK_detail=" + jTable3.getValueAt(jTable3.getSelectedRow(), 1).toString() + " and  PK_storekeeper=" + PK);
            } catch (SQLException ex) {
                Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTable4.setModel(DbUtils.resultSetToTableModel(resSet));
            DefaultTableModel dtm4 = (DefaultTableModel) jTable4.getModel();
            Object[] objects = {};
            dtm4.addColumn("Взять деталей(шт)");
            indsParTable.add(jTable3.getSelectedRow());
            sctructForTableses.add(dtm4);
        }
        jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable4.getColumnModel().getColumn(0).setMinWidth(0);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable4.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable4.getColumnModel().getColumn(1).setMinWidth(0);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(0);
        jTable4.getColumnModel().getColumn(2).setHeaderValue("Деталь");
        jTable4.getColumnModel().getColumn(3).setHeaderValue("Расположение");
        jTable4.getColumnModel().getColumn(4).setHeaderValue("Колличество на складе");
    }//GEN-LAST:event_jTable3MousePressed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
        AddUpdateDetailFromStore addUpdateDetailFromStore = new AddUpdateDetailFromStore(0, -1, PK);
        addUpdateDetailFromStore.setListenerCloseForm(new ListenerCloseForm(this));
        addUpdateDetailFromStore.setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Выделите запись для изменения");
        } else {
            Object PKTMP = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            int primKey = Integer.parseInt(PKTMP.toString());
            AddUpdateDetailFromStore addUpdateDetailFromStore = new AddUpdateDetailFromStore(1, primKey, PK);
            addUpdateDetailFromStore.setListenerCloseForm(new ListenerCloseForm(this));
            addUpdateDetailFromStore.setVisible(true);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Выделите строку для удаления");
        } else {
            try {
                Object PK = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                int primKey = Integer.parseInt(PK.toString());

                int option = JOptionPane.showConfirmDialog(this, "Вы уверены что хотите удалить запись",
                        "Удаление записи", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == 0) {
                    MainRemOtdel.st.executeQuery("delete from detailfromwarehouse where PK_detailfromwh=" + PK);
                    addDataInTable();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Удаление невозможно");
                Logger.getLogger(DetailsStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Detail detail = new Detail();
        detail.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItemCloseActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonExecute;
    private javax.swing.JButton jButtonUpdate;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelFIO;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemClose;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables

}
