package com.dhyx.panel;


import com.dhyx.myclass.*;
import com.dhyx.MainApp;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class PanelTest extends JPanel {
    private Logger logger = LogManager.getLogger();
    private PanelQuery panelQuery;
    private JPanel panelOrder = new JPanel();
    private JLabel lblExperiment, lblCurve, lblTest, lblTestData;
    private MyIconButton btnQuery, btnDel, btnTest;
    private MyTable tblExperiment, tblCurve, tblTest, tblTestData;
    private DefaultTableModel dmExperiment, dmCurve, dmTest, dmTestData;
    private String sqlSelectExp = "SELECT DISTINCT 项目名称,实验ID,实验名称,实验创建日期,项目ID  FROM view_pro_exp ";
    private String sqlSelectCurve = "SELECT DISTINCT 曲线序号,曲线ID FROM view_project_exp_curve ";
    private String sqlSelectTest = "SELECT DISTINCT 浓度序号,X1,X2,TC值,实验ID,曲线ID,浓度ID,测试ID,测试时间  FROM view_exp_curve_test ";
    private String projectID,experimentID, curveID,concentrationID, testID;
    private static int TABLE_WIDHT_325 = 325;
    private static int TABLE_HEIGHT_275 = 275,TABLE_HEIGHT_250 = 250;
    private MyChart panelChart;
    private JList<Integer> listCurveOrder, listConcentrationOrder;
    private  MyDatabase db = MainApp.myDB;
    private  Connection conn = MainApp.myDB.conn;
    private  Statement stmt = MainApp.myDB.stmt;


    public PanelTest() {
        initSelf();
        initQueryPanel();
        initButton();
        initLabel();
        initTable();
        initChart();
        initPanelOrder();
        btnQueryClicked();
    }


    //初始化面板基本属性
    private void initSelf() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(Const.LEFT_MARGIN, Const.TITLE_HEIGHT, Const.PANEL_WIDHT, Const.PANEL_HEIGHT);
    }   // END :  private void initSelf()


    //初始化查询面板
    private void initQueryPanel() {
        panelQuery = new PanelQuery();

        panelQuery.txtQuery.setSize(250, Const.BUTTON_HEIGHT);
        panelQuery.txtQuery.setToolTipText("请输入实验名称。支持模糊查询，不区分大小写");
        panelQuery.txtQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按回车键执行相应操作;
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btnQueryClicked();
                }
            }
        });

        btnQuery = panelQuery.btnQuery;
        btnQuery.setBounds(360, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);

    }   // END :  private void initQueryPanel()


    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：测试管理-查询" + panelQuery.txtQuery.getText());
                    btnQueryClicked();
                }
            }
        });


        //删除选中记录按钮
        btnDel = new MyIconButton(Const.ICON_DEL_TEST, Const.ICON_DEL_TEST_ENABLED, Const.ICON_DEL_TEST_DISABLED);
        btnDel.setLocation(btnQuery.getX() + btnQuery.getWidth() + 53, 0);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：实验管理-删除选中记录");
                    btnDelClicked();
                }
            }
        });
        panelQuery.add(btnDel);

        //开始测试按钮
        btnTest = new MyIconButton(Const.ICON_TEST, Const.ICON_TEST_ENABLED, Const.ICON_TEST_DISABLED);
        btnTest.setLocation(btnDel.getX() + btnDel.getWidth() + 53, 0);
        btnTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnTest.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：测试管理-开始测试");
                    btnStartTest();
                }
            }
        });
        panelQuery.add(btnTest);
    }   // END : private void initButton()


    //初始化4个标签
    private void initLabel() {
        int standardY = panelQuery.getY() + panelQuery.getHeight();
        lblExperiment = new JLabel("实验列表");
        lblExperiment.setBounds(0, standardY, TABLE_WIDHT_325, 25);

        lblCurve = new JLabel("曲线列表");
        lblCurve.setBounds(btnQuery.getX(), standardY, 120, 25);

        lblTest = new JLabel("测试记录");
        lblTest.setBounds(lblCurve.getX() + lblCurve.getWidth() + 20, standardY, 360, 25);

        lblTestData = new JLabel("原始数据");
        lblTestData.setBounds(0, 325, TABLE_WIDHT_325, 25);

        this.add(lblExperiment);
        this.add(lblCurve);
        this.add(lblTest);
        this.add(lblTestData);
    }   // END : private void initLabel()


    //初始化图表
    private void initChart() {
        panelChart = new MyChart();
        panelChart.setBounds(tblCurve.getX(), tblTestData.getY(), 640, TABLE_HEIGHT_275);
        this.add(panelChart);
    }


    //初始化4个表格
    private void initTable() {
        initTableGetModel();
        initTableCreate();
        initTableAddListener();
    }  // END:initTalbe()


    // 1. 创建TableModel
    private void initTableGetModel() {
        dmExperiment = TableMethod.getTableModel(sqlSelectExp + " WHERE 0=1");
        dmCurve = TableMethod.getTableModel(sqlSelectCurve + " WHERE 0=1");
        dmTest = TableMethod.getTableModelWithCheckbox(sqlSelectTest + " WHERE 0=1");
        dmTestData = TableMethod.getTableModelForTestData("1");
    }


    // 2.初始化表格
    private void initTableCreate() {
        // 2.1 创建表格
        tblExperiment = new MyTable(dmExperiment);
        tblCurve = new MyTable(dmCurve);
        tblTestData = new MyTable(dmTestData);
        tblTestData.setEnabled(false);

        tblTest = new MyTable(dmTest) {
            @Override  //表格不可编辑
            public boolean isCellEditable(int row, int column) {
                return column <= 0;
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
        tblTest.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 2.2 设置颜色、尺寸等基本属性
        int standardY = lblExperiment.getY() + lblExperiment.getHeight();
        tblExperiment.setBounds(lblExperiment.getX(), standardY, TABLE_WIDHT_325, TABLE_HEIGHT_250);
        tblCurve.setBounds(lblCurve.getX(), standardY, 120, TABLE_HEIGHT_250);
        tblTest.setBounds(lblTest.getX(), standardY, 360, TABLE_HEIGHT_250);
        tblTestData.setBounds(lblTestData.getX(), lblTestData.getY() + lblTestData.getHeight(), TABLE_WIDHT_325, TABLE_HEIGHT_275);

        // 2.3 表格列宽
        tblExperiment.setWidth(70, 65, 90, 85,10);
        tblCurve.setWidth(60, 60);
        tblTest.setWidth(30, 55, 70, 70, 70, 70, 70, 70, 70, 70);
        tblTestData.setAutoCreateColumnsFromModel(true);

        // 2.4 设置滚动面板
        tblExperiment.jScrollPane.setBounds(tblExperiment.getBounds());
        tblExperiment.jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblCurve.jScrollPane.setBounds(tblCurve.getBounds());
        tblCurve.jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblTest.jScrollPane.setBounds(tblTest.getBounds());
        tblTestData.jScrollPane.setBounds(tblTestData.getBounds());
        this.add(tblExperiment.jScrollPane);
        this.add(tblCurve.jScrollPane);
        this.add(tblTest.jScrollPane);
        this.add(tblTestData.jScrollPane);
    }


    // 添加监听
    private void initTableAddListener() {

        // 在实验列表释放鼠标，触发实验列表单击事件
        tblExperiment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    tblExperimentClicked("单击tblExperiment调用tblExperimentClicked");
                }
            }
        });

        //单击监听
        tblCurve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tblCurveClicked("单击tblCurve调用tblCurveClicked");
            }
        });

        // 测试列表单击事件，实际调用鼠标释放事件
        tblTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    tblTestClicked("单击tblTest调用tblTestClicked");
                }
            }
        });

        //点击表头全选的单击事件
        tblTest.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tblTest.getTableHeader().columnAtPoint(e.getPoint());
                if (columnIndex == 0) {
                    selectAll(tblTest);
                    updateTestData("表头全选调用");

                }
            }
        });

    }   // END : private void initTableAddListener()


    private void initPanelOrder() {
        int width =52;

        JLabel lblCurveOrder = new JLabel("曲线序号");
        JLabel lblConentrationOrder = new JLabel("浓度序号");
        //面板内部的坐标
        lblCurveOrder.setBounds(0,0, width,25);
        lblConentrationOrder.setBounds(55,0, width, 25);

        listCurveOrder = new JList<>();
        listConcentrationOrder = new JList<>();
        listCurveOrder.setBounds(0,25, width,TABLE_HEIGHT_250);
        listConcentrationOrder.setBounds(55,25, width,TABLE_HEIGHT_250);

        JScrollPane j1 = new JScrollPane(listCurveOrder);
        JScrollPane j2 = new JScrollPane(listConcentrationOrder);
        j1.getVerticalScrollBar().setUI(new MyScrollBarUI());
        j2.getVerticalScrollBar().setUI(new MyScrollBarUI());
        j1.setBounds(listCurveOrder.getBounds());
        j2.setBounds(listConcentrationOrder.getBounds());

        panelOrder.setLayout(null);
        panelOrder.setBounds(890, 40, 110, TABLE_HEIGHT_250 + 25);
        panelOrder.setOpaque(false);
        panelOrder.add(lblCurveOrder);
        panelOrder.add(lblConentrationOrder);
        panelOrder.add(j1);
        panelOrder.add(j2);

        updateListOrder(null,null);
        this.add(panelOrder);
    }


    /**
     * 单击查询按钮时执行的代码
     */
    private void btnQueryClicked() {
        btnQuery.setEnabled(false);
        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String sql, strQueryKey;
        strQueryKey = panelQuery.txtQuery.getText().trim();
        strQueryKey = StringUtils.replace(strQueryKey, "'", "");
        strQueryKey = StringUtils.replace(strQueryKey, "%", "");
        sql = sqlSelectExp + " WHERE `实验名称` LIKE '%" + strQueryKey + "%'";

        dmExperiment = TableMethod.getTableModel(sql);
        tblExperiment.setModel(dmExperiment);

        if (tblExperiment.getRowCount() > 0) {
            tblExperiment.setRowSelectionInterval(0, 0);
        }
        //调用表格点击事件，刷新右侧数据
        tblExperimentClicked("查询按钮调用");

        //后续处理
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }


    /**
     * 1 校验是否允许删除
     * 2 取消自动提交
     * 3 取得testID、concentrationID、curveID
     * 4 逻辑删除Test、Test_Original
     * 5 检查testID对应的浓度是否还有数据，重新计算concentration，或者直接逻辑删除
     * 6 提交事务
     * 6 调用曲线列表点击事件，刷新表格数据和统计图
     */
    private void btnDelClicked() {

        /* 1 校验是否允许删除:
            1)只能单选一行
            2）曲线没有被锁。此条件暂时忽略，因为本页面显示的曲线，已经设定了isLocked=N
        */
        int[] nowRows = tblTest.getSelectedRows();
        if (nowRows.length != 1) {
            // 选中行数不等于1行，提示，不执行代码
            JOptionPane.showMessageDialog(null, "请单选1行，然后点击删除按钮。");
        } else {
            try {
                // 2 取消自动提交
                conn.setAutoCommit(false);

                // 3 取得testID、concentrationID
                DefaultTableModel currentDM = ((DefaultTableModel) tblTest.getModel());
                testID = tblTest.getValueAt(nowRows[0], currentDM.findColumn("测试ID")).toString();
                concentrationID = tblTest.getValueAt(nowRows[0], currentDM.findColumn("浓度ID")).toString();
//                JOptionPane.showMessageDialog(null, concentrationID);


                // 4 逻辑删除Test、Test_Original
                String sqlDeleteTest, sqlDeleteTestOriginal;
                sqlDeleteTest = "UPDATE test SET isDeleted = 'Y' WHERE isDeleted = 'N' AND testID = ?;";
                sqlDeleteTestOriginal = "UPDATE test_original SET isDeleted = 'Y' WHERE isDeleted = 'N' AND testID = ?;";
                PreparedStatement pstmt1 = conn.prepareStatement(sqlDeleteTest);
                PreparedStatement pstmt2 = conn.prepareStatement(sqlDeleteTestOriginal);
                pstmt1.setString(1,testID);
                pstmt2.setString(1,testID);
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                pstmt1.close();
                pstmt2.close();



                // 5 重新计算concentration。如果该浓度ID还有test数据，则计算平均值，否则逻辑删除该浓度ID数据并设置为0



//                // 5 提交事务
                conn.commit();
//
//                // 6 调用曲线列表点击事件，刷新表格数据和统计图
                tblCurveClicked("单击删除按钮调用");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "创建失败，数据库未作任何修改。请查看日志。");
                db.dbRollback();
            }


        }



    }


    private void btnStartTest() {

    }


    // 点击某行实验数据，右侧展示对应的曲线列表数据。如果没有曲线数据，则清空
    private void tblExperimentClicked(String msg) {
        btnDel.setEnabled(false);
        if (tblExperiment.getRowCount() > 0) {
            int nowRow = tblExperiment.getSelectedRow();
            if (nowRow != -1) {
                DefaultTableModel currentDM = (DefaultTableModel) tblExperiment.getModel();

                projectID = tblExperiment.getValueAt(nowRow, currentDM.findColumn("项目ID")).toString();
                experimentID = tblExperiment.getValueAt(nowRow, currentDM.findColumn("实验ID")).toString();
                String sql = sqlSelectCurve + "WHERE `实验ID` = '" + experimentID + "' ORDER BY `曲线ID`";

                dmCurve = TableMethod.getTableModel(sql);
                tblCurve.setModel(dmCurve);
                if (tblCurve.getRowCount() > 0) {
                    tblCurve.setRowSelectionInterval(0, 0);
                }
            }
        } else {
            // 查不到实验数据时，清空曲线列表
            dmCurve = TableMethod.getTableModel(sqlSelectCurve + " WHERE 0=1");
            tblCurve.setModel(dmCurve);
        }

        tblCurveClicked("单击tblExperiment调用");
    }   // END : private void tblExperimentClicked()


    // 点击曲线某行数据，则在右侧测试数据列表展示对应数据
    private void tblCurveClicked(String msg) {
        btnDel.setEnabled(false);
        if (tblExperiment.getRowCount() > 0) {
            int nowRow = tblCurve.getSelectedRow();
            if (nowRow != -1) {
                int index = ((DefaultTableModel) tblCurve.getModel()).findColumn("曲线ID");
                curveID = tblCurve.getValueAt(nowRow, index).toString();
            } else {
                // 单击实验列表时，调用本方法，如果此时曲线列表没有数据（即getSelectedRow（）=-1），则另curveID=-1，使测试表格被清空
                curveID = String.valueOf(-1);
            }
            String sql = sqlSelectTest + "WHERE `曲线ID` = '" + curveID + "' ORDER BY `测试ID`";
            dmTest = TableMethod.getTableModelWithCheckbox(sql);
            tblTest.setModel(dmTest);
        } else {
            // 查不到实验数据时，测试列表
            dmTest = TableMethod.getTableModel(sqlSelectTest + " WHERE 0=1");
            tblTest.setModel(dmTest);
        }
        //全选测试列表。如果没有数据，不影响
        selectAll(tblTest);
        updateTestData("曲线列表点击调用");
        panelChart.updateChart(tblTestData,projectID);

    }


    // JTable多选时，复选框打钩的功能
    private void tblTestClicked(String msg) {
        //获取所有被选中的行
        int[] nowRows = tblTest.getSelectedRows();
        for (int i = 0; i < tblTest.getRowCount(); i++) {
            if (ArrayUtils.contains(nowRows, i)) {
                tblTest.setValueAt(true, i, 0);
            } else {
                tblTest.setValueAt(false, i, 0);
            }
        }

        //如果单选，设置删除按钮可用
        if (nowRows.length == 1) {
            btnDel.setEnabled(true);
        } else {
            btnDel.setEnabled(false);
        }

        updateTestData(msg + " + \ntblTestClicked内部调用updateTestData");
        panelChart.updateChart(tblTestData,projectID);

    }



    //表格全选，并打钩
    private void selectAll(MyTable myTable) {
        int rowCount = myTable.getRowCount();
        btnDel.setEnabled(false);
        if (rowCount > 0) {
            myTable.setRowSelectionInterval(0, rowCount - 1);
            for (int i = 0; i < myTable.getRowCount(); i++) {
                myTable.setValueAt(true, i, 0);
            }
            if (myTable.getSelectedRows().length == 1) {
                btnDel.setEnabled(true);
            }
        }
    }


    //更新原始测试数据表格的功能
    private void updateTestData(String msg) {

        // 取得tblTest选中行的索引
        // 取得tblTest选中行的testID
        // 更新sql
        // 更新dm
        if (tblTest.getRowCount() > 0) {
            StringBuffer str = new StringBuffer();

            // 取得tblTest选中行的索引
            int[] nowRow = tblTest.getSelectedRows();
            String[] testID = new String[nowRow.length];

            // 取得tblTest选中行的testID
            int index = ((DefaultTableModel) tblTest.getModel()).findColumn("测试ID");
            for (int i = 0; i < nowRow.length; i++) {
                testID[i] = tblTest.getValueAt(nowRow[i], index).toString();
                str = str.append(testID[i]).append(",");
            }
            str.deleteCharAt(str.length() - 1);

            // 更新dm
            dmTestData = (DefaultTableModel) tblTestData.getModel();
            dmTestData = TableMethod.getTableModelForTestData(testID);
            tblTestData.setModel(dmTestData);
        } else {
            dmTestData = TableMethod.getTableModelForTestData("0");
            tblTestData.setModel(dmTestData);
        }

    }


    //更新列表框
    private void updateListOrder(int[] curve,int[]concentration) {
        Vector vCurve = new Vector();
        Vector vConcentration = new Vector();

        for(int i=1;i<=10;i++) {
            vCurve.addElement(String.valueOf(i));
            vConcentration.addElement(String.valueOf(i));
        }
        listCurveOrder.setListData(vCurve);

        for(int i=11;i<=30;i++) {
            vConcentration.addElement(String.valueOf(i));
        }
        listConcentrationOrder.setListData(vConcentration);


    }





}