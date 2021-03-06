package p1.helpers;

import entity.Project;
import entity.Release;
import entity.Revision;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import p1.enums.ImageEnum;
import p1.enums.TableEnum;
import p1.utils.DatabaseUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon Mecka
 * @param <E>
 */
public class FullListViewHelper<E> {

    private Object[][] data;
    private final DatabaseUtil dbUtil = DatabaseUtil.getInstance();
    private String[] columns;
    private TableEnum tableEnum;

    private E element;

    ;

    public FullListViewHelper() {
    }

    public FullListViewHelper(String[] columns, TableEnum tableEnum, E element) {
        this.columns = columns;
        this.tableEnum = tableEnum;
        this.element = element;
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
                List<Release> lista = OperationsUtil.getInstance().getAllReleases((Project) element);
                data = new Object[lista.size()][7];
                for (int i = 0; i < lista.size(); i++) {
                    data[i][0] = lista.get(i).getId();
                    data[i][1] = lista.get(i).getCode();
                    data[i][2] = lista.get(i).getDateAdded();
                    data[i][3] = lista.get(i).getClosed();
                    data[i][4] = lista.get(i).getDateClosed();
                    data[i][5] = lista.get(i).getReleaseFolder();
                    data[i][6] = OperationsUtil.getInstance().getIcon(ImageEnum.CLOSE);
                }
                break;
            }
            case REVISION_TABLE: {
                List<Revision> lista=OperationsUtil.getInstance().getAllRevisions((Release) element);
                data=new Object[lista.size()][3];
                for (int i = 0; i < lista.size(); i++) {
                    data[i][0] = lista.get(i).getId();
                    data[i][1] = lista.get(i).getRevisionNumber();
                    data[i][2] = lista.get(i).getDateAdded();
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

        @Override
        public Class<?> getColumnClass(int column) {
            if (tableEnum == TableEnum.RELEASE_TABLE) {
                if (column == 6) {
                    return ImageIcon.class;
                }
            }
            return Object.class;
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

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

}
