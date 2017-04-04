package p1.listeners;

import entity.Project;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import p1.enums.ActiveView;
import p1.enums.LabelEnum;
import p1.utils.FlowUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 */
public class MListener extends MouseAdapter {

    private final OperationsUtil operationsUtils = OperationsUtil.getInstance();
    private final FlowUtil flowUtil = FlowUtil.getInstance();

    @Override
    public void mouseClicked(MouseEvent event) {
        Object src = event.getSource();
        if (src instanceof JLabel) {
            JLabel temp = (JLabel) src;
            if (temp.getName().equals(LabelEnum.NEW_PRJ_LBL.name())) {
                operationsUtils.popUpMessages(operationsUtils.addNewProject(), "Project Successfully Added", "Project Failed To Be Added", ActiveView.PROJECT_VIEW);
            } else if (temp.getName().equals(LabelEnum.NEW_RELEASE_LBL.name())) {
                flowUtil.addNewRelease();
            }
        } else if (src instanceof JTable) {
            JTable table = (JTable) src;
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
        }
    }
}
