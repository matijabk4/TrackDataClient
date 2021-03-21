/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Race;
import rs.ac.bg.fon.ps.domain.RaceItem;

/**
 *
 * @author Matija
 */
public class RaceTableModel extends AbstractTableModel {

    private final List<Race> races;
    private final String[] columnNames = {"ID", "Track", "Date", "Name", "Total laps"};
    //private final Class[] columnClasses = {Integer.class, String.class, Date.class, String.class, Integer.class};

    public RaceTableModel(List<Race> races) {
        this.races = races;
    }

    @Override
    public int getRowCount() {
        if (races == null) {
            return 0;
        }
        return races.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Race r = races.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getId();
            case 1:
                return r.getTrack();
            case 2:
                return r.getDate().toString();
            case 3:
                return r.getName();
            case 4:
                return r.getTotalLaps();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Race r = races.get(rowIndex);
        /*switch (columnIndex) {
            case 0:
                r.setId(Integer.parseInt(String.valueOf(aValue)));
                break;
            case 1:
                r.setSponsor(String.valueOf(aValue));
                break;
            case 2:
                r.setBudget(Double.parseDouble(aValue.toString()));
                break;
            case 3:
                r.setName(String.valueOf(aValue));
                break;
            case 4:
                r.setGeneralManager(String.valueOf(aValue));
                break;

            case 5:
                r.setHeadquarters(String.valueOf(aValue));
                break;

        }*/
    }
public Race getRaceAt(int row) {
        return races.get(row);
    }
 public List<RaceItem> getRaceItemsForRace(Race r){
     return r.getItems();
 }
    /*@Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }*/

 /*public void addTeam(RacingTeam t) {
        teams.add(t);
        fireTableRowsInserted(teams.size() - 1, 0);
    }

    public RacingTeam getTeamAt(int row) {
        return teams.get(row);
    }

    public void deleteTeam(RacingTeam r) {
        int index = teams.indexOf(r);
        fireTableRowsDeleted(index, index);
    }

    public void updateTeam(Rider r) {
        int index = teams.indexOf(r);
        fireTableRowsUpdated(index, index);
    }*/

   
}
