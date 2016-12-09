package RemOtdel;

import java.io.Serializable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author tigler
 */
public class ReadOnlyModel implements TableModel, Serializable {
    
    private final TableModel tableModel;

    public ReadOnlyModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    @Override
    public String getColumnName(int column) {
        return tableModel.getColumnName(column);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tableModel.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tableModel.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        tableModel.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        tableModel.removeTableModelListener(l);
    }

    @Override
    public int getRowCount() {
        return this.tableModel.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return this.tableModel.getColumnCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableModel.getValueAt(rowIndex, columnIndex);
    }
}

