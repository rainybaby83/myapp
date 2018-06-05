package com.dhyx.myclass;

//import jxl.Workbook;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Date;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.FILES_ONLY;

public class MyTable extends JTable{
    public JScrollPane j;
    private Logger logger = LogManager.getLogger();
    public JPopupMenu jPopupMenu = new JPopupMenu();

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
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }


    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }



    //初始化表格
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


    private void initOther() {

        MyIconButton btnExcel = new MyIconButton(Const.ICON_EXCEL, Const.ICON_EXCEL_ENABLED, Const.ICON_EXCEL_ENABLED);
        btnExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exportExcel();
            }
        });

        jPopupMenu.add(btnExcel);
        jPopupMenu.setBorder(BorderFactory.createEtchedBorder());
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
     * 设置滚动面板的默认属性
     */
    private void setScrollPanel() {
        j = new JScrollPane(this);
        j.getVerticalScrollBar().setUI(new MyScrollBarUI());
        j.getHorizontalScrollBar().setUI(new MyScrollBarUI());
        j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        j.setWheelScrollingEnabled(true);
        j.setBackground(Color.white);
        j.getViewport().setOpaque(false);
    }


    /**
     * 将当前表格内容导出到excel
     */
    private void exportExcel(){
        JFileChooser jFileChooser = new JFileChooser(Const.DESKTOP_DIR);
        jFileChooser.setFileSelectionMode(FILES_ONLY);
        DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmss");


        jFileChooser.setSelectedFile(new File( DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmss") + ".xlsx"));
        int select = jFileChooser.showSaveDialog(getParent().getParent());
        if (select == APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            try {
                OutputStream out = new FileOutputStream(file);
                TableModel model = this.getModel();

                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("导出");
                XSSFRow row = sheet.createRow(0);
                XSSFCell cell;

                int rowCount = model.getRowCount();
                int colCount = model.getColumnCount();
                int rowNum,colNum;

                cell = row.createCell(0);
                cell.setCellValue("序号");

                for (colNum = 1; colNum < colCount + 1; colNum++) {
                    //getColumnName的参数，是从0开始
                    cell = row.createCell(colNum);
                    cell.setCellValue( model.getColumnName(colNum - 1));
                }

                // 从上到下按行添加
                for (rowNum = 1; rowNum < rowCount+1; rowNum++) {
                    //开始新的一行，并设置序号
                    row = sheet.createRow(rowNum);
                    cell = row.createCell(0);
                    cell.setCellValue(rowNum);

                    for (colNum = 1; colNum < colCount + 1; colNum++) {
                        //getValueAt的参数，是从0开始
                        cell = row.createCell(colNum);
                        cell.setCellValue(model.getValueAt(rowNum - 1, colNum - 1).toString());
                    }
                }

                wb.write(out);

                out.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "导出失败，错误原因请参考日志文件。");
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            }
        }   // END : if (select == APPROVE_OPTION)

    }



    //获取竖向列的最大值
    public int getMaxValueByColumn(String columnName) {
        int max = 0, tmp ;
        DefaultTableModel currentDM = (DefaultTableModel) this.getModel();
        int index = currentDM.findColumn(columnName);
        if (index != -1) {
            for (int i = 0; i < this.getRowCount(); i++) {
                tmp = NumberUtils.toInt(this.getValueAt(i, index).toString());
                if (tmp > max) {
                    max = tmp;
                }
            }
        }
        return max;
    }


    public void setLastRow() {
        if (getRowCount() > 0) {
            setRowSelectionInterval(getRowCount() - 1, getRowCount() - 1);
        }
    }

    public void setFirstRow() {
        if (getRowCount() > 0) {
            setRowSelectionInterval(0,0);
        }
    }


    public void setRowByValue(int columnIndex,String value) {
        if (columnIndex < this.getColumnCount()) {
            int rowCount = this.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String s = getValueAt(i, columnIndex).toString();
                if (value.equals(s)) {
                    setRowSelectionInterval(i, i);
                    break;
                } else {
                    clearSelection();
                }
            }
        }

    }
}
