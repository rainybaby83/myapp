package com.dhyx.panel;

import com.dhyx.myclass.Const;
import com.dhyx.myclass.MyIconButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelBatchQuery extends JPanel {
    private PanelQuery panelQuery;
    private MyIconButton btnQuery, btnSaveQRpic;


    public PanelBatchQuery() {
        initSelf();
        initQueryPanel();
        initButton();
        initLabel();
        initTableGetModel();
        initTableCreate();
        initTableAddListener();
        initList();
        initOther();
        clickBtnQuery("启动窗体调用，PanelBatchQuery() ");
        clickTblCurve("启动窗体调用，PanelBatchQuery() ");
    }


    /**
     * 初始化面板基本属性
     */
    private void initSelf() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(Const.LEFT_MARGIN, Const.TITLE_HEIGHT, Const.PANEL_WIDHT, Const.PANEL_HEIGHT);
    }   // END : private void initSelf()


    /**
     * 初始化查询面板
     */
    private void initQueryPanel() {
        panelQuery = new PanelQuery();
        panelQuery.txtQuery.setSize(250, Const.BUTTON_HEIGHT);
        panelQuery.txtQuery.setToolTipText("请输入批号");
        panelQuery.txtQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按回车键执行相应操作;
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    clickBtnQuery("文本框回车调用，initQueryPanel() ");
                    clickTblCurve("文本框回车调用，initQueryPanel() ");
                }
            }
        });

        btnQuery = panelQuery.btnQuery;
        btnQuery.setBounds(panelQuery.txtQuery.getX() + panelQuery.txtQuery.getWidth() + 60, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);
    }   // END : private void initQueryPanel()


    private void initButton() {
    }

    private void initLabel() {
    }

    private void initTableGetModel() {
    }

    private void initTableCreate() {
    }

    private void initTableAddListener() {
    }

    private void initList() {
    }

    private void initOther() {
    }

    private void clickBtnQuery(String s) {

    }

    private void clickTblCurve(String s) {
    }
}
