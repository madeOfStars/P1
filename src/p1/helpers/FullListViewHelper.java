/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.helpers;

import entity.Project;
import entity.Release;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import p1.enums.TableEnum;
import p1.utils.DatabaseUtil;
import p1.utils.FlowUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon Mecka
 */
public class FullListViewHelper {

    private Object[][] data;
    private final DatabaseUtil dbUtil = DatabaseUtil.getInstance();
    private String[] columns;
    private TableEnum tableEnum;

    public FullListViewHelper() {
    }

    public FullListViewHelper(String[] columns, TableEnum tableEnum) {
        this.columns = columns;
        this.tableEnum = tableEnum;
    }

    protected void fillJTable() {
        switch (tableEnum) {
            case PROJECT_TABLE: {
                List<Project> lista = dbUtil.getAllProjects();
                data = new Object[lista.size()][4];
                for (int i = 0; i < lista.size(); i++) {
                    data[i][0] = lista.get(i).getId();
                    data[i][1] = lista.get(i).getProjectName();
                    data[i][2] = lista.get(i).getPath();
                    data[i][3] = lista.get(i).getDateAdded();
                }
                break;
            }
            case RELEASE_TABLE: {
                List<Release> lista = OperationsUtil.getInstance().getAllReleases(FlowUtil.getInstance().getCurrentProject());
                data = new Object[lista.size()][5];
                for (int i = 0; i < lista.size(); i++) {
                    data[i][0] = lista.get(i).getId();
                    data[i][1] = lista.get(i).getCode();
                    data[i][2] = lista.get(i).getDateAdded();
                    data[i][3] = lista.get(i).getClosed();
                    data[i][4] = lista.get(i).getDateClosed();
                }
                break;
            }
        }
    }

    protected class CellEditable extends DefaultTableModel {

        public CellEditable(Object[][] data, String[] columns) {
            super(data, columns);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    protected Object[][] getData() {
        return data;
    }

    protected void setData(Object[][] data) {
        this.data = data;
    }

    protected String[] getColumns() {
        return columns;
    }

    protected void setColumns(String[] columns) {
        this.columns = columns;
    }

    protected TableEnum getTableEnum() {
        return tableEnum;
    }

    protected void setTableEnum(TableEnum tableEnum) {
        this.tableEnum = tableEnum;
    }

}
