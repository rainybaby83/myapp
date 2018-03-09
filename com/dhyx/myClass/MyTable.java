package com.dhyx.myClass;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.FILES_ONLY;

public class MyTable extends JTable{
    public JScrollPane jScrollPane;
    private Logger logger = LogManager.getLogger();
    private JPopupMenu jPopupMenu = new JPopupMenu();

    public MyTable(DefaultTableModel dm) {
        super(dm);
        this.setRowSorter(new TableRowSorter<>(dm));
        initTable();
        initTableHead();
        setScrollPanel();
        initOther();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
//                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                    jPopupMenu.show(e.getComponent().getParent().getParent().getParent(), 0, 0);
             }
            }
        });
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


    private void initTable(){
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setSelectionBackground(Const.GREEN_ACTIVE);
        this.setRowHeight(25);
        this.setAutoCreateColumnsFromModel(false);
        this.setAutoCreateRowSorter(true);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //设置居中
        DefaultTableCellRenderer dlrc = new DefaultTableCellRenderer();
        dlrc.setHorizontalAlignment(JLabel.CENTER);
        this.setDefaultRenderer(Object.class, dlrc);
    }


    private void initOther() {

        MyIconButton btnExcel = new MyIconButton(Const.ICON_EXCEL, Const.ICON_EXCEL_ENABLED, Const.ICON_EXCEL_ENABLED);
        btnExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exportExcel();
            }
        });

        jPopupMenu = new JPopupMenu();
        jPopupMenu.add(btnExcel);
    }


    /**
     * 用于调整表格的列宽
     * @param width 整数数组，列宽
     */
    public void setWidth(int... width){
        int length = Math.min(this.getColumnCount(), width.length);
        for (int i = 0; i < length; i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
            this.getColumnModel().getColumn(i).setMinWidth(width[i]);
        }
    }


    /**
     * 设置表头的默认属性
     */
    private void initTableHead() {
        JTableHeader header = this.getTableHeader();
        header.setUI(new BasicTableHeaderUI());
        header.setBackground(new Color(200, 200, 200));
        header.setOpaque(true);
        header.setReorderingAllowed(false);
    }


    /**
     * 设置滚动面板的默认属性
     */
    private void setScrollPanel() {
        jScrollPane = new JScrollPane(this);
        jScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        jScrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setWheelScrollingEnabled(true);
        jScrollPane.setBackground(Color.white);
        jScrollPane.getViewport().setOpaque(false);
    }

    /**
     * 将当前表格内容导出到excel2003
     */
    private void exportExcel(){
        JFileChooser jFileChooser = new JFileChooser(Const.DESKTOP_DIR);
        jFileChooser.setFileSelectionMode(FILES_ONLY);
        DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmss");


        jFileChooser.setSelectedFile(new File( DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmss") + ".xls"));
        int select = jFileChooser.showSaveDialog(getParent().getParent());
        if (select == APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            try {
                OutputStream out = new FileOutputStream(file);
                TableModel model = this.getModel();

                WritableWorkbook wwb = Workbook.createWorkbook(out);
                // 创建字表，并写入数据
                WritableSheet ws = wwb.createSheet("Sheet", 0);

                // 添加标题
                int rowCount = model.getRowCount();
                int columnCount = model.getColumnCount();
                int row = 0, column = 0;

                //添加第1行标题（row=0)
                jxl.write.Label[][] label = new jxl.write.Label[rowCount+1][columnCount+1];
                label[column][row] = new jxl.write.Label(column, row, "序号");
                for (column = 1; column < model.getColumnCount() + 1; column++) {
                    //getColumnName的参数，是从0开始
                    label[0][column] = new jxl.write.Label(column, 0, model.getColumnName(column - 1));
                }   // END : for


                // 从上到下按行添加
                for (row = 1; row < model.getRowCount() + 1; row++) {
                    //第一列label[row][0]，设置为序号，取row的值
                    label[row][0] = new jxl.write.Label(0, row, String.valueOf(row));
                    for (column = 1; column < model.getColumnCount() + 1; column++) {
                        //getValueAt的参数，是从0开始
                        label[row][column] = new jxl.write.Label(column, row, model.getValueAt(row -1, column-1).toString());
                    }
                }

                for (row = 0; row < rowCount + 1; row++) {
                    for (column = 0; column < columnCount + 1; column++) {
                        ws.addCell(label[row][column]);
                    }
                }
                wwb.write();
                wwb.close();
                out.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "导出失败，错误原因请参考日志文件。");
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            }
        }   // END : if (select == APPROVE_OPTION)

    }



}
