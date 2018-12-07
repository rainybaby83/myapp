package com.dhyx.panel;

import com.dhyx.MainApp;
import com.dhyx.myclass.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private MyTable tblCurve, tblConcentration;
    private DefaultTableModel dmCurve, dmConcentration;
    private String sqlSelectCurve = "SELECT DISTINCT  曲线ID,拟合日期,项目名称,实验名称,拟合方法 FROM view_lock_curve";
    private String sqlSelectConcentration = "SELECT DISTINCT 浓度序号,浓度值,反应值,浓度ID FROM view_curve_concentration";


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



    /**
     * 1. 创建TableModel
     */
    private void initTableGetModel() {
        dmCurve = TableMethod.getTableModel(sqlSelectCurve );
        dmConcentration = TableMethod.getDMwithCheck(sqlSelectConcentration + " WHERE 0=1");
    }   // END : private void initTableGetModel()


    /**
     * 2.创建表格并设置属性
     */
    private void initTableCreate() {
        // 2.1 创建表格
        tblCurve = new MyTable(dmCurve);
        tblConcentration = new MyTable(null) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }

            @Override   // 表格带有单选框
            public Class getColumnClass(int c) {
                Object value = getValueAt(0, c);
                if (value != null) {
                    return value.getClass();
                } else {
                    return super.getClass();
                }
            }
        };
        tblConcentration.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tblConcentration.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 2.2 设置尺寸
        tblCurve.setBounds(0, 65, 480   , 300);
        tblConcentration.setBounds(535, 65, 465, 550);

        // 2.3 表格列宽
        //项目名称,实验名称,曲线序号,创建日期,曲线ID,实验ID,项目ID
        tblCurve.setWidth(87, 81, 87, 113, 112);
        //浓度序号,浓度值,反应值,浓度ID
        tblConcentration.setWidth(30, 75, 105, 105);

        // 2.4 设置滚动面板
        tblCurve.j.setBounds(tblCurve.getBounds());
        tblConcentration.j.setBounds(tblConcentration.getBounds());
        tblConcentration.j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //2.5 取得“浓度值”的index，然后对该列设置CellEditor，使其只能输入小数
        int index = dmConcentration.findColumn("浓度值");
//        tblConcentration.getColumnModel().getColumn(index).setCellEditor(new PanelCurveNew.FloatCellEditor());

        //2.6 添加弹出菜单
//        tblCurve.jPopupMenu.add(btnDel);

        // 2.7 添加表格到面板
        this.add(tblCurve.j);
        this.add(tblConcentration.j);
    }   // END : private void initTableCreate()



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
