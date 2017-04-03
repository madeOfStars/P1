/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.enums;

/**
 *
 * @author Ertjon Mecka
 */
public enum LabelEnum {
    NEW_PRJ_LBL("No Available Projects! Click here to add a new One"),
    NEW_RELEASE_LBL("No Available Release! Click here to add a new One");
    
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
