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
    NEW_RLS(101, "Add New release"),
    EDIT_RLS(102, "Edit release"),
    DEL_RLS(103, "Delete release"),
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
