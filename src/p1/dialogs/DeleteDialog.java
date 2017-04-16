package p1.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import p1.components.EnhancedJCheckBox;
import p1.dialogs.helpers.DeleteDialogHelper;
import p1.enums.ContainerPositionEnum;
import p1.enums.FontEnum;
import p1.interfaces.Identifiable;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 * @param <E>
 */
public class DeleteDialog<E extends Identifiable> extends DeleteDialogHelper<E> {

    private final List<EnhancedJCheckBox> checkBocList;
    private final JButton okBtn;
    private final JButton cancelBtn;
    
    private final Class<E> cls;

    public DeleteDialog(JFrame frame, String title, List<E> data, Class<E> cls) {
        super(frame, title, data);
        this.cls=cls;
        setSize(350, 500);
        BListener bl=new BListener();

        checkBocList = populateList();

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        for (EnhancedJCheckBox cb : checkBocList) {
            checkBoxPanel.add(cb);
        }

        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel buttonContainer = new JPanel(new GridLayout(1, 2));
        okBtn = new JButton("OK");
        okBtn.setFont(FontEnum.CONTENT.getFont());
        okBtn.addActionListener(bl);
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(FontEnum.CONTENT.getFont());
        cancelBtn.addActionListener(bl);
        buttonContainer.add(okBtn);
        buttonContainer.add(cancelBtn);

        getContentPane().add(scrollPane, ContainerPositionEnum.CENTER.getLocation());
        getContentPane().add(buttonContainer, ContainerPositionEnum.SOUTH.getLocation());
    }

    private class BListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object src=e.getSource();
            if (src==okBtn){
                OperationsUtil.getInstance().exitDialog(DeleteDialog.this);
                delete();
            } else {
                OperationsUtil.getInstance().exitDialog(DeleteDialog.this);
            }
        }
        
    }
    
    private void delete(){
        List<Integer> ids=new ArrayList<>();
        
        for (EnhancedJCheckBox el : checkBocList){
            if (el.isSelected())
                ids.add(el.getId());
        }
        
        OperationsUtil.getInstance().delete(cls.getSimpleName(),ids);
    }
}
