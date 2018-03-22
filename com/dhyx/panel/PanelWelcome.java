package com.dhyx.panel;

import com.dhyx.myclass.Const;
import com.dhyx.MainApp;
import com.dhyx.myclass.MyIconButton;

import javax.swing.*;

public class PanelWelcome extends JPanel{

    public PanelWelcome(){
        this.setLayout(null);
        this.setBounds(0, Const.TITLE_HEIGHT, 1100, Const.PANEL_HEIGHT);
        ImageIcon img = new ImageIcon(MainApp.class.getResource("/icon/flag.jpg"));
        MyIconButton mybtn = new MyIconButton(img, img, img);
        this.add(mybtn);
    }
}
