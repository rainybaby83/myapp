package dhyx.UI;

import dhyx.Class.Const;
import dhyx.Class.MyIconButton;
import dhyx.Class.MyList;
import dhyx.Class.MyTable;
import dhyx.MainApp;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.FILES_ONLY;

public class PanelProject extends JPanel {
    private Logger logger = LogManager.getLogger();
    private PanelQuery panelQuery;  //查询面板
    private JPanel subPanel;        //下部的面板
    private JLabel lblProject;      //一个标签，“项目列表”
    private JTextField[] text = new JTextField[14];
    private JLabel[] lable = new JLabel[14];
    private MyTable tblProject;
    private MyIconButton btnQuery, btnNew, btnEdit, btnSave, btnDel, btnExcel;
    private MyList lstPeak, lstTC;
    private String sqlSelectProject = "SELECT * FROM view_project ";
    private DefaultTableModel defaultTableModel;   //表格显示用的Model
    private int saveState = Const.SAVE_STATE_CANCEL;
    private String projectID, createDate, modifyDate, projectName, prepareDuration, peakTypeID, tcTypeID, subUnit, subMin, subMax;
    private int x1Left = 0, x2Left = 0, x1Right = 0, x2Right = 0, x1N = 0, x2N = 0;


    public PanelProject() {
        initSelf();
        initQueryPanel();
        initLabel();
        initTable();
        initSubPanel();
        initButton();
        btnQueryClicked();
        tblProjectClicked();
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

        panelQuery.txtQuery.setSize(275, Const.BUTTON_HEIGHT);
        panelQuery.txtQuery.setToolTipText("请输入项目名称关键字。支持模糊查询，不区分大小写");
        panelQuery.txtQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按回车键执行相应操作;
                if(e.getKeyChar()==KeyEvent.VK_ENTER )
                {
                    btnQueryClicked();
                }
            }
        });

        btnQuery = panelQuery.btnQuery;
        btnQuery.setBounds(390, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);
    }


    //初始化标签
    private void initLabel() {
        lblProject = new JLabel("项目列表");
        lblProject.setBounds(0, panelQuery.getY() + panelQuery.getHeight(), 850, 25);
        this.add(lblProject);
    }


    //初始化表格及滚动面板
    private void initTable() {
        //从全局变量Const.myDB获得DefaultTableModel，用于创建table
        defaultTableModel = MainApp.myDB.getTableModel(sqlSelectProject);
        tblProject = new MyTable(defaultTableModel);
        tblProject.setWidth(60, 80, 80, 130, 80, 110, 110, 70, 70, 70, 70, 70, 70, 70, 70, 70);
        tblProject.setBounds(0, lblProject.getY() + lblProject.getHeight(), 840, 325);
        tblProject.jScrollPane.setBounds(tblProject.getBounds());
        this.add(tblProject.jScrollPane);


        //添加监听，单击时选中某行，并在下方显示明细
        tblProject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tblProjectClicked();
            }
        });

    }  // END:initTalbe()


    //初始化按钮
    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-查询" + panelQuery.txtQuery.getText());
                    btnQueryClicked();
                }
            }
        });

        //新建按钮
        btnNew = new MyIconButton(Const.ICON_NEW, Const.ICON_NEW_ENABLED, Const.ICON_NEW_DISABLED);
        btnNew.setLocation(this.getWidth() - Const.BUTTON_WIDTH, tblProject.getY());
        btnNew.setEnabled(true);
        btnNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnNew.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-新建");
                    btnNewClicked();
                }
            }
        });

        //编辑按钮
        btnEdit = new MyIconButton(Const.ICON_EDIT, Const.ICON_EDIT_ENABLED, Const.ICON_EDIT_DISABLED);
        btnEdit.setLocation(btnNew.getX(), btnNew.getY() + btnNew.getHeight() + 30);
        btnEdit.setEnabled(false);
        btnEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnEdit.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-编辑");
                    btnEditClicked();
                }
            }
        });

        //保存按钮
        btnSave = new MyIconButton(Const.ICON_SAVE, Const.ICON_SAVE_ENABLED, Const.ICON_SAVE_DISABLED);
        btnSave.setLocation(btnNew.getX(), btnEdit.getY() + btnEdit.getHeight() + 30);
        btnSave.setEnabled(false);
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnSave.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-保存");
                    btnSaveClicked();
                }
            }
        });


        //删除按钮
        btnDel = new MyIconButton(Const.ICON_DEL, Const.ICON_DEL_ENABLED, Const.ICON_DEL_DISABLED);
        btnDel.setLocation(btnNew.getX(), btnSave.getY() + btnSave.getHeight() + 30);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-删除");
                    btnDelClicked();
                }
            }
        });

        //导出Excel按钮
        btnExcel = new MyIconButton(Const.ICON_EXCEL, Const.ICON_EXCEL_ENABLED, Const.ICON_EXCEL_DISABLED);
        btnExcel.setLocation(btnNew.getX(), btnDel.getY() + btnDel.getHeight() + 30);
        btnExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnExcel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-导出Excel");
                    btnExcelClicked();
                }
            }
        });

        //添加到面板
        this.add(btnNew);
        this.add(btnEdit);
        this.add(btnSave);
        this.add(btnDel);
        this.add(btnExcel);
    } // END:initButton()


    //初始化最下方的子面板，添加组件
    private void initSubPanel() {
        //创建面板
        subPanel = new JPanel();
        subPanel.setLayout(null);
        subPanel.setOpaque(false);
        subPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder
                (1, 1, 1, 1, Color.gray), "项目明细"));
        subPanel.setBounds(-2, tblProject.getY() + tblProject.getHeight() + 30, 844, 190);

        //初始化子面板的下拉框、标签、文本框、位置
        initSubPanelList();
        initSubPanelLabel();
        initSubPanelText();
        initSubPanelPosition();

        //清空并禁用所有输入组件
        resetSubPanel(true, false);
        this.add(subPanel);

    } // END:initSubPanel()


    //为下方的子面板初始化下拉框
    private void initSubPanelList() {
        //填充下拉框
        String sqlPeak = "SELECT peakTypeID, peakName FROM peakType WHERE isDeleted = 'N' ORDER BY peakTypeID;";
        String sqlTC = "SELECT tcTypeID, tcExpession FROM tcType WHERE isDeleted = 'N' ORDER BY tcTypeID;";
        lstPeak = new MyList(sqlPeak);
        lstPeak.setForeground(Color.blue);
        lstPeak.setVisible(false);

        lstTC = new MyList(sqlTC);
        lstTC.setForeground(Color.blue);
        lstTC.setVisible(false);

        subPanel.add(lstPeak);
        subPanel.add(lstTC);
    }   // END : private void initSubPanelList()


    //为子面板初始化标签
    private void initSubPanelLabel() {
        //创建标签、文本框，设置字体、位置、颜色等属性
        String[] title = {"项目ID", "项目名称", "取峰算法", "T C 公式", "准备时长", "浓度单位", "最小值", "最大值", "", "左边界", "右边界", "N", "X1", "X2"};
        for (int i = 0; i < lable.length; i++) {
            if (i < lable.length) {
                lable[i] = new JLabel(title[i]);
                lable[i].setHorizontalAlignment(JLabel.CENTER);
                subPanel.add(lable[i]);
            }   // END :if (i < lable.length)
        }
    }   // END : private void initSubPanelLabel()


    //为子面板初始化文本框
    private void initSubPanelText() {
        for (int i = 0; i < text.length; i++) {

            text[i] = new JTextField();
            text[i].setHorizontalAlignment(JTextField.CENTER);
            text[i].setBorder(BorderFactory.createMatteBorder
                    (0, 0, 1, 0, new Color(180, 180, 180)));
            //此处设置背景色，但组件透明。当某些验证不通过时，使组件不透明，显示颜色
            text[i].setBackground(Color.pink);
            text[i].setOpaque(false);
            text[i].setForeground(Color.blue);

            // 按需给文本框加监听
            // 1.所有失去焦点时去空格
            text[i].addFocusListener(Const.textTrimFocusListener());

            // 2.个别文本框只能输入整数。同时限制输入位数
            int[] arrayIntNo = {4, 7, 8, 9, 10, 11, 12, 13};
            if (ArrayUtils.contains(arrayIntNo, i)) {
                text[i].addFocusListener(Const.integerTextFocusListener());
                text[i].setToolTipText("只能输入正整数");
                if (i == 4 || i == 7) {
                    text[i].addKeyListener(Const.countTextCharListener(5));
                } else {
                    text[i].addKeyListener(Const.countTextCharListener(3));
                }
            }

            // 3.个别文本框只能输入小数，并限制输入位数
            int[] arrayFloatNo = {6};
            if (ArrayUtils.contains(arrayFloatNo, i)) {
                text[i].setToolTipText("只能输入小数");
                text[i].addFocusListener(Const.floatTextFocusListener());
                text[i].addKeyListener(Const.countTextCharListener(5));
            }

            //添加所有文本框
            subPanel.add(text[i]);
        }   // END : for
        //第一个text[0]文本框是“项目ID”，不允许修改
        text[0].setEnabled(false);
    }   // END : private void initSubPanelText()


    //为子面板的组件确定位置
    private void initSubPanelPosition() {
        //设置组件位置，4个一列
        //项目名称、准备时长、取峰算法、TC公式
        int standardX = 30, standardY = 35;
        lable[0].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        lable[1].setBounds(standardX, standardY + Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        lable[2].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        lable[3].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);

        //文本框、数字文本框、算法下拉框、TC公式下拉框
        standardX = lable[0].getX() + lable[0].getWidth();
        text[0].setBounds(standardX, standardY, 130, Const.BUTTON_HEIGHT);
        text[1].setBounds(standardX, standardY + 1 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);
        lstPeak.setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT + 5, 130, 25);
        text[2].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);
        lstTC.setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT + 5, 130, 25);
        text[3].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);

        //C线位置、浓度单位、最小值、最大值
        standardX = text[0].getX() + text[0].getWidth() + 15;
        lable[4].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        lable[5].setBounds(standardX, standardY + 1 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        lable[6].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        lable[7].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);


        //浓度文本框、最小值文本框、最大值文本框
        standardX = lable[4].getX() + lable[4].getWidth();
        text[4].setBounds(standardX, standardY, 130, Const.BUTTON_HEIGHT);
        text[5].setBounds(standardX, standardY + 1 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);
        text[6].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);
        text[7].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);

        //空白标签、左边界、右边界、N，一共4个
        standardX = text[4].getX() + text[4].getWidth() + 50;
        lable[8].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        lable[9].setBounds(standardX, standardY + 1 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        lable[10].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        lable[11].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);

        // X1标签，+3个文本框
        standardX = lable[8].getX() + lable[8].getWidth();
        lable[12].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        text[8].setBounds(standardX, standardY + 1 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        text[9].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        text[10].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);

        // X2标签，+3个文本框
        standardX = lable[12].getX() + lable[12].getWidth() + 30;
        lable[13].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        text[11].setBounds(standardX, standardY + 1 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        text[12].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        text[13].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
    }   // END : private void initSubPanelPosition()


    //查询按钮
    private void btnQueryClicked() {
        btnQuery.setEnabled(false);
        PanelProject.this.resetSubPanel(true, false);

        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String str = panelQuery.txtQuery.getText().trim();
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "%", "");
        String sql = sqlSelectProject + "WHERE 项目名称 LIKE '%" + str + "%' ";

        defaultTableModel = MainApp.myDB.getTableModel(sql);
        tblProject.setModel(defaultTableModel);
        //如果有数据，则选中第1行
        if (tblProject.getRowCount() > 0) {
            tblProject.setRowSelectionInterval(0, 0);
            tblProjectClicked();
        }

        panelQuery.txtQuery.requestFocus();
        btnEdit.setEnabled(false);      //True状态在选中表格行的单击事件里处理
        btnSave.setEnabled(false);
        btnDel.setEnabled(false);

        btnQuery.setEnabled(true);

    }   // END : private void btnQueryClicked()


    //新建按钮
    private void btnNewClicked() {
        //清空子面板的组件，并启用。
        resetSubPanel(true, true);
        text[0].setText("无需填写");

        //设置各按钮的状态
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        saveState = Const.SAVE_STATE_NEW;
    }   // END : private void btnNewClicked()


    //编辑按钮
    private void btnEditClicked() {
        //锁住表格组件
        tblProject.setEnabled(false);

        //校验当前数据是否允许编辑
        String sql = "SELECT COUNT(*) FROM `view_project_exp_curve` WHERE `项目ID` = ?";
        if (MainApp.myDB.IsExistRecord(sql, projectID)) {
            //如果存在数据，则不允许编辑，给出提示。
            JOptionPane.showMessageDialog(null, "该项目已有测试数据，不能编辑，否则会产生数据混乱。\n" +
                    "请先去“生成曲线”页面尝试删除曲线，再返回本页面进行编辑。\n" +
                    "如果曲线已锁定投入生产，则本项目不能再做任何修改。", "不能编辑", JOptionPane.WARNING_MESSAGE);
        } else {
            //如果不存在数据，则允许编辑
            //启用子面板的各个控件，填充List
            resetSubPanel(false, true);
            //设置各按钮的状态
            btnNew.setEnabled(true);
            btnEdit.setEnabled(false);
            btnSave.setEnabled(true);
            saveState = Const.SAVE_STATE_EDIT;
        }
        //释放表格组件
        tblProject.setEnabled(true);
    }    // END : private  void btnEditClicked()


    //保存按钮
    private void btnSaveClicked() {
        /* 功能描述
         * 1.验证所有必选项是否已填写
         * 2.获取字段数据
         * 3.校验数字的逻辑关系
         * 4.满足逻辑关系时，判断是新建，还是编辑
         * 5.更新或插入成功，则执行收尾工作
         */

        //1.验证所有必选项是否已填写
        if (checkEmptyForClickSave()) {
            // 如果有空项，返回值为true，则不允许继续。
            JOptionPane.showMessageDialog(null, "请准确填写红色部分，不要有空项。",
                    "输入有空项", JOptionPane.ERROR_MESSAGE);
        } else {
            //2.获取字段数据
            projectID = text[0].getText().trim();
            projectName = text[1].getText().trim();
            peakTypeID = lstPeak.getSelectedKey();
            tcTypeID = lstTC.getSelectedKey();
            prepareDuration = text[4].getText().trim();
            subUnit = text[5].getText().trim();
            subMin = text[6].getText().trim();
            subMax = text[7].getText().trim();
            createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            modifyDate = createDate;
            try {
                x1Left = NumberUtils.toInt(text[8].getText());
                x1Right = NumberUtils.toInt(text[9].getText());
                x1N = NumberUtils.toInt(text[10].getText());
                x2Left = NumberUtils.toInt(text[11].getText());
                x2Right = NumberUtils.toInt(text[12].getText());
                x2N = NumberUtils.toInt(text[13].getText());
            } catch (NumberFormatException ignored) {
            }

            //3.校验数字的逻辑关系
            boolean isAllowedSave = checkLogicForClickSave();

            //4.满足逻辑关系时，判断是新建，还是编辑
            if (isAllowedSave) {
                boolean isSuccess = false;
                if (saveState == Const.SAVE_STATE_NEW) {
                    //如果是新建，则插入数据库。
                    isSuccess = insertForClickSave();
                } else if (saveState == Const.SAVE_STATE_EDIT) {
                    //如果是编辑，按照项目ID更新数据库
                    isSuccess = updateForClickSave();
                }

                //5.更新或插入成功，则执行收尾工作
                if (isSuccess) {
                    btnNew.setEnabled(true);
                    btnEdit.setEnabled(true);
                    btnSave.setEnabled(false);
                    resetSubPanel(true, false);

                    //选中最后一行
                    btnQueryClicked();

                    panelQuery.txtQuery.setText(projectName);
                    btnQueryClicked();

                }   // END : if (isSuccess)
            }   // END : if (isAllowedSave == false) else
        }   // END : if (checkComponetReady()==false) else
    }   // END : private void btnSaveClicked()


    //删除按钮
    private void btnDelClicked() {

        //判断是否允许删除
        String sql = "SELECT COUNT(*) FROM view_project_exp_curve WHERE 项目ID = ?";
        if (MainApp.myDB.IsExistRecord(sql, projectID)) {
            //如果存在数据，则不允许删除，给出提示。
            JOptionPane.showMessageDialog(null, "该项目已有测试数据，不能删除，否则会导致数据混乱。",
                    "不能删除", JOptionPane.WARNING_MESSAGE);
        } else {
            //如果不存在数据，则允许删除。需用户二次确认
            int answer = JOptionPane.showConfirmDialog(null, "即将删除项目“" + projectName + "”，请再次确认！",
                    "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (answer == JOptionPane.OK_OPTION) {
                //删除数据，更新projcet、experiment
                String updateProject = "UPDATE project SET isDeleted='Y' WHERE projectID = ? AND isDeleted = 'N';";
                try {
                    MainApp.myDB.conn.setAutoCommit(false);
                    PreparedStatement pstmtUpdate = MainApp.myDB.conn.prepareStatement(updateProject);
                    pstmtUpdate.setString(1, projectID);
                    pstmtUpdate.executeUpdate();
                    pstmtUpdate.close();
                    MainApp.myDB.conn.commit();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "删除失败，请查看日志\n" + e.getMessage());
                    MainApp.myDB.dbRollback();
                }   // END : catch e，回滚

                //更新表格
                btnQueryClicked();
            }
        }
    }   // END : private void btnDelClicked()


    //导出Excel按钮
    private void btnExcelClicked() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(FILES_ONLY);
        DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmss");

        jFileChooser.setSelectedFile(new File("项目列表-" + DateFormatUtils.format(new Date(),
                "yyyyMMdd-HHmmss") + ".xls"));
        int select = jFileChooser.showSaveDialog(getParent());

        //取到文件对象
        if (select == APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            Const.exportExcel(tblProject, file);
        }   // END : if (select == APPROVE_OPTION)
    }   // END : private void btnExcelClicked()


    // JTable的监听器，用于单击某行后，显示在下方
    private void tblProjectClicked() {
        resetSubPanel(true, false);
        int nowRow = tblProject.getSelectedRow();
        if (nowRow != -1) {
            btnEdit.setEnabled(true);
            btnSave.setEnabled(false);
            btnDel.setEnabled(true);
            String peakName, tcExpession;

            //项目ID，projectID
            projectID = tblProject.getValueAt(nowRow, 0).toString();
            text[0].setText(projectID);

            // 项目名称projectName
            projectName = tblProject.getValueAt(nowRow, 3).toString();
            text[1].setText(projectName);

            //取峰算法 peakName
            peakName = tblProject.getValueAt(nowRow, 5).toString();
            lstPeak.setSelectedIndexFromValue(peakName);
            text[2].setText(peakName);

            //TC公式 tcExpession
            tcExpession = tblProject.getValueAt(nowRow, 6).toString();
            lstTC.setSelectedIndexFromValue(tcExpession);
            text[3].setText(tcExpession);

            // 准备时长 prepareDuration
            text[4].setText(tblProject.getValueAt(nowRow, 4).toString());

            //浓度单位 subUnit
            text[5].setText(tblProject.getValueAt(nowRow, 7).toString());

            //最小值 subMin
            text[6].setText(tblProject.getValueAt(nowRow, 8).toString());

            //最大值 subMax
            text[7].setText(tblProject.getValueAt(nowRow, 9).toString());

            //X1左边界
            text[8].setText(tblProject.getValueAt(nowRow, 10).toString());

            //X1右边界
            text[9].setText(tblProject.getValueAt(nowRow, 11).toString());

            //X1取数N
            text[10].setText(tblProject.getValueAt(nowRow, 12).toString());

            //X2左边界
            text[11].setText(tblProject.getValueAt(nowRow, 13).toString());

            //X2右边界
            text[12].setText(tblProject.getValueAt(nowRow, 14).toString());

            //X2取数N
            text[13].setText(tblProject.getValueAt(nowRow, 15).toString());
        } else {
            //未选中，清空并禁用
            btnEdit.setEnabled(false);
            btnDel.setEnabled(false);
        }   // END :if (nowRow != -1)
    }   // END : private void tblProjectClicked()


    //重置子面板
    private void resetSubPanel(Boolean isReset, Boolean isEnabled) {

        if (isReset) {
            for (int i = 0; i < text.length; i++) {
                text[i].setText("");
            }
            text[5].setText("ug/mL");

            lstPeak.setSelectedIndex(-1);       //取峰算法
            lstPeak.setBackground(Color.white);
            lstTC.setSelectedIndex(-1);         //TC公式
            lstTC.setBackground(Color.white);
        }   // END :if (isReset)

        //text[0]为projectID，不可修改，所以i从1开始循环
        for (int i = 1; i < text.length; i++) {
            text[i].setEnabled(isEnabled);
            text[i].setOpaque(false);
        }

        lstPeak.setVisible(isEnabled);
        lstTC.setVisible(isEnabled);
        text[2].setVisible(!isEnabled);
        text[3].setVisible(!isEnabled);
        this.updateUI();
    }  // END : private void resetSubPanel)


    //检查各文本框及下拉框，不能为空值
    private boolean checkEmptyForClickSave() {
        boolean isEmpty = false;
        for (int i = 1; i < text.length; i++) {
            //跳过2个不用作编辑的文本框，不做任何处理
            if (i != 2 && i != 3) {
                if (StringUtils.isEmpty(text[i].getText().trim())) {
                    text[i].setOpaque(true);
                    isEmpty = true;
                } else {
                    text[i].setOpaque(false);
                }
            }
        }

        if (lstPeak.getSelectedIndex() == -1) {
            lstPeak.setBackground(Color.pink);
            isEmpty = true;
        } else {
            lstPeak.setBackground(Color.white);
        }

        if (lstTC.getSelectedIndex() == -1) {
            lstTC.setBackground(Color.pink);
            isEmpty = true;
        } else {
            lstTC.setBackground(Color.white);
        }
        this.updateUI();

        return isEmpty;
    }   // END : private boolean checkEmptyForClickSave()


    //校验逻辑关系
    private boolean checkLogicForClickSave() {
        /* 逻辑关系：X1左边界 ＜ X1右边界 ＜ X2左边界 ＜ X2右边界
                   取数N ≤（右边界 - 左边界）
                   最小值 < 最大值
        */
        boolean checkPosition = true;
        boolean checkN = true;
        boolean checkMaxMix = true;
        boolean isAllowedSave;

        if (x1Left < x1Right) {
            text[8].setOpaque(false);
        } else {
            checkPosition = false;
            text[8].setOpaque(true);
        }

        if (x1Right < x2Left) {
            text[9].setOpaque(false);
        } else {
            checkPosition = false;
            text[9].setOpaque(true);
        }

        if (x2Left < x2Right) {
            text[11].setOpaque(false);
        } else {
            checkPosition = false;
            text[11].setOpaque(true);
        }

        if (x1N <= (x1Right - x1Left) && x1N != 0) {
            text[10].setOpaque(false);
        } else {
            text[10].setOpaque(true);
            checkN = false;
        }

        if (x2N <= (x2Right - x2Left) && x2N != 0) {
            text[13].setOpaque(false);
        } else {
            text[13].setOpaque(true);
            checkN = false;
        }

        if (NumberUtils.toInt(subMax) > NumberUtils.toInt(subMin)) {
            //正确的情况
            text[6].setOpaque(false);
            text[7].setOpaque(false);
        } else {
            text[6].setOpaque(true);
            text[7].setOpaque(true);
            checkMaxMix = false;
        }

        StringBuffer message = new StringBuffer("红色部分有误，需满足以下逻辑关系：\n");
        if (!checkPosition) {
            message.append("● X1左边界 ＜ X1右边界 ＜ X2左边界 ＜ X2右边界\n");
        }
        if (!checkN) {
            message.append("● 取数N ≤（右边界 - 左边界）\n");
        }
        if (!checkMaxMix) {
            message.append("● 最小值 < 最大值\n");
        }

        isAllowedSave = (checkMaxMix && checkN && checkPosition);
        if (!isAllowedSave) {
            JOptionPane.showMessageDialog(null, message, "缺少信息", JOptionPane.WARNING_MESSAGE);
        }
        return isAllowedSave;
    }   // END : private boolean checkLogicForClickSave()


    //点击新建再点保存，插入数据库
    private boolean insertForClickSave() {
        String insertProject = "INSERT INTO `project`(isDeleted , createDate , modifyDate , projectName , prepareDuration , " +
                "peakTypeID , tcTypeID , subUnit , subMin , subMax ,X1Left , X1Right , X1N , X2Left , X2Right , X2N ) " +
                " VALUES ( 'N', ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        String[] paraProject = {createDate, modifyDate, projectName, prepareDuration, peakTypeID, tcTypeID, subUnit, subMin, subMax,
                String.valueOf(x1Left), String.valueOf(x1Right), String.valueOf(x1N),
                String.valueOf(x2Left), String.valueOf(x2Right), String.valueOf(x2N)};

        //try insert
        try {
            MainApp.myDB.conn.setAutoCommit(false);
            PreparedStatement pstmt = MainApp.myDB.conn.prepareStatement(insertProject);
            for (int i = 0; i < paraProject.length; i++) {
                pstmt.setString(i + 1, paraProject[i]);
            }
            pstmt.executeUpdate();
            MainApp.myDB.conn.commit();
            JOptionPane.showMessageDialog(null, "更新成功!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "创建失败，数据库未作任何修改。请查看日志。");
            MainApp.myDB.dbRollback();
            return false;
        }   // END : try catch
    }   // END : private void insertForClickSave()


    //点击编辑再点保存，更新数据库
    private boolean updateForClickSave() {
        String updateProject = "UPDATE project SET modifyDate = ? , projectName= ? , prepareDuration = ? , " +
                " peakTypeID = ? , tcTypeID = ? , subUnit = ? , subMin = ? , subMax = ? , " +
                " X1Left = ? , X1Right = ? , X1N = ? , X2Left = ? , X2Right = ? , X2N = ? " +
                " WHERE projectID =? AND isDeleted = 'N';";
        String[] paraProject = {modifyDate, projectName, prepareDuration, peakTypeID, tcTypeID, subUnit, subMin, subMax, projectID,
                String.valueOf(x1Left), String.valueOf(x1Right), String.valueOf(x1N),
                String.valueOf(x2Left), String.valueOf(x2Right), String.valueOf(x2N)};
        try {
            //更新前，先禁用自动提交
            MainApp.myDB.conn.setAutoCommit(false);
            PreparedStatement pstmt = MainApp.myDB.conn.prepareStatement(updateProject);
            for (int i = 0; i < paraProject.length; i++) {
                pstmt.setString(i + 1, paraProject[i]);
            }
            pstmt.executeUpdate();
            // 更新完毕
            MainApp.myDB.conn.commit();
            JOptionPane.showMessageDialog(null, "更新成功!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL语法错误，未能更新\n" + e.getMessage());
            MainApp.myDB.dbRollback();
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            return false;
        } catch (Exception e) {
            MainApp.myDB.dbRollback();
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            JOptionPane.showMessageDialog(null, "创建失败，数据库未作任何修改。请查看日志。");
            return false;
        } // END : try
    }   // END : private void updateForClickSave()


}   // END :public class PanelProject extends JPandel




