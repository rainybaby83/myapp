package com.dhyx.panel;

import com.dhyx.myclass.Const;
import com.flanagan.physchem.ImmunoAssay;

import javax.swing.*;
import java.awt.*;

public class PanelFit extends JPanel {




    PanelFit(ImmunoAssay assay) {
        initSelf();
    }

    private void initSelf() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(Const.LEFT_MARGIN, Const.TITLE_HEIGHT, Const.PANEL_WIDHT, Const.PANEL_HEIGHT);
    }


}
