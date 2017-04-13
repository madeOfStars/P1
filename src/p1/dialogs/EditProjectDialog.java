/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.dialogs;

import p1.dialogs.helpers.DialogHelper;
import entity.Project;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import p1.enums.ContainerPositionEnum;
import p1.enums.FontEnum;
import p1.utils.DatabaseUtil;
import p1.utils.FlowUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 */
public class EditProjectDialog extends DialogHelper {

    private final JComboBox<Project> cb;
    private List<Project> completeList;
    private final JTextField prjNameTF = new JTextField();
    private final JTextField prjPathTf = new JTextField();
    private final JButton okBtn;
    private final JButton cancelBtn;

    private final JPanel changable;
    private final JPanel controllers;

    public EditProjectDialog(JFrame frame, String title) {
        super(frame, title);
        prjPathTf.setEditable(false);
        prjPathTf.addMouseListener(new LocalMouseAdapter());
        LocalProjectLister lpl = new LocalProjectLister();
        setSize(650, 350);
        JPanel header = new JPanel(new GridLayout(1, 2));
        JLabel lbl = new JLabel("Select Project");
        lbl.setFont(FontEnum.CONTENT.getFont());
        header.add(lbl);
        cb = new JComboBox();
        cb.setFont(FontEnum.CONTENT.getFont());
        cb.addActionListener(new LocalProjectLister());
        getProjectList();
        header.add(cb);
        changable = new JPanel(new GridLayout(10, 2));
        JLabel prjNameL = new JLabel("Project Name");
        prjNameL.setFont(FontEnum.CONTENT.getFont());
        JLabel prjPathL = new JLabel("Project Path");
        prjPathL.setFont(FontEnum.CONTENT.getFont());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(prjNameL);
        prjNameTF.setFont(FontEnum.CONTENT.getFont());
        changable.add(prjNameTF);
        prjPathTf.setFont(FontEnum.CONTENT.getFont());
        changable.add(prjPathL);
        changable.add(prjPathTf);
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        controllers = new JPanel(new GridLayout(1, 2));
        okBtn = new JButton("OK");
        okBtn.addActionListener(lpl);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(lpl);
        okBtn.setFont(FontEnum.CONTENT.getFont());
        controllers.add(okBtn);
        cancelBtn.setFont(FontEnum.CONTENT.getFont());
        controllers.add(cancelBtn);

        cb.addActionListener(lpl);

        getContentPane().add(header, ContainerPositionEnum.NORTH.getLocation());
        getContentPane().add(changable, ContainerPositionEnum.CENTER.getLocation());
        getContentPane().add(controllers, ContainerPositionEnum.SOUTH.getLocation());
    }

    private void getProjectList() {
        completeList = DatabaseUtil.getInstance().getAllProjects();
        for (Project p : completeList) {
            cb.addItem(p);
        }
    }

    private void exit() {
        OperationsUtil.getInstance().exitDialog(this);
    }

    private class LocalProjectLister implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src instanceof JComboBox) {
                JComboBox<Project> combo = (JComboBox<Project>) src;
                Project selectedProject = (Project) combo.getSelectedItem();
                //FlowUtil.getInstance().setCurrentProject(selectedProject);
                prjNameTF.setText(selectedProject.getProjectName());
                prjPathTf.setText(selectedProject.getPath());
            } else {
                if (src == okBtn) {
                    Project p = (Project)FlowUtil.getInstance().getReturnable().getElement();
                    p.setProjectName(prjNameTF.getText());
                    p.setPath(prjPathTf.getText());
                    exit();
                    OperationsUtil.getInstance().updateProject(p);
                } else {
                    exit();
                }
            }
        }

    }

    private class LocalMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent event) {
            File f = OperationsUtil.getInstance().chooseFile();
            if (f != null) {
                prjPathTf.setText(f.getPath());
            }
        }
    }
}
