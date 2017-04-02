package p1.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import p1.enums.FontEnum;
import p1.listeners.MListener;

/**
 *
 * @author Ertjon
 */
public class NoProjectsPanel extends JPanel{
    public NoProjectsPanel(){
        JLabel message=new JLabel("No Available Projects! Click here to add a new One");
        message.setFont(FontEnum.HEADER.getFont());
        message.setName("NEW_PRJ_LBL");
        this.add(message);
        message.addMouseListener(new MListener());
    }
}
