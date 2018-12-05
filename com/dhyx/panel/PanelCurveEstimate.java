package com.dhyx.panel;

import com.dhyx.MainApp;
import com.dhyx.myclass.Const;
import com.dhyx.myclass.MyDatabase;
import com.dhyx.myclass.MyIconButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCurveEstimate extends JPanel {
    private Logger logger = LogManager.getLogger();
    private MyDatabase db = MainApp.myDB;
    private PanelQuery panelQuery;
    private MyIconButton btnQuery, btnEstimate, btnLock;
    private JLabel lblImage = new JLabel();
    private JLabel[] lRight = new JLabel[7];


    public PanelCurveEstimate() {
        initSelf();
        initQueryPanel();
        initButton();
        initLabel();
        initTableGetModel();
        initTableCreate();
        initTableAddListener();
        initList();
        initOther();
        clickBtnQuery("启动窗体调用，PanelCurveEstimate() ");
        clickTblCurve("启动窗体调用，PanelCurveEstimate() ");
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
        panelQuery.txtQuery.setSize(220, Const.BUTTON_HEIGHT);
        panelQuery.txtQuery.setToolTipText("请输入项目名称、实验名称");
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
        btnQuery.setBounds(panelQuery.txtQuery.getX() + panelQuery.txtQuery.getWidth() + 60, 0, Const.BUTTON_WIDTH,
                Const.BUTTON_HEIGHT);
        this.add(panelQuery);
    }   // END : private void initQueryPanel()


    /**
     * 初始化按钮，添加监听：查询、评估、锁定曲线
     */
    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：生成曲线-查询" + panelQuery.txtQuery.getText());
                    clickBtnQuery("监听：鼠标单击查询按钮。mouseClicked() ");
                    clickTblCurve("监听：鼠标单击查询按钮。mouseClicked() ");
                }
            }
        });

        //"评估"按钮
        btnEstimate = new MyIconButton(Const.ICON_CURVE_ESTIMATE, Const.ICON_CURVE_ESTIMATE_ENABLED,
                Const.ICON_CURVE_ESTIMATE_DISABLED);
        btnEstimate.setBounds(535, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        btnEstimate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnEstimate.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {


                }
            }
        });
        panelQuery.add(btnEstimate);


        //"锁定曲线"按钮
        btnLock = new MyIconButton(Const.ICON_CURVE_LOCK, Const.ICON_CURVE_LOCK_ENABLED, Const.ICON_CURVE_LOCK_DISABLED);
        btnLock.setBounds(710, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        btnLock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnLock.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {

                }
            }
        });
        panelQuery.add(btnLock);
    }


    /**
     * 初始化标签
     */
    private void initLabel() {
        JLabel lblCurveList = new JLabel("已拟合、未锁定的曲线");
        JLabel lblReverse = new JLabel("反推计算");
        JLabel lblCurveParam = new JLabel("曲线参数");
        JLabel lblX = new JLabel("X对数：");
        JLabel lblY = new JLabel("X对数：");

        lblCurveList.setBounds(0, 40, 480, 25);
        lblCurveParam.setBounds(0, 375, lblCurveList.getWidth(), 25);
        lblReverse.setBounds(btnEstimate.getX(), lblCurveList.getY(), 465, 25);
        lblX.setBounds(40, 535, 55, 25);
        lblY.setBounds(40, 570, 55, 25);
        lblImage.setBounds(40, 430, 200, 70);
        lblImage.setIcon(Const.ICON_FX);

        JLabel[] lLeft = new JLabel[7];
        String[] title = {"方法", "a =", "b =", "c =", "d =", "e =", "R² ="};

        for (int i = 0; i < lLeft.length; i++) {
            lLeft[i] = new JLabel();
            lLeft[i].setText(title[i]);
            lLeft[i].setBounds(285, 420 + i * 25, 45, 25);
            lLeft[i].setHorizontalAlignment(JLabel.CENTER);
            this.add(lLeft[i]);

            lRight[i] = new JLabel();
            lRight[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
            lRight[i].setBounds(335, 420 + i * 25, 100, 25);
            lRight[i].setHorizontalAlignment(JLabel.CENTER);
            this.add(lRight[i]);
        }

        this.add(lblCurveList);
        this.add(lblReverse);
        this.add(lblCurveParam);
        this.add(lblX);
        this.add(lblY);
        this.add(lblImage);

        //方框
        JPanel jp = new JPanel();
        jp.setOpaque(false);
        jp.setBounds(0, lblCurveParam.getY()+ lblCurveParam.getHeight(), lblCurveParam.getWidth(),215);
        jp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        this.add(jp);
    }   // END : private void initLabel()



    private void initTableGetModel() {
    }

    private void initTableCreate() {
    }

    private void initTableAddListener() {
    }

    private void initList() {
    }



    /**
     * 初始化其它组件
     */
    private void initOther() {

    }





    private void clickBtnQuery(String s) {

    }

    private void clickTblCurve(String s) {
    }
}
