/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Race;
import rs.ac.bg.fon.ps.domain.RaceItem;
import rs.ac.bg.fon.ps.domain.RacingTeam;
import rs.ac.bg.fon.ps.domain.Rider;

/**
 *
 * @author Matija
 */
public class RaceItemTableModel extends AbstractTableModel {

    private final Race race;
    private String[] colNames = new String[]{"Rider racing number", "Rider", "Starting position", "Team"};

    public RaceItemTableModel(Race race) {
        this.race = race;
    }

    @Override
    public int getRowCount() {
        if (race != null) {
            return race.getItems().size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void addRaceItem(int racingNumber, Rider r, RacingTeam t, int startingPosition) {
        RaceItem raceItem = new RaceItem();
        raceItem.setRider(r);
        raceItem.setTeam(t);
        raceItem.setStartPosition(startingPosition);
        raceItem.setRaceNumber(racingNumber);
        race.getItems().add(raceItem);
        fireTableRowsInserted(race.getItems().size() - 1, race.getItems().size() - 1);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RaceItem item = race.getItems().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getRaceNumber();
            case 1:
                return item.getRider().getFirstname() + " " + item.getRider().getSurname();
            case 2:
                return item.getStartPosition();
            case 3:
                    return item.getTeam().getName();
            default:
                return "n/a";
        }
    }

    public void removeRaceItem(int rowIndex) {
        RaceItem r = race.getItems().get(rowIndex);
        race.getItems().remove(rowIndex);
        fireTableRowsDeleted(race.getItems().size() - 1, race.getItems().size() - 1);
    }

    public Race getRace() {
        return race;
    }

    public void removeAllRaceItems() {
        race.getItems().clear();
        fireTableDataChanged();
    }
}
