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
public enum ImageEnum {

    OK("resources/OK.png","OK"),
    FAIL("resources/FAIL.png","FAIL"),
    HOME("resources/home2.png","HOME"),
    BACK("resources/back2.png","BACK");

    private String path;
    private String alt;

    ImageEnum(String path, String alt) {
        this.path = path;
        this.alt = alt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

}
