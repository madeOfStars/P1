/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.panels;

import entity.Project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import p1.enums.FontEnum;
import p1.listeners.MListener;
import p1.utils.DatabaseUtil;
import p1.utils.FlowUtil;

/**
 *
 * @author Ertjon
 */
public class FullListView extends JPanel {

    private DatabaseUtil dbUtil = DatabaseUtil.getInstance();
    private final String[] columns = new String[]{"ID","Project Name", "Path", "Date Added"};
    private Object[][] data;
    private JTable jTable;
    
    public FullListView() {
        JPanel tablePanel = new JPanel();
        getData();
        jTable = new JTable();
        jTable.setModel(new CellEditable(data, columns));
        jTable.setRowHeight(50);
        jTable.setShowVerticalLines(false);
        jTable.setFont(FontEnum.TABLE_CONTENT.getFont());
        jTable.getTableHeader().setFont(FontEnum.TABLE_HEADER.getFont());
        jTable.addMouseListener(new MListener());
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        jTable.removeColumn(jTable.getColumnModel().getColumn(1));
        //jTable.setSize(250, 600);
        JScrollPane jsp = new JScrollPane(jTable);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        //jsp.setSize(250, 650);
        tablePanel.add(jsp);
        this.add(tablePanel);
    }

    private void getData() {
        
        List<Project> lista = dbUtil.getAllProjects();
        data = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            data[i][0] = lista.get(i).getId();
            data[i][1] = lista.get(i).getProjectName();
            data[i][2] = lista.get(i).getPath();
            data[i][3] = lista.get(i).getDateAdded();
        }
    }
    
    private class CellEditable extends DefaultTableModel {

        public CellEditable(Object[][] data, String[] columns) {
            super(data, columns);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
