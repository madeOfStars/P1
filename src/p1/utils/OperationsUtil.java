package p1.utils;

import entity.Project;
import entity.Release;
import entity.Revision;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import p1.enums.ActiveView;
import p1.enums.ImageEnum;
import p1.interfaces.Pathable;

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

    public File chooseFile() {
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
        File f = chooseFile();
        if (f != null) {
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
                return new ImageIcon(ImageEnum.BACK.getPath(), ImageEnum.BACK.getAlt());

            case CLOSE:
                return new ImageIcon(ImageEnum.CLOSE.getPath(), ImageEnum.CLOSE.getAlt());
        }
        return null;
    }

    public void popUpMessages(Boolean success, String messageOK, String messageFAIL) {
        popUpMessages(success, messageOK, messageFAIL, flowUtil.getActiveView());
    }

    public void popUpMessages(Boolean success, String messageOK, String messageFAIL, ActiveView activeView) {
        if (success == null) {
            return;
        }
        if (success) {
            JOptionPane.showMessageDialog(flowUtil.getCp(), messageOK, "Success", JOptionPane.DEFAULT_OPTION, this.getIcon(ImageEnum.OK));
            switch (activeView) {
                case HOME:
                    FlowUtil.getInstance().defineFirstView();
                    break;
                case PROJECT_VIEW:
                    flowUtil.defineProjectView((Project) FlowUtil.getReturnable().getElement());
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
        File f = addFolder(project, releaseCode);
        if (f != null) {
            Release rls=databaseUtil.addNewRelease(project, releaseCode,f.getPath());
            return rls!=null;
        }
        return false;
    }

    private File addReleaseFolder(Project p, int releaseCode) {
        File f = new File("C:\\releases\\" + p.getProjectName() + "-" + releaseCode + "");
        if (!f.exists()) {
            if (f.mkdir()) {
                return f;
            } else {
                return null;
            }
        } else {
            return f;
        }
    }
    
    private <E extends Pathable> File addFolder(E element, int code){
        File f = new File(element.folderPath(code));
        /*if (element instanceof Release){
            Release rls=(Release)element;
            f = new File("C:\\releases\\" + element.toString()+ "-" + code + "");
        } else {
            f = new File("C:\\releases\\" + element.toString()+ "-" + code + "");
        }*/
        
        if (!f.exists()) {
            if (f.mkdir()) {
                return f;
            } else {
                return null;
            }
        } else {
            return f;
        }
    }

    public boolean closeRelease(Release release) {
        return databaseUtil.closeRelease(release);
    }

    public List<Revision> getAllVersions(Release release) {
        return databaseUtil.getAllVersion(release);
    }

    public void exitDialog(JDialog dialog) {
        dialog.setVisible(false);
        dialog.dispose();
    }

    public void updateProject(Project p) {
        popUpMessages(databaseUtil.updateProject(p), "Project Updated Successfully", "Project Failed To Update", ActiveView.HOME);
    }

    public void deleteProjects(List<Integer> lista) {
        popUpMessages(databaseUtil.deleteProjects(lista), "Project(s) deleted Successfully", "Failed to delete Project(s)", ActiveView.PROJECT_VIEW);
    }

    public void delete(String elementName, List<Integer> lista) {
        popUpMessages(databaseUtil.delete(elementName, lista), elementName + "(s) deleted Successfully", "Failed to delete " + elementName + "(s)");
    }

    public void updateRelease(Release release) {
        updateReleaseFolder(release);
        popUpMessages(databaseUtil.update(release), "Release Updated Successfully", "Release Failed To Update");
    }
    
    private void updateReleaseFolder(Release release){
        String currentFileName=release.getReleaseFolder();
        File currentFile=new File(currentFileName);
        String []currentFileNameParts=currentFileName.split("-");
        String newFileName=currentFileNameParts[0]+"-"+release.getCode();
        currentFile.renameTo(new File(newFileName));
        release.setReleaseFolder(newFileName);
    }
    
    public void addNewRevision(Release release, int revisionCode){
        File f = addFolder(release, revisionCode);
        runCommand(release);
    }
    
    private void runCommand(Release release){
        
    }
}
