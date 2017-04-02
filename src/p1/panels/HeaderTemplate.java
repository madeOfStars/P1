/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import p1.enums.FontEnum;

/**
 *
 * @author Ertjon
 */
public class HeaderTemplate extends JPanel{
    public HeaderTemplate(String message){
        JLabel msg=new JLabel(message);
        msg.setFont(FontEnum.HEADER.getFont());
        this.add(msg);
    }
}
