package p1.utils;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import p1.enums.ImageEnum;

/**
 *
 * @author Ertjon
 */
public class OperationsUtil {

    private static OperationsUtil operationsUtil;

    private DatabaseUtil databaseUtil = DatabaseUtil.getInstance();
    private FlowUtil flowUtil = FlowUtil.getInstance();

    private OperationsUtil() {
    }

    public static OperationsUtil getInstance() {
        if (operationsUtil == null) {
            operationsUtil = new OperationsUtil();
        }
        return operationsUtil;
    }

    public Boolean addNewProject() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose Project");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            System.out.println(file.getName());
            return databaseUtil.addNewProject(file);
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        return false;
    }

    public ImageIcon getIcon(ImageEnum imageEnum) {
        switch (imageEnum) {
            case OK:
                return new ImageIcon(ImageEnum.OK.getPath(), ImageEnum.OK.getAlt());

            case FAIL:
                return new ImageIcon(ImageEnum.FAIL.getPath(), ImageEnum.FAIL.getAlt());
        }
        return null;
    }
}
