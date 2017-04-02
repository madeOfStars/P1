package p1.listeners;

import entity.Project;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 */
public class MListener extends MouseAdapter{
    private final OperationsUtil operationsUtils=OperationsUtil.getInstance();
    
    @Override
    public void mouseClicked(MouseEvent event){
        Object src=event.getSource();
        if (src instanceof JLabel){
            JLabel temp=(JLabel)src;
            if (temp.getName().equals("NEW_PRJ_LBL")){
                operationsUtils.addNewProject();
            }
        } else if (src instanceof JTable){
            JTable table=(JTable)src;
            Point p=event.getPoint();
            int row=table.rowAtPoint(p);
            if (event.getClickCount()==2){
                TableModel model=table.getModel();
                Project project=new Project();
                project.setId((int)model.getValueAt(row, 0));
                project.setProjectName((String)model.getValueAt(row, 1));
                project.setPath((String)model.getValueAt(row, 2));
                project.setDateAdded((Date)model.getValueAt(row, 3));
            }
        }
    }
}
