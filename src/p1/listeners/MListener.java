package p1.listeners;

import entity.Project;
import entity.Release;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import p1.enums.ActiveView;
import p1.enums.LabelEnum;
import p1.enums.TableEnum;
import p1.helpers.MouseListenerHelper;
import p1.utils.FlowUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 * @param <E>
 */
public class MListener<E> extends MouseListenerHelper {

    private final OperationsUtil operationsUtils = OperationsUtil.getInstance();
    private final FlowUtil flowUtil = FlowUtil.getInstance();

    private E element;

    public MListener() {
    }

    public MListener(E element) {
        this.element = element;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Object src = event.getSource();
        if (src instanceof JLabel) {
            JLabel temp = (JLabel) src;
            if (temp.getName().equals(LabelEnum.NEW_PRJ_LBL.name())) {
                operationsUtils.popUpMessages(operationsUtils.addNewProject(), "Project Successfully Added", "Project Failed To Be Added", ActiveView.PROJECT_VIEW);
            } else if (temp.getName().equals(LabelEnum.NEW_RELEASE_LBL.name())) {
                flowUtil.addNewRelease();
            } else if (temp.getName().equals(LabelEnum.NEW_REVISION_LBL.name())) {
                flowUtil.addNewRevision();
            }
        } else if (src instanceof JTable) {
            JTable table = (JTable) src;
            if (table.getName().equals(TableEnum.PROJECT_TABLE.name())) {
                Point p = event.getPoint();
                int row = table.rowAtPoint(p);
                if (event.getClickCount() == 2) {
                    TableModel model = table.getModel();
                    Project project = new Project();
                    project.setId((int) model.getValueAt(row, 0));
                    project.setProjectName((String) model.getValueAt(row, 1));
                    project.setPath((String) model.getValueAt(row, 2));
                    project.setDateAdded((Date) model.getValueAt(row, 3));
                    flowUtil.defineProjectView(project);
                }
            } else if (table.getName().equals(TableEnum.RELEASE_TABLE.name())) {
                Point p = event.getPoint();
                int row = table.rowAtPoint(p);
                int column = table.columnAtPoint(p);
                TableModel model = table.getModel();
                Release release = creteRelease(model, row, (Project) element);
                if (event.getClickCount() == 1) {
                    if (column == 2) {
                        flowUtil.closeRelease(release);
                    }
                } else if (event.getClickCount() == 2) {
                    if (column != 2) {
                        flowUtil.defineReleaseView(release);
                    }
                }
            }
        }
    }
}
