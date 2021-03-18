/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.MotorcycleMake;
import rs.ac.bg.fon.ps.domain.Rider;

/**
 *
 * @author Matija
 */
public class RiderTableModel extends AbstractTableModel {

    private final List<Rider> riders;
    private final String[] columnNames = {"ID", "Firstname", "Surname", "Nationality", "Motorcycle", "Racing number"};
    private final Class[] columnClasses = {Long.class, String.class, String.class, String.class, MotorcycleMake.class, Integer.class};

    public RiderTableModel(List<Rider> riders) {
        this.riders = riders;
    }

    @Override
    public int getRowCount() {
        if (riders == null) {
            return 0;
        }
        return riders.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rider r = riders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getID();
            case 1:
                return r.getFirstname();
            case 2:
                return r.getSurname();
            case 3:
                return r.getNationality();
            case 4:
                return r.getMotorcycle();
            case 5:
                return r.getRacingNum();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addRider(Rider rider) {
        riders.add(rider);
        fireTableRowsInserted(riders.size() - 1, 0);
    }

    public Rider getRiderAt(int row) {
        return riders.get(row);
    }

    public void deleteRider(Rider r) {
        int index = riders.indexOf(r);
        fireTableRowsDeleted(index, index);
    }

    public void updateRider(Rider r) {
        int index = riders.indexOf(r);
        fireTableRowsUpdated(index, index);
    }
}
