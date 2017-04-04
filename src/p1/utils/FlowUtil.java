package p1.utils;

import entity.Project;
import entity.Release;
import java.awt.Container;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import p1.enums.ContainerPositionEnum;
import p1.enums.LabelEnum;
import p1.panels.FullListView;
import p1.panels.HeaderTemplate;
import p1.panels.NoItemPanel;
import p1.panels.NoProjectsPanel;

/**
 *
 * @author Ertjon
 */
public class FlowUtil {

    private static FlowUtil instance = null;

    private Container cp;
    private final DatabaseUtil databaseUtil = DatabaseUtil.getInstance();

    private Project currentProject;

    public Container getCp() {
        return cp;
    }

    public void setCp(Container cp) {
        this.cp = cp;
    }

    private FlowUtil() {

    }

    public static FlowUtil getInstance() {
        if (instance == null) {
            instance = new FlowUtil();
        }
        return instance;
    }

    public void defineFirstView() {
        clean();
        if (databaseUtil.getAllProjects() != null && databaseUtil.getAllProjects().size() > 0) {
            setView(new HeaderTemplate("All Projects"));
            setView(new FullListView().getPanel());
            getCp().setLayout(new BoxLayout(cp, BoxLayout.PAGE_AXIS));
        } else {
            setView(new NoProjectsPanel(), ContainerPositionEnum.NORTH.getLocation());
        }
    }

    public void defineProjectView(Project project) {
        this.currentProject = project;
        clean();
        OperationsUtil ou = OperationsUtil.getInstance();
        List<Release> lista = ou.getAllReleases(project);
        if (lista != null && lista.size() > 0) {

        } else {
            setView(new HeaderTemplate(project.getProjectName()));
            setView(new NoItemPanel(LabelEnum.NEW_RELEASE_LBL.name(), LabelEnum.NEW_RELEASE_LBL.getMessage()));
        }

    }

    public void addNewRelease() {
        String code = JOptionPane.showInputDialog(getCp(), "Enter Release Code");
        if (code != null) {
            int intCode = Integer.parseInt(code);
            OperationsUtil.getInstance().addNewRelease(currentProject, intCode);
        }
    }

    public void setView(JPanel panel) {
        setView(panel, "Center");
    }

    public void setView(JPanel panel, String location) {
        getCp().add(panel, location);
    }

    public void clean() {
        getCp().removeAll();
        getCp().revalidate();
    }
}
