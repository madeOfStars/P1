/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.helpers;

import entity.Project;
import entity.Release;
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
        release.setProject(project);
        return release;
    }
}