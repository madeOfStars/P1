package p1.dialogs;

import entity.Project;
import entity.Release;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import p1.dialogs.helpers.DialogHelper;
import p1.enums.ContainerPositionEnum;
import p1.enums.FontEnum;
import p1.utils.DatabaseUtil;
import p1.utils.FlowUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 */
public class EditReleaseDialog extends DialogHelper {

    private final JComboBox<Release> cb;
    private List<Release> completeList;

    public EditReleaseDialog(JFrame frame, String title) {
        super(frame, title);
        setSize(400, 200);
        JPanel header = new JPanel(new GridLayout(1, 2));
        JLabel lbl = new JLabel("Select Release");
        lbl.setFont(FontEnum.CONTENT.getFont());
        header.add(lbl);
        cb = new JComboBox<>();
        cb.setFont(FontEnum.CONTENT.getFont());
        cb.addActionListener(new LocalProjectLister());
        getReleaseList();
        header.add(cb);
        getContentPane().add(header, ContainerPositionEnum.NORTH.getLocation());
    }

    private void getReleaseList() {
        completeList = DatabaseUtil.getInstance().getAllReleases(FlowUtil.getInstance().getCurrentProject());
        for (Release p : completeList) {
            cb.addItem(p);
        }
    }

    private class LocalProjectLister implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*Object src = e.getSource();
            if (src instanceof JComboBox) {
                JComboBox<Project> combo = (JComboBox<Project>) src;
                Project selectedProject = (Project) combo.getSelectedItem();
                FlowUtil.getInstance().setCurrentProject(selectedProject);
                //prjNameTF.setText(selectedProject.getProjectName());
                //prjPathTf.setText(selectedProject.getPath());
            } else {
                /*if (src == okBtn) {
                    Project p = FlowUtil.getInstance().getCurrentProject();
                    p.setProjectName(prjNameTF.getText());
                    p.setPath(prjPathTf.getText());
                    exit();
                    OperationsUtil.getInstance().updateProject(p);
                } else {
                    exit();
                }
            }*/
        }

    }

}
