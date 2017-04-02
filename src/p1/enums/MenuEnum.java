package p1.enums;

/**
 *
 * @author Ertjon
 */
public enum MenuEnum {
    FILE(1, "File"),
    NEW_PRJ(11, "Add New project");

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
