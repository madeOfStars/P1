package p1.utils;

import entity.Project;
import entity.Release;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import p1.enums.ActiveView;
import p1.enums.ImageEnum;

/**
 *
 * @author Ertjon
 */
public class OperationsUtil {

    private static OperationsUtil operationsUtil;

    private final DatabaseUtil databaseUtil = DatabaseUtil.getInstance();
    private final FlowUtil flowUtil = FlowUtil.getInstance();

    private OperationsUtil() {
    }

    public static OperationsUtil getInstance() {
        if (operationsUtil == null) {
            operationsUtil = new OperationsUtil();
        }
        return operationsUtil;
    }
    
    public File chooseFile(){
        JFileChooser chooser = new JFileChooser("D:/U/ujava/TestDirForP1");
        chooser.setDialogTitle("Choose Project");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            System.out.println(file.getName());
            return file;
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        return null;
    }

    public Boolean addNewProject() {
        File f=chooseFile();
        if (f!=null){
            return databaseUtil.addNewProject(f);
        } else {
            return null;
        }
    }

    public ImageIcon getIcon(ImageEnum imageEnum) {
        switch (imageEnum) {
            case OK:
                return new ImageIcon(ImageEnum.OK.getPath(), ImageEnum.OK.getAlt());

            case FAIL:
                return new ImageIcon(ImageEnum.FAIL.getPath(), ImageEnum.FAIL.getAlt());
                
            case HOME:
                return new ImageIcon(ImageEnum.HOME.getPath(), ImageEnum.HOME.getAlt());
                
            case BACK:
                return new ImageIcon(ImageEnum.BACK.getPath(),ImageEnum.BACK.getAlt());
        }
        return null;
    }

    public void popUpMessages(Boolean success, String messageOK,String messageFAIL, ActiveView activeView) {
        if (success == null) {
            return;
        }
        if (success) {
            JOptionPane.showMessageDialog(flowUtil.getCp(), messageOK, "Success", JOptionPane.DEFAULT_OPTION, this.getIcon(ImageEnum.OK));
            switch (activeView){
                case PROJECT_VIEW:
                    FlowUtil.getInstance().defineFirstView();
                    break;
                case RELEASE_VIEW:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, messageFAIL, "Fail", JOptionPane.ERROR_MESSAGE, this.getIcon(ImageEnum.FAIL));
        }
    }

    public List<Release> getAllReleases(Project p) {
        return databaseUtil.getAllReleases(p);
    }

    public boolean addNewRelease(Project project, int releaseCode) {
        return databaseUtil.addNewRelease(project, releaseCode) && addReleaseFolder(releaseCode);
    }
    
    private boolean addReleaseFolder(int releaseCode){
        File f=new File("C:\\releases\\"+releaseCode+"");
        if (!f.exists()){
            f.mkdir();
            return true;
        } else {
            return false;
        }
    }
    
    public void exitDialog(JDialog dialog){
        dialog.setVisible(false);
        dialog.dispose();
    }
    
    public void updateProject(Project p){
        popUpMessages(databaseUtil.updateProject(p), "Project Updated Successfully", "Project Failed To Update", ActiveView.PROJECT_VIEW);
        flowUtil.defineFirstView();
    }
    
    public void deleteProjects(List<Integer> lista){
        popUpMessages(databaseUtil.deleteProjects(lista), "Project(s) deleted Successfully", "Failed to delete Project(s)", ActiveView.PROJECT_VIEW);
        flowUtil.defineFirstView();
    }
}
