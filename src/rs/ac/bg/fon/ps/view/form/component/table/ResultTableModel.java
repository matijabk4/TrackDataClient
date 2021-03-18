/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.sql.Time;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.RaceItem;

/**
 *
 * @author Matija
 */
public class ResultTableModel extends AbstractTableModel {

    private final List<RaceItem> raceItems;
    private final String[] columnNames = {"ID", "Racing number", "Rider", "Starting position", "Number of laps", "Average speed", "Top speed", "Fastest lap", "Finishing position", "Team"};
    private final Class[] columnClasses = {Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Double.class, Double.class, Time.class, Integer.class, Integer.class};

    public ResultTableModel(List<RaceItem> raceItems) {
        this.raceItems = raceItems;
    }

    @Override
    public int getRowCount() {
        if (raceItems == null) {
            return 0;
        }
        return raceItems.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RaceItem r = raceItems.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getRace().getId();
            case 1:
                return r.getRaceNumber();
            case 2:
                return r.getRider().getFirstname()+" "+r.getRider().getSurname();
            case 3:
                return r.getStartPosition();
            case 4:
                return r.getNumberOfLaps();
            case 5:
                return r.getAverageSpeed();
            case 6:
                return r.getTopSpeed();
            case 7:
                return r.getFastestLap();
            case 8:
                return r.getFinishPosition();
            case 9:
                return r.getTeam().getName();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4 || columnIndex == 5 || columnIndex == 6 || columnIndex == 7 || columnIndex == 8;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RaceItem r = raceItems.get(rowIndex);
        switch (columnIndex) {
            case 4:
                r.setNumberOfLaps(Integer.parseInt(String.valueOf(aValue)));
                break;
            case 5:
                r.setAverageSpeed(Double.parseDouble(String.valueOf(aValue)));
                break;
            case 6:
                r.setTopSpeed(Double.parseDouble(String.valueOf(aValue)));
                break;
            case 7:
                r.setFastestLap(Time.valueOf(String.valueOf(aValue)));
                break;
            case 8:
                r.setFinishPosition((Integer.parseInt(String.valueOf(aValue))));
                break;

        }
    }

    public List<RaceItem> getRaceItems() {
        return raceItems;
    }
    /* @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    public void addRider(Rider rider) {
        raceItems.add(rider);
        fireTableRowsInserted(raceItems.size() - 1, 0);
    }

    public Rider getRiderAt(int row) {
        return raceItems.get(row);
    }

    public void deleteRider(Rider r) {
        int index = raceItems.indexOf(r);
        fireTableRowsDeleted(index, index);
    }

    public void updateRider(Rider r) {
        int index = raceItems.indexOf(r);
        fireTableRowsUpdated(index, index);
    }*/
}
