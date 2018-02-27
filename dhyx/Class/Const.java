package dhyx.Class;

import dhyx.MainApp;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


/**
 * UI相关的常量
 *
 * @author R
 */
public class Const {

    /**
     * 软件名称,版本
    */
    public final static String APP_NAME = "诊断分析系统";
    public final static int APP_WIDHT = 1220;
    public final static int APP_HEIGHT = 700;

    public final static int TITLE_HEIGHT = 40;
    public final static int BUTTON_WIDTH = 120;
    public final static int BUTTON_HEIGHT = 30;

    public final static int PANEL_WIDHT = 1000;
    public final static int PANEL_HEIGHT = APP_HEIGHT - TITLE_HEIGHT;
    public final static int LEFT_MARGIN = 50;
    private static Logger logger = LogManager.getLogger();
    /**
     * 系统当前路径
    */
    public final static String CURRENT_DIR = System.getProperty("user.dir");


    /**
     * 某个框架内的编辑状态
     */
    public final static int SAVE_STATE_CANCEL = 0;
    public final static int SAVE_STATE_NEW = 1;
    public final static int SAVE_STATE_EDIT = 2;


    /**
     * 文本框只允许输入小数FocusListener
    */
    public static FocusListener floatTextFocusListener() {
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                JTextField jTextField = (JTextField) e.getSource();
                float inputInt = NumberUtils.toFloat(jTextField.getText(),0.000F);
                //负数取绝对值
                if (inputInt > 0) {
                    jTextField.setText(String.valueOf(inputInt));
                }else {
                    jTextField.setText(String.valueOf(0 - inputInt));
                }
            }   // END : public void focusLost()
        };  // END : return new FocusListener()
    }   // END : private FocusListener floatTextFocusListener()




    /**
     * 系统常用字体
    */
