package p1.panels;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import p1.enums.ButtonEnum;
import p1.enums.FontEnum;
import p1.enums.ImageEnum;
import p1.listeners.BListener;
import p1.utils.OperationsUtil;

/**
 *
 * @author Ertjon
 */
public class HeaderTemplate<E> extends JPanel {

    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel msgPanel;

    private JButton homeBtn;
    private JButton backBtn;

    public HeaderTemplate(String message) {
        JLabel msg = new JLabel(message);
        msg.setFont(FontEnum.HEADER.getFont());

        msgPanel = new JPanel();
        msgPanel.add(msg);
        homePanel = new JPanel();
        backPanel = new JPanel();
        this.setLayout(new GridLayout(1, 3));
        this.add(msgPanel);
    }

    public HeaderTemplate(String message, boolean composed, E element) {
        this(message);
        if (composed) {
            clean();
            BListener bListener = getBListener(element);
            homeBtn = new JButton(OperationsUtil.getInstance().getIcon(ImageEnum.HOME));
            homeBtn.setName(ButtonEnum.HOME.name());
            homeBtn.setFont(FontEnum.CONTENT.getFont());
            homeBtn.addActionListener(bListener);
            backBtn = new JButton(OperationsUtil.getInstance().getIcon(ImageEnum.BACK));
            backBtn.setName(ButtonEnum.BACK.name());
            backBtn.setFont(FontEnum.CONTENT.getFont());
            backBtn.addActionListener(bListener);
            homePanel.add(homeBtn);
            backPanel.add(backBtn);
            this.add(homePanel);
            this.add(msgPanel);
            this.add(backPanel);
        }
    }

    private void clean() {
        this.removeAll();;
        this.revalidate();;
    }

    private BListener getBListener(E element) {
        BListener bListener = null;
        if (element != null) {
            bListener = new BListener(element);
        } else {
            bListener = new BListener();
        }
        return bListener;
    }
}
