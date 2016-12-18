/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.manager;

import com.toedter.calendar.JTextFieldDateEditor;
import spravochn.manufacture.Manufacturer;
import spravochn.typeofcrash.TypeCrash;
import spravochn.typeofdevice.TypeDevice;
import spravochn.status.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import main.rem.otdel.ListenerCloseForm;
import main.rem.otdel.MainRemOtdel;
import main.rem.otdel.UpdatesDataInForms;
import net.proteanit.sql.DbUtils;
import spravochn.manufacture.ManufacturerAddUpdate;
import spravochn.typeofcrash.TypeCrashAddUpdate;
import spravochn.typeofdevice.TypeDeviceAddUpdate;

/**
 *
 * @author tigler
 */
public class Orders extends javax.swing.JFrame implements UpdatesDataInForms {

    /**
     * Creates new form Orders
     */
    private int PK;
    private int PKClient;
    private boolean isCreateNew = true;

    public Orders(int PK) {
        initComponents();
        this.PK = PK;
        addDataInTable();
    }

    @Override
    public void addDataInTable() {
        this.setEnabled(true);
        ResultSet resSet = null;
        ResultSet resSet2 = null;

        try {
            resSet = MainRemOtdel.st.executeQuery("select myorder.PK_ORDER,myorder.NUMOFORDER,"
                    + "TO_CHAR(myorder.TIMETOACCEPT, 'DD.MM.YYYY'),"
                    + "TO_CHAR(myorder.TIMETODELIVERY, 'DD.MM.YYYY'),"
                    + "myorder.COSTOFORDER,myorder.TYPEOFORDER,myorder.PK_MANAGER"
                    + ",myorder.PK_STATUS,"
                    + " status.NAMEOFSTATUS,"
                    + " myorder.PK_CLIENT,"
                    + " client.FAMOFCLIENT || ' ' || client.NAMEOFCLIENT  || ' ' || client.OTCOFCLIENT"
                    // + " manager.FAMOFMANAGER || ' ' || manager.NAMEOFMANAGER  || ' ' || manager.OTCOFMANAGER"
                    + " from myorder "
                    + " inner join status on status.PK_status=myorder.PK_status"
                    + " inner join manager on manager.PK_manager=myorder.PK_manager"
                    + " inner join client on client.PK_client=myorder.PK_client"
                    + " where myorder.PK_status=6 or myorder.PK_status=7 or myorder.PK_status=8");
            jTable1.setModel(DbUtils.resultSetToTableModel(resSet));
            resSet2 = MainRemOtdel.st.executeQuery("select myorder.PK_ORDER,myorder.NUMOFORDER,"
                    + "TO_CHAR(myorder.TIMETOACCEPT, 'DD.MM.YYYY'),"
                    + "TO_CHAR(myorder.TIMETODELIVERY, 'DD.MM.YYYY'),"
                    + "myorder.COSTOFORDER,myorder.TYPEOFORDER,myorder.PK_MANAGER"
                    + ",myorder.PK_STATUS,"
                    + " status.NAMEOFSTATUS,"
                    + " myorder.PK_CLIENT,"
                    + " client.FAMOFCLIENT || ' ' || client.NAMEOFCLIENT  || ' ' || client.OTCOFCLIENT"
                    // + " manager.FAMOFMANAGER || ' ' || manager.NAMEOFMANAGER  || ' ' || manager.OTCOFMANAGER"
                    + " from myorder "
                    + " inner join status on status.PK_status=myorder.PK_status"
                    + " inner join manager on manager.PK_manager=myorder.PK_manager"
                    + " inner join client on client.PK_client=myorder.PK_client"
                    + " ");
            jTable2.setModel(DbUtils.resultSetToTableModel(resSet2));
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTable1.getColumnModel().getColumn(1).setHeaderValue("Номер");
        jTable1.getColumnModel().getColumn(2).setHeaderValue("Дата создания");
        jTable1.getColumnModel().getColumn(3).setHeaderValue("Дата завершения");
        jTable1.getColumnModel().getColumn(4).setHeaderValue("Стоимость");
        jTable1.getColumnModel().getColumn(5).setHeaderValue("Тип");
        jTable1.getColumnModel().getColumn(8).setHeaderValue("Статус");
        jTable1.getColumnModel().getColumn(10).setHeaderValue("Клиент");

        //пк заказа
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        //пк менеджера
        jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(6).setMinWidth(0);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);
        //пк статуса
        jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(7).setMinWidth(0);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);
        //пк клиета
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(0);

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (jTable1.getValueAt(i, 5).toString().equals("0")) {
                jTable1.setValueAt("Гарантийный", i, 5);
            } else {
                if (jTable1.getValueAt(i, 5).toString().equals("1")) {
                    jTable1.setValueAt("Не гарантийный", i, 5);
                } else {
                    jTable1.setValueAt("Неизвестно", i, 5);
                }
            }
        }

        //
        jTable2.getColumnModel().getColumn(1).setHeaderValue("Номер");
        jTable2.getColumnModel().getColumn(2).setHeaderValue("Дата создания");
        jTable2.getColumnModel().getColumn(3).setHeaderValue("Дата завершения");
        jTable2.getColumnModel().getColumn(4).setHeaderValue("Стоимость");
        jTable2.getColumnModel().getColumn(5).setHeaderValue("Тип");
        jTable2.getColumnModel().getColumn(8).setHeaderValue("Статус");
        jTable2.getColumnModel().getColumn(10).setHeaderValue("Клиент");

        //пк заказа
        jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(0).setMinWidth(0);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);
        //пк менеджера
        jTable2.getColumnModel().getColumn(6).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(6).setMinWidth(0);
        jTable2.getColumnModel().getColumn(6).setPreferredWidth(0);
        //пк статуса
        jTable2.getColumnModel().getColumn(7).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(7).setMinWidth(0);
        jTable2.getColumnModel().getColumn(7).setPreferredWidth(0);
        //пк клиета
        jTable2.getColumnModel().getColumn(9).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(9).setMinWidth(0);
        jTable2.getColumnModel().getColumn(9).setPreferredWidth(0);

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            if (jTable2.getValueAt(i, 5).toString().equals("0")) {
                jTable2.setValueAt("Гарантийный", i, 5);
            } else {
                if (jTable2.getValueAt(i, 5).toString().equals("1")) {
                    jTable2.setValueAt("Не гарантийный", i, 5);
                } else {
                    jTable2.setValueAt("Неизвестно", i, 5);
                }
            }
        }

        jDateChooserAddDate.setDateFormatString("dd.MM.yyyy");
        jDateChooserAddDate.setDate(new Date());
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) jDateChooserAddDate.getDateEditor();
        editor2.setEditable(false);

        ResultSet resSetCrash = null;
        pkCrash = new ArrayList<String>();
        valueCrash = new ArrayList<String>();

        ResultSet resSetProizv = null;
        pkProizv = new ArrayList<String>();
        valueProizv = new ArrayList<String>();

        ResultSet resTypeDevice = null;
        pkTypeDevice = new ArrayList<String>();
        valueTypeDevice = new ArrayList<String>();
        try {
            //тип поломки
            resSetCrash = MainRemOtdel.st.executeQuery("select * from typeofcrash");
            TableModel tableModel = DbUtils.resultSetToTableModel(resSetCrash);
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                pkCrash.add(tableModel.getValueAt(i, 0).toString());
                valueCrash.add(tableModel.getValueAt(i, 1).toString());
            }
            jComboBoxTypeCrash.setModel(new DefaultComboBoxModel(valueCrash.toArray()));

            //производитель
            resSetProizv = MainRemOtdel.st.executeQuery("select * from manufacturer");
            tableModel = DbUtils.resultSetToTableModel(resSetProizv);
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                pkProizv.add(tableModel.getValueAt(i, 0).toString());
                valueProizv.add(tableModel.getValueAt(i, 1).toString());
            }
            jComboBoxManufacturers.setModel(new DefaultComboBoxModel(valueProizv.toArray()));

            //тип устройства
            resSetProizv = MainRemOtdel.st.executeQuery("select * from typeofdevice");
            tableModel = DbUtils.resultSetToTableModel(resSetProizv);
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                pkTypeDevice.add(tableModel.getValueAt(i, 0).toString());
                valueTypeDevice.add(tableModel.getValueAt(i, 1).toString());
            }
            jComboBoxTypeDevice.setModel(new DefaultComboBoxModel(valueTypeDevice.toArray()));

            jComboBoxTypeCrash.setSelectedIndex(-1);
            jComboBoxTypeDevice.setSelectedIndex(-1);
            jComboBoxManufacturers.setSelectedIndex(-1);

            ArrayList<String> type = new ArrayList<String>();
            type.add("Гарантийный");
            type.add("Не гарантийный");
            jComboBoxType.setModel(new DefaultComboBoxModel(type.toArray()));
            jComboBoxType.setSelectedIndex(-1);
            //
            correctSizeTable(jTable2);
            correctSizeTable(jTable1);
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    ArrayList<String> pkCrash;
    ArrayList<String> valueCrash;
    ArrayList<String> pkProizv;
    ArrayList<String> valueProizv;
    ArrayList<String> pkTypeDevice;
    ArrayList<String> valueTypeDevice;

    public void correctSizeTable(javax.swing.JTable jTable) {
        jTable.getColumnModel().getColumn(1).setMaxWidth(60);
        jTable.getColumnModel().getColumn(1).setMinWidth(60);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        //
        jTable.getColumnModel().getColumn(2).setMaxWidth(100);
        jTable.getColumnModel().getColumn(2).setMinWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        //
        jTable.getColumnModel().getColumn(3).setMaxWidth(100);
        jTable.getColumnModel().getColumn(3).setMinWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        //
        jTable.getColumnModel().getColumn(4).setMaxWidth(90);
        jTable.getColumnModel().getColumn(4).setMinWidth(90);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        //
        jTable.getColumnModel().getColumn(5).setMaxWidth(110);
        jTable.getColumnModel().getColumn(5).setMinWidth(110);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(110);
        //
        jTable.getColumnModel().getColumn(8).setMaxWidth(150);
        jTable.getColumnModel().getColumn(8).setMinWidth(150);
        jTable.getColumnModel().getColumn(8).setPreferredWidth(150);
        //
        jTable.getColumnModel().getColumn(10).setMaxWidth(200);
        jTable.getColumnModel().getColumn(10).setMinWidth(200);
        jTable.getColumnModel().getColumn(10).setPreferredWidth(200);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable( )
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldAddFam = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldAddName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAddOtch = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAddTelefon = new javax.swing.JTextField();
        jButtonChooseExist = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jComboBoxManufacturers = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldModel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxTypeDevice = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxTypeCrash = new javax.swing.JComboBox<>();
        jButtonAddTypeOfDevice = new javax.swing.JButton();
        jButtonAddManufacturer = new javax.swing.JButton();
        jButtonAddTypeOfCrash = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButtonAccept = new javax.swing.JButton();
        jDateChooserAddDate = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable( )
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuClose = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemStatus = new javax.swing.JMenuItem();
        jMenuItemManufacturer = new javax.swing.JMenuItem();
        jMenuItemTypeCrash = new javax.swing.JMenuItem();
        jMenuItemTypeDevice = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Заказы");

        jTabbedPane1.setToolTipText("");

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

        jButton1.setText("Выдать");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Выдача", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Клиент"));

        jLabel1.setText("Фамилия");

        jLabel2.setText("Имя");

        jLabel3.setText("Отчество");

        jTextFieldAddOtch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAddOtchActionPerformed(evt);
            }
        });

        jLabel4.setText("Телефон");

        jButtonChooseExist.setText("Выбрать существующего пользователя");
        jButtonChooseExist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseExistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonChooseExist, javax.swing.GroupLayout.PREFERRED_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(jTextFieldAddName, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(jTextFieldAddOtch)
                    .addComponent(jTextFieldAddTelefon)
                    .addComponent(jTextFieldAddFam, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldAddFam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldAddName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAddOtch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAddTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonChooseExist)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Устройство"));

        jLabel5.setText("Производитель");

        jLabel6.setText("Модель");

        jLabel7.setText("Тип устройства");

        jLabel8.setText("Тип поломки");

        jButtonAddTypeOfDevice.setText("Добавить тип устройства");
        jButtonAddTypeOfDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddTypeOfDeviceActionPerformed(evt);
            }
        });

        jButtonAddManufacturer.setText("Добавить производителя");
        jButtonAddManufacturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddManufacturerActionPerformed(evt);
            }
        });

        jButtonAddTypeOfCrash.setText("Добавить тип поломки");
        jButtonAddTypeOfCrash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddTypeOfCrashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxManufacturers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxTypeCrash, 0, 271, Short.MAX_VALUE)
                            .addComponent(jComboBoxTypeDevice, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldModel)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonAddTypeOfDevice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAddManufacturer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAddTypeOfCrash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxTypeDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxManufacturers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxTypeCrash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButtonAddTypeOfDevice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAddManufacturer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAddTypeOfCrash)
                .addGap(9, 9, 9))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Заказ"));

        jLabel9.setText("Дата оформления");

        jButtonAccept.setText("Оформить");
        jButtonAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptActionPerformed(evt);
            }
        });

        jLabel10.setText("Тип заказа");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jDateChooserAddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(37, 37, 37)
                .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addComponent(jDateChooserAddDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Добавление", jPanel1);

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
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("Редактировать");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Просмотр всех заказов", jPanel6);

        jMenu2.setText("Файл");

        jMenuClose.setText("Закрыть");
        jMenuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCloseActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuClose);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Справочники");

        jMenuItemStatus.setText("Статусы");
        jMenuItemStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStatusActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemStatus);

        jMenuItemManufacturer.setText("Производители");
        jMenuItemManufacturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemManufacturerActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemManufacturer);

        jMenuItemTypeCrash.setText("Типы поломок");
        jMenuItemTypeCrash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTypeCrashActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemTypeCrash);

        jMenuItemTypeDevice.setText("Типы устройств");
        jMenuItemTypeDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTypeDeviceActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemTypeDevice);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemManufacturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemManufacturerActionPerformed
        // TODO add your handling code here:
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setVisible(true);
    }//GEN-LAST:event_jMenuItemManufacturerActionPerformed

    private void jMenuItemStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemStatusActionPerformed
        // TODO add your handling code here:
        Status status = new Status();
        status.setVisible(true);
    }//GEN-LAST:event_jMenuItemStatusActionPerformed

    private void jMenuItemTypeDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTypeDeviceActionPerformed
        // TODO add your handling code here:
        TypeDevice typeDevice = new TypeDevice();
        typeDevice.setVisible(true);
    }//GEN-LAST:event_jMenuItemTypeDeviceActionPerformed

    private void jMenuItemTypeCrashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTypeCrashActionPerformed
        // TODO add your handling code here:
        TypeCrash typeCrash = new TypeCrash();
        typeCrash.setVisible(true);
    }//GEN-LAST:event_jMenuItemTypeCrashActionPerformed

    private void jMenuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuCloseActionPerformed

    //добавление заказа
    private void jButtonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptActionPerformed

        if (jTextFieldAddFam.equals("") || jTextFieldAddName.equals("") || jTextFieldAddOtch.equals("")
                || jTextFieldAddTelefon.equals("")
                || jComboBoxTypeDevice.getSelectedIndex() == -1
                || jComboBoxManufacturers.getSelectedIndex() == -1
                || jComboBoxTypeCrash.getSelectedIndex() == -1
                || jComboBoxType.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Пожалуйста заполните все поля");
            return;
        }

        if (jTextFieldAddTelefon.getText().length() != 12 || jTextFieldAddTelefon.getText().charAt(0) != '+') {
            JOptionPane.showMessageDialog(this, "Неверный номер телефона");
            return;
        }

        String textFam = jTextFieldAddFam.getText();
        String textName = jTextFieldAddName.getText();
        String textOtch = jTextFieldAddOtch.getText();
        String textTelefon = jTextFieldAddTelefon.getText();
        //проверка по номеру телефона
        try {
            ResultSet resSet = MainRemOtdel.st.executeQuery("SELECT PK_Client,NameOfClient,FamOfClient,OtcOfClient  "
                    + " from SERVERADM.client "
                    + " WHERE NUMBEROFPHONE= '" + textTelefon + "'"
            );

            if (resSet.next() && isCreateNew == true) {
                int reply = JOptionPane.showConfirmDialog(null, "Пользователь с данным номером телефона уже существует:\n"
                        + " " + resSet.getString(3) + " " + resSet.getString(2) + " " + resSet.getString(4) + "\n"
                        + " Хотите выбрать его?", "Выбор", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    jTextFieldAddFam.setText(resSet.getString(3));
                    jTextFieldAddName.setText(resSet.getString(2));
                    jTextFieldAddOtch.setText(resSet.getString(4));
                    PKClient = Integer.parseInt(resSet.getString(1));
                    isCreateNew = false;
                }
            }
            //добавление клиента
            if (isCreateNew == true) {
                resSet = MainRemOtdel.st.executeQuery("INSERT INTO SERVERADM.CLIENT (NAMEOFCLIENT, FAMOFCLIENT, OTCOFCLIENT, NUMBEROFPHONE) "
                        + "VALUES ('" + textName + "', '" + textFam + "', '" + textOtch + "', '" + textTelefon + "')"
                );
                resSet = MainRemOtdel.st.executeQuery("select SEQCLIENT.currval from dual");
                int pkZapros = 0;
                if (resSet.next()) {
                    PKClient = resSet.getInt(1);
                }
            }

            //добавление заказа
            int typeOfOrder = jComboBoxType.getSelectedIndex();
            Date dateChooserAddDate = jDateChooserAddDate.getDate();
            java.sql.Date date = new java.sql.Date(dateChooserAddDate.getTime());
            resSet = MainRemOtdel.st.executeQuery("INSERT INTO SERVERADM.MYORDER "
                    + "(NUMOFORDER, TIMETOACCEPT, TIMETODELIVERY, COSTOFORDER, TYPEOFORDER, PK_STATUS, PK_MANAGER, PK_CLIENT) "
                    + "VALUES (SeqOrderNumber.nextval,"
                    + "TO_DATE('" + date + "', 'YYYY-MM-DD'),"
                    + "null,"
                    + " '0',"
                    + " '" + typeOfOrder + "',"
                    + " '2',"
                    + " '" + PK + "',"
                    + " '" + PKClient + "')"
            );
            resSet = MainRemOtdel.st.executeQuery("select SEQMYORDER.currval from dual");
            int pkOrder = 0;
            if (resSet.next()) {
                pkOrder = resSet.getInt(1);
            }
            //добавление устройства
            resSet = MainRemOtdel.st.executeQuery("INSERT INTO SERVERADM.DEVICE "
                    + "(MODELOFDEVICE, PK_TYPEOFDEVICE, PK_CRASH, PK_MANUFACTURER, PK_ORDER) "
                    + "VALUES ("
                    + "'" + jTextFieldModel.getText() + "', "
                    + " '" + pkTypeDevice.get(jComboBoxTypeDevice.getSelectedIndex()) + "',"
                    + " '" + pkCrash.get(jComboBoxTypeCrash.getSelectedIndex()) + "',"
                    + " '" + pkProizv.get(jComboBoxManufacturers.getSelectedIndex()) + "',"
                    + " '" + pkOrder + "'"
                    + ")"
            );
            JOptionPane.showMessageDialog(this, "Заказ успешно создан!");
            resetElements();
            this.addDataInTable();
            isCreateNew = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: Невозможно выполнить операцию(возможно введены неверные данные)");
        }

    }//GEN-LAST:event_jButtonAcceptActionPerformed
    public void resetElements() {
        jComboBoxManufacturers.setSelectedIndex(-1);
        jComboBoxTypeCrash.setSelectedIndex(-1);
        jComboBoxTypeDevice.setSelectedIndex(-1);
        jComboBoxType.setSelectedIndex(-1);
        jTextFieldAddFam.setText("");
        jTextFieldAddName.setText("");
        jTextFieldAddOtch.setText("");
        jTextFieldAddTelefon.setText("");
        jTextFieldModel.setText("");
    }

    public void setUser(int pkClient) {
        try {
            ResultSet resSet = MainRemOtdel.st.executeQuery("SELECT PK_Client,NameOfClient,FamOfClient,OtcOfClient,NUMBEROFPHONE  "
                    + " from SERVERADM.client "
                    + " WHERE PK_Client= '" + pkClient + "'"
            );
            resSet.next();
            jTextFieldAddFam.setText(resSet.getString(3));
            jTextFieldAddName.setText(resSet.getString(2));
            jTextFieldAddOtch.setText(resSet.getString(4));
            jTextFieldAddTelefon.setText(resSet.getString(5));
            PKClient = Integer.parseInt(resSet.getString(1));
            isCreateNew = false;

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //именить статус заказа на выдан и поставить сегодняшнюю дату
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Выделите запись для изминения статуса");
        } else {
            Object PK = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            int primKey = Integer.parseInt(PK.toString());
            try {
                MainRemOtdel.st.executeQuery("UPDATE SERVERADM.myorder SET  PK_STATUS= " + 7 + " WHERE PK_ORDER=" + PK);
                Date datenow = new Date();
                java.sql.Date date = new java.sql.Date(datenow.getTime());
                MainRemOtdel.st.executeQuery("UPDATE SERVERADM.myorder SET  myorder.TIMETODELIVERY= TO_DATE('" + date + "', 'YYYY-MM-DD') WHERE PK_ORDER=" + PK);
                JOptionPane.showMessageDialog(this, "Запись успешно изменена");
                this.addDataInTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: Невозможно изменить");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTable2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Выделите заказ для изминения!");
        } else {
            Object PK = jTable2.getValueAt(jTable2.getSelectedRow(), 0);
            int primKey = Integer.parseInt(PK.toString());
            OrderUpdate orderUpdate = new OrderUpdate(1, primKey);
            orderUpdate.setListenerCloseForm(new ListenerCloseForm(this));
            orderUpdate.setVisible(true);
            this.setEnabled(false);

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldAddOtchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAddOtchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAddOtchActionPerformed

    private void jButtonAddTypeOfDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddTypeOfDeviceActionPerformed
        TypeDeviceAddUpdate typeDeviceAddUpdate = new TypeDeviceAddUpdate(0, -1);
        typeDeviceAddUpdate.setListenerCloseForm(new ListenerCloseForm(this));
        typeDeviceAddUpdate.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_jButtonAddTypeOfDeviceActionPerformed

    private void jButtonAddTypeOfCrashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddTypeOfCrashActionPerformed
        TypeCrashAddUpdate typeCrashAddUpdate = new TypeCrashAddUpdate(0, -1);
        typeCrashAddUpdate.setListenerCloseForm(new ListenerCloseForm(this));
        typeCrashAddUpdate.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_jButtonAddTypeOfCrashActionPerformed

    private void jButtonAddManufacturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddManufacturerActionPerformed
        ManufacturerAddUpdate manufacturerAddUpdate = new ManufacturerAddUpdate(0, -1);
        manufacturerAddUpdate.setListenerCloseForm(new ListenerCloseForm(this));
        manufacturerAddUpdate.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_jButtonAddManufacturerActionPerformed

    private void jButtonChooseExistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChooseExistActionPerformed
        ChooseExistClient chooseExistClient = new ChooseExistClient(this);
        chooseExistClient.setListenerCloseForm(new ListenerCloseForm(this));
        chooseExistClient.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_jButtonChooseExistActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAccept;
    private javax.swing.JButton jButtonAddManufacturer;
    private javax.swing.JButton jButtonAddTypeOfCrash;
    private javax.swing.JButton jButtonAddTypeOfDevice;
    private javax.swing.JButton jButtonChooseExist;
    private javax.swing.JComboBox<String> jComboBoxManufacturers;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JComboBox<String> jComboBoxTypeCrash;
    private javax.swing.JComboBox<String> jComboBoxTypeDevice;
    private com.toedter.calendar.JDateChooser jDateChooserAddDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuClose;
    private javax.swing.JMenuItem jMenuItemManufacturer;
    private javax.swing.JMenuItem jMenuItemStatus;
    private javax.swing.JMenuItem jMenuItemTypeCrash;
    private javax.swing.JMenuItem jMenuItemTypeDevice;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldAddFam;
    private javax.swing.JTextField jTextFieldAddName;
    private javax.swing.JTextField jTextFieldAddOtch;
    private javax.swing.JTextField jTextFieldAddTelefon;
    private javax.swing.JTextField jTextFieldModel;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    // End of variables declaration//GEN-END:variables

}
