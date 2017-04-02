/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.utils;

import entity.Project;
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
    
    private DatabaseUtil(){}
    
    public static DatabaseUtil getInstance(){
        if (databaseUtil==null){
            databaseUtil=new DatabaseUtil();
        }
        return databaseUtil;
    }
    
    public boolean addNewProject(File file){
        SessionPackage sp=new SessionPackage();
        
        try {
            Project p=new Project();
            p.setProjectName(file.getName());
            p.setPath(file.getPath());
            p.setDateAdded(new Date());
            sp.getSession().save(p);
            sp.getTx().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            sp.getSession().close();
        }
    }
    
    public List<Project> getAllProjects(){
        SessionPackage sp=new SessionPackage();
        String hql="FROM Project";
        Query q=sp.getSession().createQuery(hql);
        List<Project> lista=q.list();
        return lista;
    }
}
