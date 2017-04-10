package p1.utils;

import entity.Project;
import entity.Release;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Ertjon
 */
public class DatabaseUtil {

    private static DatabaseUtil databaseUtil;

    private DatabaseUtil() {
    }

    public static DatabaseUtil getInstance() {
        if (databaseUtil == null) {
            databaseUtil = new DatabaseUtil();
        }
        return databaseUtil;
    }

    public boolean addNewProject(File file) {
        SessionPackage sp = new SessionPackage();

        try {
            Project p = new Project();
            p.setProjectName(file.getName());
            p.setPath(file.getPath());
            p.setDateAdded(new Date());
            sp.getSession().save(p);
            sp.getTx().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            sp.getSession().close();
        }
    }

    public List<Project> getAllProjects() {
        SessionPackage sp = new SessionPackage();
        String hql = "FROM Project";
        Query q = sp.getSession().createQuery(hql);
        List<Project> lista = q.list();
        sp.getSession().close();
        return lista;
    }

    public List<Release> getAllReleases(Project p) {
        SessionPackage sp = new SessionPackage();
        String hql = "select r "
                + "from Release r "
                + "where r.project.id=:id and r.closed=0";
        Query q = sp.getSession().createQuery(hql);
        q.setParameter("id", p.getId());
        List<Release> lista = q.list();
        sp.getSession().close();
        return lista;
    }

    public boolean addNewRelease(Project project, int codeRelease) {
        SessionPackage sp = new SessionPackage();
        try {
            Release rls = new Release(codeRelease, new Date(), 0, null);
            rls.setProject(project);
            sp.getSession().save(rls);
            sp.getTx().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            sp.getSession().close();
        }
    }

    public boolean updateProject(Project p) {
        SessionPackage sp = new SessionPackage();
        try {
            sp.getSession().update(p);
            sp.getTx().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            sp.getSession().close();
        }
    }

    public boolean deleteProjects(List<Integer> lista) {
        SessionPackage sp = new SessionPackage();
        String qry = "delete Project where id in (:ids)";
        Query q = sp.getSession().createQuery(qry);
        q.setParameterList("ids", lista);
        int nr = q.executeUpdate();
        sp.getTx().commit();
        sp.getSession().close();
        return nr > 0;
    }
}
