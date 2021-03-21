/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.MotorcycleMake;
import rs.ac.bg.fon.ps.domain.RacingTeam;
import rs.ac.bg.fon.ps.domain.Rider;

/**
 *
 * @author Matija
 */
public class TeamTableModel extends AbstractTableModel {

    private final List<RacingTeam> teams;
    private final String[] columnNames = {"ID", "Sponsor", "Budget", "Name", "General manager", "Headquarters"};
    private final Class[] columnClasses = {Integer.class, String.class, Double.class, String.class, String.class, String.class};

    public TeamTableModel(List<RacingTeam> teams) {
        this.teams = teams;
    }

    @Override
    public int getRowCount() {
        if (teams == null) {
            return 0;
        }
        return teams.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RacingTeam r = teams.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getId();
            case 1:
                return r.getSponsor();
            case 2:
                return r.getBudget();
            case 3:
                return r.getName();
            case 4:
                return r.getGeneralManager();
            case 5:
                return r.getHeadquarters();
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
        RacingTeam r = teams.get(rowIndex);
        switch (columnIndex) {
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

        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    public void addTeam(RacingTeam t) {
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
    }
}
