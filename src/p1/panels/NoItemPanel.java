package p1.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import p1.enums.FontEnum;
import p1.interfaces.Returnable;
import p1.listeners.MListener;

/**
 *
 * @author Ertjon Mecka
 * @param <E>
 */
public class NoItemPanel<E> extends JPanel implements Returnable<E> {

    private E element;
    
    private final JPanel mainPanel = new JPanel();

    public NoItemPanel() {
    }

    public NoItemPanel(String name, String label) {
        JLabel message = new JLabel(label);
        message.setName(name);
        message.setFont(FontEnum.CONTENT.getFont());
        message.addMouseListener(new MListener());
        this.mainPanel.add(message);
    }

    public NoItemPanel(String name, String label, E element) {
        this(name, label);
        this.element = element;
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    @Override
    public void setElement(E element) {
        this.element=element;
    }

    @Override
    public E getElement() {
        return this.element;
    }
}
