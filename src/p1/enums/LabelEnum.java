package p1.enums;

/**
 *
 * @author Ertjon Mecka
 */
public enum LabelEnum {
    NEW_PRJ_LBL("No Available Projects! Click here to add a new One"),
    NEW_RELEASE_LBL("No Available Releases! Click here to add a new One"),
    NEW_REVISION_LBL("No Available Revisions! Click here to add a new One");
    
    private String message;
    
    LabelEnum(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
