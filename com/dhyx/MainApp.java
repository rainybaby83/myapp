package com.dhyx;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.dhyx.myclass.Const;
import com.dhyx.myclass.MyDatabase;
import com.dhyx.panel.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainApp extends JFrame{
    private static Logger logger = LogManager.getLogger();
    private PanelMenu panelMenu = new PanelMenu();
    private JPanel panelMain = new JPanel();
    private JLabel lblTitle = new JLabel();
    public static MyDatabase myDB;



    private MainApp() {
        super();
        initApp();
        addComponent();
        addListener();
        setUIFont();
    }

    private void initApp(){
        String lookAndFeel = WindowsLookAndFeel.class.getName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            //提示文本的出现时间间隔，单位秒
            ToolTipManager.sharedInstance().setInitialDelay(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"缺少外观组件，本软件会变的不美观，但不影响正常使用。",
                    "缺少组件",JOptionPane.WARNING_MESSAGE);
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }

        this.setResizable(false);
        this.setLayout(null);
        this.setSize(new Dimension(Const.APP_WIDHT, Const.APP_HEIGHT));
        this.setTitle(Const.APP_NAME);
        this.setLocationRelativeTo(null);
        this.setIconImage(Const.ICON_APP.getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //主面板上方的绿色标题
        lblTitle.setOpaque(true);
        lblTitle.setBackground(Color.white);
        lblTitle.setFont(new Font("方正准圆简体",Font.PLAIN,20));
        lblTitle.setForeground(Const.GREEN);
        lblTitle.setText("    欢迎使用");
        lblTitle.setBounds( 0, 0, getWidth() - panelMenu.getWidth(), Const.TITLE_HEIGHT);

        //主面板
        panelMain.setBackground(Color.white);
        panelMain.setLayout(null);
        panelMain.setBounds(panelMenu.getWidth(), 0, 1100, Const.APP_HEIGHT);



    }

    private void addComponent(){
        this.add(panelMenu);
        this.add(panelMain);
        panelMain.add(lblTitle);
        panelMain.add(new PanelWelcome());
    }

    private void addListener(){
        //菜单：项目管理
        panelMenu.btnMenuProject.addActionListener(e -> {
            logger.trace("点击菜单：项目管理");
            panelMain.removeAll();
            lblTitle.setText("    项目管理");
            panelMain.add(lblTitle);
            panelMain.add(new PanelProject());
            panelMain.updateUI();
        });

        //菜单：实验管理
        panelMenu.btnMenuExperiment.addActionListener(e -> {
            logger.trace("点击菜单：实验管理");
            panelMain.removeAll();
            lblTitle.setText("    实验管理");
            panelMain.add(lblTitle);
            panelMain.add(new PanelExperiment());
            panelMain.updateUI();
        });

        //菜单：测试管理
        panelMenu.btnMenuTest.addActionListener(e -> {
            logger.trace("点击菜单：测试管理");
            panelMain.removeAll();
            lblTitle.setText("    测试管理");
            panelMain.add(lblTitle);
            panelMain.add(new PanelTest());
            panelMain.updateUI();
        });

        //菜单：生成曲线
        panelMenu.btnMenuCurveNew.addActionListener(e -> {
            logger.trace("点击菜单：生成曲线");
            panelMain.removeAll();
            lblTitle.setText("    生成曲线");
            panelMain.add(lblTitle);
            panelMain.add(new PanelCurveNew());
            panelMain.updateUI();
        });


        //菜单：评估曲线
        panelMenu.btnMenuCurveEstimate.addActionListener(e -> {
            logger.trace("点击菜单：评估曲线");
            panelMain.removeAll();
            lblTitle.setText("    评估曲线");
            panelMain.add(lblTitle);
            panelMain.add(new PanelCurveEstimate());
            panelMain.updateUI();
        });


        //菜单：历史曲线
        panelMenu.btnMenuCurveHistory.addActionListener(e -> {
            logger.trace("点击菜单：历史曲线");
            panelMain.removeAll();
            lblTitle.setText("    历史曲线");
            panelMain.add(lblTitle);
            panelMain.add(new PanelCurveHistory());
            panelMain.updateUI();
        });


        //菜单：新建批号
        panelMenu.btnMenuBatchNew.addActionListener(e -> {
            logger.trace("点击菜单：新建批号");
            panelMain.removeAll();
            lblTitle.setText("    新建批号");
            panelMain.add(lblTitle);
            panelMain.add(new PanelBatchNew());
            panelMain.updateUI();
        });


        //菜单：追溯批号
        panelMenu.btnMenuBatchQuery.addActionListener(e -> {
            logger.trace("点击菜单：追溯批号");
            panelMain.removeAll();
            lblTitle.setText("    追溯批号");
            panelMain.add(lblTitle);
            panelMain.add(new PanelBatchQuery());
            panelMain.updateUI();
        });


        //右上角退出
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logger.trace("点击右上角退出。");
//                myDB.dbClose();
            }
        });

        //退出菜单
        panelMenu.btnMenuExit.addActionListener(e -> {
            logger.trace("点击菜单：退出");
//            myDB.dbClose();
            System.exit(0);
        });
    }

    private void setUIFont() {
        Font f = Const.FONT_YAHEI_12;
        String names[] = {"Label", "CheckBox", "PopupMenu", "MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem", "ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "panel", "List", "ColorChooser",
                "PasswordField", "TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem", "RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item + ".font", f);
        }
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            logger.trace("启动程序");
            try {
                MainApp window = new MainApp();
                myDB = new MyDatabase();
                window.setVisible(true);
                logger.trace("启动完毕");
                window.setVisible(true);
            } catch (Exception e) {
                logger.debug(e.getClass().getSimpleName() + "，" + e.getMessage());
            }
        });
    }



}
