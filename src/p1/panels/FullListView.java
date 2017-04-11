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
import p1.enums.TableEnum;
import p1.helpers.FullListViewHelper;
import p1.interfaces.Returnable;
import p1.listeners.MListener;

/**
 *
 * @author Ertjon
 * @param <E>
 */
public class FullListView<E> extends FullListViewHelper implements Returnable{
    private JTable jTable=null;
    private final JPanel mainPanel=new JPanel();
    
    private E element;
    
    public FullListView(){
    }
    
    public FullListView(TableEnum tableEnum){
        this(tableEnum, null);
    }
    
    public FullListView(TableEnum tableEnum, E element){
        this(tableEnum.getColumns(), tableEnum, element);
    }
    
    public FullListView(String []columns, TableEnum tableEnum, E element) {
        super(columns,tableEnum);
        this.element=element;
        switch (tableEnum){
            case PROJECT_TABLE:{
                setProjectTable();
                break;
            }
            case RELEASE_TABLE: {
                setReleaseTable();
                break;
            }
            case VERSION_TABLE: {
                setVersionTable();
                break;
            }
        }
    }
    
    private void setProjectTable(){
        JPanel tablePanel = new JPanel();
        fillJTable();
        jTable = new JTable();
        jTable.setName(getTableEnum().name());
        jTable.setModel(new CellEditable(getData(), getColumns()));
        jTable.setRowHeight(50);
        jTable.setShowVerticalLines(false);
        jTable.setFont(FontEnum.TABLE_CONTENT.getFont());
        jTable.getTableHeader().setFont(FontEnum.TABLE_HEADER.getFont());
        jTable.addMouseListener(new MListener());
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        jTable.removeColumn(jTable.getColumnModel().getColumn(1));
        JScrollPane jsp = new JScrollPane(jTable);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(jsp);
        mainPanel.add(tablePanel);
    }
    
    private void setReleaseTable(){
        JPanel tablePanel = new JPanel();
        fillJTable();
        jTable = new JTable();
        jTable.setName(getTableEnum().name());
        jTable.setModel(new CellEditable(getData(), getColumns()));
        jTable.setRowHeight(50);
        jTable.setShowVerticalLines(false);
        jTable.setFont(FontEnum.TABLE_CONTENT.getFont());
        jTable.getTableHeader().setFont(FontEnum.TABLE_HEADER.getFont());
        jTable.addMouseListener(new MListener(element));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        jTable.removeColumn(jTable.getColumnModel().getColumn(2));
        jTable.removeColumn(jTable.getColumnModel().getColumn(2));
        JScrollPane jsp = new JScrollPane(jTable);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(jsp);
        mainPanel.add(tablePanel);
    }
    
    private void setVersionTable(){
        JPanel tablePanel = new JPanel();
        fillJTable();
        jTable = new JTable();
        jTable.setName(getTableEnum().name());
        jTable.setModel(new CellEditable(getData(), getColumns()));
        jTable.setRowHeight(50);
        jTable.setShowVerticalLines(false);
        jTable.setFont(FontEnum.TABLE_CONTENT.getFont());
        jTable.getTableHeader().setFont(FontEnum.TABLE_HEADER.getFont());
        jTable.addMouseListener(new MListener());
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        JScrollPane jsp = new JScrollPane(jTable);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(jsp);
        mainPanel.add(tablePanel);
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}