//以下为废弃代码
//    //根据项目ID，获取X1、X2的明细
//    private void setProjectEntry_old(String projectID) {
//        ResultSet rs;
//        int rowCount;
//        String selectProjectID = "select * from project_entry where isDeleted = 'N' and projectID =? ";
//        try {
//            PreparedStatement pstmt = MainApp.myDB.conn.prepareStatement(selectProjectID);
//            pstmt.setString(1, projectID);
//            rs = pstmt.executeQuery();
//
//            //移到最后一行，获取记录的数量
//            rs.last();
//            rowCount = rs.getRow();
//            entryIDX1 = "";
//            entryIDX2 = "";
//            if (rowCount == 1 || rowCount == 2 ) {
//                do {
//                    String xName, leftPos, rightPos, N;
//                    xName = rs.getString("xName");
//                    leftPos = rs.getString("leftPos");
//                    rightPos = rs.getString("rightPos");
//                    N = rs.getString("N");
//
//                    if ("X1".equals(xName)) {
//                        text[8].setText(leftPos);
//                        text[9].setText(rightPos);
//                        text[10].setText(N);
//                        entryIDX1 = rs.getString("entryID");
//                    } else if ("X2".equals(xName)) {
//                        text[11].setText(leftPos);
//                        text[12].setText(rightPos);
//                        text[13].setText(N);
//                        entryIDX2 = rs.getString("entryID");
//                    }
//
//                } while (rs.previous());
//            } else if (rowCount > 2) {
//                JOptionPane.showMessageDialog(this, "该项目的X1、X2数据超过2条，请尽快找开发人员修复数据库。");
//            }
//            pstmt.close();
//        } catch (SQLException e) {
//            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
//        }
//    }   // END : private void setProjectEntry(String projectID)



