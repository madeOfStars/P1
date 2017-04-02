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
public enum ContainerPositionEnum {
    NORTH("North"),
    EAST("East"),
    CENTER("Center"),
    WEST("West"),
    SOUTH("South");
    
    private String location;
    
    ContainerPositionEnum(String location){
        this.location=location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
}
