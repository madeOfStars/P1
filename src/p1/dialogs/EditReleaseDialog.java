package p1.dialogs;

import entity.Project;
import entity.Release;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
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

    private final JTextField codeTF = new JTextField();
    private final JRadioButton yes = new JRadioButton("Yes");
    private final JRadioButton no = new JRadioButton("No");

    private final JButton okBtn;
    private final JButton cancelBtn;

    private Release selectedRelease = null;

    public EditReleaseDialog(JFrame frame, String title) {
        super(frame, title);
        setSize(400, 200);
        LocalProjectLister lpl = new LocalProjectLister();
        JPanel header = new JPanel(new GridLayout(1, 2));
        JLabel lbl = new JLabel("Select Release");
        lbl.setFont(FontEnum.CONTENT.getFont());
        header.add(lbl);
        cb = new JComboBox<>();
        cb.setFont(FontEnum.CONTENT.getFont());
        cb.addActionListener(new LocalProjectLister());
        getReleaseList();
        header.add(cb);

        JPanel changeable = new JPanel(new GridLayout(3, 2));
        changeable.add(new JPanel());
        changeable.add(new JPanel());
        JLabel codeLabel = new JLabel("Release Code");
        codeLabel.setFont(FontEnum.CONTENT.getFont());
        codeTF.setFont(FontEnum.CONTENT.getFont());
        changeable.add(codeLabel);
        changeable.add(codeTF);
        JLabel active = new JLabel("Active");
        active.setFont(FontEnum.CONTENT.getFont());
        yes.setFont(FontEnum.CONTENT.getFont());
        no.setFont(FontEnum.CONTENT.getFont());
        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(yes);
        rbGroup.add(no);
        JPanel rbPanel = new JPanel(new FlowLayout());
        rbPanel.add(yes);
        rbPanel.add(no);
        changeable.add(active);
        changeable.add(rbPanel);
        cb.addActionListener(lpl);

        JPanel controllers = new JPanel(new GridLayout(1, 2));
        okBtn = new JButton("OK");
        okBtn.addActionListener(lpl);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(lpl);
        okBtn.setFont(FontEnum.CONTENT.getFont());
        controllers.add(okBtn);
        cancelBtn.setFont(FontEnum.CONTENT.getFont());
        controllers.add(cancelBtn);

        getContentPane().add(header, ContainerPositionEnum.NORTH.getLocation());
        getContentPane().add(changeable, ContainerPositionEnum.CENTER.getLocation());
        getContentPane().add(controllers, ContainerPositionEnum.SOUTH.getLocation());
    }

    private void getReleaseList() {
        completeList = DatabaseUtil.getInstance().getAllReleases((Project) FlowUtil.getReturnable().getElement());
        for (Release p : completeList) {
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
                selectedRelease = (Release) combo.getSelectedItem();
                codeTF.setText(selectedRelease.getCode() + "");

                if (selectedRelease.getClosed() == 0) {
                    yes.setSelected(true);
                }

            } else {
                if (src == okBtn) {
                    selectedRelease.setCode(Integer.parseInt(codeTF.getText()));
                    if (no.isSelected()) {
                        selectedRelease.setClosed(1);
                        selectedRelease.setDateClosed(new Date());
                    }
                    exit();
                    OperationsUtil.getInstance().updateRelease(selectedRelease);
                } else {
                    exit();
                }
            }
        }

    }

}
