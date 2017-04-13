package p1.utils;

import entity.Project;
import entity.Release;
import entity.Revision;
import java.awt.Container;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import p1.enums.ActiveView;
import p1.enums.ContainerPositionEnum;
import p1.enums.LabelEnum;
import p1.enums.TableEnum;
import p1.interfaces.Returnable;
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

    private ActiveView activeView;

    private static Returnable r;

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
            setView((FlowUtil.r = new FullListView(TableEnum.PROJECT_TABLE)).getPanel());
            getCp().setLayout(new BoxLayout(cp, BoxLayout.PAGE_AXIS));
        } else {
            setView(new NoProjectsPanel(), ContainerPositionEnum.NORTH.getLocation());
        }
        this.activeView = ActiveView.HOME;
    }

    public void defineProjectView(Project project) {
        //this.currentProject = project;
        clean();
        OperationsUtil ou = OperationsUtil.getInstance();
        List<Release> lista = ou.getAllReleases(project);
        if (lista != null && lista.size() > 0) {
            setView(new HeaderTemplate("All Releases", true, project));
            setView((FlowUtil.r = new FullListView(TableEnum.RELEASE_TABLE, project)).getPanel());
            getCp().setLayout(new BoxLayout(cp, BoxLayout.PAGE_AXIS));
        } else {
            setView(new HeaderTemplate(project.getProjectName(), true, project));
            setView(new NoItemPanel(LabelEnum.NEW_RELEASE_LBL.name(), LabelEnum.NEW_RELEASE_LBL.getMessage()));
        }
        this.activeView = ActiveView.PROJECT_VIEW;
    }

    public void addNewRelease() {
        String code = JOptionPane.showInputDialog(getCp(), "Enter Release Code");
        if (code != null) {
            int intCode = Integer.parseInt(code);
            OperationsUtil.getInstance().popUpMessages(OperationsUtil.getInstance().addNewRelease((Project) this.getReturnable().getElement(), intCode), "Release Successfully Added", "Release Failed To Be Added", ActiveView.PROJECT_VIEW);
            defineProjectView((Project) this.getReturnable().getElement());
        }
    }

    public void defineReleaseView(Release release) {
        clean();
        OperationsUtil oUtil = OperationsUtil.getInstance();
        List<Revision> lista = oUtil.getAllVersions(release);
        if (lista != null && lista.size() > 0) {
            setView(new HeaderTemplate("All Versions", true, release));
            setView((FlowUtil.r = new FullListView(TableEnum.VERSION_TABLE, release)).getPanel());
            getCp().setLayout(new BoxLayout(cp, BoxLayout.PAGE_AXIS));
        } else {
            setView(new HeaderTemplate(release.getCode() + "", true, release));
            setView(new NoItemPanel(LabelEnum.NEW_REVISION_LBL.name(), LabelEnum.NEW_REVISION_LBL.getMessage()));
        }
        this.activeView = ActiveView.RELEASE_VIEW;
    }

    public void closeRelease(Release release) {
        int response = JOptionPane.showConfirmDialog(getCp(), "Do you want to close this release?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            OperationsUtil.getInstance().popUpMessages(OperationsUtil.getInstance().closeRelease(release), "Release Closed Successfully", "Failed to closse Release", ActiveView.RELEASE_VIEW);
            defineProjectView(release.getProject());
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

    public ActiveView getActiveView() {
        return activeView;
    }

    public void setActiveView(ActiveView activeView) {
        this.activeView = activeView;
    }

    public static Returnable getReturnable() {
        return FlowUtil.r;
    }
}
