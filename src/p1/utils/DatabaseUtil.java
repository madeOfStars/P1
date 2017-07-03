package p1.utils;

import entity.Project;
import entity.Release;
import entity.Revision;
import java.io.File;
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

    public Release addNewRelease(Project project, int codeRelease, String f) {
        SessionPackage sp = new SessionPackage();
        try {
            Release rls = new Release(codeRelease, new Date(), 0, null);
            rls.setProject(project);
            rls.setReleaseFolder(f);
            sp.getSession().save(rls);
            sp.getTx().commit();
            return rls;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sp.getSession().close();
        }
    }
    
    public boolean closeRelease(Release release){
        release.setClosed(1);
        release.setDateClosed(new Date());
        
        SessionPackage sp=new SessionPackage();
        
        try {
            sp.getSession().update(release);
            sp.getTx().commit();
            return true;
        } catch(Exception e){
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
    
    public boolean delete(String elementName, List<Integer> lista){
        SessionPackage sp = new SessionPackage();
        String qry = "delete 1 where id in (:ids)";
        qry=qry.replace("1", elementName);
        Query q = sp.getSession().createQuery(qry);
        q.setParameterList("ids", lista);
        int nr = q.executeUpdate();
        sp.getTx().commit();
        sp.getSession().close();
        return nr > 0;
    }
    
    public List<Revision> getAllRevisions(Release release){
        SessionPackage sp = new SessionPackage();
        String hql = "select r "
                + "from Revision r "
                + "where r.release.id=:id "
                + "order by r.revisionNumber asc";
        Query q = sp.getSession().createQuery(hql);
        q.setParameter("id", release.getId());
        List<Revision> lista = q.list();
        sp.getSession().close();
        return lista;
    }
    
    public <E> List<E> getAll(E component){
        return null;
    }
    
    public <E> boolean update(E element){
        SessionPackage sp=new SessionPackage();
        try{
            sp.getSession().update(element);
            sp.getTx().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            sp.getSession().close();
        }
    }
    
    public Revision addNewRevision(Release release, int code){
        SessionPackage sp=new SessionPackage();
        try {
            Revision rev=new Revision(code, new Date());
            rev.setRelease(release);
            sp.getSession().save(rev);
            sp.getTx().commit();
            return rev;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            sp.getSession().close();
        }
    }
}
