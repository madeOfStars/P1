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
public enum ButtonEnum {

    HOME("Home",ImageEnum.HOME),
    BACK("Go Back",ImageEnum.BACK);

    private String text;
    private ImageEnum icon;

    ButtonEnum(String text, ImageEnum icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ImageEnum getIcon() {
        return icon;
    }

    public void setIcon(ImageEnum icon) {
        this.icon = icon;
    }

}
