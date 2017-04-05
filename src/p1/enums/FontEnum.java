/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.enums;

import java.awt.Font;

/**
 *
 * @author Ertjon
 */
public enum FontEnum {
    HEADER(new Font("Courier New", Font.PLAIN, 22)),
    TABLE_CONTENT(new Font("Courier New", Font.PLAIN, 15)),
    TABLE_HEADER(new Font("Courier New", Font.BOLD, 18)),
    CONTENT(new Font("Courier New", Font.BOLD, 12));
    
    private String family;
    private int style;
    private int size;
    
    private Font font;
    
    FontEnum(Font font){
        this.font=font;
    }
    
    FontEnum(String family,int style,int size){
        this.family=family;
        this.style=style;
        this.size=size;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
    
    
}
