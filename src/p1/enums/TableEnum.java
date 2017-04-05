/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.enums;

/**
 *
 * @author Ertjon
 */
public enum TableEnum {

    PROJECT_TABLE(new String[]{"ID", "Project Name", "Path", "Date Added"}),
    RELEASE_TABLE(new String[]{"ID", "Code", "Date Added", "Closed", "Date Closed"});

    private String[] columns;

    TableEnum(String[] columns) {
        this.columns = columns;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

}
