package com.dhyx.panel;

import com.dhyx.MainApp;
import com.dhyx.myclass.MyDatabase;
import com.dhyx.myclass.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;


public class PanelCurveNew extends JPanel {
    private Logger logger = LogManager.getLogger();
    private MyDatabase db = MainApp.myDB;
    private PanelQuery panelQuery;
    private MyIconButton btnQuery, btnDel, btnFit;
    private DefaultTableModel dmCurve, dmConcentration;
    private String sqlSelectCurve = "SELECT DISTINCT 项目名称,实验名称,曲线序号,创建日期,曲线ID,实验ID,项目ID FROM view_project_exp_curve";
    private String sqlSelectConcentration = "SELECT DISTINCT 浓度序号,浓度值,反应值,浓度ID FROM view_curve_concentration";
    private String curveID;
    private MyTable tblCurve, tblConcentration;
    private MyList lstXLog, lstYLog;
    private JLabel[] lRight = new JLabel[7];
    private JLabel lblImage = new JLabel();
    private JSpinner jSpinner = new JSpinner();
    private int xLog, yLog;
    private double blank;
    private String msg;


    public PanelCurveNew() {

        initSelf();
        initQueryPanel();
        initButton();
        initLabel();
        initTableGetModel();
        initTableCreate();
        initTableAddListener();
        initList();
        initOther();
        clickBtnQuery("启动窗体调用，PanelCurveNew() ");
        clickTblCurve("启动窗体调用，PanelCurveNew() ");
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
        panelQuery.txtQuery.setToolTipText("请输入实验名称。");
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
        btnQuery.setBounds(435, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);
    }   // END : private void initQueryPanel()


