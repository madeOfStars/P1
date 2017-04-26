package p1.utils;

import entity.Project;
import entity.Release;
import entity.Revision;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import p1.enums.ActiveView;
import p1.enums.FileTypeEnum;
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
            Release rls = databaseUtil.addNewRelease(project, releaseCode, f.getPath());
            return rls != null;
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

    private <E extends Pathable> File addFolder(E element, int code) {
        File f = new File(element.folderPath(code));

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

    public List<Revision> getAllRevisions(Release release) {
        return databaseUtil.getAllRevisions(release);
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

    private void updateReleaseFolder(Release release) {
        String currentFileName = release.getReleaseFolder();
        File currentFile = new File(currentFileName);
        String[] currentFileNameParts = currentFileName.split("-");
        String newFileName = currentFileNameParts[0] + "-" + release.getCode();
        currentFile.renameTo(new File(newFileName));
        release.setReleaseFolder(newFileName);
    }

    public void addNewRevision(Release release, int revisionCode) {
        File f = addFolder(release, revisionCode);
        Revision rvs = DatabaseUtil.getInstance().addNewRevision(release, revisionCode);
        if (rvs != null) {
            popUpMessages(elaborateFiles(rvs, f), "Revision Added Successfully", "Failed to add Revision");
        }
    }

    private boolean elaborateFiles(Revision revision, File f) {
        return runCMD(revision, f);
    }

    private boolean runCMD(Revision revision, File f) {
        BufferedReader reader = null;
        BufferedWriter bw = null;
        FileWriter outFile = null;
        try {
            String path = revision.getRelease().getProject().getPath();
            String extenedPath = revision.getRelease().getProject().getPath() + "\\arm_am-ejb"; //this is the real path, used from my PC at work
            //String path="C:\\Temp\\repo1trunk\\realStuff"; // path used from my pc at home
            String command = "svn log " + extenedPath + " -v -r " + revision.getRevisionNumber();
            System.out.println(command);
            StringBuilder output = new StringBuilder();
            String outFileName = f.getPath();
            String extenedOutFileName = f.getPath() + "\\" + revision.getRelease().getCode() + "_" + revision.getRevisionNumber() + ".fwc";
            outFile = new FileWriter(extenedOutFileName);
            bw = new BufferedWriter(outFile);
            Process child = Runtime.getRuntime().exec(command);
            child.waitFor();
            reader = new BufferedReader(new InputStreamReader(child.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
                separateFiles(line, path, outFileName);
            }

            bw.write(output.toString());
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OperationsUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InterruptedException ex) {
            Logger.getLogger(OperationsUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                bw.close();
                outFile.close();
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(OperationsUtil.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

    private void separateFiles(String line, String source, String dest) throws IOException {
        if (line.toLowerCase().contains("arm_am".toLowerCase())) {
            String[] pathElements = line.split("/");
            String outFileName = pathElements[pathElements.length - 1];
            int pos = line.indexOf("arm_am");
            String subPath = line.substring(pos);
            String fullPath = source + "\\" + subPath;
            Path pathIn = Paths.get(fullPath);
            Path pathOut = Paths.get(dest + "\\" + outFileName);
            Files.copy(pathIn, pathOut, StandardCopyOption.REPLACE_EXISTING);

        }
    }

    private FileTypeEnum getFileType(String line) {
        if (line.endsWith(FileTypeEnum.AS.getExtension())) {
            return FileTypeEnum.AS;
        } else if (line.endsWith(FileTypeEnum.JAVA.getExtension())) {
            return FileTypeEnum.JAVA;
        } else if (line.endsWith(FileTypeEnum.MXML.getExtension())) {
            return FileTypeEnum.MXML;
        } else {
            return FileTypeEnum.SQL;
        }
    }
}
