package dhyx.UI;

import dhyx.Class.Const;
import dhyx.Class.MyIconButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PanelQuery extends  JPanel{
    public MyIconButton btnQuery;
    public JLabel lblQuery ;
    public JTextField txtQuery;

    PanelQuery() {
        init();
        addComponent();
        addListener();
    }

    private void init(){
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.setBounds(0, 0,Const.PANEL_WIDHT,Const.TITLE_HEIGHT);

        lblQuery = new JLabel("查询关键字");
        lblQuery.setBounds(0, 0, 70, Const.BUTTON_HEIGHT);

        txtQuery = new JTextField();
        txtQuery.setLocation(lblQuery.getX()+lblQuery.getWidth(),0);
        txtQuery.setFont(new Font("宋体", Font.PLAIN,14));
        txtQuery.setForeground(Color.DARK_GRAY);
        txtQuery.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));

        btnQuery = new MyIconButton(Const.ICON_QUERY, Const.ICON_QUERY_ENABLED, Const.ICON_QUERY_DISABLED);

    }

    private void addComponent(){
        this.add(lblQuery);
        this.add(txtQuery);
        this.add(btnQuery);
    }

    private void addListener(){
        txtQuery.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtQuery.selectAll();
            }
        });
    }
}

