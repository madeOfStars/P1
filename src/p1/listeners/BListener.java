package p1.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import p1.enums.ButtonEnum;
import p1.utils.FlowUtil;

/**
 *
 * @author Ertjon
 * @param <E>
 */
public class BListener<E> implements ActionListener {

    private final FlowUtil flowUtil = FlowUtil.getInstance();
    private E element;

    public BListener() {
    }

    public BListener(E element) {
        this.element = element;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src.getName().equals(ButtonEnum.HOME.name())) {
            FlowUtil.getInstance().defineFirstView();
        } else if (src.getName().equals(ButtonEnum.BACK.name())) {
            switch (flowUtil.getActiveView().getPreviousViw()) {
                case PROJECT_VIEW: {
                    flowUtil.defineFirstView();
                    break;
                }
            }
        }
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}
