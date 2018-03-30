package com.dhyx.panel;


import com.dhyx.dbclass.MyDatabase;
import com.dhyx.dbclass.ProjectClass;
import com.dhyx.myclass.*;
import com.dhyx.MainApp;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Date;

public class PanelTest extends JPanel {
    private Logger logger = LogManager.getLogger();
    private PanelQuery panelQuery;
    private JPanel panelOrder = new JPanel();
    private JLabel lblExperiment, lblCurve, lblTest, lblTestData;
    private MyIconButton btnQuery, btnDel, btnStartTest;
    private MyTable tblExperiment, tblCurve, tblTest, tblTestData;
    private DefaultTableModel dmExperiment, dmCurve, dmTest, dmTestData;
    private String sqlSelectExp = "SELECT DISTINCT 项目名称,实验ID,实验名称,实验创建日期,项目ID  FROM view_pro_exp ";
    private String sqlSelectCurve = "SELECT DISTINCT 曲线序号,曲线ID,锁定 FROM view_project_exp_curve ";
    private String sqlSelectTest = "SELECT DISTINCT 浓度序号,X1,X2,TC值,实验ID,曲线ID,浓度ID,测试ID,测试时间  FROM view_exp_curve_test ";
    private String experimentID, curveID,concentrationID, testID,isLocked="Y";
    private static int TABLE_WIDHT_325 = 325;
    private static int TABLE_HEIGHT_275 = 275,TABLE_HEIGHT_250 = 250;
    private MyChart panelChart;
    private MyList listCurveOrder, listConcentrationOrder;
    private MyDatabase db = MainApp.myDB;
    private Connection conn = MainApp.myDB.conn;
    private ProjectClass pro = new ProjectClass();



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
        btnQuery.setBounds(360-10, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
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
        btnDel.setLocation(btnQuery.getX() + btnQuery.getWidth() + 60, 0);
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
        btnStartTest = new MyIconButton(Const.ICON_TEST, Const.ICON_TEST_ENABLED, Const.ICON_TEST_DISABLED);
        btnStartTest.setLocation(880,0);
        btnStartTest.setEnabled(false);
        btnStartTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnStartTest.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：测试管理-开始测试");
                    click_btnStartTest();
                }
            }
        });
        panelQuery.add(btnStartTest);
    }   // END : private void initButton()


    //初始化4个标签
    private void initLabel() {
        int standardY = panelQuery.getY() + panelQuery.getHeight();
        lblExperiment = new JLabel("实验列表");
        lblExperiment.setBounds(0, standardY, TABLE_WIDHT_325, 25);

        lblCurve = new JLabel("曲线列表");
        lblCurve.setBounds(btnQuery.getX(), standardY, 120, 25);

        lblTest = new JLabel("测试记录");
        lblTest.setBounds(lblCurve.getX() + lblCurve.getWidth() + 30, standardY, 360, 25);

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
        panelChart.setBounds(tblCurve.getX(), tblTestData.getY(), 650, TABLE_HEIGHT_275);
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
        tblCurve.setBounds(lblCurve.getX(), standardY, 120+5, TABLE_HEIGHT_250);
        tblTest.setBounds(lblTest.getX(), standardY, 360, TABLE_HEIGHT_250);
        tblTestData.setBounds(lblTestData.getX(), lblTestData.getY() + lblTestData.getHeight(), TABLE_WIDHT_325, TABLE_HEIGHT_275);

        // 2.3 表格列宽
        tblExperiment.setWidth(70, 65, 90, 85,10);
        tblCurve.setWidth(54, 42, 30);
        tblTest.setWidth(30, 55, 70, 70, 70, 70, 70, 70, 70, 70);
        tblTestData.setAutoCreateColumnsFromModel(true);

        // 2.4 设置滚动面板
        tblExperiment.j.setBounds(tblExperiment.getBounds());
        tblCurve.j.setBounds(tblCurve.getBounds());
        tblTest.j.setBounds(tblTest.getBounds());
        tblTestData.j.setBounds(tblTestData.getBounds());

        tblExperiment.j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblCurve.j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(tblExperiment.j);
        this.add(tblCurve.j);
        this.add(tblTest.j);
        this.add(tblTestData.j);
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
                    click_tblTest("单击tblTest调用");
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
        int width =55;

        JLabel lblCurveOrder = new JLabel("曲线序号");
        JLabel lblConentrationOrder = new JLabel("浓度序号");
        //面板内部的坐标
        lblCurveOrder.setBounds(0,0, width,25);
        lblConentrationOrder.setBounds(65,0, width, 25);
        lblCurveOrder.setHorizontalAlignment(JLabel.CENTER);
        lblConentrationOrder.setHorizontalAlignment(JLabel.CENTER);

        listCurveOrder = new MyList(1,10);
        listConcentrationOrder = new MyList(1,50);

        listCurveOrder.setBounds(lblCurveOrder.getX(),25, width,TABLE_HEIGHT_250);
        listConcentrationOrder.setBounds(lblConentrationOrder.getX(),25, width,TABLE_HEIGHT_250);

        listCurveOrder.j.setBounds(listCurveOrder.getBounds());
        listConcentrationOrder.j.setBounds(listConcentrationOrder.getBounds());

        listCurveOrder.addListSelectionListener(e -> selectionChangeLister());

        listConcentrationOrder.addListSelectionListener(e -> selectionChangeLister());


        panelOrder.setLayout(null);
        panelOrder.setBounds(880, 40, 120, TABLE_HEIGHT_250 + 25);
        panelOrder.setOpaque(false);
        panelOrder.add(lblCurveOrder);
        panelOrder.add(lblConentrationOrder);
        panelOrder.add(listCurveOrder.j);
        panelOrder.add(listConcentrationOrder.j);
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
        tblExperiment.setFirstRow();
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
        // 1 校验是否允许删除:
        int[] nowRows = tblTest.getSelectedRows();

        // 1.1 只能单选一行
        if (nowRows.length != 1) {
            // 选中行数不等于1行，给出提示，结束。
            JOptionPane.showMessageDialog(null, "请单选1行，然后点击删除按钮。");

        // 1.2 曲线没有被锁。此校验在曲线列表的点击事件已校验过一次，当点击了已锁定曲线时，删除按钮不可用
        } else if (!"N".equals(isLocked)) {
            JOptionPane.showMessageDialog(null, "曲线已被锁定，不能删除记录。");
        } else {
            try {
                // 2 取消自动提交
                conn.setAutoCommit(false);

                // 3 取得testID、concentrationID
                DefaultTableModel currentDM = ((DefaultTableModel) tblTest.getModel());
                testID = tblTest.getValueAt(nowRows[0], currentDM.findColumn("测试ID")).toString();
                concentrationID = tblTest.getValueAt(nowRows[0], currentDM.findColumn("浓度ID")).toString();

                // 4 逻辑删除Test、Test_Original
                String sqlDeleteTest= "UPDATE test SET isDeleted = 'Y' WHERE isDeleted = 'N' AND testID = ?;";
                String sqlDeleteTestOriginal = "UPDATE test_original SET isDeleted = 'Y' WHERE isDeleted = 'N' AND testID = ?;";
                db.pstmtUpdateNotCommit(false,sqlDeleteTest, testID);
                db.pstmtUpdateNotCommit(false,sqlDeleteTestOriginal, testID);

                // 5 重新计算concentration。如果该浓度ID还有对应的testID数据，则计算平均值，否则逻辑删除该浓度ID数据并设置为0
                updateDB_Concentration(concentrationID,"删除调用");

                // 6 提交事务
                conn.commit();

                // 7 调用曲线列表点击事件，刷新表格数据和统计图
                click_tblCurve("单击删除按钮调用");

            } catch (Exception e) {
                try {
                    conn.rollback();
                    conn.commit();
                } catch (Exception e1) {
                    logger.error(e1.getClass().getSimpleName() + "，回滚失败。" + e.getMessage());
                }
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
                JOptionPane.showMessageDialog(null, "创建失败，数据库未作任何修改。请查看日志。");
            }
        }   // END : if else
    }   // END : private void click_btnDel()


    /**
     * 1 从设备获取采集的数据
     * 2 根据实验ID，获得 取峰算法ID、TC公式ID、峰的左右边界及取数
     *   2.1 计算X1\X2的峰值
     *   2.2 计算TC值
     * 3 获取列表框的曲线序号+浓度序号。
     * 4 以“实验ID+曲线序号”作为curveID的唯一识别条件，查找“未锁定”的curveID
     *   4.1 如果没有curveID：【插入】curve表，获取curveID
     *   4.2 如果有curveID，已锁定，给出提示并结束。此代码不会被执行，因为isLocked=Y时，测试按钮已不可用
     *   5 取得curveID,按照“curveID+浓度序号”查找concentrationID
     *     5.1 如果没有concentrationID：【插入】浓度表，获取concentrationID，数据先空着。
     *     5.2 如果有concentrationID，往下进行第6步
     *       6 根据第2步的数值、第4步的concentrationID，【插入】Test表，获得TestID
     *       7 【更新】浓度表，计算TC值的平均值，写入浓度表，调用 updateDB_Concentration()
     *       8 插入原始数据与test_original表
     *       9 提交事务
     */
    private void click_btnStartTest() {

        // 1 从设备获取采集的数据
        int i = RandomUtils.nextInt(0,Const.testData.length);
        TestDataClass testData = Const.testData[i];
        testData.setProject(pro);

        // 2 根据实验ID，获得 取峰算法ID、TC公式ID、峰的左右边界及取数
        // 此环节已在click_tblExperiment()完成，写入实例pro

        // 2.1 计算X1\X2的峰值
        float x1Value = testData.getX1Value();
        float x2Value = testData.getX2Value();

        // 2.2 计算TC值
        float tcValue = testData.getTCValue();

        // 3 获取列表框的曲线序号+浓度序号。
        String curveOrder = listCurveOrder.getSelectedValue();
        String concentrationOrder = listConcentrationOrder.getSelectedValue();

        // 4 以“实验ID+曲线序号”作为curveID的唯一识别条件，查找“未锁定”的curveID
        String sqlSelectCurve = "SELECT DISTINCT `曲线ID` ,`锁定` FROM view_project_exp_curve " +
                "WHERE `实验ID` = " + experimentID + " AND  `曲线序号` = " + curveOrder;
        try {
            String newCurveID, newIsLocked,newConcentrationID,newTestID;
            conn.setAutoCommit(false);
            ResultSet rs = conn.createStatement().executeQuery(sqlSelectCurve);

            // 4.1 如果没有curveID：【插入】curve表，获取curveID,
            if (!rs.next()) {
                String createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                String sqlInsertCurve = "INSERT INTO `curve` (isDeleted , createDate , isLocked , experimentID , curveOrder , tcTypeID ) " +
                        " VALUES ( 'N',?,'N',?,?,?);";
                String[] paramCurve = {createDate, experimentID, curveOrder, pro.tcTypeID};
                newCurveID = db.pstmtUpdateNotCommit(true,sqlInsertCurve, paramCurve);
                newIsLocked = "N";
            } else {
                newCurveID = rs.getString(1);
                newIsLocked = rs.getString(2);
            }
            rs.close();

            // 4.2 如果有curveID，已锁定，给出提示并结束。此代码不会被执行，因为isLocked=Y时，测试按钮已不可用
            if ("Y".equals(newIsLocked)) {
                JOptionPane.showMessageDialog(null, "曲线已锁定，不能测试，请选择其他曲线序号。");
            } else {
                // 5 取得curveID,按照“curveID+浓度序号”查找concentrationID
                String sqlSelectConcentration = "SELECT DISTINCT `浓度ID` FROM view_curve_concentration " +
                        "WHERE `曲线ID` = " + newCurveID + " AND `浓度序号` = " + concentrationOrder;
                rs = conn.createStatement().executeQuery(sqlSelectConcentration);

                //5.1 如果没有concentrationID：【插入】浓度表，获取concentrationID，数据先空着。
                if (!rs.next()) {
                    String createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                    String sqlInsertConcentration =
                            "INSERT INTO concentration (isDeleted , createDate , curveID , concentrationOrder , tcAverValue ) " +
                                    " VALUES ( 'N',?,?,?,0);";
                    String[] paramConcentration = {createDate, newCurveID, concentrationOrder};

                    newConcentrationID = db.pstmtUpdateNotCommit(true,sqlInsertConcentration, paramConcentration);
                } else {
                    // 5.2 如果有concentrationID，往下进行第6步
                    newConcentrationID = rs.getString(1);
                }
                rs.close();

                // 6 根据第2步的数值、第5步的concentrationID，【插入】Test表，获得TestID
                String createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                String sqlInsertTest = "INSERT INTO `test`" +
                        "(isDeleted , createDate , modifyDate , concentrationID , X1Value , X2Value , tcValue )" +
                        " VALUES ( 'N',?,?,?,?,?,?);";
                String[] paramTest = {createDate, createDate, newConcentrationID,
                        String.valueOf(x1Value), String.valueOf(x2Value), String.valueOf(tcValue)};

                newTestID = db.pstmtUpdateNotCommit(true,sqlInsertTest,paramTest);

                // 7 【更新】浓度表，计算TC值的平均值，写入浓度表，调用 updateDB_Concentration()
                updateDB_Concentration(newConcentrationID,"开始测试调用");

                // 8 插入原始数据test_original表
                updateDB_TestOriginal(newTestID,testData);

                // 9 提交事务
                conn.commit();

                // 10 收尾，执行查询后，浓度序号会+1，所以此处-1。
                click_btnQuery();
//                int order = NumberUtils.toInt(listConcentrationOrder.getSelectedValue()) - 1;
//                listConcentrationOrder.setSelectedByValue(String.valueOf(order));

            }
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.commit();
            } catch (Exception e1) {
                logger.error(e1.getClass().getSimpleName() + "，回滚失败。" + e.getMessage());
            }

            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            JOptionPane.showMessageDialog(null, "操作失败，未能更新。请查看日志。\n" + e.getMessage());
        }
    }




    // 点击某行实验数据，右侧展示对应的曲线列表数据。如果没有曲线数据，则清空
    private void click_tblExperiment(String msg) {
        btnDel.setEnabled(false);
        if (tblExperiment.getRowCount() > 0) {
            //getRowCount大于0 ，表示列表里有数据
            int nowRow = tblExperiment.getSelectedRow();
            if (nowRow != -1) {
                DefaultTableModel currentDM = (DefaultTableModel) tblExperiment.getModel();
                String proID = tblExperiment.getValueAt(nowRow, currentDM.findColumn("项目ID")).toString();
                pro.setData(proID);

                experimentID = tblExperiment.getValueAt(nowRow, currentDM.findColumn("实验ID")).toString();
                String sql = sqlSelectCurve + "WHERE `实验ID` = " + experimentID + " ORDER BY `曲线ID`";

                dmCurve = TableMethod.getTableModel(sql);
                tblCurve.setModel(dmCurve);
                tblCurve.setLastRow();
            }
        } else {
            // 查不到实验数据时，清空曲线列表
            dmCurve = TableMethod.getTableModel(sqlSelectCurve + " WHERE 0=1");
            tblCurve.setModel(dmCurve);
        }
//        int max = tblCurve.getMaxValueByColumn("曲线序号");
//        listCurveOrder.setSelectedByValue(String.valueOf(max + 1));
        listCurveOrder.setSelectedByValue("1");

        click_tblCurve("单击tblExperiment调用");
    }   // END : private void click_tblExperiment()


    // 点击曲线某行数据，则在右侧测试数据列表展示对应数据
    private void click_tblCurve(String msg) {
        if (tblExperiment.getRowCount() > 0) {
            int nowRow = tblCurve.getSelectedRow();
            if (nowRow != -1) {
                DefaultTableModel currentDM = (DefaultTableModel) tblCurve.getModel();
                curveID = tblCurve.getValueAt(nowRow, currentDM.findColumn("曲线ID")).toString();
                isLocked = tblCurve.getValueAt(nowRow, currentDM.findColumn("锁定")).toString();
                dmTest = TableMethod.getDMwithCheck(sqlSelectTest + "WHERE `曲线ID` = '" + curveID + "' ORDER BY `测试ID`");
            } else{
                dmTest = TableMethod.getTableModel(sqlSelectTest + " WHERE 0=1");
            }

            if (!isLocked.equals("N")) {
                btnDel.setEnabled(false);
            }
            tblTest.setModel(dmTest);
        } else {
            // 查不到实验数据时，测试列表
            dmTest = TableMethod.getTableModel(sqlSelectTest + " WHERE 0=1");
            tblTest.setModel(dmTest);
            btnStartTest.setEnabled(false);
        }

        //找到当前实验的曲线序号最大值，加1，然后在列表框表里选中
//        int max1 = tblCurve.getMaxValueByColumn("曲线序号");
//        listCurveOrder.setSelectedByValue(String.valueOf(max1 + 1));
        if (tblTest.getRowCount() == 0) {
            listConcentrationOrder.setSelectedByValue("1");
        } else {
            int max2 = tblTest.getMaxValueByColumn("浓度序号");
            if (max2 > listConcentrationOrder.getMaxSelectionIndex()) {
                max2 = listConcentrationOrder.getMaxSelectionIndex();
            }
            listConcentrationOrder.setSelectedByValue(String.valueOf(max2 + 1));
        }

        //全选测试列表。如果没有列表为空，不影响
        selectAll(tblTest);
        updateUI_TestData("曲线列表点击调用");
        panelChart.updateChart(tblTestData, pro.projectID);

    }


    // 测试记录的单击事件
    private void click_tblTest(String msg) {
        //获取所有被选中的行
        int[] nowRows = tblTest.getSelectedRows();

        //多选时，复选框打钩的功能
        for (int i = 0; i < tblTest.getRowCount(); i++) {
            if (ArrayUtils.contains(nowRows, i)) {
                tblTest.setValueAt(true, i, 0);
            } else {
                tblTest.setValueAt(false, i, 0);
            }
        }

        //如果曲线已锁，删除按钮不可用
        btnDel.setEnabled(false);
        if ("N".equals(isLocked) && nowRows.length == 1) {
            btnDel.setEnabled(true);
        }

        updateUI_TestData(msg + " + \ntblTestClicked内部调用updateTestData");
        panelChart.updateChart(tblTestData, pro.projectID);
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
            if ((myTable.getSelectedRows().length == 1) && (isLocked.equals("N"))){
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


    /**
     * 更新浓度表，有test数据时重新求平均值，无test数据时逻辑删除
     * @param concentrationID 要更新的浓度数据的ID
     * @param msg             记录调用来源
     * @throws SQLException 抛出SQLException，以便btnDelClicked()调用时，使所有更改保持在同一事务
     */
    private void updateDB_Concentration(String concentrationID, String msg) throws SQLException {
        String sqlUpdateConcentration;
        String[] paramConcentration;

        String sqlSelectCount = "SELECT count(concentrationID) FROM test WHERE isDeleted = 'N' AND concentrationID = ?";
        Boolean isExist = db.isExistByCount(sqlSelectCount, concentrationID);

        if (isExist) {
            //如果该浓度ID还有对应的testID数据，则计算TC平均值，
            String averTC = "0";
            String sqlSelectAver = "SELECT cast(avg(tcValue) as decimal(11,4)) FROM test WHERE isDeleted = 'N' AND concentrationID = "+concentrationID;
            ResultSet rs = conn.createStatement().executeQuery(sqlSelectAver);

            if (rs.next()) {
                averTC = rs.getString(1);
            }
            sqlUpdateConcentration = "UPDATE concentration SET tcAverValue = ? WHERE isDeleted = 'N' AND concentrationID = ?";
            paramConcentration = new String[]{averTC, concentrationID};
        } else {
            //否则逻辑删除该数据并设置为0
            sqlUpdateConcentration = "UPDATE concentration SET tcAverValue = 0,isDeleted = 'Y' WHERE concentrationID = ?";
            paramConcentration = new String[]{concentrationID};
        }
        db.pstmtUpdateNotCommit(false,sqlUpdateConcentration, paramConcentration);
    }



    private void updateDB_TestOriginal(String testID, TestDataClass td) throws SQLException {
        String createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        String sqlInsert = "INSERT INTO test_original (isDeleted , createDate , modifyDate ,testID,x,y ) " +
                " VALUES ( 'N',?,?,?,?,?);";
        for (int i = 0; i < td.length; i++) {
            String[] param = {createDate, createDate, testID, String.valueOf(td.x[i]), String.valueOf(td.y[i])};
            db.pstmtUpdateNotCommit(false,sqlInsert, param);
        }
    }


    //列表框每次变更时，判断该曲线的浓度是否可用
    private void selectionChangeLister() {
        String myCurveOrder, myConcentrationOrder;
        myCurveOrder = listCurveOrder.getSelectedValue();
        myConcentrationOrder = listConcentrationOrder.getPrototypeCellValue();
//        String selectCurve = "SELECT COUNT(curveID) FROM curve WHERE experimentID = ? AND curveOrder = ? ";
        String selectCurve = "SELECT DISTINCT curveID FROM curve WHERE experimentID = "+experimentID
                +" AND curveOrder =  " +myCurveOrder;
        String[] paraCurve = {experimentID, myCurveOrder};
//        boolean isCurve = db.isExistByCount(selectCurve, paraCurve);
        ;
        try {
            btnStartTest.setEnabled(false);
            ResultSet rs = conn.createStatement().executeQuery(selectCurve);
            if (!rs.next()) {
                btnStartTest.setEnabled(true);
            }else{
                String myCurveID = rs.getString(1);
                String selectCurveID = "SELECT DISTINCT isLocked FROM curve WHERE isDeleted='N' AND curveID =  " + myCurveID ;
                ResultSet rs2 = conn.createStatement().executeQuery(selectCurveID);
                if (rs2.next()) {
                    String lock = rs2.getString(1);
                    if (lock.equals("N")) {
                        btnStartTest.setEnabled(true);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



//
//        if (isCurve == false) {
//            //如果不存在曲线，测试按钮可用
//            btnStartTest.setEnabled(true);
//        } else {
//            String selectCurveLock = "SELECT COUNT(isLocked) FROM curve WHERE "
//
//            btnStartTest.setEnabled(false);
//        }


    }
}