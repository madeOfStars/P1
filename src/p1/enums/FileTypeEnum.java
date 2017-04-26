package p1.enums;

/**
 *
 * @author Ertjon Mecka
 */
public enum FileTypeEnum {
    JAVA(".java"),
    AS(".as"),
    MXML(".mxml"),
    SQL(".sql");

    private String extension;

    FileTypeEnum(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