//    //点击编辑再点保存，更新数据库
//    private boolean clickSave_Update() {
//
//        String updateProject = "UPDATE project SET modifyDate = ? , projectName= ? , prepareDuration = ? ,  " +
//                "peakTypeID = ? , tcTypeID = ? , subUnit = ? , subMin = ? , subMax = ? WHERE projectID =? AND isDeleted = 'N';";
//        String updateEntry = "UPDATE project_entry SET modifyDate = ? , leftPos = ? , rightPos  = ?, N = ?  " +
//                "WHERE projectID = ? AND xName = ? AND isDeleted = 'N'";
//        String[] paraProject = {modifyDate, projectName, prepareDuration, peakTypeID, tcTypeID, subUnit, subMin, subMax, projectID};
//        String[] paraEntryX1 = {modifyDate, String.valueOf(x1Left), String.valueOf(x1Right), String.valueOf(x1N), projectID, "X1"};
//        String[] paraEntryX2 = {modifyDate, String.valueOf(x2Left), String.valueOf(x2Right), String.valueOf(x2N), projectID, "X2"};
//
//
//        try {
//            //更新前，先禁用自动提交
//            MainApp.myDB.conn.setAutoCommit(false);
//
//            //更新project表
//            PreparedStatement pstmt = MainApp.myDB.conn.prepareStatement(updateProject);
//            for (int i = 0; i < paraProject.length; i++) {
//                pstmt.setString(i + 1, paraProject[i]);
//            }
//            pstmt.executeUpdate();
////
////            //更新project_entry表
////            pstmt = MainApp.myDB.conn.prepareStatement(updateEntry);
////
////            //更新X1
////            if(StringUtils.isNotEmpty(entryIDX1)){
////                // entryID不为空，说明有数据，可以更新
////                for (int i = 0; i < paraEntryX1.length; i++) {
////                    pstmt.setString(i + 1, paraEntryX1[i]);
////                }
////                pstmt.executeUpdate();
////            }  else {
////                /* 处理异常：
////                   entryID为空，说明数据异常，需要插入，不能update。
////                   正常情况下entryID不会为空
////                 */
////                String insertEntry = "INSERT INTO `project_entry`(isDeleted , createDate , modifyDate , " +
////                        "projectID , xName , leftPos , rightPos , N ) VALUES ('N', ?,?,?,'X1',?,?,?)";
////                String[] para = {createDate, modifyDate, projectID, String.valueOf(x1Left), String.valueOf(x1Right), String.valueOf(x1N)};
////                PreparedStatement subPstmt = MainApp.myDB.conn.prepareStatement(insertEntry);
////                for (int i = 0; i < para.length; i++) {
////                    subPstmt.setString(i + 1, para[i]);
////                }
////                subPstmt.executeUpdate();
////                subPstmt.close();
////            }
////
////
////            //更新X2
////            if(StringUtils.isNotEmpty(entryIDX2)) {
////                // entryID不为空，说明有数据，可以更新
////                for (int i = 0; i < paraEntryX2.length; i++) {
////                    pstmt.setString(i + 1, paraEntryX2[i]);
////                }
////                pstmt.executeUpdate();
////                pstmt.close();
////            } else {
////                /* 处理异常：
////                   entryID为空，说明数据异常，需要插入，不能update。
////                   正常情况下entryID不会为空
////                 */
////                String insertEntry = "INSERT INTO `project_entry`(isDeleted , createDate , modifyDate , " +
////                        "projectID , xName , leftPos , rightPos , N ) VALUES ('N', ?,?,?,'X2',?,?,?)";
////                String[] para = {createDate, modifyDate, projectID, String.valueOf(x2Left), String.valueOf(x2Right), String.valueOf(x2N)};
////                PreparedStatement subPstmt = MainApp.myDB.conn.prepareStatement(insertEntry);
////                for (int i = 0; i < para.length; i++) {
////                    subPstmt.setString(i + 1, para[i]);
////                }
////                subPstmt.executeUpdate();
////                subPstmt.close();
////            }
//            // 更新完毕
//            MainApp.myDB.conn.commit();
//            JOptionPane.showMessageDialog(null, "更新成功!");
//            return true;
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "SQL语法错误，未能更新\n" + e.getMessage());
//            MainApp.myDB.dbRollback(e);
//            return false;
//        } catch (Exception e) {
//            return false;
//        } // END : try
//    }   // END : private void clickSave_Update()

    //点击新建再点保存，插入数据库
