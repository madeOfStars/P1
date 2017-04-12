package p1.frames;

import entity.Project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import p1.dialogs.DeleteDialog;
import p1.dialogs.EditProjectDialog;
import p1.enums.ActiveView;
import p1.enums.FontEnum;
import p1.enums.MenuEnum;
import p1.utils.DatabaseUtil;
import p1.utils.FlowUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 */
public class MainFrame extends JFrame {

    private final OperationsUtil op = OperationsUtil.getInstance();
    private JMenu prj;
    private JMenu rls;

    public MainFrame() {
        setSize(700, 600);
        addWindowListener(new WindowCloser());
        FlowUtil flowUtil = FlowUtil.getInstance();
        flowUtil.setCp(this.getContentPane());
        flowUtil.defineFirstView();
        setMenus();
    }

    private void setMenus() {
        JMenuBar menuBar = new JMenuBar();
        MenuItemListener mListener = new MenuItemListener();
        MListener ml = new MListener();
        JMenu fileMenu = new JMenu(MenuEnum.FILE.getLabel());
        fileMenu.setFont(FontEnum.CONTENT.getFont());
        fileMenu.setName(MenuEnum.FILE.name());
        fileMenu.addMenuListener(ml);
        menuBar.add(fileMenu);

        prj = new JMenu(MenuEnum.PROJECT.getLabel());
        prj.setFont(FontEnum.CONTENT.getFont());
        fileMenu.add(prj);

        JMenuItem newPrj = new JMenuItem(MenuEnum.NEW_PRJ.getLabel());
        newPrj.setName(MenuEnum.NEW_PRJ.name());
        newPrj.setFont(FontEnum.CONTENT.getFont());
        prj.add(newPrj);
        newPrj.addActionListener(mListener);
        JMenuItem editPrj = new JMenuItem(MenuEnum.EDIT_PRJ.getLabel());
        editPrj.setName(MenuEnum.EDIT_PRJ.name());
        editPrj.setFont(FontEnum.CONTENT.getFont());
        prj.add(editPrj);
        editPrj.addActionListener(mListener);
        JMenuItem delPrj = new JMenuItem(MenuEnum.DEL_PRJ.getLabel());
        delPrj.setName(MenuEnum.DEL_PRJ.name());
        delPrj.setFont(FontEnum.CONTENT.getFont());
        prj.add(delPrj);
        delPrj.addActionListener(mListener);

        rls = new JMenu(MenuEnum.RELEASE.getLabel());
        rls.setFont(FontEnum.CONTENT.getFont());
        fileMenu.add(rls);

        JMenuItem newRls = new JMenuItem(MenuEnum.NEW_RLS.getLabel());
        newRls.setFont(FontEnum.CONTENT.getFont());
        newRls.setName(MenuEnum.NEW_RLS.name());
        rls.add(newRls);
        newRls.addActionListener(mListener);
        JMenuItem editRls = new JMenuItem(MenuEnum.EDIT_RLS.getLabel());
        editRls.setFont(FontEnum.CONTENT.getFont());
        editRls.setName(MenuEnum.EDIT_RLS.name());
        rls.add(editRls);
        editRls.addActionListener(mListener);
        JMenuItem delRls = new JMenuItem(MenuEnum.DEL_RLS.getLabel());
        delRls.setFont(FontEnum.CONTENT.getFont());
        rls.add(delRls);
        delRls.addActionListener(mListener);

        fileMenu.addSeparator();
        JMenuItem exit = new JMenuItem(MenuEnum.EXIT.getLabel());
        exit.setFont(FontEnum.CONTENT.getFont());
        exit.setName(MenuEnum.EXIT.name());
        fileMenu.add(exit);
        exit.addActionListener(mListener);

        setJMenuBar(menuBar);
    }

    private class WindowCloser extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }
    }

    private class MenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object src = ae.getSource();
            JMenuItem temp = (JMenuItem) src;
            if (temp.getName().equals(MenuEnum.NEW_PRJ.name())) {
                Boolean newPrj = op.addNewProject();
                op.popUpMessages(newPrj, "Project Successfully Added", "Project Failed To Be Added", ActiveView.PROJECT_VIEW);
            } else if (temp.getName().equals(MenuEnum.EDIT_PRJ.name())) {
                EditProjectDialog epd = new EditProjectDialog(MainFrame.this, MenuEnum.EDIT_PRJ.getLabel());
                epd.setVisible(true);
            } else if (temp.getName().equals(MenuEnum.DEL_PRJ.name())) {
                switch (FlowUtil.getInstance().getActiveView()) {
                    case HOME: {
                        List<Project> lista = DatabaseUtil.getInstance().getAllProjects();
                        DeleteDialog<Project> deleteDialog = new DeleteDialog<>(MainFrame.this, "Select Projects To be Deleted", lista);
                        deleteDialog.setVisible(true);
                        break;
                    }
                    case RELEASE_VIEW: {
                        break;
                    }
                }
            } else if (temp.getName().equals(MenuEnum.NEW_RLS.name())) {
                FlowUtil.getInstance().addNewRelease();
            } else if (temp.getName().equals(MenuEnum.EDIT_RLS.name())) {
                
            } else if (temp.getName().equals(MenuEnum.EXIT.name())) {
                System.exit(0);
            }
        }

    }

    private class MListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            Object src = e.getSource();
            JMenu temp = (JMenu) src;
            if (temp.getName().equals(MenuEnum.FILE.name())) {
                switch (FlowUtil.getInstance().getActiveView()) {
                    case HOME: {
                        prj.setEnabled(true);
                        rls.setEnabled(false);
                        break;
                    }
                    case PROJECT_VIEW: {
                        prj.setEnabled(false);
                        rls.setEnabled(true);
                        break;
                    }
                    case RELEASE_VIEW: {
                        rls.setEnabled(false);
                        prj.setEnabled(false);
                        break;
                    }
                }
            }
        }

        @Override
        public void menuDeselected(MenuEvent e) {

        }

        @Override
        public void menuCanceled(MenuEvent e) {

        }

    }
}
