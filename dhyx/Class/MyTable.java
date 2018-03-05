package dhyx.Class;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MyTable extends JTable{
    public JScrollPane jScrollPane;

    public MyTable(DefaultTableModel defaultTableModel) {
        super(defaultTableModel);
        init();
        initTableHead();
        setScrollPanel();
        this.setRowSorter(new TableRowSorter<>(defaultTableModel));
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    private void init(){
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
     * 用于调整表格的列宽
     * @param width 整数数组，列宽
     */
    public void setWidth(int... width){
        int length = Math.min(this.getColumnCount(), width.length);
        for (int i = 0; i < length; i++) {
//            this.getColumnModel().getColumn(i).setWidth(width[i]);
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


}