//    private boolean clickSave_Insert() {
//
//        String insertProject = "INSERT INTO `project`(isDeleted , createDate ," +
//                " modifyDate , projectName , prepareDuration , peakTypeID , tcTypeID , subUnit ," +
//                " subMin , subMax) VALUES ( 'N', ?,?,?,?,?,?,?,?,?);";
//
//        //try insert
//        try {
//            MainApp.myDB.conn.setAutoCommit(false);
//            ResultSet rs;
//            PreparedStatement pstmt = MainApp.myDB.conn.prepareStatement(insertProject, PreparedStatement.RETURN_GENERATED_KEYS);
//            String[] para = {createDate, modifyDate, projectName, prepareDuration, peakTypeID, tcTypeID, subUnit, subMin, subMax};
//            for (int i = 0; i < para.length; i++) {
//                pstmt.setString(i + 1, para[i]);
//            }
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            rs.next();
//            String newProjectID = rs.getString(1);
//
//            //拿到插入之后的ID，准备project_entry的数据
//            String[] insertEntry = new String[2];
//            insertEntry[0] = "INSERT INTO `project_entry`(isDeleted , createDate , modifyDate , " +
//                    "projectID , xName , leftPos , rightPos , N ) VALUES ('N', ?,?,?,'X1',?,?,?)";
//            insertEntry[1] = "INSERT INTO `project_entry`(isDeleted , createDate , modifyDate , " +
//                    "projectID , xName , leftPos , rightPos , N ) VALUES ('N', ?,?,?,'X2',?,?,?)";
//
//            String[] paraEntry1 = {createDate, modifyDate, newProjectID, String.valueOf(x1Left), String.valueOf(x1Right), String.valueOf(x1N)};
//            String[] paraEntry2 = {createDate, modifyDate, newProjectID, String.valueOf(x2Left), String.valueOf(x2Right), String.valueOf(x2N)};
//
//            //insert X1
//            pstmt = MainApp.myDB.conn.prepareStatement(insertEntry[0]);
//            for (int i = 0; i < paraEntry1.length; i++) {
//                pstmt.setString(i + 1, paraEntry1[i]);
//            }
//            pstmt.executeUpdate();
//
//            //insert X2
//            pstmt = MainApp.myDB.conn.prepareStatement(insertEntry[1]);
//            for (int i = 0; i < paraEntry2.length; i++) {
//                pstmt.setString(i + 1, paraEntry2[i]);
//            }
//            pstmt.executeUpdate();
//
//            pstmt.close();
//            MainApp.myDB.conn.commit();
//            return true;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"创建失败，数据库未作任何修改。请查看日志。");
//            MainApp.myDB.dbRollback(e);
//            return false;
//        }   // END : try catch
//    }   // END : private void clickSave_Insert()
