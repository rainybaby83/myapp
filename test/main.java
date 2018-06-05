package test;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        JTable table = new JTable(5, 4);
        //实例化MyEvent，这里的invoke写你自己处理按钮被点击的逻辑
        MyEvent e = new MyEvent() {
            @Override
            public void invoke(ActionEvent e) {
                MyButton button = (MyButton)e.getSource();
                //打印被点击的行和列
                System.out.println("row:" + button.getRow() + "column :" + button.getColumn());
            }

        };
        //设置表格的渲染器
        table.getColumnModel().getColumn(2).setCellRenderer(new MyButtonRender());

        MyButtonEditor editor = new MyButtonEditor(e);
        //设置表格的编辑器table.getColumnModel().getColumn(2).setCellEditor(editor);
        frame.getContentPane().add(table);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}



class MyButton extends JButton{
    private int row;
    private int column;
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public MyButton() {

    }
    public MyButton(String name) {
        super(name);
    }

}



class MyButtonEditor extends DefaultCellEditor{

    private MyButton button;

    private MyEvent event;

    public MyButtonEditor() {
        super(new JTextField());
        button = new MyButton("确认");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //这里调用自定义的事件处理方法
                event.invoke(e);
            }

        });

    }

    public MyButtonEditor(MyEvent e) {
        this();
        this.event = e;
    }
    /*
    重写编辑器方法，返回一个按钮给JTable
    */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
//      setClickCountToStart(1);
//将这个被点击的按钮所在的行和列放进button里面
        button.setRow(row);
        button.setColumn(column);
        return button;
    }


}

 class MyButtonRender implements TableCellRenderer {

    private JButton button;

    public MyButtonRender() {
        button = new JButton("确认");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

}

abstract class MyEvent {
    public abstract void invoke(ActionEvent e);
}