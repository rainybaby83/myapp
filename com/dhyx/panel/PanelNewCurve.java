package com.dhyx.panel;

import com.dhyx.myclass.*;
import flanagan.physchem.ImmunoAssay;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PanelNewCurve extends JPanel {
    private Logger logger = LogManager.getLogger();
    private PanelQuery panelQuery;
    private MyIconButton btnQuery, btnDel, btnFit;
    private DefaultTableModel dmCurve,dmConcentration;
    private String sqlSelectCurve = "SELECT DISTINCT 项目名称,实验名称,曲线序号,曲线日期,曲线ID,实验ID,项目ID FROM view_project_exp_curve";
    private String sqlSelectConcentration = "SELECT DISTINCT 浓度序号,浓度值,反应值 FROM view_curve_concentration";
    private MyTable tblCurve,tblConcentration;
    private MyComboBox lstXType,lstYType ;
    private JLabel[] lRight = new JLabel[6];



    public PanelNewCurve() {
        initSelf();
        initQueryPanel();
        initButton();
        initLabel();
        initTable();
        initList();
        initOther();
        click_btnQuery("启动窗体调用");
        click_tblCurve("启动窗体调用");
    }



    //初始化面板基本属性
    private void initSelf() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(Const.LEFT_MARGIN, Const.TITLE_HEIGHT, Const.PANEL_WIDHT, Const.PANEL_HEIGHT);
    }


    //初始化查询面板
    private void initQueryPanel() {
        panelQuery = new PanelQuery();
        panelQuery.lblQuery.setText("实验关键字");
        panelQuery.txtQuery.setSize(250, Const.BUTTON_HEIGHT);
        panelQuery.txtQuery.setToolTipText("请输入实验名称.");
        panelQuery.txtQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按回车键执行相应操作;
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    click_btnQuery("文本框回车调用");
                    click_tblCurve("文本框回车调用");
                }
            }
        });

        btnQuery = panelQuery.btnQuery;
        btnQuery.setBounds(435, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);
    }   // END :  private void initQueryPanel()


    //初始化按钮
    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：生成曲线-查询" + panelQuery.txtQuery.getText());
                    click_btnQuery("监听：鼠标单击查询按钮");
                    click_tblCurve("监听：鼠标单击查询按钮");
                }
            }
        });

        //删除选中曲线按钮
        btnDel = new MyIconButton(Const.ICON_CURVE_DEL, Const.ICON_CURVE_DEL_ENABLED, Const.ICON_CURVE_DEL_DISABLED);
        btnDel.setBounds(625, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：生成曲线-删除选中曲线");
                    click_btnDel();
                }
            }
        });
        panelQuery.add(btnDel);

        //查看所有拟合按钮
        btnFit = new MyIconButton(Const.ICON_FIT, Const.ICON_FIT_ENABLED, Const.ICON_FIT_DISABLED);
        btnFit.setBounds(845, 170, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        btnFit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnFit.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：生成曲线-查看所有拟合");
                    click_btnFit();
                }
            }
        });
        this.add(btnFit);
    }   // END : private void initButton()
    

    //初始化标签
    private void initLabel() {
        JLabel lblCurve = new JLabel("曲线列表");
        JLabel lblConcentration = new JLabel("所选曲线的浓度(已求平均)");
        JLabel lblFitType = new JLabel("拟合方式");
        JLabel lblFitTitle = new JLabel("选中的拟合结果");
        JLabel lblXType = new JLabel("X处理");
        JLabel lblYType = new JLabel("Y处理");
        JLabel lblFitResult = new JLabel("方法：");

        lblCurve.setBounds(0, 40, 385, 25);
        lblConcentration.setBounds(435, 40, 310, 25);
        lblFitType.setBounds(800, 40, 200, 25);
        lblFitTitle.setBounds(800, 250, 200, 25);
        lblXType.setBounds(815, 85, 45, 25);
        lblYType.setBounds(815, 120, 45, 25);
        lblFitResult.setBounds(820, 295, 160, 25);

        this.add(lblCurve);
        this.add(lblConcentration);
        this.add(lblFitType);
        this.add(lblFitTitle);
        this.add(lblXType);
        this.add(lblYType);
        this.add(lblFitResult);


        JLabel[] lLeft = new JLabel[6];
        String[] title = {"R =", "a =", "b =", "c =", "d =", "e ="};
        for (int i = 0; i < 6; i++) {
            lLeft[i] = new JLabel();
            lLeft[i].setText(title[i]);
            lLeft[i].setFont(new Font("宋体", Font.PLAIN, 14));
            lLeft[i].setBounds(820, 430 + i * 25, 45, 25);
            lLeft[i].setHorizontalAlignment(JLabel.CENTER);
            lLeft[i].setVerticalAlignment(JLabel.BOTTOM);
            this.add(lLeft[i]);

            lRight[i] = new JLabel();
            lRight[i].setText("");
            lRight[i].setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.gray));
            lRight[i].setBounds(880, 430 + i * 25, 85, 25);
            lRight[i].setHorizontalAlignment(JLabel.CENTER);
            this.add(lRight[i]);
        }

    }


    //初始化3个表格
    private void initTable() {
        initTableGetModel();
        initTableCreate();
        initTableAddListener();
    }  // END:initTalbe()


    // 1. 创建TableModel
    private void initTableGetModel() {
        dmCurve = TableMethod.getTableModel(sqlSelectCurve + " WHERE 0=1");
        dmConcentration = TableMethod.getDMwithCheck(sqlSelectConcentration + " WHERE 0=1");
    }


    // 2.初始化表格
    private void initTableCreate() {

        // 2.1 创建表格
        tblCurve = new MyTable(dmCurve);
        tblConcentration = new MyTable(dmConcentration){
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
        tblConcentration.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 2.2 设置颜色、尺寸等基本属性
        tblCurve.setBounds(0, 65, 385, 550);
        tblConcentration.setBounds(435,65,315,550);

        // 2.3 表格列宽
        tblCurve.setWidth(70, 125, 60, 80, 50, 50, 50);
        tblConcentration.setWidth(30, 75, 105, 105);

        // 2.4 设置滚动面板
        tblCurve.j.setBounds(tblCurve.getBounds());
        tblConcentration.j.setBounds(tblConcentration.getBounds());
        tblConcentration.j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(tblCurve.j);
        this.add(tblConcentration.j);
    }




    // 添加表格的监听
    private void initTableAddListener() {

        // 在实验列表释放鼠标，触发实验列表单击事件
        tblCurve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    click_tblCurve("监听：鼠标单击曲线列表");
                }
            }
        });

        //单击监听
        tblConcentration.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    click_tblConcentration("监听：鼠标单击浓度列表");
                }
            }
        });
    }   // END : private void initTableAddListener()


    // 初始化列表框
    private void initList() {
        String[] value = {"1 不变换", "2 取e为底的对数", "3 取10为底的对数", "4 取2为底的对数"};
        String[] key = {"1", "2", "3", "4"};
        lstXType = new MyComboBox();
        lstYType = new MyComboBox();
        lstXType.setKeyValue(key, value);
        lstYType.setKeyValue(key, value);
        lstXType.setBounds(860, 85, 125, 25);
        lstYType.setBounds(860, 120, 125, 25);
        this.add(lstXType);
        this.add(lstYType);
    }


    private void initOther() {
        //两个方框
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp1.setBounds(800, 65, 200, 160);
        jp2.setBounds(800, 275, 200, 340);
        jp1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        jp2.setBorder(jp1.getBorder());
        this.add(jp1);
        this.add(jp2);

        //拟合公式图片

    }


    //查询按钮
    private void click_btnQuery(String msg) {
        btnQuery.setEnabled(false);
        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String sql, strQueryKey;
        strQueryKey = panelQuery.txtQuery.getText().trim();
        strQueryKey = StringUtils.replace(strQueryKey, "'", "");
        strQueryKey = StringUtils.replace(strQueryKey, "%", "");
        sql = sqlSelectCurve + " WHERE `实验名称` LIKE '%" + strQueryKey + "%' " +
                " AND `锁定`='N' ORDER BY `项目ID`,`实验ID`,`曲线序号`";
        dmCurve = TableMethod.getTableModel(sql);
        tblCurve.setModel(dmCurve);
        tblCurve.setFirstRow();

        //后续处理
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }



    private void click_btnDel() {
    }


    private void click_btnFit() {

        ImmunoAssay assay = new ImmunoAssay("123");
        assay.selectEquation();
    }


    //点击曲线列表时的操作
    private void click_tblCurve(String msg) {
        //设置DM默认值，如果符合if条件，则设置为正确的
        dmConcentration = TableMethod.getTableModel(sqlSelectConcentration + " WHERE 0=1");
        btnDel.setEnabled(false);
        btnFit.setEnabled(false);

        if (tblCurve.getRowCount() > 0) {
            int nowRow = tblCurve.getSelectedRow();
            if (nowRow > -1) {
                DefaultTableModel currentDM = (DefaultTableModel) tblCurve.getModel();
                String curveID = tblCurve.getValueAt(nowRow, currentDM.findColumn("曲线ID")).toString();
                String sql = sqlSelectConcentration + " WHERE `曲线ID` = '" + curveID + "' AND `锁定`= 'N' ORDER BY `浓度序号`";
                dmConcentration = TableMethod.getDMwithCheck(sql);
                btnDel.setEnabled(true);
                btnFit.setEnabled(true);
            }
        }
        tblConcentration.setModel(dmConcentration);
    }



    private void click_tblConcentration(String msg) {
        //获取所有被选中的行
        int[] nowRows = tblConcentration.getSelectedRows();

        //多选时，复选框打钩的功能
        for (int i = 0; i < tblConcentration.getRowCount(); i++) {
            if (ArrayUtils.contains(nowRows, i)) {
                tblConcentration.setValueAt(true, i, 0);
            } else {
                tblConcentration.setValueAt(false, i, 0);
            }
        }
    }

}