//    public final static Font FONT_YAHEI_12 = new Font("微软雅黑", Font.PLAIN, 12);
    public final static Font SONG_12 = new Font("宋体", Font.PLAIN, 12);


    /**
     * 主窗口背景色
     */
    public final static Color GREEN = new Color(37, 174, 96);
    public final static Color GREEN_ACTIVE = new Color(106, 200, 146);
    public final static Color GREEN_LIGHT = new Color(166, 237, 198);


    /**
     * 主图标
     */
    public final static ImageIcon ICON_APP = new ImageIcon(MainApp.class.getResource("/icon/app.png"));

    //工具栏图标
    //项目与实验管理  曲线管理  批号管理  系统管理
    public final static ImageIcon ICON_LABLE_PROJECT = new ImageIcon(MainApp.class.getResource("/icon/项目与实验管理.png"));
    public final static ImageIcon ICON_LABLE_CURVE = new ImageIcon(MainApp.class.getResource("/icon/曲线管理.png"));
    public final static ImageIcon ICON_LABLE_BATCH = new ImageIcon(MainApp.class.getResource("/icon/批号管理.png"));
    public final static ImageIcon ICON_LABLE_SYSTEM = new ImageIcon(MainApp.class.getResource("/icon/系统管理.png"));

    //菜单图标
    //项目管理
    public final static ImageIcon MENU_PROJECT = new ImageIcon(MainApp.class.getResource("/icon/项目管理.png"));
    public final static ImageIcon MENU_PROJECT_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/项目管理-激活.png"));
    //实验管理
    public final static ImageIcon MENU_EXPERIMENT = new ImageIcon(MainApp.class.getResource("/icon/实验管理.png"));
    public final static ImageIcon MENU_EXPERIMENT_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/实验管理-激活.png"));
    //测试管理
    public final static ImageIcon MENU_TEST = new ImageIcon(MainApp.class.getResource("/icon/测试管理.png"));
    public final static ImageIcon MENU_TEST_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/测试管理-激活.png"));
    //生成曲线
    public final static ImageIcon MENU_NEW_CURVE = new ImageIcon(MainApp.class.getResource("/icon/生成曲线.png"));
    public final static ImageIcon MENU_NEW_CURVE_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/生成曲线-激活.png"));
    //评估曲线
    public final static ImageIcon MENU_ESTIMATE_CURVE = new ImageIcon(MainApp.class.getResource("/icon/评估曲线.png"));
    public final static ImageIcon MENU_ESTIMATE_CURVE_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/评估曲线-激活.png"));
    //历史曲线
    public final static ImageIcon MENU_CURVE_LIST = new ImageIcon(MainApp.class.getResource("/icon/历史曲线.png"));
    public final static ImageIcon MENU_CURVE_LIST_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/历史曲线-激活.png"));
    //新建批号
    public final static ImageIcon MENU_NEW_BATCH = new ImageIcon(MainApp.class.getResource("/icon/新建批号.png"));
    public final static ImageIcon MENU_NEW_BATCH_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/新建批号-激活.png"));
    //追溯批号
    public final static ImageIcon MENU_QUERY_BATCH = new ImageIcon(MainApp.class.getResource("/icon/追溯批号.png"));
    public final static ImageIcon MENU_QUERY_BATCH_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/追溯批号-激活.png"));
    //设置
    public final static ImageIcon MENU_OPTION = new ImageIcon(MainApp.class.getResource("/icon/设置.png"));
    public final static ImageIcon MENU_OPTION_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/设置-激活.png"));
    //退出
    public final static ImageIcon MENU_EXIT = new ImageIcon(MainApp.class.getResource("/icon/退出.png"));
    public final static ImageIcon MENU_EXIT_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/退出-激活.png"));


    /**
     * 按钮 图标
     */
    // 查询
    public final static ImageIcon ICON_QUERY = new ImageIcon(MainApp.class.getResource("/icon/查询.png"));
    public final static ImageIcon ICON_QUERY_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/查询-激活.png"));
    public final static ImageIcon ICON_QUERY_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/查询-禁用.png"));

    // 新建
    public final static ImageIcon ICON_NEW = new ImageIcon(MainApp.class.getResource("/icon/新建.png"));
    public final static ImageIcon ICON_NEW_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/新建-激活.png"));
    public final static ImageIcon ICON_NEW_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/新建-禁用.png"));

    // 编辑
    public final static ImageIcon ICON_EDIT = new ImageIcon(MainApp.class.getResource("/icon/编辑.png"));
    public final static ImageIcon ICON_EDIT_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/编辑-激活.png"));
    public final static ImageIcon ICON_EDIT_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/编辑-禁用.png"));

    // 保存
    public final static ImageIcon ICON_SAVE = new ImageIcon(MainApp.class.getResource("/icon/保存.png"));
    public final static ImageIcon ICON_SAVE_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/保存-激活.png"));
    public final static ImageIcon ICON_SAVE_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/保存-禁用.png"));

    // 删除
    public final static ImageIcon ICON_DEL= new ImageIcon(MainApp.class.getResource("/icon/删除.png"));
    public final static ImageIcon ICON_DEL_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/删除-激活.png"));
    public final static ImageIcon ICON_DEL_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/删除-禁用.png"));

    // 导出Excel
    public final static ImageIcon ICON_EXCEL = new ImageIcon(MainApp.class.getResource("/icon/导出Excel.png"));
    public final static ImageIcon ICON_EXCEL_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/导出Excel-激活.png"));
    public final static ImageIcon ICON_EXCEL_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/导出Excel-禁用.png"));

    // 新建实验
    public final static ImageIcon ICON_NEW_EXPERIMENT = new ImageIcon(MainApp.class.getResource("/icon/新建实验.png"));
    public final static ImageIcon ICON_NEW_EXPERIMENT_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/新建实验-激活.png"));
    public final static ImageIcon ICON_NEW_EXPERIMENT_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/新建实验-禁用.png"));

    // 删除选中记录
    public final static ImageIcon ICON_DEL_TEST = new ImageIcon(MainApp.class.getResource("/icon/删除选中记录.png"));
    public final static ImageIcon ICON_DEL_TEST_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/删除选中记录-激活.png"));
    public final static ImageIcon ICON_DEL_TEST_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/删除选中记录-禁用.png"));

    // 开始测试
    public final static ImageIcon ICON_TEST = new ImageIcon(MainApp.class.getResource("/icon/开始测试.png"));
    public final static ImageIcon ICON_TEST_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/开始测试-激活.png"));
    public final static ImageIcon ICON_TEST_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/开始测试-禁用.png"));





    private ArrayList<Object> getArrayFromFile() {
        File file = new File("src/data.txt");
        ArrayList<Object> result = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            int i=0;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.add(NumberUtils.toDouble(s));
                i++;
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }


    /**
     *  文本框只允许输入整数的FocusListener
     */
    public static FocusListener integerTextFocusListener() {
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                JTextField jTextField = (JTextField) e.getSource();
                int inputInt = NumberUtils.toInt(jTextField.getText(),0);
                //负数取绝对值
                if (inputInt > 0) {
                    jTextField.setText(String.valueOf(inputInt));
                }else {
                    //输入负数则取绝对值
                    jTextField.setText(String.valueOf(0 - inputInt));
                }
            }   // END : public void focusLost()
        };  // END : return new FocusListener()
    }   // END : private FocusListener integerTextFocusListener()


    /**
     *  文本框只允许规定的字符数
     *  @param count 限制输入的字符数量
     */
    public static KeyListener countTextCharListener(int count) {
        return new KeyAdapter() {
            JTextField jTextField;
            @Override
            public void keyTyped(KeyEvent e) {
                jTextField = (JTextField) e.getSource();
                if (jTextField.getText().length() >= count) {
                    jTextField.setText("");
                    e.consume();
                }
            }
        };
    }

    /**
     *  针对所有文本框去空格
     */
    public static FocusListener textTrimFocusListener() {
        return new FocusAdapter() {
            JTextField jTextField;
            @Override
            public void focusLost(FocusEvent e) {
                jTextField = (JTextField) e.getSource();
                jTextField.setText(jTextField.getText().trim());
            }
        };
    }


    /**
     * 导出JTable的数据到Excel2003
     * @param table 要导出的JTable
     * @param file 要导出到的file
     */
    public static void exportExcel(JTable table, File file){
        try {
            OutputStream out = new FileOutputStream(file);
            TableModel model = table.getModel();

            WritableWorkbook wwb = Workbook.createWorkbook(out);
            // 创建字表，并写入数据
            WritableSheet ws = wwb.createSheet("Sheet", 0);

            // 添加标题
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();
            int row = 0, column = 0;

            //添加第1行标题（row=0)
            jxl.write.Label[][] label = new jxl.write.Label[rowCount+1][columnCount+1];
            label[0][0] = new jxl.write.Label(0, 0, "序号");
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
    }


}
