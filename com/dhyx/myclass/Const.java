package com.dhyx.myclass;

import com.dhyx.MainApp;
import jdk.nashorn.internal.scripts.JO;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;


/**
 * UI相关的常量
 *
 * @author R
 */
public class Const {
    public static TestDataClass[] testData = getArrayFromFile();

    /**
     * 软件名称,版本
     */
    public final static String APP_NAME = "诊断分析系统";
    public final static int APP_WIDHT = 1220;
    public final static int APP_HEIGHT = 730;

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
    public final static File DESKTOP_DIR = FileSystemView.getFileSystemView().getHomeDirectory();


    /**
     * 某个框架内的编辑状态
     */
    public final static int SAVE_STATE_CANCEL = 0;
    public final static int SAVE_STATE_NEW = 1;
    public final static int SAVE_STATE_EDIT = 2;


    // 系统常用字体
    public final static Font FONT_YAHEI_12 = new Font("微软雅黑", Font.PLAIN, 12);
    public final static Font SONG_12 = new Font("宋体", Font.PLAIN, 13);


    //主窗口背景色
    public final static Color GREEN = new Color(37, 174, 96);
    public final static Color GREEN_ACTIVE = new Color(106, 200, 146);
    public final static Color GREEN_LIGHT = new Color(166, 237, 198);


    //主图标
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
    public final static ImageIcon MENU_LOCKED_CURVE = new ImageIcon(MainApp.class.getResource("/icon/历史曲线.png"));
    public final static ImageIcon MENU_LOCKED_CURVE_CHECKED = new ImageIcon(MainApp.class.getResource("/icon/历史曲线-激活.png"));
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


    //按钮 图标
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
    public final static ImageIcon ICON_DEL = new ImageIcon(MainApp.class.getResource("/icon/删除.png"));
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

    // 删除选中曲线
    public final static ImageIcon ICON_CURVE_DEL = new ImageIcon(MainApp.class.getResource("/icon/删除选中曲线.png"));
    public final static ImageIcon ICON_CURVE_DEL_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/删除选中曲线-激活.png"));
    public final static ImageIcon ICON_CURVE_DEL_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/删除选中曲线-禁用.png"));

    // 查看所有拟合
    public final static ImageIcon ICON_FIT = new ImageIcon(MainApp.class.getResource("/icon/查看所有拟合.png"));
    public final static ImageIcon ICON_FIT_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/查看所有拟合-激活.png"));
    public final static ImageIcon ICON_FIT_DISABLED = new ImageIcon(MainApp.class.getResource("/icon/查看所有拟合-禁用.png"));

    public final static ImageIcon ICON_4PL = new ImageIcon(MainApp.class.getResource("/icon/公式四参数.png"));
    public final static ImageIcon ICON_5PL = new ImageIcon(MainApp.class.getResource("/icon/公式五参数.png"));
    public final static ImageIcon ICON_LINEAR = new ImageIcon(MainApp.class.getResource("/icon/公式直线.png"));

    public final static ImageIcon ICON_VIEW_IMAGE = new ImageIcon(MainApp.class.getResource("/icon/查看图形.png"));
    public final static ImageIcon ICON_VIEW_IMAGE_ENABLED = new ImageIcon(MainApp.class.getResource("/icon/查看图形-选中.png"));


    //调用excel文件里的数据，模拟从设备端采集数据
    private static TestDataClass[] getArrayFromFile() {
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("src/Data.xlsx")));
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(0);

            //excel表格中一列为一组数据，每组有超过255个数据，所以只能用列
            int columnCount = row.getLastCellNum();
            int rowCount = sheet.getLastRowNum() + 1;
            TestDataClass[] testdata = new TestDataClass[columnCount];

            for (int i = 0; i < columnCount; i++) {
                testdata[i] = new TestDataClass(rowCount);
                for (int j = 0; j < rowCount; j++) {
                    testdata[i].x[j] = j + 1;
                    testdata[i].y[j] = (float) sheet.getRow(j).getCell(i).getNumericCellValue();
                }
            }
            workbook.close();
            return testdata;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TestDataClass[0];
    }


}