    /**
     * 初始化按钮
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

        //"删除选中曲线"按钮
        btnDel = new MyIconButton(Const.ICON_CURVE_DEL, Const.ICON_CURVE_DEL_ENABLED, Const.ICON_CURVE_DEL_DISABLED);
        btnDel.setBounds(625, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    int answer = JOptionPane.showConfirmDialog(null, "确认要删除该曲线及其所有数据吗？", "删除确认", JOptionPane.YES_NO_OPTION);
                    if (answer == JOptionPane.YES_OPTION) {
                        boolean status = clickBtnDel();
                        logger.trace("点击按钮：生成曲线-删除选中曲线。曲线ID=" + curveID + "，结果=" + status);
                        clickBtnQuery("点击删除按钮后调用");
                    }

                }
            }
        });
        panelQuery.add(btnDel);

        //"查看所有拟合"按钮
        btnFit = new MyIconButton(Const.ICON_FIT, Const.ICON_FIT_ENABLED, Const.ICON_FIT_DISABLED);
        btnFit.setBounds(840, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        btnFit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnFit.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：生成曲线-查看所有拟合");
                    clickBtnFit();
                }
            }
        });
        panelQuery.add(btnFit);
    }   // END : private void initButton()


    /**
     * 初始化标签
     */
    private void initLabel() {
        JLabel lblCurve = new JLabel("曲线列表");
        JLabel lblConcentration = new JLabel("所选曲线的浓度(已求平均)");
        JLabel lblFitType = new JLabel("取对数(仅对直线拟合有效)");
        JLabel lblFitTitle = new JLabel("拟合结果");
        JLabel lblX = new JLabel("X对数：");
        JLabel lblY = new JLabel("X对数：");
        JLabel lbl100 = new JLabel("%");
        JLabel lblBlank = new JLabel("扣Blank：");

        lblCurve.setBounds(0, 40, 385, 25);
        lblConcentration.setBounds(435, 40, 310, 25);
        lblFitType.setBounds(800, 145, 200, 25);
        lblFitTitle.setBounds(800, 305, 200, 25);
        lblX.setBounds(830, 180, 55, 20);
        lblY.setBounds(910, 180, 55, 20);
        lblImage.setBounds(800, 535, 200, 70);
        lblImage.setIcon(Const.ICON_FX);
        lbl100.setBounds(955, 83, 30, 20);
        lblBlank.setBounds(830, 80, 70, 25);

        JLabel[] lLeft = new JLabel[7];
        String[] title = {"方法", "a =", "b =", "c =", "d =", "e =", "R² ="};

        for (int i = 0; i < lLeft.length; i++) {
            lLeft[i] = new JLabel();
            lLeft[i].setText(title[i]);

            lLeft[i].setBounds(820, 350 + i * 25, 45, 25);
            lLeft[i].setHorizontalAlignment(JLabel.CENTER);
            this.add(lLeft[i]);

            lRight[i] = new JLabel();
            lRight[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
            lRight[i].setBounds(870, 350 + i * 25, 100, 25);
            lRight[i].setHorizontalAlignment(JLabel.CENTER);
            this.add(lRight[i]);
        }

        this.add(lblCurve);
        this.add(lblConcentration);
        this.add(lblFitType);
        this.add(lblFitTitle);
        this.add(lblX);
        this.add(lblY);
        this.add(lbl100);
        this.add(lblImage);
        this.add(lblBlank);
    }   // END : private void initLabel()


    /**
     * 1. 创建TableModel
     */
    private void initTableGetModel() {
        dmCurve = TableMethod.getTableModel(sqlSelectCurve + " WHERE 0=1");
        dmConcentration = TableMethod.getDMwithCheck(sqlSelectConcentration + " WHERE 0=1");
    }   // END : private void initTableGetModel()


    /**
     * 2.创建表格并设置属性
     */
    private void initTableCreate() {
        // 2.1 创建表格
        tblCurve = new MyTable(dmCurve);
        tblConcentration = new MyTable(dmConcentration) {
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
        tblCurve.setBounds(0, 65, 385, 550);
        tblConcentration.setBounds(435, 65, 315, 550);

        // 2.3 表格列宽
        //项目名称,实验名称,曲线序号,创建日期,曲线ID,实验ID,项目ID
        tblCurve.setWidth(70, 125, 60, 80, 50, 50, 50);
        //浓度序号,浓度值,反应值,浓度ID
        tblConcentration.setWidth(30, 75, 105, 105);

        // 2.4 设置滚动面板
        tblCurve.j.setBounds(tblCurve.getBounds());
        tblConcentration.j.setBounds(tblConcentration.getBounds());
        tblConcentration.j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //2.5 取得“浓度值”的index，然后对该列设置CellEditor，使其只能输入小数
        int index = dmConcentration.findColumn("浓度值");
        tblConcentration.getColumnModel().getColumn(index).setCellEditor(new FloatCellEditor());

        //2.6 添加弹出菜单
//        tblCurve.jPopupMenu.add(btnDel);

        // 2.7 添加表格到面板
        this.add(tblCurve.j);
        this.add(tblConcentration.j);
    }   // END : private void initTableCreate()


    /**
     * 3.添加表格的监听
     */
    private void initTableAddListener() {

        // 曲线列表的单击事件
        tblCurve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    clickTblCurve("监听：鼠标单击曲线列表");
                }
            }
        });

        // 浓度列表的单击事件
        tblConcentration.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    clickTblConcentration("监听：鼠标单击浓度列表");
                }
            }
        });

        //对浓度表的列“浓度值”添加监听，当编辑结束时，把手写的浓度值写入数据库
        int index = dmConcentration.findColumn("浓度值");
        tblConcentration.getColumnModel().getColumn(index).getCellEditor().addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                //此处退出编辑状态，如果数据符合要求，则写入数据库
                updateDbStopEdit("监听：editingStopped");
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
            }
        });


        //点击表头全选的单击事件
        tblConcentration.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tblConcentration.getTableHeader().columnAtPoint(e.getPoint());
                if (columnIndex == 0) {
                    selectAll(tblConcentration);
                }
            }
        });
    }   // END : private void initTableAddListener()


    /**
     * 初始化列表框
     */
    private void initList() {
        String[] value = {"不取对数", "log 10", "log e"};

        lstXLog = new MyList();
        lstYLog = new MyList();
        lstXLog.addString(value);
        lstYLog.addString(value);
        lstXLog.setBounds(820, 210, 80, 59);
        lstYLog.setBounds(900, 210, 80, 59);
        lstXLog.setBorder(BorderFactory.createEtchedBorder());
        lstYLog.setBorder(BorderFactory.createEtchedBorder());
        lstXLog.setSelectedIndex(1);
        lstYLog.setSelectedIndex(1);
        this.add(lstXLog);
        this.add(lstYLog);
    }   // END : private void initList()


    /**
     * 初始化其它组件
     */
    private void initOther() {
        //三个方框
        JPanel[] jp = new JPanel[3];
        Rectangle[] rectangle = new Rectangle[3];
        rectangle[0] = new Rectangle(800, 65, 200, 55);
        rectangle[1] = new Rectangle(800, 170, 200, 115);
        rectangle[2] = new Rectangle(800, 330, 200, 285);
        for (int i = 0; i < 3; i++) {
            jp[i] = new JPanel();
            jp[i].setOpaque(false);
            jp[i].setBounds(rectangle[i]);
            jp[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
            this.add(jp[i]);
        }

        jSpinner.setModel(new SpinnerNumberModel(100,1,100,10));
        jSpinner.setBounds(900,80,50,25);
        this.add(jSpinner);

    }   // END : private void initOther()


    /**
     * 查询按钮
     * @param msg 记录上一级的调用方
     */
    private void clickBtnQuery(String msg) {
        btnQuery.setEnabled(false);
        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String sql, strQueryKey;
        strQueryKey = panelQuery.txtQuery.getText().trim();
        strQueryKey = StringUtils.replace(strQueryKey, "'", "");
        strQueryKey = StringUtils.replace(strQueryKey, "%", "");
        sql = sqlSelectCurve + " WHERE `实验名称` LIKE '%" + strQueryKey + "%' AND `锁定`='N' ORDER BY `项目ID`,`实验ID`,`曲线序号`";
        dmCurve = TableMethod.getTableModel(sql);
        tblCurve.setModel(dmCurve);
        tblCurve.setFirstRow();

        //后续处理
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }   // END : private void clickBtnQuery()


    /**
     * 删除曲线的代码
     * 取消自动提交
     * 逻辑删除曲线
     * 获取浓度ID集合，循环1
     * 获得1个浓度ID，逻辑删除该浓度
     * 根据该浓度查询所有testID集合，进入循环2
     * 获得1个testID，逻辑删除该test数据
     * 逻辑删除该testID对应的所有test_original
     * 结束循环2
     * 结束循环1
     * 提交
     */
    private boolean clickBtnDel() {
        boolean status = false;
        String modifyDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

        try {
            //取消自动提交
            db.conn.setAutoCommit(false);

            //逻辑删除曲线
            String sqlUpdateCurve = "UPDATE curve SET isDeleted = 'Y' WHERE curveID = ?";
            db.pstmtUpdateNotCommit(false, sqlUpdateCurve, curveID);

            //获取浓度ID集合，循环1
            String sqlSelectConcentration = "SELECT DISTINCT concentrationID FROM concentration WHERE curveID = " +
                    curveID + " AND isDeleted = 'N';";
            ResultSet rs1 = db.conn.createStatement().executeQuery(sqlSelectConcentration);

            while (rs1.next()) {
                // 获得1个浓度ID
                String delConcentrationID = rs1.getString("ConcentrationID");
                // 逻辑删除该浓度
                String sqlUpdateConcentration = "UPDATE concentration SET isDeleted = 'Y' WHERE concentrationID = ?";
                db.pstmtUpdateNotCommit(false, sqlUpdateConcentration, delConcentrationID);

                // 根据该浓度查询所有testID集合，进入循环2
                String sqlSelectTest = "SELECT DISTINCT testID FROM test WHERE concentrationID = " +
                        delConcentrationID + " AND isDeleted = 'N';";
                ResultSet rs2 = db.conn.createStatement().executeQuery(sqlSelectTest);
                while (rs2.next()) {

                    // 获得1个testID，逻辑删除该test数据。
                    String testID = rs2.getString("testID");
                    String sqlUpdateTest = "UPDATE test SET isDeleted = 'Y' ,modifyDate = ? WHERE testID = ?";

                    db.pstmtUpdateNotCommit(false, sqlUpdateTest, modifyDate, testID);

                    // 逻辑删除该testID对应的所有test_original
                    String sqlUpdateTestOriginal = "UPDATE test_original SET isDeleted = 'Y' , modifyDate = ? WHERE testID = ?";
                    db.pstmtUpdateNotCommit(false, sqlUpdateTestOriginal, modifyDate, testID);
                }   // END : rs2的循环
            }   // END : rs1的循环

            //提交

            db.conn.commit();
            status = true;
        } catch (SQLException e) {
            try {
                db.conn.rollback();
//                db.conn.commit();
            } catch (SQLException e1) {
                logger.error(e1.getClass().getSimpleName() + "，回滚失败。" + e.getMessage());
            }
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            JOptionPane.showMessageDialog(null, "删除失败，数据库未作任何修改。请查看日志。");
        }
        return status;
    }   // END : private void clickBtnDel()


    /**
     * 点击查看拟合按钮
     */
    private void clickBtnFit() {
        //获取被选中的行数，校验是否满足拟合数量要求
        int[] nowRows = tblConcentration.getSelectedRows();
        int minCount = 6;
        if (nowRows.length < minCount) {
            JOptionPane.showMessageDialog(null, "拟合至少需要" + minCount + "个数据，还需再选"
                    + String.valueOf(minCount - nowRows.length) + "个。", "缺少数据", JOptionPane.ERROR_MESSAGE);
        } else {
            //数据按浓度排序，写入数组
            DefaultTableModel dm = (DefaultTableModel) tblConcentration.getModel();
            int index1 = dm.findColumn("浓度值");
            int index2 = dm.findColumn("反应值");

            MyFitData data = new MyFitData(nowRows.length);
            int count0 = 0;
            for (int i = 0; i < nowRows.length; i++) {
                data.c[i] = NumberUtils.toDouble(tblConcentration.getValueAt(nowRows[i], index1).toString());
                if (data.c[i] == 0) {
                    count0++;
                }
                data.v[i] = NumberUtils.toDouble(tblConcentration.getValueAt(nowRows[i], index2).toString());
            }
            //从0开始
            xLog = lstXLog.getSelectedIndex();
            yLog = lstYLog.getSelectedIndex();
            // 校验浓度为0的个数，最多只有1个
            if (count0 > 1) {
                JOptionPane.showMessageDialog(null, "浓度值为0的数据最多只能有1个，当前有"
                        + count0 + "个，请检查。", "数据错误", JOptionPane.ERROR_MESSAGE);
            } else if (count0 == 1 || count0 == 0) {

                //----------------for test data
                double[] xArray = {0, 0.5, 1, 2, 4, 20, 100, 250, 500, 750, 1000};
                double[] yArray = {0.0679, 0.12955, 0.2518, 0.315, 0.4562, 1.2585, 5.996, 8.7685, 21.61, 24.23, 42.165};

                data.length = xArray.length;
                data.c = xArray;
                data.v = yArray;
                //------------------------------

                //选中 扣Blank
                double ratio = NumberUtils.toDouble(String.valueOf(jSpinner.getValue())) / 100;

                //处理blank数据
                if (data.removeBlank(ratio)) {
                    blank = data.blank;
                    PanelViewFit panelViewFit = new PanelViewFit(this, data, xLog, yLog);
                    panelViewFit.setVisible(true);
                } else {
                    blank = 0;
                    JOptionPane.showMessageDialog(null,"浓度为0的反应值不是最小值，请重新选择参与拟合的数据。");
                }
            }
        }
    }   // END : private void clickBtnFit()


    /**
     * 表格全选，并打钩
     * @param myTable 要设置此功能的表格实例
     */
    private void selectAll(MyTable myTable) {
        int rowCount = myTable.getRowCount();
        if (rowCount > 0) {
            myTable.setRowSelectionInterval(0, rowCount - 1);
            for (int i = 0; i < myTable.getRowCount(); i++) {
                myTable.setValueAt(true, i, 0);
            }
        }
    }   // END : private void selectAll()


    /**
     * 点击曲线表格时的操作
     * @param msg 记录上一级的调用方
     */
    private void clickTblCurve(String msg) {
        //设置DM默认值，如果符合if条件，则设置为正确的
        dmConcentration = TableMethod.getTableModel(sqlSelectConcentration + " WHERE 0=1");
        btnDel.setEnabled(false);
        btnFit.setEnabled(false);

        if (tblCurve.getRowCount() > 0) {
            int nowRow = tblCurve.getSelectedRow();
            if (nowRow > -1) {
                DefaultTableModel currentDM = (DefaultTableModel) tblCurve.getModel();
                curveID = tblCurve.getValueAt(nowRow, currentDM.findColumn("曲线ID")).toString();
                String sql = sqlSelectConcentration + " WHERE `曲线ID` = '" + curveID + "' AND `锁定`= 'N' ORDER BY `浓度序号`";
                dmConcentration = TableMethod.getDMwithCheck(sql);
                btnDel.setEnabled(true);
                btnFit.setEnabled(true);
            }
        }
        tblConcentration.setModel(dmConcentration);
    }   // END : private void clickTblCurve()


    /**
     * 点击浓度表格时的操作
     * @param msg 记录上一级的调用方
     */
    private void clickTblConcentration(String msg) {
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
    }   // END : private void clickTblConcentration()


    /**
     * 浓度表某个单元格的退出编辑状态时，将符合条件的浓度写入数据库，不符合的给出提示，不保存
     * @param msg  记录上一级的调用方
     */
    private void updateDbStopEdit(String msg) {
        this.msg = msg;
        int nowRow = tblConcentration.getSelectedRow();
        DefaultTableModel currentDM = (DefaultTableModel) tblConcentration.getModel();
        String concentrationID = tblConcentration.getValueAt(nowRow, currentDM.findColumn("浓度ID")).toString();
        String concentrationValue = tblConcentration.getValueAt(nowRow, currentDM.findColumn("浓度值")).toString();
        String sqlUpdate = "UPDATE concentration SET concentrationValue = ? WHERE concentrationID = ? ";
        String[] params = {concentrationValue, concentrationID};
        db.pstmtUpdateAndCommit(sqlUpdate, params);
    }   // END : private void updateDbStopEdit()


    /**
     * 用户查看所有拟合后，选定一个拟合，然后新面板调用本面板的receiveFitData()，接收数据。
     * @param fitResults 接收到的拟合结果
     */
    public void receiveFitData(Vector fitResults) {
        boolean status;
        //"序号","公式", "拟合方法", "a", "b", "c", "d", "e", "R²"
        if (fitResults != null) {

            //1 接收数据
            String fitMethod;
            String a, b, c, d, e, R2;

            fitMethod = String.valueOf(fitResults.get(0));
            lblImage.setIcon((Icon) fitResults.get(1));
            a = String.valueOf(fitResults.get(3));
            b = String.valueOf(fitResults.get(4));
            c = String.valueOf(fitResults.get(5));
            d = String.valueOf(fitResults.get(6));
            e = String.valueOf(fitResults.get(7));
            R2 = String.valueOf(fitResults.get(8));

            for (int i = 0; i < lRight.length; i++) {
                lRight[i].setText(String.valueOf(fitResults.get(i+2)));
            }

            //2 准备其他字段
            String strXLog, strYlog, strBlank;
            strXLog = String.valueOf(xLog);
            strYlog = String.valueOf(yLog);
            strBlank = String.valueOf(blank);
            String modifyDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            if ("1".equals(fitMethod)) {
                // 直线拟合，不需要cde
                c = null;
                d = null;
                e = null;
            } else if ("2".equals(fitMethod)) {
                // 四参数拟合，不需要e
                e = null;
            }

            //3 更新数据库
            String sqlUpdateCurve = "UPDATE curve SET modifyDate = ?, fitMethod = ?,xLog =? , yLog = ?, " +
                    "a= ?, b = ? , c = ? , d = ? , e = ? , R2 = ? , blank = ? WHERE curveID = ?;";
            String[] params = {modifyDate, fitMethod, strXLog, strYlog, a, b, c, d, e, R2, strBlank,curveID};
            status = db.pstmtUpdateAndCommit(sqlUpdateCurve, params);
        } else {
            status = false;
        }
        logger.trace("选中拟合方式，返回后写数据库，curveID = " + curveID + "，结果=" + status);
    }   // END : private void receiveFitData()


    /**
     * 内部类，仅在生成曲线模块，输入浓度时使用。
     * 用于限制JTable的某个单元格只能输入小数。用NumberUtils进行转化，如果不能转化为小数则设置为0
     */
    class FloatCellEditor extends DefaultCellEditor {
        FloatCellEditor() {
            super(new JTextField());
        }

        @Override
        public Object getCellEditorValue() {
            String value = (String) delegate.getCellEditorValue();
            String pattern = "0";
            float tmp = NumberUtils.toFloat(value);
            if (tmp != 0) {
                pattern = "0.0000";
            }
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(tmp);
        }
    }   // END : class FloatCellEditor extends DefaultCellEditor

}


