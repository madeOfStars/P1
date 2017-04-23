package p1.enums;

/**
 *
 * @author Ertjon
 */
public enum MenuEnum {
    FILE(1, "File"),
    PROJECT(10,"Project"),
    NEW_PRJ(101, "Add New project"),
    EDIT_PRJ(102, "Edit project"),
    DEL_PRJ(103, "Delete project"),
    RELEASE(11, "Release"),
    NEW_RLS(111, "Add New release"),
    EDIT_RLS(112, "Edit release"),
    DEL_RLS(113, "Delete release"),
    REVISION(12, "Revision"),
    NEW_RVS(121,"Add New revision"),
    EDIT_RVS(122,"Edit revision"),
    DEL_RVS(123,"Delete revision"),
    EXIT(999,"Exit");

    private int id;
    private String label;

    MenuEnum(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
