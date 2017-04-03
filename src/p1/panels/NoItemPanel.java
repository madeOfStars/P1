package p1.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import p1.listeners.MListener;

/**
 *
 * @author Ertjon Mecka
 */
public class NoItemPanel extends JPanel{
    public NoItemPanel(String name, String label){
        JLabel message=new JLabel(label);
        message.setName(name);
        message.addMouseListener(new MListener());
        this.add(message);
    }
}
