/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import p1.enums.FontEnum;
import p1.helpers.FullListViewHelper;
import p1.interfaces.Returnable;
import p1.listeners.MListener;

/**
 *
 * @author Ertjon
 */
public class FullListView extends FullListViewHelper implements Returnable{
    private final JTable jTable;
    private JPanel mainPanel=new JPanel();
    
    public FullListView() {
        JPanel tablePanel = new JPanel();
        fillJTable();
        jTable = new JTable();
        jTable.setModel(new CellEditable(getData(), getColumns()));
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
        mainPanel.add(tablePanel);
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}
