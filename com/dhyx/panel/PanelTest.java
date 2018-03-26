package com.dhyx.panel;


import com.dhyx.myclass.*;
import com.dhyx.MainApp;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
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
import java.sql.*;
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
        click_btnQuery();
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
                    click_btnQuery();
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
                    click_btnQuery();
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
                    click_btnDel();
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
                    click_btnStartTest();
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
        dmTest = TableMethod.getDMwithCheck(sqlSelectTest + " WHERE 0=1");
        dmTestData = TableMethod.getDMforTestData("1");
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
                    click_tblExperiment("单击tblExperiment调用tblExperimentClicked");
                }
            }
        });

        //单击监听
        tblCurve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                click_tblCurve("单击tblCurve调用tblCurveClicked");
            }
        });

        // 测试列表单击事件，实际调用鼠标释放事件
        tblTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    click_tblTest("单击tblTest调用tblTestClicked");
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
                    updateUI_TestData("表头全选调用");

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

        updateUI_ListOrder(null,null);
        this.add(panelOrder);
    }


    //单击查询按钮时执行的代码
    private void click_btnQuery() {
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
        click_tblExperiment("查询按钮调用");

        //后续处理
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }   // END : private void click_btnQuery()


    /** 点击删除按钮后执行的代码
     * 1 校验是否允许删除
     * 2 取消自动提交
     * 3 取得testID、concentrationID、curveID
     * 4 逻辑删除Test、Test_Original
     * 5 重新计算concentration。如果该浓度ID还有对应的testID数据，则计算平均值，否则逻辑删除该浓度ID数据并设置为0
     * 6 提交事务
     * 6 调用曲线列表点击事件，刷新表格数据和统计图
     */
    private void click_btnDel() {
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

                // 4 逻辑删除Test、Test_Original
                String sqlDeleteTest, sqlDeleteTestOriginal;
                sqlDeleteTest = "UPDATE test SET isDeleted = 'Y' WHERE isDeleted = 'N' AND testID = ?;";
                sqlDeleteTestOriginal = "UPDATE test_original SET isDeleted = 'Y' WHERE isDeleted = 'N' AND testID = ?;";
                PreparedStatement pstmt1 = conn.prepareStatement(sqlDeleteTest);
                PreparedStatement pstmt2 = conn.prepareStatement(sqlDeleteTestOriginal);
                pstmt1.setString(1, testID);
                pstmt2.setString(1, testID);
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                pstmt1.close();
                pstmt2.close();

                // 5 重新计算concentration。如果该浓度ID还有对应的testID数据，则计算平均值，否则逻辑删除该浓度ID数据并设置为0
                updateDB_Concentration(concentrationID,"删除调用");

                // 6 提交事务
                conn.commit();

                // 7 调用曲线列表点击事件，刷新表格数据和统计图
                click_tblCurve("单击删除按钮调用");

            } catch (SQLException e) {
                db.dbRollback();
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
                JOptionPane.showMessageDialog(null, "创建失败，错误类型SQLException，数据库未作任何修改。请查看日志。");
            } catch (Exception e) {
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
                JOptionPane.showMessageDialog(null,e.getClass().getSimpleName() );
            }
        }   // END : if else
    }   // END : private void click_btnDel()


    /**
     * 1 从设备获取采集的数据
     * 2 根据实验ID，获得 取峰算法ID、TC公式ID、峰的左右边界及取数
     *   2.1 计算X1\X2的峰值
     *   2.2 计算TC值
     * 3 获取曲线序号+浓度序号，以“实验ID+曲线序号”作为curveID的唯一识别条件，查找“未锁定”的curvID
     *   3.1 如果没有curveID：【插入】curve表，获取curveID
     *   3.2 如果有curveID，没有锁定：向下走，第4步
     *     4 按照“curveID+浓度序号”查找concentrationID
     *       4.1 如果没有concentrationID：【插入】浓度表，获取concentrationID，数据先空着。
     *       4.2 如果有concentrationID，往下进行第5步
     *         5 根据第2步的数值、第4步的concentrationID，【插入】Test表，获得TestID
     *         6 【更新】浓度表，计算TC值的平均值，写入浓度表，调用 updateDB_Concentration()
     *         7 插入原始数据与test_original表
     *         8 提交事务
     */
    private void click_btnStartTest() {

        // 1 从设备获取采集的数据
        int i = RandomUtils.nextInt(0,Const.testData.length);
        TestDataClass testData = Const.testData[i];
//        JOptionPane.showMessageDialog(null,i);

        String inputTestID;

//             * 2 根据实验ID，获得 取峰算法ID、TC公式ID、峰的左右边界及取数
//                *   2.1 计算X1\X2的峰值
//                *   2.2 计算TC值
//                * 3 获取曲线序号+浓度序号，以“实验ID+曲线序号”作为curveID的唯一识别条件，查找“未锁定”的curvID
//                *   3.1 如果没有curveID：【插入】curve表，获取curveID
//                *   3.2 如果有curveID，没有锁定：向下走，第4步
//                *     4 按照“curveID+浓度序号”查找concentrationID
//                *       4.1 如果没有concentrationID：【插入】浓度表，获取concentrationID，数据先空着。
//                        4.2 如果有concentrationID，往下进行第5步
//                *         5 根据第2步的数值、第4步的concentrationID，【插入】Test表，获得TestID
//                *         6 【更新】浓度表，计算TC值的平均值，写入浓度表，调用 updateDB_Concentration()
//                *         7 插入原始数据与test_original表
//                *         8 提交事务

    }


    // 点击某行实验数据，右侧展示对应的曲线列表数据。如果没有曲线数据，则清空
    private void click_tblExperiment(String msg) {
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

        click_tblCurve("单击tblExperiment调用");
    }   // END : private void click_tblExperiment()


    // 点击曲线某行数据，则在右侧测试数据列表展示对应数据
    private void click_tblCurve(String msg) {
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
            dmTest = TableMethod.getDMwithCheck(sql);
            tblTest.setModel(dmTest);
        } else {
            // 查不到实验数据时，测试列表
            dmTest = TableMethod.getTableModel(sqlSelectTest + " WHERE 0=1");
            tblTest.setModel(dmTest);
        }
        //全选测试列表。如果没有数据，不影响
        selectAll(tblTest);
        updateUI_TestData("曲线列表点击调用");
        panelChart.updateChart(tblTestData,projectID);

    }


    // JTable多选时，复选框打钩的功能
    private void click_tblTest(String msg) {
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

        updateUI_TestData(msg + " + \ntblTestClicked内部调用updateTestData");
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
    private void updateUI_TestData(String msg) {
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
            dmTestData = TableMethod.getDMforTestData(testID);
            tblTestData.setModel(dmTestData);
        } else {
            dmTestData = TableMethod.getDMforTestData("0");
            tblTestData.setModel(dmTestData);
        }

    }


    //更新列表框
    private void updateUI_ListOrder(int[] curve, int[]concentration) {
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


    /**
     * 更新浓度表，有test数据时重新求平均值，无test数据时逻辑删除，
     * @param concentrationID   要更新的浓度数据的ID
     * @param msg 记录调用来源
     * @throws SQLException     抛出SQLException，以便btnDelClicked()调用时，使所有更改保持在同一事务
     */
    private void updateDB_Concentration(String concentrationID, String msg)
            throws SQLException {

        String sqlSelectCount = "SELECT count(concentrationID) FROM test WHERE isDeleted = 'N' AND concentrationID = ?" ;
        Boolean isExist = db.isExistRecord(sqlSelectCount, concentrationID);
        PreparedStatement pstmt;
        if (isExist) {
            //如果该浓度ID还有对应的testID数据，则计算TC平均值，
            String averX1,averX2,averTC;
            String sqlSelectAver = "SELECT cast(avg(tcValue) as decimal(11,4)) \n" +
                    "FROM test WHERE isDeleted = 'N' AND concentrationID = ?";

            pstmt = conn.prepareStatement(sqlSelectAver);
            pstmt.setString(1,concentrationID);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            averTC = rs.getString(1);

            String sqlUpdate = "UPDATE concentration SET tcAverValue = ? WHERE isDeleted = 'N' AND concentrationID = ?";
            pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setString(1,averTC);
            pstmt.setString(2, concentrationID);
            pstmt.executeUpdate();
        } else {
            //否则逻辑删除该数据并设置为0
            String sqlUpdate = "UPDATE concentration SET tcAverValue = 0,isDeleted = 'Y' WHERE concentrationID = ?";
            pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setString(1, concentrationID);
            pstmt.executeUpdate();

            //
        }




    }


}