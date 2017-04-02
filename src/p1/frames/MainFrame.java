package p1.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import p1.enums.ImageEnum;
import p1.enums.MenuEnum;
import p1.utils.FlowUtil;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 */
public class MainFrame extends JFrame {

    private JMenuItem newPrj;
    private OperationsUtil op = OperationsUtil.getInstance();

    public MainFrame() {
        setSize(700, 500);
        addWindowListener(new WindowCloser());
        FlowUtil flowUtil = FlowUtil.getInstance();
        setMenus();
        flowUtil.setCp(this.getContentPane());
        flowUtil.defineFirstView();
    }

    private void setMenus() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu(MenuEnum.FILE.getLabel());
        menuBar.add(fileMenu);

        newPrj = new JMenuItem(MenuEnum.NEW_PRJ.getLabel());
        fileMenu.add(newPrj);
        newPrj.addActionListener(new MenuListener());
    }

    private class WindowCloser extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }
    }

    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Boolean newPrj=op.addNewProject();
            if (newPrj==null)
                return;
            if (newPrj) {
                JOptionPane.showMessageDialog(rootPane, "Project Successfully Added", "Success", JOptionPane.DEFAULT_OPTION, op.getIcon(ImageEnum.OK));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Project Failed To Be Added", "Fail", JOptionPane.ERROR_MESSAGE, op.getIcon(ImageEnum.FAIL));
            }
        }

    }
}
