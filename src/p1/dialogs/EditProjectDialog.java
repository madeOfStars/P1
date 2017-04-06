/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.dialogs;

import entity.Project;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import p1.enums.ContainerPositionEnum;
import p1.utils.DatabaseUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 */
public class EditProjectDialog extends DialogHelper {

    private final JComboBox<Project> cb;
    private List<Project> completeList;
    private JTextField prjNameTF = new JTextField();
    private JTextField prjPathTf = new JTextField();
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
        header.add(lbl);
        cb = new JComboBox();
        cb.addActionListener(new LocalProjectLister());
        getProjectList();
        header.add(cb);
        changable = new JPanel(new GridLayout(10, 2));
        JLabel prjNameL = new JLabel("Project Name");
        JLabel prjPathL = new JLabel("Project Path");
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(new JPanel());
        changable.add(prjNameL);
        changable.add(prjNameTF);
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
        cancelBtn = new JButton("Cancel");
        controllers.add(okBtn);
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

    private class LocalProjectLister implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src instanceof JComboBox) {
                JComboBox<Project> combo = (JComboBox<Project>) src;
                Project selectedProject = (Project) combo.getSelectedItem();

                prjNameTF.setText(selectedProject.getProjectName());
                //prjNameTF.setPreferredSize(new Dimension(100, 20));
                prjPathTf.setText(selectedProject.getPath());
            } else {

            }
        }

    }
    
    private class LocalMouseAdapter extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent event){
            
        }
    }
}
