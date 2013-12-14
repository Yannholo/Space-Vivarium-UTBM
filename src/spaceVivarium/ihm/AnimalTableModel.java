package spaceVivarium.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AnimalTableModel extends AbstractTableModel {

    private List<AnimalQuantite> list;

    public AnimalTableModel(List<AnimalQuantite> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String name = null;
        switch (columnIndex) {
        case 0:
            name = "animal";
            break;
        case 1:
            name = "quantite";
            break;
        default:
            break;
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AnimalQuantite animalQuantite = list.get(rowIndex);
        Object object = null;
        switch (columnIndex) {
        case 0:
            object = animalQuantite.getName();
            break;
        case 1:
            object = animalQuantite.getNum();
            break;
        default:
            break;
        }

        return object;
    }

}
