package p1.interfaces;

import javax.swing.JPanel;

/**
 *
 * @author Ertjon Mecka
 * @param <E>
 */
public interface Returnable<E> {
    public JPanel getPanel();
    public void setElement(E element);
    public E getElement();
}
