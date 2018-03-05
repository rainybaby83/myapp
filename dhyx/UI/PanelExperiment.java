package dhyx.UI;

import dhyx.Class.Const;
import dhyx.Class.MyIconButton;
import dhyx.Class.MyTable;
import dhyx.MainApp;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Date;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.FILES_ONLY;

public class PanelExperiment extends JPanel {
    private Logger logger = LogManager.getLogger();
    private PanelQuery panelQuery;
    private JLabel lblProject, lblExperiment;
    private MyTable tblProject, tblExperiment;
    private DefaultTableModel dmProject, dmExperiment;
    private MyIconButton btnQuery, btnNew, btnDel, btnExcel;
    private String sqlSelectPro = "SELECT DISTINCT 项目ID,创建日期,修改日期,项目名称 FROM view_pro_exp ";
    private String sqlSelectExp = "SELECT DISTINCT 项目名称,实验创建日期,实验名称,实验ID FROM view_pro_exp ";
    private String strQueryKey;
    private String projectID, projectName, experimentID, experimentName;



    public PanelExperiment() {
        initSelf();
        initQueryPanel();
        initLabel();
        initTable();
        initButton();
        btnQueryClicked();
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
        panelQuery.txtQuery.setToolTipText("请输入实验名称。支持模糊查询，不区分大小写");
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


    //初始化2个标签
    private void initLabel() {
        lblProject = new JLabel("项目列表");
        lblProject.setBounds(0, panelQuery.getY() + panelQuery.getHeight(), 345, 25);

        lblExperiment = new JLabel("实验列表");
        lblExperiment.setBounds(btnQuery.getX(), panelQuery.getY() + panelQuery.getHeight(), 450, 25);

        this.add(lblProject);
        this.add(lblExperiment);
    }


    //初始化2个表格，及滚动条
    private void initTable() {
        initTableGetModel();
        initTableCreateTable();
        initTableAddListener();
    }  // END:initTable()


    // 1. 创建TableModel
    private void initTableGetModel() {
        //从全局变量Const.myDB获得DefaultTableModel，用于创建table
        dmProject = MainApp.myDB.getTableModel(sqlSelectPro + " LIMIT 50");
        dmExperiment = MainApp.myDB.getTableModel(sqlSelectExp + "WHERE `实验ID` is not null LIMIT 50");
    }

    // 2.初始化表格及滚动面板
    private void initTableCreateTable() {

        tblProject = new MyTable(dmProject);
        tblExperiment = new MyTable(dmExperiment) ;

        tblProject.setWidth(65, 70, 70, 140 - 2);
        tblExperiment.setWidth(100, 100, 180, 70 - 2);

        tblProject.setBounds(0, lblProject.getY() + lblProject.getHeight(), 345, 545);
        tblExperiment.setBounds(lblExperiment.getX(), lblExperiment.getY() + lblExperiment.getHeight(), 450, 545);

        tblProject.jScrollPane.setBounds(tblProject.getBounds());
        this.add(tblProject.jScrollPane);

        tblExperiment.jScrollPane.setBounds(tblExperiment.getBounds());
        this.add(tblExperiment.jScrollPane);
    }   // END : private void initTableCreateTable()


    // 3.添加监听
    private void initTableAddListener() {
        tblProject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tblProjectClicked();
            }
        });
        tblExperiment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tblExperimentSelectRow();
            }
        });
    }   // END : private void initTableAddListener()


    //初始化按钮
    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：实验管理-查询" + panelQuery.txtQuery.getText());
                    btnQueryClicked();
                }
            }
        });

        //新建按钮
        btnNew = new MyIconButton(Const.ICON_NEW_EXPERIMENT, Const.ICON_NEW_EXPERIMENT_ENABLED, Const.ICON_NEW_EXPERIMENT_DISABLED);
        btnNew.setLocation(this.getWidth() - Const.BUTTON_WIDTH, tblExperiment.getY());
        btnNew.setEnabled(true);
        btnNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnNew.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：实验管理-新建实验");
                    btnNewClicked();
                }
            }
        });


        //删除按钮
        btnDel = new MyIconButton(Const.ICON_DEL, Const.ICON_DEL_ENABLED, Const.ICON_DEL_DISABLED);
        btnDel.setLocation(btnNew.getX(), btnNew.getY() + btnNew.getHeight() + 30);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：实验管理-删除");
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
                    logger.trace("点击按钮：实验管理-导出Excel");
                    btnExcelClicked();
                }
            }
        });

        //添加到面板
        this.add(btnNew);
        this.add(btnDel);
        this.add(btnExcel);
    } // END:initButton()


    //查询按钮
    private void btnQueryClicked() {
        btnQuery.setEnabled(false);
        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String sql,sql2;
        strQueryKey = panelQuery.txtQuery.getText().trim();
        strQueryKey = StringUtils.replace(strQueryKey, "'", "");
        strQueryKey = StringUtils.replace(strQueryKey, "%", "");
        sql = sqlSelectPro + " WHERE `实验名称` LIKE '%" + strQueryKey + "%'";
        sql2 = sqlSelectExp + " WHERE `实验名称` LIKE '%" + strQueryKey + "%' AND `实验ID` IS NOT NULL ";

        dmProject = MainApp.myDB.getTableModel(sql);
        dmExperiment = MainApp.myDB.getTableModel(sql2);

        tblProject.setModel(dmProject);
        tblExperiment.setModel(dmExperiment);
//        //如果有数据，则选中第1行
//        if (tblExperiment.getRowCount() > 0) {
//            tblExperiment.setRowSelectionInterval(0, 0);
//        }

        //后续处理
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }   // END : private void btnQueryClicked()


    //新建实验按钮
    private void btnNewClicked() {
        String createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        String defautlName = DateFormatUtils.format(new Date(), "yyyyMMdd");
        experimentName = JOptionPane.showInputDialog(null, "当前项目为" + projectID + "." + projectName + "\n请输入新的实验名称：", defautlName);
        experimentName = StringUtils.replace(experimentName, "'", "");
        experimentName = StringUtils.replace(experimentName, "%", "");

        if (StringUtils.isNotEmpty(experimentName)) {
            String insertExperiment = "INSERT INTO `experiment` (`isDeleted`,`createDate`,`modifyDate`,`projectID`,`experimentName`) " +
                    "VALUES ( 'N' , ? , ? , ? , ?);";
            //try insert
            try {
                MainApp.myDB.conn.setAutoCommit(false);
                PreparedStatement pstmt = MainApp.myDB.conn.prepareStatement(insertExperiment);
                pstmt.setString(1, createDate);
                pstmt.setString(2, createDate);
                pstmt.setString(3, projectID);
                pstmt.setString(4, experimentName);
                pstmt.executeUpdate();
                pstmt.close();
                MainApp.myDB.conn.commit();
                tblProjectClicked();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "创建失败，数据库未作任何修改。请查看日志。");
                MainApp.myDB.dbRollback();
            }// END : try catch
        } else {
            JOptionPane.showMessageDialog(null, "未输入实验名称，停止创建。");
        }
    }   // END : private void btnNewClicked()


    //删除按钮
    private void btnDelClicked() {
        String sql = "SELECT COUNT(*) FROM `view_project_exp_curve` WHERE `实验ID` = ?";
        if (MainApp.myDB.IsExistRecord(sql, experimentID)) {
            //允许删除则再次确认
            int answer = JOptionPane.showConfirmDialog(null, "即将删除实验“" + experimentName + "”，请再次确认！",
                    "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (answer == JOptionPane.OK_OPTION) {
                //不允许删除则给出提示。
                JOptionPane.showMessageDialog(null, "该项目已有测试数据，不能删除，否则会导致数据混乱。",
                        "不能删除", JOptionPane.WARNING_MESSAGE);
            }   // END : if (answer == JOptionPane.OK_OPTION)
        } else {
            //删除数据，更新experiment
            String updateExperiment = "UPDATE `experiment` SET `isDeleted`='Y' WHERE `experimentID` = ? AND `isDeleted` = 'N';";

            try {
                MainApp.myDB.conn.setAutoCommit(false);
                PreparedStatement pstmt = MainApp.myDB.conn.prepareStatement(updateExperiment);
                pstmt.setString(1, experimentID);
                pstmt.executeUpdate();
                pstmt.close();
                MainApp.myDB.conn.commit();
                //更新表格数据显示
                tblProjectClicked();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "删除失败，请查看日志\n" + e.getMessage());
                MainApp.myDB.dbRollback();
            }   // END : catch e，回滚
        }   // END : if (MainApp.myDB.IsExistRecord(sql, experimentID))
    }   // END : private void btnDelClicked()


    //导出excel按钮
    private void btnExcelClicked() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(FILES_ONLY);
        DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmss");

        jFileChooser.setSelectedFile(new File("实验列表-" + DateFormatUtils.format(new Date(),
                "yyyyMMdd-HHmmss") + ".xls"));
        int select = jFileChooser.showSaveDialog(getParent());

        //取到文件对象
        if (select == APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, strQueryKey);
            File file = jFileChooser.getSelectedFile();
            String sql = " SELECT DISTINCT `实验创建日期`,`实验名称`,`实验ID`,`项目名称` " +
                    "FROM view_pro_exp " +
                    "WHERE (`项目名称` LIKE '%" + strQueryKey + "%' OR `实验名称` LIKE '%" + strQueryKey + "%') " +
                    "AND (`实验ID` IS NOT NULL)  ORDER BY `项目名称`";

            DefaultTableModel tmpDM = MainApp.myDB.getTableModel(sql);
            JTable tmpTable = new JTable(tmpDM);
            Const.exportExcel(tmpTable, file);
        }   // END : if (select == APPROVE_OPTION)
    }   // END : private void btnExcelClicked()


    //点击左侧项目列表，自动查找右侧的实验数据。
    private void tblProjectClicked() {
        btnDel.setEnabled(false);
        int nowRow = tblProject.getSelectedRow();
        String sql;
        if (nowRow != -1) {
            //项目ID，projectID
            projectID = tblProject.getValueAt(nowRow, 0).toString();
            projectName = tblProject.getValueAt(nowRow, 3).toString();
            sql = sqlSelectExp + "WHERE `项目ID` = '" + projectID + "' AND `实验ID` IS NOT NULL";
        } else {
            sql = sqlSelectExp + "WHERE 0=1";
        }
        dmExperiment = MainApp.myDB.getTableModel(sql);
        tblExperiment.setModel(dmExperiment);

        if (tblExperiment.getRowCount() > 0) {
            tblExperiment.setRowSelectionInterval(0, 0);
        }
    }   // END : private void tblProjectClicked()


    //点击实验列表，设置删除键是否可用
    private void tblExperimentSelectRow() {
        if (tblExperiment.getRowCount() > 0) {
            int nowRow = tblExperiment.getSelectedRow();
            experimentID = tblExperiment.getValueAt(nowRow, 0).toString();
            experimentName = tblExperiment.getValueAt(nowRow, 3).toString();
            btnDel.setEnabled(true);
        } else {
            btnDel.setEnabled(false);
        }
    }   // END : private  void tblExperimentSelectRow()


}
