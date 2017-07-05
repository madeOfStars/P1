/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.helpers;

import entity.Project;
import entity.Release;
import entity.Revision;
import java.awt.event.MouseAdapter;
import java.util.Date;
import javax.swing.table.TableModel;

/**
 *
 * @author Ertjon
 */
public class MouseListenerHelper extends MouseAdapter {

    protected Release creteRelease(TableModel model, int row, Project project) {
        Release release = new Release();
        release.setId((int) model.getValueAt(row, 0));
        release.setCode((int) model.getValueAt(row, 1));
        release.setDateAdded((Date) model.getValueAt(row, 2));
        release.setClosed((int) model.getValueAt(row, 3));
        release.setDateClosed((Date) model.getValueAt(row, 4));
        release.setReleaseFolder((String) model.getValueAt(row, 5));
        release.setProject(project);
        return release;
    }
    
    protected Revision createRevision(TableModel model, int row, Release release){
        Revision revision=new Revision();
        revision.setId((int)model.getValueAt(row, 0));
        revision.setRevisionNumber((int)model.getValueAt(row, 1));
        revision.setDateAdded((Date)model.getValueAt(row, 2));
        
        revision.setRelease(release);
        return revision;
    }
}
